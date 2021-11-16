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
        setLayout(null);

        // '예매하기' 글자
        JLabel title = new JLabel("예매하기");
        title.setBounds(60, 3, 150, 100);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        add(title);

        // 어른 버튼
        JButton adault = new JButton("어른");
        adault.setBounds(140, 450,100, 40);
        adault.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(adault);

        // 청소년 버튼
        JButton teen = new JButton("청소년");
        teen.setBounds(140, 505,100, 40);
        teen.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(teen);

        // 좌석 표시 패널
        JPanel seatsTable = new JPanel();
        seatsTable.setBackground(Color.WHITE);
        seatsTable.setLayout(null);
        seatsTable.setBounds(275, 85, 450,550);
        add(seatsTable);

        // 선택하지 않은 좌석 이미지
        ImageIcon white_seats = new ImageIcon("project__java/buspj/image/white_seats.png");
        Image image = white_seats.getImage();
        Image updateImg = image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel seats = new JLabel(updateIcon);
        seats.setBounds(60,50,70,70);
        seats.setHorizontalAlignment(JLabel.CENTER);
        seatsTable.add(seats);
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
