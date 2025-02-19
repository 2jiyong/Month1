import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    public static Coordinates getInputAndMakeCoordinates(){
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                return makeCoordinates(sc.nextLine());
            }catch (IllegalArgumentException e){
                System.out.println("오류 발생"+e);
            }
        }
    }

    public static Coordinates makeCoordinates(String input){
        Coordinates coordinates = new Coordinates();
        Pattern pattern = Pattern.compile("\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        // 모든 (x,y) 좌표 추출
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1)); // 첫 번째 캡처 그룹 (x 값)
            int y = Integer.parseInt(matcher.group(2));
            if (x>0 && x<=24 && y>0 && y<24){
                coordinates.addCoordinate(x,y);
            } else {
                throw new IllegalArgumentException("숫자의 범위가 잘못되었습니다.");
            }
        }
        return coordinates;
    }

}
