package project_java.src;

import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명
import javax.swing.event.*;    // 스윙 이벤트 처리에 필요한 클래스들의 경로명

class BoxPanel extends JPanel {
    public BoxPanel() {

    }
}

class TitlePanel extends JPanel {
    public TitlePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel title = new JLabel("로그인");
        title.setFont(new Font("고딕", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
    }
}

public class login_interface extends JFrame {
    public login_interface() {
        setTitle("로그인");
        setBounds(0,0,350,500);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new TitlePanel(), BorderLayout.NORTH);
        mainContainer.add(new BoxPanel(), BorderLayout.CENTER);

        setVisible(true);
    }
}