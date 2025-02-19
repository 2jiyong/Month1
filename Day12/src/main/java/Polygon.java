import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polygon implements Shape{
    private List<int[]> coordinateList = new ArrayList<>();

    public Polygon(List<int[]> coordinateList){
        this.coordinateList = coordinateList;
    }

    @Override
    public double calculate() {
        double area = 0 ;
        sortCoordinateListToSeparateByTriangle();
        int[] refCoordinate = coordinateList.get(0);
        for(int i=1;i<coordinateList.size()-1;i++){
            int[] coordinate1 = coordinateList.get(i);
            int[] coordinate2 = coordinateList.get(i+1);
            area += new Triangle(refCoordinate,coordinate1,coordinate2).calculate();
        }
        return area;
    }

    // 다각형을 삼각형으로 나누기 위해서 coordinateList를 정렬하는 메소드
    private void sortCoordinateListToSeparateByTriangle() {
        // 1. 다각형 위의 기준점 선택 (x가 가장 작은 점, 동일하면 y가 가장 작은 점)
        int[] referencePoint = Collections.min(coordinateList, Comparator
                .comparingInt((int[] p) -> p[0]) // x 좌표 기준
                .thenComparingInt(p -> p[1])); // x 좌표 같으면 y 기준

        coordinateList.remove(referencePoint);

        final int refX = referencePoint[0];
        final int refY = referencePoint[1];
        // 2. 기준점과 각도를 계산하여 정렬
        coordinateList.sort(Comparator.comparingDouble(p -> Math.atan2(p[1] - refY, p[0] - refX)));
        coordinateList.add(0, referencePoint);
    }


    @Override
    public String showResult(){
        return PrintCoordinates.printCoordinates(coordinateList) + coordinateList.size() +"각형의 넓이는 "+calculate();
    }
}
