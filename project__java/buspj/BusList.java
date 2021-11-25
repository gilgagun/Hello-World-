package buspj;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

class AddBus extends JFrame {
    DB_connect DB = new DB_connect();

    public AddBus() {
        setTitle("추가");
        setLayout(null);
        setSize(300,200);
        setDefaultCloseOperation(0);
        Container c = getContentPane();

        JLabel ment = new JLabel("출발 터미널");
        ment.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        ment.setBounds(100,90,100,20);
        c.add(ment);

        JTextField start = new JTextField();
        start.setBounds(100,110,100,20);
        c.add(start);

        JLabel ment2 = new JLabel("도착 터미널");
        ment2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        ment2.setBounds(100,130,100,20);
        c.add(ment2);

        JTextField end = new JTextField();
        end.setBounds(100,150,100,20);
        c.add(end);

        JButton add = new JButton("추가");
        add.setBounds(100,180,50,10);
        c.add(add);
        add.addMouseListener(new MouseAdapter() {
            public void mouseCliked(MouseEvent e) {
                DB.save_bus(start.getText(), end.getText());
            }
        });

        JButton cancel = new JButton("취소");
        cancel.setBounds(160,180,50,10);
        c.add(cancel);
        cancel.addMouseListener(new MouseAdapter() {
            public void mouseCliked(MouseEvent e) {
                AddBus.this.dispose();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                AddBus.this.dispose();
            }
        });

        setVisible(true);
    }
}

public class BusList extends JFrame {
    private static final long serialVersionUID = 1L;
    private Vector data = null;
    private Vector result = null;
    private Vector title = null;
    private JTable table = null;
    private DefaultTableModel model = null;
    private JButton btnAdd = null;
    private JButton btnDel = null;
    private JButton btnUpdate = null;
    private JButton btnClear = null;
    private JTextField tfNum = null;
    private JTextField tfName = null;
    private JTextField tfName2 = null;
    private JTextField tfAddress = null;
    private JTextField tfphone = null;
    private JLabel lblNum = null;
    private JLabel lblName = null;
    private JLabel lblName2 = null;
    private JLabel lblAddress = null;
    private JLabel phone = null;
    private String start = null;
    private String end = null;
    private String Url = "jdbc:mysql://localhost:3306/bus";
    private String user = "hr";
    private String password = "hr";
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmtAdd = null;
    private PreparedStatement pstmtDel = null;
    private PreparedStatement pstmtUpdate = null;

    public BusList(final String user2) {
        super("운행정보 관리");
        this.setLocation(470,200);  // 프레임을 위치 설정
        this.user = user2;
        this.setDefaultCloseOperation(0);
        this.preDbTreatment();
        this.data = new Vector();
        this.title = new Vector();
        this.title.add("출발 터미널");
        this.title.add("도착 터미널");
        this.model = new DefaultTableModel();
        result = this.selectAll();
        this.model.setDataVector(result, this.title);
        this.table = new JTable(this.model);
        JScrollPane sp = new JScrollPane(this.table);
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = BusList.this.table.getSelectedRow();
                Vector in = (Vector)BusList.this.data.get(index);
                start = (String)in.get(0);
                end = (String)in.get(1);
                BusList.this.tfName.setText(start);
                BusList.this.tfName2.setText(end);
            }
        });
        JPanel panel = new JPanel();
        this.tfName = new JTextField(10);
        this.tfName2 = new JTextField(10);
        this.lblName = new JLabel("출발 터미널");
        this.lblName2 = new JLabel("도착 터미널");
        this.btnAdd = new JButton("추가");
        this.btnDel = new JButton("삭제");
        this.btnUpdate = new JButton("닫기");
        this.btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBus();
                result = BusList.this.selectAll();
                BusList.this.model.setDataVector(result, BusList.this.title);
            }
        });
        this.btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = BusList.this.tfName.getText();
                end = BusList.this.tfName2.getText();
                System.out.println(start);
                System.out.println(end);
                BusList.this.delete(start, end);
                Vector result = BusList.this.selectAll();
                BusList.this.model.setDataVector(result, BusList.this.title);
            }
        });
        this.btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BusList.this.dispose();
            }
        });
        panel.add(this.lblName);
        panel.add(this.tfName);
        panel.add(this.lblName2);
        panel.add(this.tfName2);
        panel.add(this.btnAdd);
        panel.add(this.btnDel);
        panel.add(this.btnUpdate);
        Container c = this.getContentPane();
        c.add(new JLabel("운행정보 리스트", 0), "North");
        c.add(sp, "Center");
        c.add(panel, "South");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                try {
                    BusList.this.stmt.close();
                    BusList.this.conn.close();
                    BusList.this.setVisible(false);
                    BusList.this.dispose();
                } catch (Exception var3) {
                }

            }
        });
        this.setSize(600, 400);
        this.setVisible(true);
    }

    private Vector selectAll() {
        this.data.clear();

        try {
            String sql = "select distinct start,end from bus_table";
            System.out.println(sql);
            ResultSet rs = this.stmt.executeQuery(sql);

            while(rs.next()) {
                Vector in = new Vector();
                String start = rs.getString(1);
                String end = rs.getString(2);
                in.add(start);
                in.add(end);
                this.data.add(in);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        return this.data;
    }

    private void delete(String start, String end) {
        try {
            this.pstmtDel = this.conn.prepareStatement("delete from bus_table where start = '" + start + "' and end = '" + end + "'");
            this.pstmtDel.executeUpdate();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    private void preDbTreatment() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "1234");
            System.out.println("DB 연결 완료");
            this.stmt = this.conn.createStatement();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
