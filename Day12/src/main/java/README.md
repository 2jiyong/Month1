# 직선 길이
## 필요한 기능들

좌표를 입력받아 저장하는 기능
점과 점 사이의 길이를 구하는 기능

조금만 리팩토링 하면 될듯
Coordinate 각 좌표는 int[]로 저장하고, 여러 좌표들을 Coordinates 클래스에서 관리
points.sort(Comparator.comparingDouble(p -> Math.atan2(p.y - anchor.y, p.x - anchor.x)));
로 정렬해야 함

하나를 pop 하고
coordinates 클래스를 shape 클래스를 상위로 두고, 다른 클래스들을 만들자
이제 오각형만들고, 

처음엔 Calculate 클래스를 구현해서 계산만하게 했는데, 넓이 계산을 각 Shape 안으로 옮겨주었다.
showCalculateResult 메서드도 인터페이스에 구현하면 될듯