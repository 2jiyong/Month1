import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Clock extends JFrame {
    private JLabel timeLabel;

    private List<JPanel> panelList ;
    private final String[] wordList = new String[] {"오","전","후","영"};






    public Clock(){
        setTitle("한글 시계");
        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(2,1));

        JPanel row1 = new JPanel(new GridLayout(1,4));
        JPanel row2 = new JPanel(new GridLayout(1,4));

        for(int i = 0 ; i<wordList.length; i++){
            JLabel word = new JLabel(wordList[i],SwingConstants.CENTER);
            word.setFont(new Font("고딕", Font.BOLD, 48));
            row1.add(word);
        }
        for(int i = 0 ; i<wordList.length; i++){
            JLabel word = new JLabel(wordList[i],SwingConstants.CENTER);
            word.setFont(new Font("고딕", Font.BOLD, 48));
            row2.add(word);
        }
        mainPanel.add(row1);
        mainPanel.add(row2);

        add(mainPanel, BorderLayout.CENTER);

    }


}
