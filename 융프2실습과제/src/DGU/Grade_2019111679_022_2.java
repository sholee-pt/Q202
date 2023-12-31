package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Fruits 클래스: 과일의 이름, 가격 및 이미지 정보를 저장
class Fruits {
    private String name; // 과일의 이름
    private int price; // 과일의 가격
    private ImageIcon image; // 과일의 이미지

    // 생성자: 이름과 가격을 받아 과일 객체를 생성
    Fruits(String n, int p) {
        name = n;
        price = p;
        image = new ImageIcon("c:\\Q202\\images\\" + name + ".png"); // 이미지 경로 설정
    }

    // 과일 이름을 반환하는 메서드
    String getFName() {
        return name;
    }

    // 과일 가격을 반환하는 메서드
    int getFPrice() {
        return price;
    }

    // 과일 이미지를 반환하는 메서드
    ImageIcon getImage() {
        return image;
    }
}

// 메인 프레임 클래스
public class Grade_2019111679_022_2 extends JFrame {
    private int w, h; // 창의 너비와 높이
    private Container c = getContentPane(); // 컨테이너
    private ArrayList<Fruits> fruitsList = new ArrayList<>(); // 과일 목록

    // 생성자: 기본 설정
    public Grade_2019111679_022_2() {
        w = 300;
        h = 300;
        setTitle("확장"); // 창 제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 조건
        c.add(new Panel1(this), BorderLayout.CENTER); // 첫 번째 패널 추가
        setSize(w, h); // 창 크기 설정
        setVisible(true); // 창 보이기
    }

    // 패널 변경 메서드
    public void changePanel(JPanel panel) {
        c.removeAll(); // 모든 컴포넌트 제거
        c.add(panel, BorderLayout.CENTER); // 새로운 패널 추가
        c.revalidate(); // 레이아웃 재검증
        c.repaint(); // 다시 그리기
    }

    // 과일 목록을 반환하는 메서드
    public ArrayList<Fruits> getFruitsList() {
        return fruitsList;
    }

    public static void main(String[] args) {
        new Grade_2019111679_022_2(); // 애플리케이션 시작
    }
}

// Panel1: 개수 입력
class Panel1 extends JPanel {
    Panel1(final Grade_2019111679_022_2 frame) {
        setLayout(new GridLayout(3, 1)); // 레이아웃 설정
        add(new JLabel("과일의 개수를 입력하세요")); // 레이블 추가

        final JTextField numberField = new JTextField(); // 텍스트 필드 추가
        add(numberField);

        JButton confirmButton = new JButton("확인"); // 확인 버튼
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(numberField.getText()); // 입력된 과일 개수 읽기
                frame.changePanel(new Panel2(frame, size)); // Panel2로 패널 변경
            }
        });
        add(confirmButton); // 버튼 추가
    }
}

// Panel2: 이름, 가격 입력
class Panel2 extends JPanel {
    private ArrayList<JTextField> nameFields = new ArrayList<>(); // 과일 이름을 입력하는 텍스트 필드 리스트
    private ArrayList<JTextField> priceFields = new ArrayList<>(); // 과일 가격을 입력하는 텍스트 필드 리스트
    private ArrayList<JLabel> nameLabels = new ArrayList<>(); // 과일 이름 레이블 리스트
    private ArrayList<JLabel> priceLabels = new ArrayList<>(); // 과일 가격 레이블 리스트
    private JPanel fieldsPanel; // 입력 필드와 레이블을 포함하는 패널

