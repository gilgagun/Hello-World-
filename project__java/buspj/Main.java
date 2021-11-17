package buspj;
import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명


class Title extends JPanel {
    public Title() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));

        JLabel title = new JLabel("마법의 성이라는 노래를 아십니까? 안다면 당신은 옛날 사람.");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        add(title);
    }
}

class LoginAndSignup extends JPanel {
    Main frame;

    public LoginAndSignup(Main frame, String id) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton myButton = new JButton("마이페이지");
        myButton.setPreferredSize(new Dimension(150, 50));
        myButton.setFont(new Font("굴림", Font.BOLD, 20));
        add(myButton);

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setPreferredSize(new Dimension(120, 50));
        logoutButton.setFont(new Font("굴림", Font.BOLD, 20));
        add(logoutButton);

        // 마이페이지 버튼 클릭 시 이벤트
        myButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new MyPage(id);
                frame.dispose();
            }
        });

        // 로그아웃 버튼 클릭 시 이벤트
        logoutButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?","로그아웃",JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    new login_interface();
                    frame.dispose();   // dispose를 밑으로 빼니 창 전환 시 더 자연스러운 모션
                }
            }
        });
    }
}

class NorthPanel extends JPanel {
    Main frame;

    public NorthPanel(Main frame, String id) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new LoginAndSignup(this.frame, id), BorderLayout.EAST);
    }
}

class CenterPanel extends JPanel {
    Main frame;

    public CenterPanel(Main frame, String id) {
        setBackground(Color.WHITE);
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 250));

        JButton mainButton1 = new JButton("예매하기");
        mainButton1.setPreferredSize(new Dimension(180, 180));
        mainButton1.setFont(new Font("굴림", Font.BOLD, 20));
        add(mainButton1);

        JButton mainButton2 = new JButton("<HTML>예매내역<br>조회하기</HTML>");
        mainButton2.setPreferredSize(new Dimension(180, 180));
        mainButton2.setFont(new Font("굴림", Font.BOLD, 20));
        add(mainButton2);

        JButton mainButton3 = new JButton("도움말");
        mainButton3.setPreferredSize(new Dimension(180, 180));
        mainButton3.setFont(new Font("굴림", Font.BOLD, 20));
        add(mainButton3);

        // 예매하기 클릭 시 이벤트
        mainButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReservationMain(id);
                frame.dispose();
            }
        });
    }
}

public class Main extends JFrame{
    public Main(String id) {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new NorthPanel(this, id), BorderLayout.NORTH);
        mainContainer.add(new CenterPanel(this, id), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}