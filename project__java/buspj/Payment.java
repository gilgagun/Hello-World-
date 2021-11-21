package buspj;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Payment extends JFrame {
    public Payment() {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // 위쪽 진회색 패널
        JPanel top = new JPanel();
        top.setBackground(Color.LIGHT_GRAY);
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));


        // 서브 타이틀 문구
        JLabel title = new JLabel("마법의 성이라는 노래를 아십니까? 안다면 당신은 옛날 사람.");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        top.add(title);

        // 뒤로가기 버튼
        JPanel back = new JPanel();
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton back = new JButton("뒤로가기");
        back.setPreferredSize(new Dimension(120, 50));
        back.setFont(new Font("고딕", Font.BOLD, 20));
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Main(id);
                frame.dispose();
            }
        });

        addWindowListener(new JFrameWindowClosingEventHandler());
        setVisible(true);
    }
}
