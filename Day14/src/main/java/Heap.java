import java.util.*;

// 블록의 시작은 메타데이터로 시작
public class Heap {
    private final int base;
    private int[] memory;
    private final int META_DATA_SIZE = 8;
    private final int TYPE_SIZE = 8;
    private int typeIdNumber = 1;
    // 타입마다 사이즈가 얼마인지 알려주는 맵
    private final Map<String,Integer> typeSizeMap = new HashMap<>();
    private final Map<String,Integer> typeIdMap = new HashMap<>();

    public Heap(){
        base = 0 ;
    }

    public Heap(int base){
        this.base = base;
    }

    public int init(int heapSize){
        memory = new int[heapSize];
        createMetaData(0,heapSize-META_DATA_SIZE,false,"");
        return base ;
    }

    public void setSize(String type,int length){
        Set<Integer> validNumbers = new HashSet<>(Set.of(1,2,4,8,16,32));
        if (!validNumbers.contains(length)) throw new IllegalArgumentException("잘못된 길이입니다.");

        if (!typeSizeMap.containsKey(type) &&!typeIdMap.containsKey(type)){
            typeSizeMap.put(type,length);
            typeIdMap.put(type,typeIdNumber++);
        } else throw new IllegalArgumentException("이미 설정된 타입입니다.");
    }

    public int malloc(String type, int count){
        int size = typeSizeMap.get(type)>=TYPE_SIZE ? typeSizeMap.get(type)*count: TYPE_SIZE*count;
        int offset = findFreeBlock(size);
        if (offset == -1) throw new IllegalArgumentException("할당 가능한 메모리가 없습니다.");
        // 원래 블록의 사이즈
        int originBlockSize = memory[offset];
        // freeBlock을 찾고, 그 부분에 새로운 메타데이터 및 데이터 생성
        createMetaData(offset, size,true,type);
        int dataStartIdx = offset+META_DATA_SIZE;
        for(int i = 0 ; i < count; i++){
            allocateOneByteAtOffset(dataStartIdx,type);
            dataStartIdx+=8;
        }
        // 데이터 끝에 원래 메타데이터가 있었다면 그대로 놔두고, 그렇지 않다면 새로운 메타데이터 생성
        if(originBlockSize !=size){
            createMetaData(offset+size+META_DATA_SIZE,originBlockSize - size - META_DATA_SIZE,false,"");
        }

        return offset;
    }

    private void allocateOneByteAtOffset(int offset, String type){
        for(int i = 0 ; i<typeSizeMap.get(type); i++){
            if (i==8) break;
            memory[offset+i] = typeIdMap.get(type);
        }
    }
    // 데이터가 들어갈 수 있는 블록을 찾는 메서드
    // 메모리의 맨 앞부터 시작해 메타데이터를 읽어나가며 선택된 메타데이터에 입력할 수 있는지 확인하고,
    // 가능하면 그 주소값을 반환함.
    // size는 메타데이터를 제외한 size
    private int findFreeBlock(int size){
        int offset = 0 ;
        while(offset< memory.length){
            int blockSize = memory[offset];
            int isAllocated = memory[offset+1];
            if (isAllocated == 0 && blockSize>=size){
                return offset;
            }
            offset += blockSize + META_DATA_SIZE;
        }
        return -1;
    }
    // size는 항상 데이터만의 size
    private void createMetaData(int startAddress, int size, boolean isAllocated,String type){
        memory[startAddress] = size;
        // isAllocated - true: 1 , false : 0
        memory[startAddress+1] = isAllocated ? 1 : 0 ;
        // type은 map에 맞춰서
        if (isAllocated) memory[startAddress+2] = typeIdMap.get(type);
        // 나머지 5칸은 padding (무시)
    }

    // free를 할 때 그 다음 메타데이터가 allocated 되지 않았다면, 하나로 합쳐줘야함.
    // 그 전 메타데이터가 false여도 하나로 합쳐줘야 하는데
    public void free(int pointer){
        //isAllocated를 false로 변경
        memory[pointer+1] = 0;
        int size = memory[pointer];
        int nextBlockStart = pointer + size + META_DATA_SIZE;
        // 만약 다음 블록이 사용중이지 않다면 하나의 블록으로 합쳐줌
        if (memory[nextBlockStart+1] == 0 ){
            memory[pointer] = size + memory[nextBlockStart] + META_DATA_SIZE;
        }
    }

    public String heapdump(){
        int offset = 0 ;
        StringBuilder sb = new StringBuilder();
        while(offset< memory.length){
            int blockSize = memory[offset];
            int isAllocated = memory[offset+1];
            sb.append(blockInfo(offset,isAllocated));
            offset += blockSize + META_DATA_SIZE;
        }
        sb.append("----------------------\n");
        return sb.toString();
    }

    private String blockInfo(int pointer, int isAllocated){
        StringBuilder sb = new StringBuilder();
        int size = memory[pointer];
        sb.append("포인터 주소값 : ");
        sb.append("0x").append(String.format("%X",base)).append(" + ").append("0x").append(String.format("%X",pointer));
        sb.append(" (0x").append(String.format("%X",base+pointer)).append("), ");
        sb.append("크기 : ").append(size).append(", ");
        sb.append("할당 여부 : ");
        if (isAllocated == 0){
            sb.append("false\n");
            return sb.toString();
        } else{
            sb.append("true, ");
        }

        String type = null;
        for (Map.Entry<String,Integer> entry : typeIdMap.entrySet()){
            if(entry.getValue() == memory[pointer+2]){
                type = entry.getKey();
                break;
            }
        }
        if (type == null) throw new IllegalArgumentException("불가능한 type 입니다.");
        sb.append("타입 : ").append(type).append("\n");
        return sb.toString();
    }

    // 사용 주인 블록의 용량과 그렇지 않은 블록의 용량
    public int[] usage(){
        int[] usageArray = new int[3];
        usageArray[0] = memory.length;
        usageArray[1] = findUsingCapacity();
        usageArray[2] = memory.length - usageArray[1];
        return usageArray;
    }

    public int findUsingCapacity(){
        int offset = 0 ;
        int usingCapacity = 0 ;
        while(offset< memory.length){
            int blockSize = memory[offset];
            int isAllocated = memory[offset+1];
            if (isAllocated == 1){
                usingCapacity+=blockSize+META_DATA_SIZE;
            }
            offset += blockSize + META_DATA_SIZE;
        }
        return usingCapacity;
    }



}
