import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class join_interface extends JFrame implements ActionListener {
    JTextField namet = new JTextField();
    JTextField phonet = new JTextField();
    JTextField emailt = new JTextField();
    JTextField idt = new JTextField(10);
    JPasswordField pwt = new JPasswordField();
    JPasswordField pwct = new JPasswordField();
    public join_interface(){
        JFrame fr = new JFrame();
        fr.setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫음 이벤트

        fr.setBounds(0,0,350,500);
        fr.setLayout(null);

        JLabel title = new JLabel("회원 가입");
        Font a = new Font("맑은 고딕",Font.PLAIN,20);
        title.setFont(a);
        title.setBounds(120,0,100,30);
        fr.add(title);

        JLabel name = new JLabel("이름");
        name.setBounds(50,50,100,30);
        fr.add(name);
        namet.setBounds(200,50,100,30);
        fr.add(namet);

        JLabel phone = new JLabel("전화번호");
        phone.setBounds(50,100,100,30);
        fr.add(phone);
        phonet.setBounds(200,100,100,30);
        fr.add(phonet);

        JLabel email = new JLabel("이메일");
        email.setBounds(50,150,100,30);
        fr.add(email);
        emailt.setBounds(200,150,100,30);
        fr.add(emailt);

        JLabel id = new JLabel("아이디");
        id.setBounds(50,200,100,30);
        fr.add(id);
        idt.setBounds(200,200,100,30);
        fr.add(idt);
        JButton idck = new JButton("중복 확인");
        Font f = new Font("맑은 고딕",Font.PLAIN,10);
        idck.setFont(f);
        idck.setBounds(100,200,80,30);
        fr.add(idck);

        JLabel pw = new JLabel("비밀 번호");
        pw.setBounds(50,250,100,30);
        fr.add(pw);
        pwt.setBounds(200,250,100,30);
        fr.add(pwt);

        JLabel pwc = new JLabel("비밀 번호 확인");
        pwc.setBounds(50,300,100,30);
        fr.add(pwc);
        pwct.setBounds(200,300,100,30);
        fr.add(pwct);

        JButton btn1 = new JButton("등록");
        btn1.setBounds(50,370,80,40);
        fr.add(btn1);
        JButton btn2 = new JButton("취소");
        btn2.setBounds(200,370,80,40);
        fr.add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        //setSize(300,300);
        fr.setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton) e.getSource();
        String id = idt.getText();
        String pw = pwt.getText();
        String pwc = pwct.getText();
        String email = emailt.getText();
        String phone = phonet.getText();
        String name = namet.getText();

        if (b.getText().equals("등록"))
        {
            if(id.equals("")){
                JOptionPane.showMessageDialog(null,"아이디를 입력하셔야 합니다.");
            }
            else if(pw.equals("")){
                JOptionPane.showMessageDialog(null,"비밀번호를 입력하셔야 합니다.");
            }
            else if(pwc.equals("")){
                JOptionPane.showMessageDialog(null,"비밀번호를 입력하셔야 합니다.");
            }
            else if(email.equals("")){
                JOptionPane.showMessageDialog(null,"이메일을 입력하셔야 합니다.");
            }
            else if(phone.equals("")){
                JOptionPane.showMessageDialog(null,"전화번호를 입력하셔야 합니다.");
            }
            else if(name.equals("")){
                JOptionPane.showMessageDialog(null,"이름을 입력하셔야 합니다.");
            }
            else{
                JOptionPane.showMessageDialog(null, "가입 완료");
            }
        }

    }


    public static void main(String[] args) {

        new join_interface();
    }
}
