import java.util.List;

public class ShapeFactory {
    public Shape createShape(int[] coordinate1,int[] coordinate2){
        return new Line(coordinate1,coordinate2);
    }

    public Shape createShape(int[] coordinate1,int[] coordinate2,int[] coordinate3){
        return new Triangle(coordinate1,coordinate2,coordinate3);
    }

    public Shape createShape(List<int[]> coordinateList){
        if(coordinateList.size()==2) return new Line(coordinateList.get(0),coordinateList.get(1));
        else if(coordinateList.size()==3) return new Triangle(coordinateList.get(0),coordinateList.get(1),coordinateList.get(2));
//        else if(coordinateList.size()>4) makeShape(coordinateList.removeLast(),coordinateList.removeLast());
        else throw new IllegalArgumentException("잘못된 좌표입니다.");
    }
}
