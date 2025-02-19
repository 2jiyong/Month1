import java.util.Scanner;
public class CalculateTest {
    public static void main(String[] args) {
        //Test Case
        //(5,5)-(10,10)-(5,10)-(20,5)
        //(10,10)-(14,15)-(20,8)
        //(10,10)-(14,15)
        //(5,5)-(20,10)-(4,10)-(20,5)-(17,18)
        //(5,5)-(20,10)-(4,10)-(20,6)-(14,18)
        // (6,11)-(7,7)-(12,14)-(17,4)-(12,3)-(15,11)
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                System.out.println("좌표를 입력하세요");
                Shape shape = ShapeFactory.createShape(Input.getInputAndMakeCoordinates());
                System.out.println(shape.showResult());
            }catch (IllegalArgumentException e){
                System.out.println("프로그램 종료"+e);
                break;
            }
        }
    }
}
