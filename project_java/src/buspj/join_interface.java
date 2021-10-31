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
    public Member(String id, String pw, String email, String name, String phone){
        this.id=id;
        this.pw=pw;
        this.email=email;
        this.name=name;
        this.phone=phone;
    }
    public String get_id(){
        return id;
    }
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
}
// 회원 가입 인터페이스
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
        fr.setResizable(false);
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
        //setSize(300,300);
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
        Member new_member = new Member(id,pw,email,name,phone);

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
            else if(pw.equals(pwc)){
                JOptionPane.showMessageDialog(null,"비밀번호가 다릅니다.");
            }
            else{
                sing_db(new_member);
                JOptionPane.showMessageDialog(null, "가입 완료");
            }
        }
        // 아이디 중복확인 이벤트 처리
        else if(b.getText().equals("중복 확인")) {
            int check = idCheck(new_member);
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

    }
    //회원정보 db 등록
    public void sing_db(Member new_member) {
        Connection conn;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql:" +
                            "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");

            String sql="insert into new_table(id,pw,email,name,phone)";
            sql+= "values (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            String id=new_member.get_id();
            String pw=new_member.get_pw();
            String email=new_member.get_email();
            String name=new_member.get_name();
            String phone=new_member.get_phone();
            System.out.println(id+pw+email+name+phone);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, email);
            pstmt.setString(4, name);
            pstmt.setString(5, phone);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
        }
    }
    //id 중복체크 이벤트
    public int idCheck(Member new_member) {
        Connection conn;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = new_member.get_id();
        int value=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql:" +
                            "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");
            String sql = "select id from new_table where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,  id);
            rs = pstmt.executeQuery();

            if(rs.next()) value = 1;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    public static void main(String[] args) {
        new join_interface();
    }
}
