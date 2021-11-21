package buspj;
import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명

class MyDialog extends JDialog{
    JLabel comment = new JLabel();
    JTextField pw = new JTextField(10);
    JButton ok = new JButton("ok");
    public MyDialog(MyPage frame, String id){
        comment = new JLabel("비밀번호를 입력해주세요");
        add(comment);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(pw);
        add(ok);
        setSize(200,100);
        ok.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String user_pw = pw.getText();
                DB_connect db = new DB_connect();
                int n = db.login(id, user_pw);
                if (n==1){
                    int key = db.login_out(id);
                    if (key==1){
                        JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                        setVisible(false);
                        new login_interface();
                        //JFrame frames = (JFrame)e.getSource();

                        frame.dispose();  // 화면이 안 닫히는 에러.
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "db오류.");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "비밀번호 미일치");
                }
            }
        });
    }
}
class Back extends JPanel {
    MyPage frame;
    public Back(MyPage frame, String id) {
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

class MyNorthPanel extends JPanel {
    MyPage frame;

    public MyNorthPanel(MyPage frame, String id) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new Back(this.frame, id), BorderLayout.EAST);
    }
}

class MyCenterPanel extends JPanel {
    DB_connect DB = new DB_connect(); // 이건 그냥 해놓은 것.. DB 공부해올게요..
    //JTextField hi = new JTextField(); // 환영합니다 네모 칸
    JLabel hi = new JLabel();
    //login_interface idck = new login_interface();
    JTextField score = new JTextField(); // 마일리지 네모 칸
    MyDialog dialog;
    MyPage frame;
    public MyCenterPanel(MyPage frame, String id) {
        setLayout(null);
        this.frame = frame;
        hi = new JLabel(id + "님 환영합니다!");
        // 프로필 이미지 삽입
        ImageIcon profile = new ImageIcon("project__java/buspj/image/profile.png");
        Image img = profile.getImage();
        Image updateImg = img.getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        JLabel image = new JLabel(updateIcon);
        image.setBounds(80,100,100,100);
        image.setHorizontalAlignment(JLabel.CENTER);
        add(image);

        // 구분 선 삽입
        ImageIcon line = new ImageIcon("project__java/buspj/image/line.png");
        Image img2 = line.getImage();
        Image updateImg2 = img2.getScaledInstance(350,50,Image.SCALE_DEFAULT);
        ImageIcon updateIcon2 = new ImageIcon(updateImg2);

        JLabel image2 = new JLabel(updateIcon2);
        image2.setBounds(90,160,350,100);
        image2.setHorizontalAlignment(JLabel.CENTER);
        add(image2);

        // 마일리지 내역 삽입
        JLabel mileage = new JLabel("마일리지 내역");
        mileage.setBounds(80,210,200,100);
        mileage.setHorizontalAlignment(JLabel.CENTER);
        mileage.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        add(mileage);

        // 마일리지 네모 칸
        score.setBounds(290,240,100,50);
        add(score);

        // 환영 메시지 삽입
        hi.setBounds(190,130,200,50);
        hi.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(hi);

        // 세로 선 삽입
        ImageIcon line2 = new ImageIcon("project__java/buspj/image/line2.png");
        Image img3 = line2.getImage();
        Image updateImg3 = img3.getScaledInstance(50,600,Image.SCALE_DEFAULT);
        ImageIcon updateIcon3 = new ImageIcon(updateImg3);

        JLabel image3 = new JLabel(updateIcon3);
        image3.setBounds(470,40,50,600);
        image3.setHorizontalAlignment(JLabel.CENTER);
        add(image3);

        // 결재내역
        JButton paymentHistory = new JButton("결재내역");
        paymentHistory.setBounds(540, 80, 400, 100);
        paymentHistory.setFont(new Font("고딕", Font.BOLD, 30));
        add(paymentHistory);

        // 카드관리
        JButton card = new JButton("카드관리");
        card.setBounds(540, 270, 400, 100);
        card.setFont(new Font("고딕", Font.BOLD, 30));
        add(card);

        // 회원탈퇴
        JButton membershipBye = new JButton("회원탈퇴");
        membershipBye.setBounds(540, 460, 400, 100);
        membershipBye.setFont(new Font("고딕", Font.BOLD, 30));
        add(membershipBye);
        dialog = new MyDialog(this.frame, id);
        membershipBye.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "탈퇴 하시겠습니까?","회원탈퇴",JOptionPane.YES_NO_OPTION);
                if(answer==0){
                    dialog.setVisible(true);
                    //new login_interface();
                    //frame.dispose();
                }
            }
        });
        setVisible(true);
    }
}
public class MyPage extends JFrame {
    public MyPage(String id) {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new MyNorthPanel(this, id), BorderLayout.NORTH);
        mainContainer.add(new MyCenterPanel(this, id), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }


}
