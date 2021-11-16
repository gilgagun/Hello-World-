package buspj;

import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명

// 뒤로가기 버튼
class SeatsBack extends JPanel {
    SeatsSelect frame;

    public SeatsBack(SeatsSelect frame, String id) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton back = new JButton("뒤로가기");
        back.setPreferredSize(new Dimension(120, 50));
        back.setFont(new Font("고딕", Font.BOLD, 20));
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReservationMain(id);
                frame.dispose();
            }
        });
    }
}

// 화면 맨 위 회색 부분
class SeatsNorth extends JPanel {
    SeatsSelect frame;

    public SeatsNorth(SeatsSelect frame, String id) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new SeatsBack(this.frame, id), BorderLayout.EAST);
    }
}

// 화면 가운데 부분
class SeatsCenter extends JPanel {
    public SeatsCenter(SeatsSelect frame, String id) {

    }
}

// 좌석 선택 클래스 전체적인 구조
public class SeatsSelect extends JFrame {
    public SeatsSelect(String id) {
        setTitle("버스 타고가");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new SeatsNorth(this, id), BorderLayout.NORTH);
        mainContainer.add(new SeatsCenter(this, id), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}
