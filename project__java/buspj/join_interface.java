package buspj;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


class Member{ //회원 가입 db연결 클래스
    String id;
    String pw;
    String email;
    String name;
    String phone;
    int point;
    public Member() {}
    public Member(String id, String pw, String email, String name, String phone, int point){
        this.id=id;
        this.pw=pw;
        this.email=email;
        this.name=name;
        this.phone=phone;
        this.point = point;
    }
    public String get_id(){ return id; }
    public String get_pw(){
        return pw;
    }
    public String get_email(){
        return email;
    }
    public String get_name(){
        return name;
    }
    public String get_phone(){
        return phone;
    }
    public int get_point() { return point; }
    public void set_point(int point) { this.point = point; }
}
// 회원 가입 인터페이스
public class join_interface extends JFrame implements ActionListener {
    JTextField namet = new JTextField();
    JTextField phonet = new JTextField();
    JTextField emailt = new JTextField();
    JTextField idt = new JTextField(10);
    JPasswordField pwt = new JPasswordField();
    JPasswordField pwct = new JPasswordField();
    JFrame fr = new JFrame();
    public join_interface(){
        fr.setTitle("회원가입");
        fr.setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 닫음 이벤트

        fr.setSize(350,500);
        fr.setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
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
        idck.addActionListener(this);
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
        setSize(300,300);

        fr.addWindowListener(new JFrameWindowClosingEventHandler());
        fr.setVisible(true);

    }
    //이벤트 처리
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton) e.getSource();
        String id = idt.getText();
        String pw = pwt.getText();
        String pwc = pwct.getText();
        String email = emailt.getText();
        String phone = phonet.getText();
        String name = namet.getText();
        int point = 0;
        Member new_member = new Member(id,pw,email,name,phone,point);
        DB_connect DB = new DB_connect(); // DB 클래스 불러오기
        //등록 이벤트 처리
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
            else if(!(pw.equals(pwc))){
                JOptionPane.showMessageDialog(null,"비밀번호가 다릅니다.");
            }
            else{
                DB.sing_db(new_member);
                JOptionPane.showMessageDialog(null, "가입 완료");
                new login_interface();
                fr.dispose();
            }
        }
        // 아이디 중복확인 이벤트 처리
        else if(b.getText().equals("중복 확인")) {
            int check = DB.idCheck(new_member);
            if(id.equals("")){
                JOptionPane.showMessageDialog(null,"아이디를 입력하셔야 합니다.");
            }
            else if (check == 0){
                JOptionPane.showMessageDialog(null, "사용 가능");
            }
            else{
                JOptionPane.showMessageDialog(null, "사용중인 아이디");
            }
        }
        else{ //취소
            new login_interface();  // 회원가입 창 닫고 로그인 창 띄우기
            fr.dispose();
        }
    }
}
