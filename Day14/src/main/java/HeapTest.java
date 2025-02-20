public class HeapTest {
    public static void main(String[] args) {
        Heap memory = new Heap();
        int base = memory.init(1024);
        memory.setSize("short", 4);
        memory.setSize("int", 8);
        memory.setSize("string", 16);
        int arrayPointer = memory.malloc("int", 4);
        int shortPointer = memory.malloc("short", 5);

        System.out.println(arrayPointer);
        System.out.println(shortPointer);
        System.out.println(base);

//        base = memory.init(1024);
//        memory.setSize("short", 4);
//        memory.setSize("int", 8);
//        memory.setSize("string", 16);
//        arrayPointer = memory.malloc("int", 4);
//        shortPointer = memory.malloc("short", 5);
//        print(heapdump());
//        string1 = memory.malloc("string", 1);
//        string2 = memory.malloc("string", 2);
//        free(string1);
//        print(heapdump());
//        free(string2);
//        reset();
//        print(heapdump());
    }
}
