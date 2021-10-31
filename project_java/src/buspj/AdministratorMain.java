package buspj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명

class AdministratorTitle extends JPanel {
    public AdministratorTitle() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 16));

        JLabel title = new JLabel("마법의 성이라는 노래를 아십니까? 안다면 당신은 옛날 사람.");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        add(title);
    }
}

class AdministratorLoginAndSignup extends JPanel {
    public AdministratorLoginAndSignup() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setPreferredSize(new Dimension(120, 50));
        logoutButton.setFont(new Font("굴림", Font.BOLD, 20));
        add(logoutButton);

        // 로그아웃 버튼 클릭 시 이벤트
        logoutButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?","로그아웃",JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION)
                    new login_interface();
            }
        });
    }
}

class AdministratorNorthPanel extends JPanel {
    public AdministratorNorthPanel() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new BorderLayout());
        add(new AdministratorTitle(), BorderLayout.WEST);
        add(new AdministratorLoginAndSignup(), BorderLayout.EAST);
    }
}

class AdministratorCenterPanel extends JPanel {
    public AdministratorCenterPanel() {
        setBackground(Color.WHITE);

        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 250));

        JButton mainButton1 = new JButton("회원관리");
        mainButton1.setPreferredSize(new Dimension(180, 180));
        mainButton1.setFont(new Font("굴림", Font.BOLD, 20));
        add(mainButton1);

        JButton mainButton2 = new JButton("운행정보 관리");
        mainButton2.setPreferredSize(new Dimension(180, 180));
        mainButton2.setFont(new Font("굴림", Font.BOLD, 20));
        add(mainButton2);
    }
}

public class AdministratorMain extends JFrame {
    public AdministratorMain() {
        setTitle("버스 예약 시스템(가제)");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new AdministratorNorthPanel(), BorderLayout.NORTH);
        mainContainer.add(new AdministratorCenterPanel(), BorderLayout.CENTER);

        setVisible(true);
    }
}