import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Clock clock = new Clock(LocalTime.now());
        Timer timer = new Timer(clock.runClockFunction());
        clock.setVisible(true);
    }
}
