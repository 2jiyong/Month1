import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Clock extends JFrame {
    private List<JPanel> panelList ;
    private final List<String> wordList = new ArrayList<>(List.of
        ("오전후영", "열한두세", "네다여섯", "일곱여덟", "아홉시달", "이삼사오", "심일이삼", "사오육칠", "팔구분초"));

    public Clock(){
        setTitle("한글 시계");
        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(9,1));
        mainPanel.setBackground(Color.DARK_GRAY);

        for(int row = 0 ; row < wordList.size(); row ++){
            JPanel rowPanel = new JPanel(new GridLayout(1,4));
            rowPanel.setBackground(Color.DARK_GRAY);
            for(int i = 0 ; i<4; i++){
                JLabel word = new JLabel(wordList.get(row).substring(i,i+1),SwingConstants.CENTER);
                word.setFont(new Font("고딕", Font.BOLD,48));
                word.setForeground(Color.LIGHT_GRAY);
                rowPanel.add(word);
            }
            mainPanel.add(rowPanel);
        }

        add(mainPanel, BorderLayout.CENTER);

    }


}
