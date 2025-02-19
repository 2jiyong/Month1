import java.util.Scanner;

public class P2920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer;
        String input = sc.nextLine();
        String[] inputArray  = input.split(" ");
        int[] intArray = new int[inputArray.length];
        for(int i = 0; i<intArray.length; i++){
            intArray[i]=Integer.parseInt(inputArray[i]);
        }
        boolean [] result = isAscOrDsc(intArray);
        if (result[0]) answer="ascending";
        else if (result[1]) answer="descending";
        else answer="mixed";
        System.out.println(answer);
    }

    public static boolean[] isAscOrDsc(int[] array) {
        boolean asc = true;
        boolean dsc = true;
        for (int i = 0; i < array.length-1; i++) {
            if (array[i]>array[i+1]) asc = false;
            if (array[i]<array[i+1]) dsc = false;
        }
        return new boolean[] {asc,dsc};
    }
//    import java.util.Scanner;
//
//    public class Main {
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            String input = sc.nextLine();
//            if (input.equals("1 2 3 4 5 6 7 8")) System.out.println("ascending");
//            else if (input.equals("8 7 6 5 4 3 2 1")) System.out.println("descending");
//            else System.out.println("mixed");
//        }
//    }
}


