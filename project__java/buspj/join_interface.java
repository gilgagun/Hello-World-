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
public class join_interface extends JFrame {
    JTextField namet = new JTextField();
    JTextField phonet = new JTextField();
    JTextField emailt = new JTextField();
    JTextField idt = new JTextField(10);
    JPasswordField pwt = new JPasswordField();
    JPasswordField pwct = new JPasswordField();
    JFrame fr = new JFrame();
    String id = idt.getText();
    String pw = pwt.getText();
    String pwc = pwct.getText();
    String email = emailt.getText();
    String phone = phonet.getText();
    String name = namet.getText();
    int point = 0;
    Member new_member = new Member(id, pw, email, name, phone, point);
    DB_connect DB = new DB_connect(); // DB 클래스 불러오기

    public join_interface() {
        fr.setTitle("회원가입");
        fr.setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 닫음 이벤트

        fr.setSize(350, 500);
        fr.setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        fr.setLayout(null);

        JLabel title = new JLabel("회원 가입");
        Font a = new Font("휴먼엑스포", Font.PLAIN, 20);
        title.setFont(a);
        title.setBounds(120, 0, 100, 30);
        fr.add(title);

        JLabel name = new JLabel("이름");
        name.setBounds(50, 50, 100, 30);
        fr.add(name);
        namet.setBounds(200, 50, 100, 30);
        fr.add(namet);

        JLabel phone = new JLabel("전화번호");
        phone.setBounds(50, 100, 100, 30);
        fr.add(phone);
        phonet.setBounds(200, 100, 100, 30);
        fr.add(phonet);

        JLabel email = new JLabel("이메일");
        email.setBounds(50, 150, 100, 30);
        fr.add(email);
        emailt.setBounds(200, 150, 100, 30);
        fr.add(emailt);

        JLabel id = new JLabel("아이디");
        id.setBounds(50, 200, 100, 30);
        fr.add(id);
        idt.setBounds(200, 200, 100, 30);
        fr.add(idt);


        ImageIcon overlap = new ImageIcon("project__java/buspj/image/overlap1.png");
        Image overlap11 = overlap.getImage();
        Image reoverlap = overlap11.getScaledInstance(80, 30, Image.SCALE_SMOOTH);
        ImageIcon overlapIcon1 = new ImageIcon(reoverlap);
        ImageIcon overlap2 = new ImageIcon("project__java/buspj/image/overlap2.png");
        Image overlap22 = overlap2.getImage();
        Image reoverlap2 = overlap22.getScaledInstance(80, 30, Image.SCALE_SMOOTH);
        ImageIcon overlapIcon2 = new ImageIcon(reoverlap2);
        JButton idck = new JButton(overlapIcon1);
        idck.setBorderPainted(false); // 버튼 테두리 설정해제
        idck.setRolloverIcon(overlapIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        idck.setFocusPainted(false);
        idck.setContentAreaFilled(false);
        idck.setOpaque(false);
        idck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = DB.idCheck(new_member);
                String id = idt.getText();
                if (id.equals("")) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력하셔야 합니다.");
                } else if (check == 0) {
                    JOptionPane.showMessageDialog(null, "사용 가능");
                } else {
                    JOptionPane.showMessageDialog(null, "사용중인 아이디");
                }
            }
        });
        //JButton idck = new JButton("중복 확인");
        //idck.addActionListener(this);
        //Font f = new Font("맑은 고딕",Font.PLAIN,10);
        //idck.setFont(f);
        idck.setBounds(100, 200, 80, 30);
        fr.add(idck);

        JLabel pw = new JLabel("비밀 번호");
        pw.setBounds(50, 250, 100, 30);
        fr.add(pw);
        pwt.setBounds(200, 250, 100, 30);
        fr.add(pwt);

        JLabel pwc = new JLabel("비밀 번호 확인");
        pwc.setBounds(50, 300, 100, 30);
        fr.add(pwc);
        pwct.setBounds(200, 300, 100, 30);
        fr.add(pwct);


        ImageIcon my = new ImageIcon("project__java/buspj/image/registered1.png");
        Image my1 = my.getImage();
        Image remy = my1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon1 = new ImageIcon(remy);
        ImageIcon my2 = new ImageIcon("project__java/buspj/image/registered2.png");
        Image my11 = my2.getImage();
        Image remy1 = my11.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        ImageIcon reUpdateIcon2 = new ImageIcon(remy1);
        //JButton card = new JButton(reUpdateIcon1);
        JButton btn1 = new JButton(reUpdateIcon1);
        btn1.setBorderPainted(false); // 버튼 테두리 설정해제
        btn1.setRolloverIcon(reUpdateIcon2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btn1.setFocusPainted(false);
        btn1.setContentAreaFilled(false);
        btn1.setOpaque(false);
        btn1.setBounds(50, 370, 80, 40);
        fr.add(btn1);


        ImageIcon close1 = new ImageIcon("project__java/buspj/image/close1.png");
        Image close = close1.getImage();
        Image reclose = close.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        ImageIcon Iconclose = new ImageIcon(reclose);
        ImageIcon close2 = new ImageIcon("project__java/buspj/image/close2.png");
        Image acclose2 = close2.getImage();
        Image reclose2 = acclose2.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        ImageIcon Iconclose2 = new ImageIcon(reclose2);
        JButton btn2 = new JButton(Iconclose);
        btn2.setBorderPainted(false); // 버튼 테두리 설정해제
        btn2.setRolloverIcon(Iconclose2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btn2.setFocusPainted(false);
        btn2.setContentAreaFilled(false);
        btn2.setOpaque(false);
        btn2.setBounds(50, 370, 80, 40);
        btn2.setBounds(200, 370, 80, 40);
        fr.add(btn2);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                String id = idt.getText();
                String pw = pwt.getText();
                String pwc = pwct.getText();
                String email = emailt.getText();
                String phone = phonet.getText();
                String name = namet.getText();
                int point = 0;
                Member new_member = new Member(id, pw, email, name, phone, point);
                DB_connect DB = new DB_connect(); // DB 클래스 불러오기
                //등록 이벤트 처리
                if (id.equals("")) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력하셔야 합니다.");
                } else if (pw.equals("")) {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력하셔야 합니다.");
                } else if (pwc.equals("")) {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력하셔야 합니다.");
                } else if (email.equals("")) {
                    JOptionPane.showMessageDialog(null, "이메일을 입력하셔야 합니다.");
                } else if (phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "전화번호를 입력하셔야 합니다.");
                } else if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "이름을 입력하셔야 합니다.");
                } else if (!(pw.equals(pwc))) {
                    JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다.");
                } else {
                    DB.sing_db(new_member);
                    JOptionPane.showMessageDialog(null, "가입 완료");
                    new login_interface();
                    fr.dispose();
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login_interface();  // 회원가입 창 닫고 로그인 창 띄우기
                fr.dispose();
            }
        });
        //btn1.addActionListener(this);
        //btn2.addActionListener(this);
        setSize(300, 300);

        fr.addWindowListener(new JFrameWindowClosingEventHandler());
        fr.setVisible(true);

    }
}
/*
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
}*/
