package buspj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

// '출발 날짜' 콤보 박스에 삽입할 날짜 배열 클래스 DuringDateTest
class DuringDateTest {
    String[] date = new String[100];   // 날짜를 담을 배열(사이즈를 100으로 설정)
    int length;                        // 배열에 저장된 날짜의 개수를 담을 변수

    public DuringDateTest() {
        String startDt = "20211001";  // 시작 날짜
        int endDt = 20211101;        // 끝 날짜

        int startYear = Integer.parseInt(startDt.substring(0,4));   // 시작 날짜에서 연도만 잘라서 저장
        int startMonth= Integer.parseInt(startDt.substring(4,6));   // 시작 날짜에서 월만 잘라서 저장
        int startDate = Integer.parseInt(startDt.substring(6,8));   // 시작 날짜에서 일만 잘라서 저장

        Calendar cal = Calendar.getInstance();    // Calendar 객체 생성

        // Calendar의 Month, Date는 0부터 시작하므로 -1 해준다.
        // Calendar의 기본 날짜를 startDt로 세팅해준다.
        cal.set(startYear, startMonth - 1, startDate - 1);

        int count = 0;    // 날짜를 배열에 저장할 때마다 증가할 count 변수
        int i = 0;
        while(true) {
            this.date[i] = getDateByString(cal.getTime());   // 시작 날짜부터 시작하여 날짜를 배열에 저장

            // Calendar의 날짜를 하루씩 증가한다.
            cal.add(Calendar.DATE, 1); // one day increment
            i += 1;
            count += 1;   // count + 1

            // 현재 날짜가 종료일자보다 크면 종료
            if(getDateByInteger(cal.getTime()) > endDt) break;
        }

        this.length = count;   // 최종 count 값을 length에 저장
    }

    public static int getDateByInteger(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(date));
    }

    public static String getDateByString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}

// 뒤로가기 버튼
class ReservationBack extends JPanel {
    ReservationMain frame;

    public ReservationBack(ReservationMain frame, String id) {
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
    }
}

// 예약하기 화면의 위쪽 진회색 패널
class ReservationNorth extends JPanel {
    ReservationMain frame;

    public ReservationNorth(ReservationMain frame, String id) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new ReservationBack(this.frame, id), BorderLayout.EAST);
    }
}

class Text extends JPanel {
    public Text() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 17, 10));
        setBackground(Color.LIGHT_GRAY);

        JLabel text = new JLabel("출발시간     ");
        text.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(text);

        JLabel text2 = new JLabel("회사     ");
        text2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(text2);

        JLabel text3 = new JLabel("등급     ");
        text3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(text3);

        JLabel text4 = new JLabel("잔여석     ");
        text4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(text4);

        JLabel text5 = new JLabel("요금");
        text5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(text5);

        setVisible(true);
    }
}

// 표 테이블의 가운데 부분 (구현중)
class Middle extends JScrollPane {
    public Middle() {
        setBackground(Color.WHITE);

        JList<String> ticketList = new JList<String>();
        
    }
}

// 예약하기 화면의 가운데 패널
class ReservationCenter extends JPanel {
    ReservationMain frame;
    static JComboBox<String> start = new JComboBox<String>();   // 출발 터미널 콤보박스
    static JComboBox<String> end = new JComboBox<String>();     // 도착 터미널 콤보박스
    static JComboBox<String> date = new JComboBox<String>();    // 출발 날짜 콤보박스
    DB_connect DB = new DB_connect();
    public ReservationCenter(ReservationMain frame, String id) {
        setLayout(null);

        // '예매하기' 글자
        JLabel title = new JLabel("예매하기");
        title.setBounds(60, 3, 150, 100);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        add(title);

        // 3개의 콤보박스를 감싸는 각각의 네모 박스 생성
        JPanel square = new JPanel();
        square.setBackground(Color.LIGHT_GRAY);
        square.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));
        square.setBounds(60,130,380,60);
        add(square);

        JPanel nextSquare1 = new JPanel();
        nextSquare1.setBackground(Color.LIGHT_GRAY);
        nextSquare1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));
        nextSquare1.setBounds(60,330,380,60);
        add(nextSquare1);

        JPanel nextSquare2 = new JPanel();
        nextSquare2.setBackground(Color.LIGHT_GRAY);
        nextSquare2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 16));
        nextSquare2.setBounds(60,530,380,60);
        add(nextSquare2);

        // 화살표 이미지 삽입
        ImageIcon profile = new ImageIcon("project__java/buspj/image/arrow.png");
        Image img = profile.getImage();
        Image updateImg = img.getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel image = new JLabel(updateIcon);
        image.setBounds(220,235,50,50);
        image.setHorizontalAlignment(JLabel.CENTER);
        add(image);

        // '출발 터미널' 글자
        JLabel startTerminal = new JLabel("출발 터미널");
        startTerminal.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        square.add(startTerminal);

        // '도착 터미널' 글자
        JLabel endTerminal = new JLabel("도착 터미널");
        endTerminal.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nextSquare1.add(endTerminal);

        // '출발 날짜' 글자
        JLabel startDate = new JLabel("출발 날짜");
        startDate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        nextSquare2.add(startDate);

        String[] start_terminal = new String[100];
        String end_terminal[] = new String[100];
        start_terminal=DB.start();
        // '출발 터미널' 콤보박스에 정류장 리스트 삽입
        start.setPreferredSize(new Dimension(220,30));
        //start_terminal = DB.start().toArray(new String[0]);
        for (int i=0; i<start_terminal.length; i++){
            start.addItem(start_terminal[i]);
        }
        square.add(start);

        // '도착 터미널' 콤보박스에 정류장 리스트 삽입
        end.setPreferredSize(new Dimension(220,30));
        end_terminal = DB.end();
        for (int i=0; i<end_terminal.length; i++){
            end.addItem(end_terminal[i]);
        }
        nextSquare1.add(end);

        // '출발 날짜' 콤보박스에 삽입할 날짜 정보 객체 생성
        DuringDateTest ddt = new DuringDateTest();

        // '출발 날짜' 콤보박스 사이즈 설정
        this.date.setPreferredSize(new Dimension(230,30));

        // '출발 날짜' 콤보박스에 ddt 객체에서 생성했던 날짜들 삽입
        for (int i = 1; i <= ddt.length; i++) {
            this.date.addItem(ddt.date[i]);
        }
        nextSquare2.add(this.date);

        // 표 테이블
        JPanel ticketTable = new JPanel();
        ticketTable.setLayout(new BorderLayout());
        ticketTable.setBackground(Color.WHITE);
        ticketTable.setBounds(510,100,410,370);
        add(ticketTable);

        // 표 상단바
        ticketTable.add(new Text(), BorderLayout.NORTH);
        // 표 중간부분
        ticketTable.add(new Middle(), BorderLayout.CENTER);

        // 조회 버튼
        JButton lookUp = new JButton("조회");
        lookUp.setBounds(510, 540, 100, 40);
        lookUp.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(lookUp);

        // 좌석선택 버튼
        JButton seats = new JButton("좌석선택");
        seats.setBounds(810, 540, 100,40);
        seats.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(seats);

        // 좌석선택 버튼 클릭시
        seats.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new SeatsSelect(id);
                frame.dispose();
            }
        });

        setVisible(true);
    }
}

// '예약하기' 화면의 메인 부분
public class ReservationMain extends JFrame {
    public ReservationMain(String id) {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new ReservationNorth(this, id), BorderLayout.NORTH);
        mainContainer.add(new ReservationCenter(this, id), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}
