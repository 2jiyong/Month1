import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock(LocalTime.now());
        Timer timer = new Timer(clock.runClockFunction());
        clock.setVisible(true);
        timer.start();
        Thread.sleep(Long.MAX_VALUE);
    }
}
