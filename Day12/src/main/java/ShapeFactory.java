import java.util.List;

public class ShapeFactory {
    public static Shape createShape(List<int[]> coordinateList){
        if(coordinateList.size()==2) return new Line(coordinateList.get(0),coordinateList.get(1));
        else if(coordinateList.size()==3) return new Triangle(coordinateList.get(0),coordinateList.get(1),coordinateList.get(2));
        else if(coordinateList.size()>=4) return new Polygon(coordinateList);
        else throw new IllegalArgumentException("잘못된 좌표입니다.");
    }
}
