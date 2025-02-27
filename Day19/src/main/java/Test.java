import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("> 지구날짜는? ");
        String input = sc.nextLine();
        String date = "2024-1-1";
        DayConverter dayConverter = new DayConverter(date);
        Progress progress = new Progress();

        progress.start();
        dayConverter.start();

        progress.join();
        dayConverter.join();

        System.out.println(dayConverter.getResult());
    }
}
