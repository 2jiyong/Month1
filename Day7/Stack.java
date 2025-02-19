import java.util.EmptyStackException;

public class Stack {
    int MEMORY_SIZE = 8;
    int[] memory;
    int size;

    public Stack(){
        memory = new int[MEMORY_SIZE];
        size = 0;
    }

    public int pop(){
        if(!isEmpty()){
            int value = memory[size-1];
            size--;
            return value;
        }
        throw new EmptyStackException();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean push(int number){
        if (size<MEMORY_SIZE){
            size++;
            memory[size-1] = number;
            return true;
        }
        return false;
    }
}

