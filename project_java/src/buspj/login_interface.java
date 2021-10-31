package buspj;
import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명

class BoxPanel extends JPanel implements ActionListener {
    JTextField id = new JTextField();
    JPasswordField pw = new JPasswordField();

    public BoxPanel() {
        setLayout(null);

        JLabel idText = new JLabel("아이디");
        idText.setBounds(80,50,50,30);
        add(idText);

        id.setBounds(170,50,100,30);
        add(id);

        JLabel pwText = new JLabel("비밀번호");
        pwText.setBounds(80,100,50,30);
        add(pwText);

        pw.setBounds(170,100,100,30);
        add(pw);

        JButton check = new JButton("확인");
        check.setBounds(135, 160, 70,30);
        add(check);

        check.addActionListener(this);

        JLabel question = new JLabel("회원이 아니신가요?");
        question.setBounds(124, 205,100,20);
        question.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        add(question);

        JLabel signinPage = new JLabel("<HTML><U>회원가입</U></HTML>");
        signinPage.setBounds(148, 225, 100, 20);
        signinPage.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        add(signinPage);

        JLabel administratorLogin = new JLabel("<HTML><U>관리자 로그인</U></HTML>");
        administratorLogin.setBounds(135, 275, 100, 20);
        administratorLogin.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        add(administratorLogin);

        signinPage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new join_interface();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().equals("확인")) {
            if (id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
            }
            else if (pw.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
            }
            else if (id.getText().equals("") && pw.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.");
            }
            // 69번째 라인 else if : DB 연결하여 아이디 or 비밀번호 정보 없으면 "아이디 or 비밀번호를 잘못 입력하였습니다." 알림창 출력 예정
            else {
                JOptionPane.showMessageDialog(null, "로그인 성공");
            }
        }
    }


}

class TitlePanel extends JPanel {
    public TitlePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel title = new JLabel("로그인");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
    }
}

public class login_interface extends JFrame {
    public login_interface() {
        setTitle("로그인");
        setResizable(false);
        setBounds(0,0,350,400);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new TitlePanel(), BorderLayout.NORTH);
        mainContainer.add(new BoxPanel(), BorderLayout.CENTER);

        setVisible(true);
    }
}