package buspj;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ReservationCheckUp extends JFrame {
    public ReservationCheckUp(String id) {
        setTitle("예매내역 조회하기");
        setSize(500,400);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                ReservationCheckUp.this.dispose();
            }
        });

        setVisible(true);
    }
}
