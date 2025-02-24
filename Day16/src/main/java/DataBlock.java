public class DataBlock {
    private int[] startCoordinate;
    private int[] endCoordinate;

    private static int[][] QRArray = makeQR();

    public DataBlock(int[] start, int[] end){
        startCoordinate = start;
        endCoordinate = end;
    }

    public int[][] readQRArray(){
        int startR=startCoordinate[0];
        int startC=startCoordinate[1];
        int endR = endCoordinate[0];
        int endC = endCoordinate[1];

        int[][] array = new int[endR-startR+1][endC-startC+1];
        for (int r = startR, i = 0; r < endR + 1; r++, i++) {
            for (int c = startC, j = 0; c < endC + 1; c++, j++) {
                array[i][j] = QRArray[r][c];
            }
        }
        return array;
    }


    public void moveStartCoordinate(int r, int c){
        startCoordinate[0]+=r;
        startCoordinate[1]+=c;
    }

    public void moveEndCoordinate(int r, int c){
        endCoordinate[0]+=r;
        endCoordinate[1]+=c;
    }

    public int[] getStartCoordinate(){
        return startCoordinate;
    }

    public int[] getEndCoordinate(){
        return endCoordinate;
    }

    private static int[][] makeQR(){
        int[][] array = new int[21][21];
        String[] QR ={
                "111111101000001111111",
                "100000101000001000001",
                "101110100000001011101",
                "101110100000001011101",
                "101110101000001011101",
                "100000101000001000001",
                "111111101010101111111",
                "000000000000000000000",
                "111010101000011101011",
                "110001000000000000101",
                "010011100000000000100",
                "000000001000000000001",
                "001100101000000000001",
                "000000001000000000100",
                "111111100000000001100",
                "100000101000000000010",
                "101110100000000000110",
                "101110100000000000100",
                "101110101000000000100",
                "100000101100000000000",
                "111111101010000110011"
        };
        for(int i = 0 ; i<21; i++){
            for (int j = 0; j<21;j++){
                array[i][j]= Character.getNumericValue(QR[i].charAt(j));
            }
        }
        return array;
    }
}
