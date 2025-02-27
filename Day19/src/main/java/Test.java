import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        DayConverter dayConverter = new DayConverter();
        int days = dayConverter.findDaysByEarthDate(LocalDate.of(2024,1,1));
        int[] result = dayConverter.findMarsDateByDays(days);
        for(int answer : result){
            System.out.println(answer);
        }
        System.out.println(days);
    }
}
