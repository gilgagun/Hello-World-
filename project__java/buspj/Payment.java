package buspj;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

// 결재 페이지 뒤로가기 버튼
class PaymentBack extends JPanel {
    public PaymentBack(Payment frame, SeatsSelect frame2) {
        Color mycor=new Color(189,215,238);
        setBackground(mycor);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        // 뒤로가기 버튼 이미지
        ImageIcon backIcon = new ImageIcon("project__java/buspj/image/back.png");
        Image backImg = backIcon.getImage();
        Image backUpdate = backImg.getScaledInstance(100,50, Image.SCALE_SMOOTH);
        ImageIcon backUpdateIcon = new ImageIcon(backUpdate);

        ImageIcon backIcon2 = new ImageIcon("project__java/buspj/image/back2.png");
        Image backImg2 = backIcon2.getImage();
        Image backUpdate2 = backImg2.getScaledInstance(100,50, Image.SCALE_SMOOTH);
        ImageIcon backUpdateIcon2 = new ImageIcon(backUpdate2);

        JButton back = new JButton(backUpdateIcon);
        back.setPreferredSize(new Dimension(100,50));
        back.setRolloverIcon(backUpdateIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        back.setBorderPainted(false); // 버튼 테두리 설정해제
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame2.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}

// 결제 페이지 상단
class PaymentNorth extends JPanel {
    public PaymentNorth(Payment frame, SeatsSelect frame2) {
        Color mycor=new Color(189,215,238);
        setBackground(mycor);

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new PaymentBack(frame, frame2), BorderLayout.EAST);
    }
}

// 결제 페이지 중심
class PaymentCenter extends JPanel {
    JTable ticketTable;
    DefaultTableModel model;
    JScrollPane scroll;
    String start;
    String end;
    String date;
    String[] info;
    int number;
    int totalPrice;
//    int seatNum;
    DB_connect DB = new DB_connect();  // DB 연결

    public PaymentCenter(SeatsSelect frame, Payment frame2, String id, String start, String end, String date, String[] info, int price) {
        setLayout(null);
        Color bgmycor=new Color(166,222,249);
        setBackground(bgmycor);
        Color mycor=new Color(189,215,238);
        //setBackground(Color.WHITE);
        // '예매하기' 글자
        JLabel title = new JLabel("예매하기");
        title.setBounds(60, 3, 150, 100);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        add(title);

        this.start = start; // 출발 터미널 정보 저장
        this.end = end;     // 도착 터미널 정보 저장
        this.date = date;   // 날짜 정보 저장
        this.info = info;   // 표 정보 저장
        this.number = number;  // 인원 정보 저장
        this.totalPrice = price;    // 가격 정보 저장
//        this.seatNum = seatNum;


        // 날짜 정보 표시
        JLabel dateInfo = new JLabel(date);
        dateInfo.setBounds(90, 170, 200, 100);
        dateInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(dateInfo);

        // 출발 터미널 정보 표시
        JLabel startInfo = new JLabel(start);
        startInfo.setBounds(90, 220, 100, 100);
        startInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(startInfo);

        // 화살표 이미지 표시
        ImageIcon profile = new ImageIcon("project__java/buspj/image/arrow2.png");
        Image img = profile.getImage();
        Image updateImg = img.getScaledInstance(70,35,Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel image = new JLabel(updateIcon);
        image.setBounds(210,255,70,35);
        image.setHorizontalAlignment(JLabel.CENTER);
        add(image);

        // 도착 터미널 정보 표시
        JLabel endInfo = new JLabel(end);
        endInfo.setBounds(340,220,100,100);
        endInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(endInfo);

        // 테이블 기본 틀 생성
        JPanel tT = new JPanel();
        tT.setBounds(40,290,500,100);

        // 테이블 기본 값 생성
        String[] colName = {"출발시간","회사","등급","요금"}; // 컬럼 네임 설정
        String[][] row = new String[0][4];
        model = new DefaultTableModel(row, colName);

        // 테이블 생성
        ticketTable = new JTable(model);
        ticketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 스크롤 팬 추가
        scroll = new JScrollPane(ticketTable);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));

        tT.add("North", scroll);
        add(tT);

        // 테이블에 넣을 값 생성
        String[] data = {this.info[0], this.info[1], this.info[2], this.info[4]};
        model.addRow(data);

        // 총 결재금액 글자
        JLabel totalText = new JLabel("총 결제금액");
        totalText.setBounds(90,380,200,100);
        totalText.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(totalText);

        // 총 결재금액 액수
        JLabel total = new JLabel("" + totalPrice);
        total.setBounds(300,380,200,100);
        total.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        total.setForeground(Color.RED);
        add(total);

