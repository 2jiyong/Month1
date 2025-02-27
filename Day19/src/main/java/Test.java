import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        DayConverter dayConverter = new DayConverter();
        String date = "2024-1-1";
        System.out.println(dayConverter.printConvertedCalender(date));

    }
}
