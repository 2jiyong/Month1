public class ComputerMoveTest {
    public static void main(String[] args) {
        ComputerMove cm=new ComputerMove();
        //제대로 작동하는지 1부터 9까지 대입해보기
        for (int i = 1 ; i<10;i++){
            cm.showStepState(i);
        }
    }
}
