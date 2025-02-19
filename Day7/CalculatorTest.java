import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        // 테스트 해보기
        List<String[]> commandList = new ArrayList<>();
        commandList.add(new String[]{"PRINT", "PUSH0", "PRINT", "POPA"});
        commandList.add(new String[]{"PUSH1", "PUSH1", "PUSH2", "POPA", "POPB", "SWAP", "ADD", "PRINT", "PRINT"});
        commandList.add(new String[]{"PUSH3", "PUSH2", "PUSH1", "POPA", "POPB", "SWAP", "SUB", "POPA", "POPB", "ADD", "PRINT"});
        commandList.add(new String[]{"ADD", "PUSH3", "PUSH1", "PUSH0", "PUSH2", "PUSH1", "PUSH3", "PUSH2", "PUSH0", "PUSH3", "PUSH4"});
        for(int i = 0 ; i<4; i++){
            calc.executeCommandList(commandList.get(i));
            calc = new Calculator();
        }
        // 입력받아 실행해보기
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] splitedInput = input.split(",");
        String[] command =Arrays.stream(splitedInput)
                .map(String::trim)
                .map(s -> s.replaceAll("[\\[\\]]", ""))
                .map(s -> s.replaceAll("\"",""))// [] 제거
                .toArray(String[]::new);
        calc.executeCommandList(command);
    }
}
