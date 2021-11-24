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

        ImageIcon my = new ImageIcon("project__java/buspj/image/remy.png");
        Image my1 = my.getImage();
        Image remy = my1.getScaledInstance(60,50, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon1 = new ImageIcon(remy);

        ImageIcon my2 = new ImageIcon("project__java/buspj/image/remy1.png");
        Image my22 = my2.getImage();
        Image remy1 = my22.getScaledInstance(60,50, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon11 = new ImageIcon(remy1);

        ImageIcon logout = new ImageIcon("project__java/buspj/image/reout.png");
        Image relogout = logout.getImage();
        Image reUpdate1 = relogout.getScaledInstance(60,50, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon2 = new ImageIcon(reUpdate1);

        ImageIcon logout2 = new ImageIcon("project__java/buspj/image/reout1.png");
        Image relogout2 = logout2.getImage();
        Image reUpdate12 = relogout2.getScaledInstance(60,50, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon22 = new ImageIcon(reUpdate12);

        JButton myButton = new JButton(reUpdateIcon1);
        //myButton.setPreferredSize(new Dimension(150, 50));
        //myButton.setFont(new Font("굴림", Font.BOLD, 20));
        myButton.setRolloverIcon(reUpdateIcon11);
        myButton.setBorderPainted(false);
        myButton.setFocusPainted(false);
        myButton.setContentAreaFilled(false);
        myButton.setOpaque(false);
        add(myButton);

        JButton logoutButton = new JButton(reUpdateIcon2);
        //logoutButton.setPreferredSize(new Dimension(120, 50));
        //.setFont(new Font("굴림", Font.BOLD, 20));
        logoutButton.setRolloverIcon(reUpdateIcon22);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);
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

//        JButton mainButton1 = new JButton("예매하기");
//        mainButton1.setPreferredSize(new Dimension(180, 100));
//        mainButton1.setFont(new Font("굴림", Font.BOLD, 20));
//        mainButton1.setBounds(50,50,180,100);
//        add(mainButton1);
        JLabel p1 = new JLabel("예매하기");
        JLabel p2 = new JLabel("예약조회");
        JLabel p3 = new JLabel("챗봇문의");
        p1.setFont(new Font("휴먼엑스포", Font.PLAIN, 35));
        p2.setFont(new Font("휴먼엑스포", Font.PLAIN, 35));
        p3.setFont(new Font("휴먼엑스포", Font.PLAIN, 35));
        p1.setForeground(Color.WHITE);
        p2.setForeground(Color.WHITE);
        p3.setForeground(Color.WHITE);
        p1.setBounds(105,180,200,200);
        p2.setBounds(375,180,200,200);
        p3.setBounds(645,180,200,200);
        add(p1);
        add(p2);
        add(p3);

        // 예매하기 버튼 이미지
        ImageIcon reIcon = new ImageIcon("project__java/buspj/image/icon4.png");
        Image reImg = reIcon.getImage();
        Image reUpdate = reImg.getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon = new ImageIcon(reUpdate);

        ImageIcon reIcon2 = new ImageIcon("project__java/buspj/image/blurIcon2.png");
        Image reImg2 = reIcon2.getImage();
        Image reUpdate2 = reImg2.getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon2 = new ImageIcon(reUpdate2);

        JButton reservation = new JButton(reUpdateIcon);
        reservation.setBounds(75,60,190,190);
        reservation.setBorderPainted(false); // 버튼 테두리 설정해제
        Color mycor=new Color(189,215,238);
        reservation.setRolloverIcon(reUpdateIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        reservation.setBackground(mycor);
        reservation.setFocusPainted(false);
        reservation.setContentAreaFilled(false);
        reservation.setOpaque(false);
        add(reservation);

//        JButton mainButton2 = new JButton("<HTML>예매내역<br>조회하기</HTML>");
//        mainButton2.setPreferredSize(new Dimension(180, 180));
//        mainButton2.setFont(new Font("굴림", Font.BOLD, 20));
//        mainButton2.setBounds(300,50,180,180);
//        add(mainButton2);

        // 예매내역 조회 버튼 이미지
        ImageIcon chIcon = new ImageIcon("project__java/buspj/image/icon2.png");
        Image chImg = chIcon.getImage();
        Image chUpdate = chImg.getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon chUpdateIcon = new ImageIcon(chUpdate);

        ImageIcon chIcon2 = new ImageIcon("project__java/buspj/image/blurIcon3.png");
        Image chImg2 = chIcon2.getImage();
        Image chUpdate2 = chImg2.getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon chUpdateIcon2 = new ImageIcon(chUpdate2);

        JButton checkUp = new JButton(chUpdateIcon);
        checkUp.setBounds(345,60,190,190);
        checkUp.setBorderPainted(false); // 버튼 테두리 설정해제
        checkUp.setRolloverIcon(chUpdateIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        checkUp.setBackground(mycor);
        checkUp.setFocusPainted(false);
        checkUp.setContentAreaFilled(false);
        checkUp.setOpaque(false);
        add(checkUp);

        // 챗봇 버튼 이미지
        ImageIcon chatIcon = new ImageIcon("project__java/buspj/image/icon3.png");
        Image chatImg = chatIcon.getImage();
        Image chatUpdate = chatImg.getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon chatUpdateIcon = new ImageIcon(chatUpdate);

        ImageIcon chatIcon2 = new ImageIcon("project__java/buspj/image/blurIcon1.png");
        Image chatImg2 = chatIcon2.getImage();
        Image chatUpdate2 = chatImg2.getScaledInstance(180,180, Image.SCALE_SMOOTH);
        ImageIcon chatUpdateIcon2 = new ImageIcon(chatUpdate2);

        JButton chatBot = new JButton(chatUpdateIcon);
        chatBot.setBounds(615,60,190,190);
        chatBot.setRolloverIcon(chatUpdateIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        chatBot.setBorderPainted(false); // 버튼 테두리 설정해제
        chatBot.setBackground(mycor);
        chatBot.setFocusPainted(false);
        chatBot.setContentAreaFilled(false);
        chatBot.setOpaque(false);
        add(chatBot);

//        JButton mainButton3 = new JButton("챗봇");
//        mainButton3.setPreferredSize(new Dimension(180, 180));
//        mainButton3.setFont(new Font("굴림", Font.BOLD, 20));
//        mainButton3.setBounds(550,50,180,180);
//        add(mainButton3);

        // 메인 이미지
        ImageIcon background = new ImageIcon("project__java/buspj/image/bg.png");
        JLabel image2 = new JLabel(background);
        image2.setBounds(-1570,-150,4000,1200);
        add(image2);

        // 예매하기 클릭 시 이벤트
        reservation.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReservationMain(id);
                frame.dispose();
            }
        });
        checkUp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new ReservationCheckUp(id);
            }
        });
        chatBot.addMouseListener(new MouseAdapter() {
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