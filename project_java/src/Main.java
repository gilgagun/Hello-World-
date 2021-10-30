package project_java.src;

import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명
import javax.swing.event.*;    // 스윙 이벤트 처리에 필요한 클래스들의 경로명

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
    public LoginAndSignup() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton loginButton = new JButton("로그인");
        loginButton.setPreferredSize(new Dimension(100, 50));
        loginButton.setFont(new Font("굴림", Font.BOLD, 20));
        add(loginButton);

        JButton signinButton = new JButton("회원가입");
        signinButton.setPreferredSize(new Dimension(120, 50));
        signinButton.setFont(new Font("굴림", Font.BOLD, 20));
        add(signinButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login_interface();
            }
        });

        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new join_interface();
            }
        });
    }
}

class NorthPanel extends JPanel {
    public NorthPanel() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new LoginAndSignup(), BorderLayout.EAST);
    }
}

class CenterPanel extends JPanel {
    public CenterPanel() {
        setBackground(Color.WHITE);

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
    }
}

public class Main extends JFrame{
    public Main() {
        setTitle("버스 예약 시스템(가제)");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new NorthPanel(), BorderLayout.NORTH);
        mainContainer.add(new CenterPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}