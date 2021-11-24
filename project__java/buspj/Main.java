package buspj;
import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명


class Title extends JPanel {
    public Title() {
        Color mycor=new Color(189,215,238);
        setBackground(mycor);

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));

        JLabel title = new JLabel("저희 서비스를 이용해주셔서 감사합니다.");
        title.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
        add(title);
    }
}

class LoginAndSignup extends JPanel {
    Main frame;

    public LoginAndSignup(Main frame, String id) {
        Color mycor=new Color(189,215,238);
        setBackground(mycor);
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
        Color mycor=new Color(189,215,238);
        setBackground(mycor);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new LoginAndSignup(this.frame, id), BorderLayout.EAST);
    }
}

class CenterPanel extends JPanel {
    Main frame;

    public CenterPanel(Main frame, String id) {
        //setBackground(Color.WHITE);
        this.frame = frame;


        //setLayout(new FlowLayout(FlowLayout.CENTER, 100, 250));
        setLayout(null);



        JButton mainButton1 = new JButton("이미지로바꿔야함");
        mainButton1.setPreferredSize(new Dimension(180, 100));
        mainButton1.setFont(new Font("굴림", Font.BOLD, 20));
        mainButton1.setBounds(50,50,180,100);
        add(mainButton1);

        JButton mainButton2 = new JButton("<HTML>예매내역<br>조회하기</HTML>");
        mainButton2.setPreferredSize(new Dimension(180, 180));
        mainButton2.setFont(new Font("굴림", Font.BOLD, 20));
        mainButton2.setBounds(300,50,180,180);
        add(mainButton2);

        JButton mainButton3 = new JButton("챗봇");
        mainButton3.setPreferredSize(new Dimension(180, 180));
        mainButton3.setFont(new Font("굴림", Font.BOLD, 20));
        mainButton3.setBounds(550,50,180,180);
        add(mainButton3);

        ImageIcon background = new ImageIcon("project__java/buspj/image/bg.png");
        JLabel image2 = new JLabel(background);
        image2.setBounds(-1570,-150,4000,1200);
        add(image2);


        // 예매하기 클릭 시 이벤트
        mainButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReservationMain(id);
                frame.dispose();
            }
        });
        mainButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReservationCheckUp(id);
            }
        });
        mainButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ChatBot(id);
                //frame.dispose();
            }
        });
    }
}

public class Main extends JFrame{
    public Main(String id) {
        setTitle("버스타슈~");
        setSize(900,800);
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