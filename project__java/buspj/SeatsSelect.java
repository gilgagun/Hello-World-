package buspj;

import java.awt.*;             // 폰트 등 그래픽 처리를 위한 클래스들의 경로명
import java.awt.event.*;       // 이벤트 처리에 필요한 기본 클래스들의 경로명
import javax.swing.*;          // 스윙 컴포넌트 클래스들 경로명

// 선택한 좌석에 관한 클래스
class SelectSeats {
    int number;
    int check;

    public SelectSeats (int number, int check) {
        this.number = number;
        this.check = check;
    }

    public int get_number() {
        return this.number;
    }

    public int get_phone() {
        return this.check;
    }
}

// 뒤로가기 버튼
class SeatsBack extends JPanel {
    SeatsSelect frame;

    public SeatsBack(SeatsSelect frame, ReservationMain frame2) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton back = new JButton("뒤로가기");
        back.setPreferredSize(new Dimension(120, 50));
        back.setFont(new Font("고딕", Font.BOLD, 20));
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame2.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}

// 화면 맨 위 회색 부분
class SeatsNorth extends JPanel {
    SeatsSelect frame;

    public SeatsNorth(SeatsSelect frame, ReservationMain frame2) {
        setBackground(Color.LIGHT_GRAY);
        this.frame = frame;

        setLayout(new BorderLayout());
        add(new Title(), BorderLayout.WEST);
        add(new SeatsBack(this.frame, frame2), BorderLayout.EAST);
    }
}

// 화면 가운데 부분
class SeatsCenter extends JPanel implements MouseListener {
    String id;
    int number = 0;   // 인원
    int price = 0;    // 가격
    int onePrice;     // 표 하나의 가격
    int[][] seatArr = new int[7][5];  // 좌석 번호 배열
    int seatNum;      // 선택한 좌석 번호
    JLabel personnel; // 인원을 담을 JLabel
    JLabel priceInt;   // 가격을 담을 JLabel
    ImageIcon updateWhiteIcon;
    static JLabel[][] img = new JLabel[7][5];  // 좌석 배열
    static JLabel[] img2 = new JLabel[29];    // 좌석 정보를 담을 1차원 배열

