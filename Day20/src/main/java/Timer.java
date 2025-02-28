import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {
    private final Runnable task;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public Timer(Runnable task){
        this.task = task;
    }

    public void start(){
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

}
