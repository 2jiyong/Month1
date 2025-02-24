import java.util.function.Function;

public class ConvertingFunctions {
    // dataBlock을 받아서 위치를 옮기고, 그 위치의 값을 읽어 String으로 parse

//    public static Function<>


    public static Function<int[][],int[][]> convertUpToInt = array->{
        int rows = array.length;
        int cols = array[0].length;
        int[][] reversed = new int[rows][cols];
        // 180도 회전 (위아래 + 좌우 뒤집기)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                reversed[i][j] = array[rows - 1 - i][cols - 1 - j];
            }
        }
        return reversed;
    };

    public static int convertDownToInt(int[][] array){
        int rows = array.length;
        int cols = array[0].length;
        int[][] reversed = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                reversed[i][j] = array[i][cols - 1 - j]; // 좌우 반전
            }
        }

        return convertArrayToInt(reversed);
    }

    public static int convertCCWToInt(int[][] array){
        int[][] reversed = new int[2][4];
        reversed[0][0] = array[1][3]; // 6
        reversed[0][1] = array[1][2]; // 5

        reversed[0][2] = array[0][3]; // 4
        reversed[0][3] = array[0][2]; // 3

        reversed[1][0] = array[0][1]; // 8
        reversed[1][1] = array[0][0]; // 7

        reversed[1][2] = array[1][1]; // 2
        reversed[1][3] = array[1][0]; // 1
        return convertArrayToInt(reversed);
    }

    public static int convertCWToInt(int[][] array){
        int[][] reversed = new int[2][4];
        // 첫 번째 행: 두 번째 행의 오른쪽 부분(7,8)과 첫 번째 행의 왼쪽 부분(1,2)을 역순으로 결합
        reversed[0][0] = array[0][3]; // 8
        reversed[0][1] = array[0][2]; // 7
        reversed[0][2] = array[1][3]; // 2
        reversed[0][3] = array[1][2]; // 1
        // 두 번째 행: 두 번째 행의 왼쪽 부분(5,6)과 첫 번째 행의 오른쪽 부분(3,4)을 역순으로 결합
        reversed[1][0] = array[1][1]; // 6
        reversed[1][1] = array[1][0]; // 5
        reversed[1][2] = array[0][1]; // 4
        reversed[1][3] = array[0][0]; // 3
        return convertArrayToInt(reversed);
    }

    public static int convertStartToInt(int[][] array){
        int[][] reversed = new int[2][2];
        // 첫 번째 행: 두 번째 행의 오른쪽 부분(7,8)과 첫 번째 행의 왼쪽 부분(1,2)을 역순으로 결합
        reversed[0][0] = array[1][1]; // 8
        reversed[0][1] = array[1][0]; // 7
        reversed[1][0] = array[0][1]; // 2
        reversed[1][1] = array[0][0]; // 1
        return convertArrayToInt(reversed);
    }

    public static int convertEndToInt(int[][] array){
        int[][] reversed = new int[2][2];
        // 첫 번째 행: 두 번째 행의 오른쪽 부분(7,8)과 첫 번째 행의 왼쪽 부분(1,2)을 역순으로 결합
        reversed[0][0] = array[0][1]; // 8
        reversed[0][1] = array[0][0]; // 7
        reversed[1][0] = array[1][1]; // 2
        reversed[1][1] = array[1][0]; // 1
        return convertArrayToInt(reversed);
    }

    private static int convertArrayToInt(int[][] array){
        StringBuilder sb = new StringBuilder();
        for(int r = 0 ; r<array.length; r++){
            for(int c = 0 ; c<array[0].length;c++){
                sb.append(array[r][c]);
            }
        }
        return Integer.parseInt(sb.toString(),2);
    }
}
