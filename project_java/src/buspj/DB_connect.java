package buspj;

import java.sql.*;

public class DB_connect {
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

}
