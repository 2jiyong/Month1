import java.util.Scanner;

public class P8958 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i<count; i++){
            String input = sc.nextLine();
//            System.out.println(input);
            System.out.println(calculate(input));
        }

    }

    public static int calculate(String input){
        int score = 0;
        int count = 1;
        for (int i = 0 ; i<input.length(); i++){
            if (input.charAt(i)=='O'){
                score += count++;
            }
            else count=1;
        }
        return score;
    }
}

