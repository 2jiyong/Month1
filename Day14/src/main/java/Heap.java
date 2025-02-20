import java.util.HashMap;
import java.util.Map;

public class Heap {
    private int[] memory;
    private final int META_DATA_SIZE = 8;
    private int typeNumber = 1;
    private final Map<String,Integer> typeSizeMap = new HashMap<>();
    private final Map<Integer,String> typeMap = new HashMap<>();


    public int init(int heapSize){
        memory = new int[heapSize];
        return 0x0000 ;
    }

    public void setSize(String type,int length){
        if (!typeSizeMap.containsKey(type)) typeSizeMap.put(type,length);
        if (!typeMap.containsValue(type)) typeMap.put(typeNumber++,type);

    }

    private void createMetaData(int startAddress, int size, boolean isAllocated,String type){
        memory[startAddress] = size;
        // isAllocated - true: 1 , false : 0
        memory[startAddress+1] = isAllocated ? 1 : 0 ;
        // type은 map에 맞춰서
        memory[startAddress+2] = 1;
        // 나머지 5칸은 padding (무시)
    }


}
