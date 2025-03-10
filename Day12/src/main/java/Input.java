import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public static List<int[]> getInputAndMakeCoordinates(){
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                return parseStringToCoordinateList(sc.nextLine());
            }catch (IllegalArgumentException e){
                System.out.println("오류 발생"+e);
            }
        }
    }

    public static List<int[]> parseStringToCoordinateList(String input){
        List<int[]> coordinateList = new ArrayList<int[]>();
        Pattern pattern = Pattern.compile("\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        // 모든 (x,y) 좌표 추출
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1)); // 첫 번째 캡처 그룹 (x 값)
            int y = Integer.parseInt(matcher.group(2));
            if (x>0 && x<=24 && y>0 && y<=24){
                coordinateList.add(new int[] {x,y});
            } else {
                throw new IllegalArgumentException("숫자의 범위가 잘못되었습니다.");
            }
        }
        return coordinateList;
    }

}
