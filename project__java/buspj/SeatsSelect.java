package buspj;

import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명

// 선택한 좌석에 관한 클래스
class SelectSeats {
    int number;
    int check;

    public SelectSeats (int number, int check) {
        this.number = number;
        this.check = check;
    }

    public int get_number() {
        return this.number;
    }

    public int get_phone() {
        return this.check;
    }
}

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

        // 좌석 생성
        create_seats(seatsTable);

        // 가격 테이블 생성
        JPanel priceTable = new JPanel();
        priceTable.setBackground(Color.WHITE);
        priceTable.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        priceTable.setBounds(755, 360,200,150);
        add(priceTable);

        // 가격 테이블 열 이름 생성

    }

    // 좌석 이미지 생성
    public void create_seats(JPanel p) {
        int num = 7;   // 생성할 좌석의 세로 줄 수

        // 선택 가능 이미지
        ImageIcon possible = new ImageIcon("project__java/buspj/image/white_seats.png");
        Image possibleImage = possible.getImage();
        Image updatePossibleImg = possibleImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon updatePossibleIcon = new ImageIcon(updatePossibleImg);

        JLabel possibleSeats = new JLabel(updatePossibleIcon);
        possibleSeats.setBounds(12,210,50,50);
        possibleSeats.setHorizontalAlignment(JLabel.CENTER);
        p.add(possibleSeats);

        // 선택 가능 텍스트
        JLabel possibleText = new JLabel("선택가능");
        possibleText.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        possibleText.setBounds(17, 260, 50, 15);
        p.add(possibleText);

        // 선택 불가능 이미지
        ImageIcon impossible = new ImageIcon("project__java/buspj/image/black_seats.png");
        Image impossibleImage = impossible.getImage();
        Image updateImpossibleImg = impossibleImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon updateImpossibleIcon = new ImageIcon(updateImpossibleImg);

        JLabel impossibleSeats = new JLabel(updateImpossibleIcon);
        impossibleSeats.setBounds(12,290,50,50);
        impossibleSeats.setHorizontalAlignment(JLabel.CENTER);
        p.add(impossibleSeats);

        // 선택 불가능 텍스트
        JLabel impossibleText = new JLabel("선택불가능");
        impossibleText.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        impossibleText.setBounds(12, 340, 60, 15);
        p.add(impossibleText);

        // 아무것도 선택하지 않은 초기 좌석 이미지 생성
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < num; j++) {
                ImageIcon white_seats = new ImageIcon("project__java/buspj/image/white_seats.png");
                Image image = white_seats.getImage();
                Image updateImg = image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
                ImageIcon updateIcon = new ImageIcon(updateImg);

                JLabel seats = new JLabel(updateIcon);
                seats.setBounds(82 + 52*i,25 + 70*j,70,70);
                seats.setHorizontalAlignment(JLabel.CENTER);
                p.add(seats);
            }
        }

        // 맨 뒷 좌석 가운데 한 자리
        ImageIcon white_seats = new ImageIcon("project__java/buspj/image/white_seats.png");
        Image image = white_seats.getImage();
        Image updateImg = image.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel seats = new JLabel(updateIcon);
        seats.setBounds(186,445,70,70);
        seats.setHorizontalAlignment(JLabel.CENTER);
        p.add(seats);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < num; j++) {
                ImageIcon white_seats2 = new ImageIcon("project__java/buspj/image/white_seats.png");
                Image image2 = white_seats2.getImage();
                Image updateImg2 = image2.getScaledInstance(60,60,Image.SCALE_SMOOTH);
                ImageIcon updateIcon2 = new ImageIcon(updateImg2);

                JLabel seats2 = new JLabel(updateIcon2);
                seats2.setBounds(238 + 52*i,25 + 70*j,70,70);
                seats2.setHorizontalAlignment(JLabel.CENTER);
                p.add(seats2);
            }
        }
    }
}

// 좌석 선택 클래스 전체적인 구조
public class SeatsSelect extends JFrame {
    public SeatsSelect(String id, String start, String end, String date, String[] info) {
        setTitle("버스타슈~");
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
