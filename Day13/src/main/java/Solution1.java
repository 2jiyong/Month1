import java.util.*;

public class Solution1 {

    static int solveMeFirst(int a, int b) {
        return a+b;
        // Hint: Type return a+b; below
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a;
        a = in.nextInt();
        int b;
        b = in.nextInt();
        in.close();
        int sum;
        sum = solveMeFirst(a, b);
        System.out.println(sum);
    }
}
