import java.util.ArrayList;
import java.util.List;

public class Line implements Shape{
    private List<int[]> coordinateList = new ArrayList<int[]>();

    public Line(){

    }
    public Line(int[] coordinate1,int[] coordinate2){
        coordinateList.add(coordinate1);
        coordinateList.add(coordinate2);
    }

    public void addCoordinate(int x, int y){
        coordinateList.add(new int[] {x,y});
    }

    public List<int[]> getCoordinateList(){
        return coordinateList;
    }

    @Override
    public double calculate() {
        int[] coordinate1 = coordinateList.get(0);
        int[] coordinate2 = coordinateList.get(1);
        return Math.sqrt(Math.pow(coordinate1[0]-coordinate2[0],2)
                    +Math.pow(coordinate1[1]-coordinate2[1],2));
    }
}
