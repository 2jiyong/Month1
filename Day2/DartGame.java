import java.util.Arrays;
//import java.util.Scanner;

public class DartGame {
    public static void main(String[] args) {
        //[0, 1, 2, 0, 0, 0, 1, 5, 4, 5, 6, 4, 4, 6, 9, 9, 10, 0, 10, 10, 1]
//        Scanner sc = new Scanner(System.in);
//        int[] array = stringToArray(sc.nextLine());
        int[] array = {0, 1, 2, 0, 0, 0, 1, 5, 4, 5, 6, 4, 4, 6, 9, 9, 10, 0, 10, 10, 1};
        Scores scores = new Scores();
        playAllRounds(array,scores);
        printResult(scores.getScoreA(),scores.getScoreB());
    }

    public static void playAllRounds(int[] array,Scores scores){
        int numberOfRounds = array.length/7;
        for (int round = 1; round<=numberOfRounds; round++)
        {
            int[] oneRound = Arrays.copyOfRange(array,(round-1)*7,(round)*7);
            playRound(oneRound,scores);
        }

    }

    public static void playRound(int[] oneRound,Scores scores){
        Dart dart = new Dart();
        int index = oneRound[0];
        int[] indexA = Arrays.copyOfRange(oneRound, 1,4);
        int[] indexB = Arrays.copyOfRange(oneRound, 4, 7);
        scores.earnScoreA(dart.getScore(index, indexA));
        scores.earnScoreB(dart.getScore(index, indexB));
    }

    public static void printResult(int scoreA, int scoreB){
        if (scoreA>scoreB) System.out.println(scoreA+">"+scoreB);
        else if (scoreA<scoreB) System.out.println(scoreA+"<"+scoreB);
        else System.out.println(scoreA+"="+scoreB);
    }

//    public static int[] stringToArray(String input){
//        input = input.replaceAll("[\\[\\]]","").replaceAll("\\s","");
//        String[] stringResult = input.split(",");
//        int[] intResult = new int[stringResult.length];
//        for (int i = 0; i<intResult.length; i++){
//            intResult[i]= Integer.parseInt(stringResult[i]);
//        }
//        return intResult;
//    }
}