    public SeatsCenter(SeatsSelect frame, String id, String start, String end, String date, String[] info) {
        setLayout(null);
        this.id = id;   // 회원 아이디 정보 저장
        this.onePrice = Integer.valueOf(info[4]);
        Color bgmycor=new Color(166,222,249);
        setBackground(bgmycor);
        Color mycor=new Color(189,215,238);
        //setBackground(Color.WHITE);
        // '예매하기' 글자
        JLabel title = new JLabel("예매하기");
        title.setBounds(60, 3, 150, 100);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        add(title);

        // 좌석 표시 패널
        JPanel seatsTable = new JPanel();
        seatsTable.setBackground(Color.WHITE);
        seatsTable.setLayout(null);
        seatsTable.setBounds(275, 85, 450,550);
        add(seatsTable);

        // 선택좌석, 불가능좌석 표시
        this.create_seats(seatsTable);
        // 좌석 초기화
        this.init_seats(seatsTable);

        // 가격 테이블 생성
        JPanel priceTable = new JPanel();
        priceTable.setBackground(Color.WHITE);
        priceTable.setLayout(new BorderLayout());
        priceTable.setBounds(775, 430,150,70);
        add(priceTable);

        // 가격 테이블 열 이름 공간
        JPanel column = new JPanel();
        column.setBackground(Color.LIGHT_GRAY);
        column.setLayout(new FlowLayout(FlowLayout.LEFT, 20,6));
        column.setSize(150,35);
        priceTable.add(column, BorderLayout.NORTH);

        // 가격 테이블 열 이름 글자
        JLabel colName = new JLabel("인원");
        colName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        column.add(colName);

        JLabel colName2 = new JLabel("    요금");
        colName2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        column.add(colName2);

        // 인원, 가격 패널
        JPanel text = new JPanel();
        text.setBackground(Color.WHITE);
        text.setLayout(new FlowLayout(FlowLayout.LEFT, 30,6));
        text.setSize(150,35);
        priceTable.add(text, BorderLayout.CENTER);

        // 인원 디폴트 세팅
        personnel = new JLabel("" + number);
        personnel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        text.add(personnel);

        // 가격 디폴트 세팅
        priceInt = new JLabel("   " + this.price);
        priceInt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        priceInt.setVisible(false);   // 초기에는 숨기고 있음.
        text.add(priceInt);

        // 결제진행 버튼 생성
        JButton payment = new JButton("결제진행");
        payment.setBounds(820, 550, 100,40);
        payment.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        add(payment);

        // 결제진행 버튼 클릭 이벤트
        payment.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (number == 0) {
                    JOptionPane.showMessageDialog(null, "좌석을 선택하세요.");
                } else {
                    new Payment(frame, id, start, end, date, info, number, price, seatNum);
                    frame.setVisible(false);
                }
            }
        });
        ImageIcon background = new ImageIcon("project__java/buspj/image/test.jpg");
        JLabel image2 = new JLabel(background);
        image2.setBounds(-1800,-200,4500,1200);
        add(image2);
    }

    // 좌석 이미지 생성
    public void create_seats(JPanel p) {
        // 선택 가능 이미지
        ImageIcon possible = new ImageIcon("project__java/buspj/image/white_seats.png");
        Image possibleImage = possible.getImage();
        Image updatePossibleImg = possibleImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon updatePossibleIcon = new ImageIcon(updatePossibleImg);

        JLabel possibleSeats = new JLabel(updatePossibleIcon);
        possibleSeats.setBounds(12,210,50,50);
        possibleSeats.setHorizontalAlignment(JLabel.CENTER);
        p.add(possibleSeats);

        // 선택 가능 텍스트
        JLabel possibleText = new JLabel("선택가능");
        possibleText.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        possibleText.setBounds(17, 260, 50, 15);
        p.add(possibleText);

        // 선택 불가능 이미지
        ImageIcon impossible = new ImageIcon("project__java/buspj/image/black_seats.png");
        Image impossibleImage = impossible.getImage();
        Image updateImpossibleImg = impossibleImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon updateImpossibleIcon = new ImageIcon(updateImpossibleImg);

        JLabel impossibleSeats = new JLabel(updateImpossibleIcon);
        impossibleSeats.setBounds(12,290,50,50);
        impossibleSeats.setHorizontalAlignment(JLabel.CENTER);
        p.add(impossibleSeats);

        // 선택 불가능 텍스트
        JLabel impossibleText = new JLabel("선택불가능");
        impossibleText.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        impossibleText.setBounds(12, 340, 60, 15);
        p.add(impossibleText);
    }

    // 좌석 초기화 메소드
    public void init_seats(JPanel p) {
        // 아무것도 선택하지 않은 초기 좌석 이미지 생성
        ImageIcon white = new ImageIcon("project__java/buspj/image/white_seats.png");
        Image whiteImage = white.getImage();
        Image updateWhiteImg = whiteImage.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        updateWhiteIcon = new ImageIcon(updateWhiteImg);

        // 이미지 저장 과정
        JLabel seat;
        for (int i = 0; i < this.img.length; i++ ) {
            for (int j = 0; j < this.img[i].length; j++) {
                if (j == 2 && i != this.img.length - 1) {
                    continue;
                } else {
                    // JLabel에 이미지 삽입
                    seat = new JLabel(updateWhiteIcon);
                    seat.setBounds(73 + j*60,30 + i*70,60,70);
                    seat.setHorizontalAlignment(JLabel.CENTER);

                    // 2차원 배열에 좌석 이미지 저장 후 화면에 출력
                    this.img[i][j] = seat;
                    p.add(this.img[i][j]);

                    // 좌석번호 부여
//                    if (j != 3) {
//                        seatArr[i][j] = 1 + j;
//                    } else {
//                        seatArr[i][j] = 1 + (j - 1);
//                    }

                    // 이벤트 처리
                    this.img[i][j].addMouseListener(this);
                }
            }
        }
    }

    // 좌석 클릭 이벤트 처리
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel s = (JLabel) e.getSource();
        
        if (s.getIcon().equals(updateWhiteIcon)) {
            // 검은색 좌석 이미지로 교체
            ImageIcon black = new ImageIcon("project__java/buspj/image/black_seats.png");
            Image blackImage = black.getImage();
            Image updateBlackImg = blackImage.getScaledInstance(60,60,Image.SCALE_SMOOTH);
            ImageIcon updateBlackIcon = new ImageIcon(updateBlackImg);

            this.number += 1;  // 인원 수 증가
            this.personnel.setText("" + this.number);
            this.price += this.onePrice;  // 가격 증가
            this.priceInt.setText("   " + this.price);
            this.priceInt.setVisible(true);  // 가격 화면에 표현

            // 새 이미지로 교체
            s.setIcon(updateBlackIcon);
        } else {
            ImageIcon white = new ImageIcon("project__java/buspj/image/white_seats.png");
            Image whiteImage = white.getImage();
            Image updateWhiteImg = whiteImage.getScaledInstance(60,60,Image.SCALE_SMOOTH);
            updateWhiteIcon = new ImageIcon(updateWhiteImg);

            this.number -= 1;   // 인원 수 감소
            this.personnel.setText("" + this.number);
            this.price -= this.onePrice;  // 가격 감소
            this.priceInt.setText("   " + this.price);

            // 가격이 0이라면?
            if (this.price == 0) {
                this.priceInt.setVisible(false);
            }

            s.setIcon(updateWhiteIcon);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

// 좌석 선택 클래스 전체적인 구조
public class SeatsSelect extends JFrame {
    public SeatsSelect(ReservationMain frame, String id, String start, String end, String date, String[] info) {
        setTitle("버스타슈~");
        setSize(1000,800);
        setResizable(false);
        setLocationRelativeTo(null);  // 프레임을 화면 정중앙에 뜨도록 설정
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(new SeatsNorth(this, frame), BorderLayout.NORTH);
        mainContainer.add(new SeatsCenter(this, id, start, end, date, info), BorderLayout.CENTER);

        addWindowListener(new JFrameWindowClosingEventHandler());

        setVisible(true);
    }
}
