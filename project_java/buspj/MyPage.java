package buspj;
import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명

class Back extends JPanel {
    public Back() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton back = new JButton("뒤로가기");
        back.setPreferredSize(new Dimension(120, 50));
        back.setFont(new Font("고딕", Font.BOLD, 20));
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Main();
            }
        });
    }
}

class MyNorthPanel extends JPanel {
    public MyNorthPanel() {
        setBackground(Color.LIGHT_GRAY);

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new Back(), BorderLayout.EAST);
    }
}

class DrawLine extends JPanel

class MyCenterPanel extends JPanel {
    DB_connect DB = new DB_connect();

    public MyCenterPanel() {
        setLayout(null);

        // 프로필 이미지 삽입
        ImageIcon profile = new ImageIcon("C:\\Users\\DILAB\\profile.png");
        Image img = profile.getImage();
        Image updateImg = img.getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel image = new JLabel(updateIcon);
        image.setBounds(80,100,100,100);
        image.setHorizontalAlignment(JLabel.CENTER);
        add(image);

        // 환영 문장 삽입

        setVisible(true);
    }
}

public class MyPage extends JFrame {
    public MyPage() {
        setTitle("버스 예약 시스템(가제)");
        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new MyNorthPanel(), BorderLayout.NORTH);
        mainContainer.add(new MyCenterPanel(), BorderLayout.CENTER);

        setVisible(true);
    }
}
