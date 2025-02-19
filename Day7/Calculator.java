import java.util.*;

public class Calculator {
    private final Stack stack;
    private final Register registerA;
    private final Register registerB;
    private final Map<String, Runnable> commandMap;
    private final List<String> stdOutList;

    public Calculator(){
        stack = new Stack();
        registerA = new Register();
        registerB = new Register();
        commandMap = initializeCommandMap();
        stdOutList = new ArrayList<>();
    }

    private Map<String, Runnable> initializeCommandMap() {
        Map<String, Runnable> commandMap = new HashMap<>();
        commandMap.put("POPA", this::popA);
        commandMap.put("POPB", this::popB);
        commandMap.put("ADD", this::add);
        commandMap.put("SUB", this::sub);
        commandMap.put("PUSH0", () -> this.pushNumber(0));
        commandMap.put("PUSH1", () -> this.pushNumber(1));
        commandMap.put("PUSH2", () -> this.pushNumber(2));
        commandMap.put("PUSH3", () -> this.pushNumber(3));
        commandMap.put("SWAP", this::swap);
        commandMap.put("PRINT", this::print);
        return commandMap;
    }

    public void executeCommandList(String[] commandList){
        for(String command : commandList) {
            if (!commandMap.containsKey(command)) stdOutList.add("UNKNOWN");
            else commandMap.get(command).run();
        }
        System.out.println(returnResult());
    }

    private String returnResult(){
        StringBuilder sb = new StringBuilder();
        for(String result : stdOutList){
            sb.append(result);
            sb.append(", ");
        }
        if(!sb.isEmpty()) {
            sb.delete(sb.length()-2,sb.length());
        }
        return sb.toString();
    }

    private void popA(){
        try{
            registerA.setValue(stack.pop());
        }catch(EmptyStackException e){
            stdOutList.add("Empty");
        }
    }

    private void popB(){
        try{
            registerB.setValue(stack.pop());
        } catch(EmptyStackException e){
            stdOutList.add("Empty");
        }
    }

    private void add(){
        if (registerA.isInitialized() && registerB.isInitialized()) {
            if(stack.push(registerA.getValue() + registerB.getValue())) return;
            stdOutList.add("OVERFLOW");
            return;
        }
        stdOutList.add("ERROR");
    }

    private void sub(){
        if(registerA.isInitialized() && registerB.isInitialized()){
            if(stack.push(registerA.getValue() - registerB.getValue())) return;
            stdOutList.add("OVERFLOW");
            return;
        }
        stdOutList.add("ERROR");
    }

    private void pushNumber(int number){
        if (stack.push(number)){
            return;
        }
        stdOutList.add("OVERFLOW");
    }
    //완료
    private void swap(){
        if (registerA.isInitialized() && registerB.isInitialized()){
            int tempValue;
            tempValue = registerA.getValue();
            registerA.setValue(registerB.getValue());
            registerB.setValue(tempValue);
            return;
        }
        stdOutList.add("ERROR");
    }

    private void print(){
        try{
            stdOutList.add(String.valueOf(stack.pop()));
        } catch(EmptyStackException e){
            stdOutList.add("Empty");
        }
    }
}
