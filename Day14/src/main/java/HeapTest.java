import java.util.Random;
public class HeapTest {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(0xA000);

        Heap memory = new Heap(randomNumber);

        int base = memory.init(1024);
        memory.setSize("short", 4);
        memory.setSize("int", 8);
        memory.setSize("string", 16);
        int arrayPointer = memory.malloc("int", 4);
        int shortPointer = memory.malloc("short", 5);
        System.out.print(memory.heapdump());
        int string1 = memory.malloc("string", 1);
        int string2 = memory.malloc("string", 2);
        memory.free(string1);
        System.out.print(memory.heapdump());
        memory.free(string2);
        System.out.print(memory.heapdump());


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
