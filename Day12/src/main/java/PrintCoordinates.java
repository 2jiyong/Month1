import java.util.List;

public class PrintCoordinates {
    public static String printCoordinates(List<int[]> coordinateList){
        int[][] position =new int[25][25];
        for (int[] coordinate :coordinateList){
            //x좌표는 열 y좌표는 행
            position[coordinate[1]][coordinate[0]] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 24; i>0 ; i--){
            if (i%2==0) sb.append(String.format("%2d",i));
            else sb.append("  ");
            sb.append("|");
            for(int j = 1 ; j<=24; j++){
                if (position[i][j]==1) sb.append("   ●");
                else sb.append("    ");
            }
            sb.append("\n");
        }
        // 여기부터는 아래
        sb.append("  +");
        for( int i =0; i<=24;i++){
            sb.append("────");
        }
        sb.append("\n 0 ");
        for( int i =1; i<=24;i++){
            if (i%2==0) sb.append(String.format("%4d",i));
            else sb.append("    ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
