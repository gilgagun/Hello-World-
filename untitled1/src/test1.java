import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class test1 extends JFrame {


    JLabel lblTitle; // 타이틀
    JLabel lblName; // 이름
    JLabel lblNumber; // 번호
    JTextField tfName; // 이름 입력창
    JTextField tfNumber; // 번호 입력창
    JButton btnSave; // 저장 버튼
    JButton btnReset; // 리셋 버튼

    public test1() {
        init();
        setDisplay();
        showFrame();
    }
    private void init() {
        lblTitle = new JLabel("이름과 번호를 입력하세요.");
        lblName = new JLabel("Name");
        lblNumber = new JLabel("Number");
        tfName = new JTextField(10);
        tfNumber = new JTextField(10);
        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");
    }
    private void setDisplay() {

        JPanel pnlNorth = new JPanel();
        pnlNorth.add(lblTitle);
        pnlNorth.setBorder(new TitledBorder("North"));

        JPanel pnlWest = new JPanel(new GridLayout(0,1,0,10));
        pnlWest.add(lblName);
        pnlWest.add(lblNumber);
        pnlWest.setBorder(new TitledBorder("West"));

        JPanel pnlEast = new JPanel(new GridLayout(0,1,0,10));
        pnlEast.add(tfName);
        pnlEast.add(tfNumber);
        pnlEast.setBorder(new TitledBorder("East"));

        JPanel pnlcenter = new JPanel(new GridLayout(0,1,0,10));
        pnlcenter.add(tfName);
        pnlcenter.add(tfNumber);
        pnlcenter.setBorder(new TitledBorder("East"));

        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnSave);
        pnlSouth.add(btnReset);
        pnlSouth.setBorder(new TitledBorder("South"));

        add(pnlNorth, BorderLayout.NORTH);
        add(pnlWest, BorderLayout.WEST);
        add(pnlEast, BorderLayout.EAST);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    private void showFrame() {
        setTitle("GUI");
        pack();
        setLocationRelativeTo(null);
        setResizable(false); // 창을 고정
        setVisible(true);

    }
    public static void main(String[] args) {
        new test1();
    }
}