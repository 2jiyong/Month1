import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    private List<int[]> coordinateList = new ArrayList<int[]>();

    public void resetCoordinateList(){
        coordinateList = new ArrayList<int[]>();
    }

    public int[] getCoordinate(){
        return coordinateList.removeLast();
    }

    public void addCoordinate(int x, int y){
        coordinateList.add(new int[] {x,y});
    }

    public List<int[]> getCoordinateList(){
        return coordinateList;
    }

    public int getCoordinateListSize(){
        return coordinateList.size();
    }
}
