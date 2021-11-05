package buspj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

// 날짜 리스트
class DuringDateTest {
    String[] date = new String[100];
    int length;

    public DuringDateTest() {
        String startDt = "20211001";
        int endDt = 20211101;

        int startYear = Integer.parseInt(startDt.substring(0,4));
        int startMonth= Integer.parseInt(startDt.substring(4,6));
        int startDate = Integer.parseInt(startDt.substring(6,8));

        Calendar cal = Calendar.getInstance();

        // Calendar의 Month는 0부터 시작하므로 -1 해준다.
        // Calendar의 기본 날짜를 startDt로 셋팅해준다.
        cal.set(startYear, startMonth -1, startDate);

        int count = 0;
        int i = 0;
        while(true) {
            // 날짜 출력
            this.date[i] = getDateByString(cal.getTime());

            // Calendar의 날짜를 하루씩 증가한다.
            cal.add(Calendar.DATE, 1); // one day increment
            i += 1;
            count += 1;

            // 현재 날짜가 종료일자보다 크면 종료
            if(getDateByInteger(cal.getTime()) > endDt) break;
        }

        this.length = count;
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

class ReservationBack extends JPanel {
    ReservationMain frame;

    public ReservationBack(ReservationMain frame) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton back = new JButton("뒤로가기");
        back.setPreferredSize(new Dimension(120, 50));
        back.setFont(new Font("고딕", Font.BOLD, 20));
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Main();
                frame.dispose();
            }
        });
    }
}

class ReservationNorth extends JPanel {
    ReservationMain frame;

    public ReservationNorth(ReservationMain frame) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new ReservationBack(this.frame), BorderLayout.EAST);
    }
}

class ReservationCenter extends JPanel {
    static JComboBox<String> start = new JComboBox<String>();
    static JComboBox<String> end = new JComboBox<String>();
    static JComboBox<String> date = new JComboBox<String>();

    public ReservationCenter() {
        setLayout(null);

        // '예매하기' 타이틀
        JLabel title = new JLabel("예매하기");
        title.setBounds(60, 3, 150, 100);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        add(title);

        // 네모 칸 3개 생성
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

        // 화살표
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

        // '출발 날짜' 콤보박스에 담을 날짜 정보 생성
        DuringDateTest ddt = new DuringDateTest();
        
        // '출발 터미널' 콤보박스에 정류장 삽입
        start.setPreferredSize(new Dimension(220,30));
        square.add(start);

        // '도착 터미널' 콤보박스에 정류장 삽입
        end.setPreferredSize(new Dimension(220,30));
        nextSquare1.add(end);

        // '출발 날짜' 콤보박스에 날짜 삽입
        date.setPreferredSize(new Dimension(230,30));
        nextSquare2.add(date);

        setVisible(true);
    }
}

public class ReservationMain extends JFrame {
    public ReservationMain() {
        setTitle("버스 예약 시스템(가제)");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new ReservationNorth(this), BorderLayout.NORTH);
        mainContainer.add(new ReservationCenter(), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}
