# 직선 길이
## 구현 과정
우선 좌표를 입력받아 저장하는 기능, 점과 점 사이의 길이를 구하는 기능을 먼저 만들어야 한다고 생각했다.

각 좌표는 int[]로 저장하고, Coordinates 클래스에서 좌표들을 리스트로 관리하게 했다.

3번째 미션인 Polygon 클래스를 구현해가며 인터페이스를 만들고, 이를 구현하는 클래스를 만드는 방식으로 진행했다.
Coordinates 클래스를 Shape 인터페이스를 구현하는 Line, Triangle, Rectangle 클래스로 나누어 구현했다.  

Shape를 생성하는 ShapeFactory 클래스에는 좌표의 리스트를 주어, 그에 맞는 Shape를 생성하고, 반환하도록 했다.  
각 Shape에는 공통적으로 넓이를 구하는 메서드와 출력하는 메서드를 가진다.  
Triangle 클래스의 넓이를 구하는 메서드 안에서는 Line 객체를 생성하고, 그 객체로 길이를 구한다.
Polygon 클래스에서는 넓이를 구하는 메서드안에서 Triangle 객체를 생성하고, 그 객체의 넓이들의 합으로 넓이를 구한다.

## 클래스 설명
- ShapeFactory : 좌표 리스트를 받아, 그에 맞는 Shape 객체를 생성하는 클래스
- Input : 좌표를 입력받아 리스트로 반환하는 클래스
- Shape : 넓이를 구하는 메서드와 출력하는 메서드를 가진 인터페이스
- Line, Triangle, Rectangle : Shape 인터페이스를 구현하는 클래스
- PrintCoordinates : 좌표 리스트를 형식에 맞게 출력하는 클래스

## 주요 메서드
```java
public class ShapeFactory {
    public static Shape createShape(List<int[]> coordinateList){
        if(coordinateList.size()==2) return new Line(coordinateList.get(0),coordinateList.get(1));
        else if(coordinateList.size()==3) return new Triangle(coordinateList.get(0),coordinateList.get(1),coordinateList.get(2));
        else if(coordinateList.size()>=4) return new Polygon(coordinateList);
        else throw new IllegalArgumentException("잘못된 좌표입니다.");
    }
}
```
좌표 리스트를 받아, 그에 맞는 Shape 객체를 생성하여 반환  

```java
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
```
좌표로 삼각형의 세 변의 길이를 구한 후, 넓이를 구하는 메서드

```java
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
        final int refX = referencePoint[0];
        final int refY = referencePoint[1];
        // 2. 기준점과 각도를 계산하여 정렬
        coordinateList.sort(Comparator.comparingDouble(p -> Math.atan2(p[1] - refY, p[0] - refX)));
    }
```
다각형을 삼각형으로 나누기 위해, 기준점을 잡고 각도를 계산하여 정렬한 후, 삼각형을 만들어 넓이를 구하는 메서드

## 실수 
Polygon에서 삼각형을 나누기 위해 기준점을 잡고, atan2를 사용하여 정렬할 때, 기준점이 제일 앞에 갈 것이라고 생각했는데 그렇지 않았다.  
따라서 기준점을 리스트에서 제거한 후, 정렬한 다음 다시 리스트의 맨 앞에 추가하는 식으로 변경했다.  