    // 생성자: 초기 과일 개수를 인자로 받아 초기화
    Panel2(final Grade_2019111679_022_2 frame, int initialSize) {
        setLayout(new BorderLayout()); // 레이아웃 설정

        fieldsPanel = new JPanel(); // 필드를 담을 패널 생성
        fieldsPanel.setLayout(new GridLayout(0, 2)); // 그리드 레이아웃 설정
        JScrollPane scrollPane = new JScrollPane(fieldsPanel); // 스크롤 가능한 패널 추가

        // 초기 과일 필드 추가
        for (int i = 0; i < initialSize; i++) {
            addFruitField();
        }

        // 과일 추가 버튼 설정 및 이벤트 핸들러 추가
        JButton addButton = new JButton("과일 추가");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFruitField(); // 과일 필드 추가
            }
        });

        // 과일 제거 버튼 설정 및 이벤트 핸들러 추가
        JButton removeButton = new JButton("과일 제거");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeFruitField(); // 과일 필드 제거
            }
        });

        // 확인 버튼 설정 및 이벤트 핸들러 추가
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getFruitsList().clear(); // 기존 과일 목록 클리어
                // 입력된 과일 이름과 가격을 리스트에 추가
                for (int i = 0; i < nameFields.size(); i++) {
                    String name = nameFields.get(i).getText();
                    int price = changeText(priceFields.get(i).getText()); // 텍스트를 정수로 변환
                    frame.getFruitsList().add(new Fruits(name, price)); // 과일 객체 생성 및 추가
                }
                frame.changePanel(new Panel3(frame.getFruitsList())); // 다음 패널로 이동
            }
        });

        // 버튼 패널에 버튼들 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(confirmButton);

        // 메인 패널에 스크롤 패널과 버튼 패널 추가
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 과일 필드 추가 메소드
    private void addFruitField() {
        JTextField nameField = new JTextField(); // 이름 입력 필드
        JTextField priceField = new JTextField(); // 가격 입력 필드
        nameFields.add(nameField);
        priceFields.add(priceField);

        JLabel nameLabel = new JLabel("과일 이름:"); // 이름 레이블
        JLabel priceLabel = new JLabel("과일 가격:"); // 가격 레이블
        nameLabels.add(nameLabel);
        priceLabels.add(priceLabel);

        // 패널에 레이블과 텍스트 필드 추가
        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(priceLabel);
        fieldsPanel.add(priceField);
        fieldsPanel.revalidate();
        fieldsPanel.repaint();
    }

    // 과일 필드 제거 메소드
    private void removeFruitField() {
        int index = nameFields.size() - 1; // 마지막 필드 인덱스
        if (index >= 0) {
            // 마지막 필드와 레이블 제거
            fieldsPanel.remove(nameLabels.remove(index));
            fieldsPanel.remove(nameFields.remove(index));
            fieldsPanel.remove(priceLabels.remove(index));
            fieldsPanel.remove(priceFields.remove(index));
            fieldsPanel.revalidate();
            fieldsPanel.repaint();
        }
    }

    // 텍스트를 정수로 변환하는 도우미 메소드
    private int changeText(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // 변환 실패 시 0 반환
        }
    }
}

// Panel3: 라디오 버튼
class Panel3 extends JPanel {
    private JLabel showImage; // 과일 이미지를 표시할 레이블
    private JLabel showPrice; // 과일 가격을 표시할 레이블

    // 생성자: 과일 목록을 받아 인터페이스를 구성
    Panel3(ArrayList<Fruits> fruits) {
        setLayout(new BorderLayout()); // 레이아웃 설정

        // 라디오 버튼을 담을 패널 생성 및 설정
        JPanel radioPanel = new JPanel(new GridLayout(1, 0)); // 모든 라디오 버튼을 한 줄로 나열
        ButtonGroup group = new ButtonGroup(); // 라디오 버튼 그룹 생성

        // 레이블 초기화
        showImage = new JLabel("", SwingConstants.CENTER); // 이미지 레이블
        showPrice = new JLabel("과일을 선택하세요.", SwingConstants.CENTER); // 가격 레이블

        // 이미지 크기 조정을 위한 인스턴스
        transformImageSize trans = new transformImageSize();

        // 과일 목록을 순회하며 라디오 버튼 추가
        for (Fruits fruit : fruits) {
            JRadioButton button = new JRadioButton(fruit.getFName()); // 과일 이름으로 라디오 버튼 생성
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ImageIcon icon = fruit.getImage(); // 과일의 이미지 가져오기
                    if (icon.getIconWidth() == -1) {
                        // 이미지가 없는 경우 처리
                        showImage.setIcon(null);
                        showPrice.setText("이미지를 찾지 못했습니다.");
                    } else {
                        // 이미지가 있는 경우, 이미지 크기 조정 및 표시
                        icon = trans.transformImageSize(icon, 200, 200);
                        showImage.setIcon(icon);
                        showPrice.setText(fruit.getFName() + "의 가격은 " + fruit.getFPrice() + "원 입니다.");
                    }
                }
            });
            group.add(button); // 버튼 그룹에 추가
            radioPanel.add(button); // 패널에 버튼 추가
        }

        // 스크롤 가능한 라디오 버튼 패널 추가
        JScrollPane radioScrollPane = new JScrollPane(radioPanel);
        radioScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        radioScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // 패널에 스크롤 패널, 이미지 레이블, 가격 레이블 추가
        add(radioScrollPane, BorderLayout.NORTH);
        add(showImage, BorderLayout.CENTER);
        add(showPrice, BorderLayout.SOUTH);
    }
}
