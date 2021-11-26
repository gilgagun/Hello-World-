package buspj;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Statement;

public class DB_connect {
    Connection conn;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    // 수정할것
    public  DB_connect(){
        try {
            String dbURL = "jdbc:mysql://localhost:3306/bus"; // .
            String dbID = "root"; //
            String dbPassword = "1234"; //
            Class.forName("com.mysql.cj.jdbc.Driver"); //
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword); //
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    //회원정보 db 등록
    public void sing_db(Member new_member) {
        Connection conn;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:" +
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
    // 로그인 db
    public int login(String idt, String pwt){
        ResultSet rs = null;
        String SQL = "SELECT pw FROM new_table WHERE id = ?"; // 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql:" +
                            "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");
            String root = "root";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, idt);
            rs = pstmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
            if (rs.next()) {
                System.out.println("비밀번호 "+rs.getString(1));
                if (rs.getString(1).contentEquals(pwt)) {
                    if(idt.equals(root)){
                        return 2; // 관리자 로그인
                    }
                    else {
                        return 1; // 로그인 성공
                    }
                }
                else {
                    return 0; // 비밀번호 불일치
                }
            }
            return -1; // 아이디가 없음
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2; // DB 오류
    }

    //출발지 db
    public String[] start() {
        Connection conn;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            //ArrayList<String> bus = new ArrayList<String>();
            String []add = new String[100];
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:" +
                    "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");

            String sql = "select distinct start from bus_table";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int i =0;
            while (rs.next()){
                add[i]=rs.getString(1);
                i++;
            }
            /*
            Iterator<String> it = bus.iterator();
            for (int h=0; h<i; h++){
                String w=it.next();
                System.out.println(w);
            }*/
            pstmt.close();
            return add;

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
        }
        return null;
    }

    //도착지 db
    public String[] end() {
        Connection conn;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            //ArrayList<String> buss = new ArrayList<String>();
            String[] add = new String[100];
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:" +
                    "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");

            String sql = "select distinct end from bus_table";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                add[i] = rs.getString(1);
                i++;
            }
            /*
            Iterator<String> it = buss.iterator();
            for (int h=0; h<i; h++){
                String w=it.next();
                System.out.println(w);
            }*/
            pstmt.close();
            return add;

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
        }
        return null;
    }

    // 표 DB
    public Ticket ticket_load(String start, String end, String date) {
        Connection conn;
        ResultSet rs = null;
        Ticket t = new Ticket();
        String SQL = "SELECT starttime, company, class, seats, price FROM bus_table WHERE start = '" + start + "' and end = '" + end + "' and date ='" + date + "'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql:" +
                            "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");
            pstmt = conn.prepareStatement(SQL);
//            pstmt.setString(0, start);
//            pstmt.setString(0, end);
            rs = pstmt.executeQuery(SQL);

            while (rs.next()) {
                String starttime = rs.getString(1);
                String company = rs.getString(2);
                String class_ = rs.getString(3);
                int seats = rs.getInt(4);
                int price = rs.getInt(5);

                t.insertTicket(starttime, company, class_, seats, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public int login_out(String idt){
        ResultSet rs = null;
        String SQL = "DELETE FROM new_table WHERE id = ?";// 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql:" +
                            "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, idt);
            int r = pstmt.executeUpdate(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
            // r=1이면 수행완료
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2; // DB 오류
    }

    // 회원 예매 정보 DB에 저장
    public void saveUserReservation(String id, String start, String end, String date, String starttime, String company, String class_, String price) {
        Connection conn;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:" +
                    "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");

            String sql="insert into reservation_user(id,start,end,date,starttime,company,class,price)";
            sql += "values (?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            String saveId = id;
            String saveStart = start;
            String saveEnd = end;
            String saveDate = date;
            String saveStarttime = starttime;
            String saveCompany = company;
            String saveClass = class_;
            String savePrice = String.valueOf(price);

            pstmt.setString(1, saveId);
            pstmt.setString(2, saveStart);
            pstmt.setString(3, saveEnd);
            pstmt.setString(4, saveDate);
            pstmt.setString(5, saveStarttime);
            pstmt.setString(6, saveCompany);
            pstmt.setString(7, saveClass);
            pstmt.setString(8, savePrice);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
        }
    }

    // 회원 예매 정보 불러오기
    public CheckUp loadUserReservation(String id) {
        Connection conn;
        ResultSet rs = null;
        CheckUp c = new CheckUp();
        String SQL = "SELECT date,start,end,starttime,company,class,price FROM reservation_user where id = '" + id + "'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql:" +
                            "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery(SQL);

            while (rs.next()) {
                String date = rs.getString(1);
                String start = rs.getString(2);
                String end = rs.getString(3);
                String starttime = rs.getString(4);
                String company = rs.getString(5);
                String class_ = rs.getString(6);
                String price = rs.getString(7);

                c.insertData(date,start,end,starttime, company, class_, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // 예매취소 버튼 클릭 시
    public void delete_userReservation(String id) {
        PreparedStatement pstmtDel = null;
        try {
            pstmtDel = this.conn.prepareStatement("delete from reservation_user where id = '" + id + "'");
            pstmtDel.executeUpdate();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    // 관리자 : 운행정보 저장
    public void save_bus(String start, String end) {
        Connection conn;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:" +
                    "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");

            String sql="insert into bus_info(start,end)";
            sql += "values (?,?)";
            pstmt = conn.prepareStatement(sql);

            String saveStart = start;
            String saveEnd = end;

            pstmt.setString(1, saveStart);
            pstmt.setString(2, saveEnd);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
        }
    }

    // 마일리지
    public String mileage(String id) {
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String usermileage = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:" +
                    "//localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");

            String sql="select point from new_table where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usermileage=rs.getString(1);
            }
            pstmt.executeUpdate();
            pstmt.close();
            return usermileage;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 에러");
        }
        return usermileage;
    }

    // 좌석 클릭 시 불러올 DB
//    public int seat_check() {
//
//    }
}
