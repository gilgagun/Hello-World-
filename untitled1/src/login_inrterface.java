import java.awt.*;
import javax.swing.*;

public class login_inrterface extends JFrame {


    public login_inrterface(){
        JFrame fr = new JFrame();
        fr.setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫음 이벤트
        /*
        GridLayout grid = new GridLayout(4, 4);
        grid.setVgap(5); // 격자 수직 간격

        Container c = getContentPane();
        c.setLayout(null);
        c.add(new JLabel("이름"));
        c.add(new JTextField(""));
        c.add(new JLabel("이름1"));
        c.add(new JTextField(""));
        c.add(new JLabel("이름2"));
       // c.add(new JTextField(""), BorderLayout.CENTER);
       // c.add(new JButton("다음"), BorderLayout.CENTER);*/

        fr.setBounds(0,0,350,500);
        fr.setLayout(null);

        JLabel name = new JLabel("이름");
        name.setBounds(50,50,100,30);
        fr.add(name);
        JTextField namet = new JTextField();
        namet.setBounds(200,50,100,30);
        fr.add(namet);

        JLabel phone = new JLabel("전화번호");
        phone.setBounds(50,100,100,30);
        fr.add(phone);
        JTextField phonet = new JTextField();
        phonet.setBounds(200,100,100,30);
        fr.add(phonet);

        JLabel email = new JLabel("이메일");
        email.setBounds(50,150,100,30);
        fr.add(email);
        JTextField emailt = new JTextField();
        emailt.setBounds(200,150,100,30);
        fr.add(emailt);

        JLabel id = new JLabel("아이디");
        id.setBounds(50,200,100,30);
        fr.add(id);
        JTextField idt = new JTextField();
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
        JPasswordField pwt = new JPasswordField();
        pwt.setBounds(200,250,100,30);
        fr.add(pwt);

        JLabel pwc = new JLabel("비밀 번호 확인");
        pwc.setBounds(50,300,100,30);
        fr.add(pwc);
        JPasswordField pwct = new JPasswordField();
        pwct.setBounds(200,300,100,30);
        fr.add(pwct);

        JButton bt1 = new JButton("등록");
        bt1.setBounds(50,370,80,40);
        fr.add(bt1);
        JButton bt2 = new JButton("취소");
        bt2.setBounds(200,370,80,40);
        fr.add(bt2);

        //setSize(300,300);
        fr.setVisible(true);



    }
    public static void main(String[] args) {

        new login_inrterface();
    }
}
