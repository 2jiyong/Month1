import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Heap {
    private int[] memory;
    private final int META_DATA_SIZE = 8;
    private int typeIdNumber = 1;
    // 타입마다 사이즈가 얼마인지 알려주는 맵
    private final Map<String,Integer> typeSizeMap = new HashMap<>();
    private final Map<String,Integer> typeIdMap = new HashMap<>();


    public int init(int heapSize){
        memory = new int[heapSize];
        return 0x0000 ;
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
        int size = typeSizeMap.get(type)*count;
        int offset = findFreeBlock(size);
        if (offset == -1) throw new IllegalArgumentException("할당 가능한 메모리가 없습니다.");
        // freeBlock을 찾고, 그 부분에 새로운 메타데이터 및 데이터 생성

        // 데이터 끝에 원래 메타데이터가 있었다면 그대로 놔두고, 그렇지 않다면 새로운 메타데이터 생성

        return offset;
    }
    //size는 메타데이터를 제외한 사이즈
    private int findFreeBlock(int size){
        int offset = 0 ;
        while(offset< META_DATA_SIZE){
            int blockSize = memory[offset];
            int isAllocated = memory[offset+1];
            if (isAllocated == 1 && blockSize>=size){
                return offset;
            }
            offset += blockSize + META_DATA_SIZE;
        }
        return -1;
    }

    private void createMetaData(int startAddress, int size, boolean isAllocated,String type){
        memory[startAddress] = size;
        // isAllocated - true: 1 , false : 0
        memory[startAddress+1] = isAllocated ? 1 : 0 ;
        // type은 map에 맞춰서
        memory[startAddress+2] = typeIdMap.get(type);
        // 나머지 5칸은 padding (무시)
    }

    // 데이터가 들어갈 수 있는 블록을 찾는 메서드
    // 메모리의 맨 앞부터 시작해 메타데이터를 읽어나가며 선택된 메타데이터에 입력할 수 있는지 확인하고,
    // 가능하면 그 주소값을 반환함.
    // size는 메타데이터를 제외한 size

}
