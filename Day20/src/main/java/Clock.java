import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Clock extends JFrame {
    private List<JPanel> panelList;
    private final List<String> wordList = new ArrayList<>(List.of
            ("오전후영", "열한두세", "네다여섯", "일곱여덟", "아홉시달", "이삼사오", "심일이삼", "사오육칠", "팔구분초"));
    private LocalTime localTime;
    private List<boolean[]> lightList;

    public Clock(LocalTime localTime) {
        this.localTime = localTime;
        setTitle("한글 시계");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLightList();

        JPanel mainPanel = new JPanel(new GridLayout(9, 1));
        mainPanel.setBackground(Color.BLACK);

        for (int row = 0; row < wordList.size(); row++) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 4));
            rowPanel.setBackground(Color.BLACK);
            for (int i = 0; i < 4; i++) {
                JLabel word = new JLabel(wordList.get(row).substring(i, i + 1), SwingConstants.CENTER);
                word.setFont(new Font("고딕", Font.BOLD, 48));
                if (lightList.get(row)[i]) word.setForeground(Color.WHITE);
                else word.setForeground(Color.DARK_GRAY);
                rowPanel.add(word);
            }
            mainPanel.add(rowPanel);
        }

        add(mainPanel, BorderLayout.CENTER);
    }

    public Runnable runClockFunction() {
        return () -> {
            localTime.plusSeconds(1);
            this.revalidate();
            this.repaint();
        };
    }

    public void setLightList() {
        lightList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            lightList.add(new boolean[4]);
        }
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        // 오전 오후
        lightList.get(0)[0] = true;
        if (hour < 12) lightList.get(0)[1] = true;
        else {
            lightList.get(0)[2] = true;
            hour -= 12;
        }
        // 시
        lightList.get(4)[2] = true;

        switch (hour) {
            case 0 -> {
                lightList.get(1)[0] = true;
                lightList.get(1)[2] = true;
            }
            case 1 -> lightList.get(1)[1] = true;
            case 2 -> lightList.get(1)[2] = true;
            case 3 -> lightList.get(1)[3] = true;
            case 4 -> lightList.get(2)[0] = true;
            case 5 -> {
                lightList.get(2)[1] = true;
                lightList.get(2)[3] = true;
            }
            case 6 -> {
                lightList.get(2)[2] = true;
                lightList.get(2)[3] = true;
            }
            case 7 -> {
                lightList.get(3)[0] = true;
                lightList.get(3)[1] = true;
            }
            case 8 -> {
                lightList.get(3)[2] = true;
                lightList.get(3)[3] = true;
            }
            case 9 -> {
                lightList.get(4)[0] = true;
                lightList.get(4)[1] = true;
            }
            case 10 -> lightList.get(1)[0] = true;
            case 11 -> {
                lightList.get(1)[0] = true;
                lightList.get(1)[1] = true;
            }
            default -> System.out.println("오류 발생");
        }
        // 분
        int minuteDivide = minute / 10;
        int minuteMod = minute % 10;

    }
}
