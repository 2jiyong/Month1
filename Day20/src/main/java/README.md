# 한글 시계
## 설계 과정
시계는 현재 시간을 한번만 읽어오고, 1초마다 갱신하도록 함.  
Swing을 이용하여 GUI를 구현
JFrame을 extend한 Clock 클래스 만들어서 생성하고, 실행함.

필요한 기능  
1. 현재 시간을 읽어오는 기능
2. 1초마다 시간을 갱신하는 기능
3. 시간을 한글로 변환하는 기능
4. 시간을 화면에 출력하는 기능

먼저 현재 시간을 읽어와서 Clock 클래스에 저장.  
Timer 클래스는 1초마다 콜백함수를 실행함.    
콜백함수는 Clock의 시간을 1초 더하고, 출력을 갱신함.

시간을 한글로 변환하기 위해서는 각 글자를 JLable로 만들고, 한 줄을 JPanel로 만들어서 거기에 JLabel을 추가함.
시간에 따라 불이 켜져야 하는 부분에 대해 리스트로 만들어서 관리.  

## 구현 메서드 
```java
public class Timer {
    private final Runnable task;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public Timer(Runnable task) {
        this.task = task;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

}
```
타이머 클래스는 ScheduledExecutorService를 이용하여 1초마다 콜백함수를 실행함.  
```java
    public Runnable runClockFunction() {
        return () -> {
            localTime = localTime.plusSeconds(1);
            setLightList();
            paintAgain();
            this.revalidate();
            this.repaint();
        };
    }
```
Clock의 콜백 함수는 1초를 더하고, 그 시간에 따라 불이 켜져야 하는 부분을 설정하고, 화면을 갱신함.  

