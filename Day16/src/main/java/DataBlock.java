public class DataBlock {
    private int[] startCoordinate;
    private int[] endCoordinate;

    public DataBlock(int[] start, int[] end){
        startCoordinate = start;
        endCoordinate = end;
    }


    public void moveStartCoordinate(int x, int y){
        startCoordinate[0]+=x;
        startCoordinate[1]+=y;
    }

    public void moveEndCoordinate(int x, int y){
        endCoordinate[0]+=x;
        endCoordinate[1]+=y;
    }

    public int[] getStartCoordinate(){
        return startCoordinate;
    }

    public int[] getEndCoordinate(){
        return endCoordinate;
    }

}
