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
        setBackground(Color.LIGHT_GRAY);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton back = new JButton("뒤로가기");
        back.setPreferredSize(new Dimension(120, 50));
        back.setFont(new Font("고딕", Font.BOLD, 20));
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
        setBackground(Color.LIGHT_GRAY);

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new PaymentBack(frame, frame2), BorderLayout.EAST);
    }
}

// 결제 페이지 중심
class PaymentCenter extends JPanel {
    JTable ticketTable;
    DefaultTableModel model;
    String start;
    String end;
    String date;
    String[] info;
    int number;
    int totalPrice;
    int seatNum;

    public PaymentCenter(SeatsSelect frame, String id, String start, String end, String date, String[] info, int number, int price, int seatNum) {
        setLayout(null);

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
        this.seatNum = seatNum;

        // 날짜 정보 표시
        JLabel dateInfo = new JLabel(date);
        dateInfo.setBounds(150, 130, 200, 100);
        dateInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(dateInfo);

        // 출발 터미널 정보 표시
        JLabel startInfo = new JLabel(start);
        startInfo.setBounds(150, 200, 100, 100);
        startInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(startInfo);

        // 화살표 이미지 표시
        ImageIcon profile = new ImageIcon("project__java/buspj/image/arrow2.png");
        Image img = profile.getImage();
        Image updateImg = img.getScaledInstance(70,35,Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel image = new JLabel(updateIcon);
        image.setBounds(270,235,70,35);
        image.setHorizontalAlignment(JLabel.CENTER);
        add(image);

        // 도착 터미널 정보 표시
        JLabel endInfo = new JLabel(end);
        endInfo.setBounds(400,200,100,100);
        endInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(endInfo);

        // 테이블 기본 틀 생성
        JPanel tT = new JPanel();
        tT.setBounds(120,300,400,300);

        // 테이블 기본 값 생성
        String[] colName = {"출발시간","회사","등급","선택좌석","요금"}; // 컬럼 네임 설정
        String[][] row = new String[0][5];
        model = new DefaultTableModel(row, colName);

        // 테이블 생성
        ticketTable = new JTable(model);
        ticketTable.setBounds(120,300,400,300);
        tT.add(ticketTable);

        // 테이블에 넣을 값 생성
        for (int i = 0; i < number; i++) {
            String[] data = {this.info[0], this.info[1], this.info[2], String.valueOf(this.seatNum), this.info[4]};
            model.addRow(data);
        }
        add(tT);
    }
}

// 결재 페이지 메인
public class Payment extends JFrame {
    public Payment(SeatsSelect frame, String id, String start, String end, String date, String[] info, int number, int price, int seatNum) {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new PaymentNorth(this, frame), BorderLayout.NORTH);
        mainContainer.add(new PaymentCenter(frame, id, start, end, date, info, number, price, seatNum), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}
