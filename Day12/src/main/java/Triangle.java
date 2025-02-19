import java.util.ArrayList;
import java.util.List;

public class Triangle implements Shape {
    private List<int[]> coordinateList = new ArrayList<int[]>();

    public Triangle(int[] coordinate1,int[] coordinate2,int[] coordinate3){
        coordinateList.add(coordinate1);
        coordinateList.add(coordinate2);
        coordinateList.add(coordinate3);
    }

    @Override
    public String showResult(){
        return PrintCoordinates.printCoordinates(coordinateList)+"삼각형 넓이는 "+calculate();
    }

    @Override
    public double calculate(){
        int[] coordinate1 = coordinateList.get(0);
        int[] coordinate2 = coordinateList.get(1);
        int[] coordinate3 = coordinateList.get(2);
        double lengthA = new Line(coordinate1,coordinate2).calculate();
        double lengthB = new Line(coordinate2,coordinate3).calculate();
        double lengthC = new Line(coordinate3,coordinate1).calculate();
        return calculateTriangleAreaByLength(lengthA,lengthB,lengthC);
    }

    private double calculateCosineB(double lengthA,double lengthB,double lengthC){
        return (Math.pow(lengthA,2)+Math.pow(lengthC,2)-Math.pow(lengthB,2))/(2*lengthA*lengthC);
    }

    private double calculateSineB(double lengthA,double lengthB,double lengthC){
        return Math.sqrt(1-Math.pow(calculateCosineB(lengthA,lengthB,lengthC),2));
    }

    private double calculateTriangleAreaByLength(double lengthA,double lengthB,double lengthC){
        return (calculateSineB(lengthA,lengthB,lengthC)*lengthA*lengthC/2);
    }
}
