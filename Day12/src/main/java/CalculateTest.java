public class CalculateTest {
    public static void main(String[] args) {
//        String input = sc.nextLine();
//        Coordinates coordinates = Input.getInputAndMakeCoordinates();
        String input = "(10,10)-(14,15)";
        Coordinates coordinates = Input.makeCoordinates(input);

        try{
            System.out.printf(PrintCoordinates.printCoordinates(coordinates));
        }catch (IllegalArgumentException e){
            System.out.println("오류 발생 " + e);
        }
    }
}