        JPanel square1 = new JPanel();
        square1.setBackground(mycor);
        square1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));
        square1.setBounds(90,380,350,70);
        add(square1);

        // 카드 선택 글자
        JLabel cardSelectText = new JLabel("카드선택");
        cardSelectText.setBounds(580,60,100,100);
        cardSelectText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(cardSelectText);

        // 카드 선택 콤보박스
        JComboBox<String> myCard = new JComboBox<String>();
        myCard.setPreferredSize(new Dimension(320,30));
        myCard.setBounds(580, 135,320,30);
        add(myCard);

        // 카드 번호 글자
        JLabel cardNumberText = new JLabel("카드번호");
        cardNumberText.setBounds(580,150,100,100);
        cardNumberText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(cardNumberText);

        // 카드 번호 텍스트 필드
        JTextField cardNumber1 = new JTextField();
        cardNumber1.setBounds(580,225,50,30);
        add(cardNumber1);

        JLabel slash1 = new JLabel("ㅡ");
        slash1.setBounds(625,230,45,15);
        slash1.setHorizontalAlignment(JLabel.CENTER);
        slash1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(slash1);

        JTextField cardNumber2 = new JTextField();
        cardNumber2.setBounds(670,225,50,30);
        add(cardNumber2);

        JLabel slash2 = new JLabel("ㅡ");
        slash2.setBounds(715,230,45,15);
        slash2.setHorizontalAlignment(JLabel.CENTER);
        slash2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(slash2);

        JTextField cardNumber3 = new JTextField();
        cardNumber3.setBounds(760,225,50,30);
        add(cardNumber3);

        JLabel slash3 = new JLabel("ㅡ");
        slash3.setBounds(805,230,45,15);
        slash3.setHorizontalAlignment(JLabel.CENTER);
        slash3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(slash3);

        JTextField cardNumber4 = new JTextField();
        cardNumber4.setBounds(850,225,50,30);
        add(cardNumber4);

        // 유효기간 월 글자
        JLabel monthText = new JLabel("유효기간 월(MONTH)");
        monthText.setBounds(580,240,200,100);
        monthText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(monthText);

        // 유효기간 월 텍스트 필드
        JTextField month = new JTextField();
        month.setBounds(580,315,200,30);
        add(month);

        // 유효기간 년 글자
        JLabel yearText = new JLabel("유효기간 년(YEAR)");
        yearText.setBounds(580,330,200,100);
        yearText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(yearText);

        // 유효기간 년 텍스트 필드
        JTextField year = new JTextField();
        year.setBounds(580,405,200,30);
        add(year);

        // 비밀번호 글자
        JLabel passwordText = new JLabel("비밀번호 앞 2자리");
        passwordText.setBounds(580,420,200,100);
        passwordText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(passwordText);

        // 비밀번호 입력창
        JPasswordField password = new JPasswordField();
        password.setBounds(580,495,200,30);
        add(password);

//        // 결제완료 버튼
//        JButton clear = new JButton("결제완료");
//        clear.setBounds(820, 550, 100,40);
//        clear.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//        add(clear);

        // 결제완료 버튼 이미지
        ImageIcon paymentClearIcon = new ImageIcon("project__java/buspj/image/paymentClear.png");
        Image paymentClearImg = paymentClearIcon.getImage();
        Image paymentClearUpdate = paymentClearImg.getScaledInstance(100,50, Image.SCALE_SMOOTH);
        ImageIcon paymentClearUpdateIcon = new ImageIcon(paymentClearUpdate);

        ImageIcon paymentClearIcon2 = new ImageIcon("project__java/buspj/image/paymentClear2.png");
        Image paymentClearImg2 = paymentClearIcon2.getImage();
        Image paymentClearUpdate2 = paymentClearImg2.getScaledInstance(100,50, Image.SCALE_SMOOTH);
        ImageIcon paymentClearUpdateIcon2 = new ImageIcon(paymentClearUpdate2);

        JButton clear = new JButton(paymentClearUpdateIcon);
        clear.setBounds(820,550,100,50);
        clear.setRolloverIcon(paymentClearUpdateIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        clear.setBorderPainted(false); // 버튼 테두리 설정해제
        clear.setFocusPainted(false);
        clear.setContentAreaFilled(false);
        clear.setOpaque(false);
        add(clear);

        JPanel square = new JPanel();
        square.setBackground(mycor);
        square.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));
        square.setBounds(570,100,380,450);
        add(square);
        // 결재완료 버튼 클릭 시
        clear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // 예매 DB에 저장 후 dispose
                DB.saveUserReservation(id, start, end, date, info[0], info[1], info[2], info[4]);
                JOptionPane.showMessageDialog(null, "예매에 성공하였습니다.");
                new Main(id);
                frame2.dispose();
            }
        });

        ImageIcon background = new ImageIcon("project__java/buspj/image/test.jpg");
        JLabel image2 = new JLabel(background);
        image2.setBounds(-1800,-200,4500,1200);
        add(image2);

    }
}

// 결재 페이지 메인
public class Payment extends JFrame {
    public Payment(SeatsSelect frame, String id, String start, String end, String date, String[] info, int price) {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new PaymentNorth(this, frame), BorderLayout.NORTH);
        mainContainer.add(new PaymentCenter(frame, this, id, start, end, date, info, price), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}
