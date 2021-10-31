/*
import java.sql.*;
// 삭제 할거
public class sing_dbs {
    public sing_dbs(Member new_member) {
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
            String phone=new_member.get_email();
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
}
*/