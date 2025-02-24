public class DataBlock {
    private int[] startCoordinate;
    private int[] endCoordinate;

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
                array[i][j] = QRMaker.QRArray[r][c];
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

}
