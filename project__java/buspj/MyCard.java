
package buspj;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MyCard extends JFrame {
    private static final long serialVersionUID = 1L;
    private Vector data = null;
    private Vector title = null;
    private JTable table = null;
    private DefaultTableModel model = null;
    private JButton btnAdd = null;
    private JButton btnDel = null;
    private JButton btnUpdate = null;
    private JButton btnClear = null;
    private JTextField tfNum = null;
    private JTextField tfName = null;
    private JTextField tfAddress = null;
    private JLabel lblNum = null;
    private JLabel lblName = null;
    private JLabel lblAddress = null;
    private String Url = "jdbc:mysql://localhost:3306/bus";
    private String user = "hr";
    private String password = "hr";
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmtAdd = null;
    private PreparedStatement pstmtDel = null;
    private PreparedStatement pstmtUpdate = null;

    public MyCard(final String user) {
        super("카드 관리");
        this.user = user;
        this.setDefaultCloseOperation(0);
        this.preDbTreatment();
        this.data = new Vector();
        this.title = new Vector();
        this.title.add("카드사");
        this.title.add("카드번호");
        this.model = new DefaultTableModel();
        Vector result = this.selectAll();
        this.model.setDataVector(result, this.title);
        this.table = new JTable(this.model);
        JScrollPane sp = new JScrollPane(this.table);
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = MyCard.this.table.getSelectedRow();
                Vector in = (Vector)MyCard.this.data.get(index);
                String bank = (String)in.get(0);
                String cardnum = (String)in.get(1);
                MyCard.this.tfNum.setText(bank);
                MyCard.this.tfName.setText(cardnum);
                MyCard.this.tfAddress.setText("");
            }
        });
        JPanel panel = new JPanel();
        this.tfNum = new JTextField(8);
        this.tfName = new JTextField(20);
        this.tfAddress = new JTextField(5);
        this.lblNum = new JLabel("카드사");
        this.lblName = new JLabel("카드번호");
        this.lblAddress = new JLabel("비밀번호");
        this.btnAdd = new JButton("추가");
        this.btnDel = new JButton("삭제");
        this.btnUpdate = new JButton("닫기");
        this.btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bank = MyCard.this.tfNum.getText();
                String cardnum = MyCard.this.tfName.getText();
                String pw = MyCard.this.tfAddress.getText();
                MyCard.this.insert(user, bank, cardnum, pw);
                Vector result = MyCard.this.selectAll();
                MyCard.this.model.setDataVector(result, MyCard.this.title);
            }
        });
        this.btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String card = MyCard.this.tfName.getText();
                System.out.println(card);
                MyCard.this.delete(card);
                Vector result = MyCard.this.selectAll();
                MyCard.this.model.setDataVector(result, MyCard.this.title);
            }
        });
        this.btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyCard.this.dispose();
            }
        });
        panel.add(this.lblNum);
        panel.add(this.tfNum);
        panel.add(this.lblName);
        panel.add(this.tfName);
        panel.add(this.lblAddress);
        panel.add(this.tfAddress);
        panel.add(this.btnAdd);
        panel.add(this.btnDel);
        panel.add(this.btnUpdate);
        Container c = this.getContentPane();
        c.add(new JLabel("나의 카드 리스트", 0), "North");
        c.add(sp, "Center");
        c.add(panel, "South");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                try {
                    MyCard.this.stmt.close();
                    MyCard.this.conn.close();
                    MyCard.this.setVisible(false);
                    MyCard.this.dispose();
                    System.exit(0);
                } catch (Exception var3) {
                }

            }
        });
        this.setSize(800, 400);
        this.setVisible(true);
    }

    private Vector selectAll() {
        this.data.clear();

        try {
            String sql = "select bank, cardnum from member  where id=" + this.user;
            System.out.println(sql);
            ResultSet rs = this.stmt.executeQuery(sql);

            while(rs.next()) {
                Vector in = new Vector();
                String bank = rs.getString(1);
                String cardnum = rs.getString(2);
                in.add(bank);
                in.add(cardnum);
                this.data.add(in);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return this.data;
    }

    private void insert(String user, String bank, String cardnum, String pw) {
        try {
            System.out.println(user + bank + cardnum + pw);
            this.pstmtAdd = this.conn.prepareStatement("insert into member values(?,?,?,?)");
            this.pstmtAdd.setString(1, user);
            this.pstmtAdd.setString(2, bank);
            this.pstmtAdd.setString(3, cardnum);
            this.pstmtAdd.setString(4, pw);
            this.pstmtAdd.executeUpdate();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    private void delete(String card) {
        try {
            this.pstmtDel = this.conn.prepareStatement("delete from member where cardnum = ?");
            this.pstmtDel.setString(1, card);
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