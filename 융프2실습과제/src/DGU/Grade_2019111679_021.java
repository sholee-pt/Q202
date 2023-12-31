package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Grade_2019111679_021 extends JFrame {
    // 스캐너 객체 생성
    Scanner sc = new Scanner(System.in);
    
    int w, h; // 이미지의 가로, 세로 크기를 저장할 변수
    transformImageSize trans = new transformImageSize(); // 이미지 크기 조정 객체
    
    // 선택사항 이름 배열
    String name[] = {"사과", "배", "체리"};

    // 선택해제되었을 때 이미지 아이콘 배열
    ImageIcon icon[] = new ImageIcon[name.length];

    // 선택됐을 때 이미지 아이콘 배열
    ImageIcon selectedIcon[] = new ImageIcon[name.length];

    // 라디오 버튼 배열
    JRadioButton radioButton[] = new JRadioButton[name.length];

    // 총 가격을 표시할 레이블
    JLabel sumLabel;

    public Grade_2019111679_021() {
        setTitle("combined radiobutton"); // 창 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 시 프로그램 종료 설정
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new FlowLayout()); // 레이아웃 설정

        // 첫번째 패널: 과일 가격 정보
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("사과: 100원, 배: 500원, 체리: 20000원"));
        c.add(panel1);

        // 두번째 패널: 라디오 버튼
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        // 사용자로부터 이미지 크기 입력 받기
        System.out.print("이미지의 가로 길이를 입력하세요: ");
        w = sc.nextInt();
        System.out.print("이미지의 세로 길이를 입력하세요: ");
        h = sc.nextInt();

        // 선택되지 않았을 때의 이미지 설정
        icon[0] = new ImageIcon("c:\\Q202\\images\\apple.png");
        icon[1] = new ImageIcon("c:\\Q202\\images\\pear.png");
        icon[2] = new ImageIcon("c:\\Q202\\images\\cherry.png");
        for (int i = 0; i < 3; i++) {
            icon[i] = trans.transformImageSize(icon[i], w, h); // 이미지 크기 변환
        }

        // 선택됐을 때의 이미지 설정
        selectedIcon[0] = new ImageIcon("c:\\Q202\\images\\appleB.png");
        selectedIcon[1] = new ImageIcon("c:\\Q202\\images\\pearB.png");
        selectedIcon[2] = new ImageIcon("c:\\Q202\\images\\cherryB.png");
        for (int i = 0; i < 3; i++) {
            selectedIcon[i] = trans.transformImageSize(selectedIcon[i], w, h); // 이미지 크기 변환
        }

        ButtonGroup g = new ButtonGroup();
        for (int i = 0; i < name.length; i++) {
            radioButton[i] = new JRadioButton(name[i], icon[i]); // 라디오 버튼 생성
            radioButton[i].setSelectedIcon(selectedIcon[i]); // 선택된 아이콘 설정
            radioButton[i].setBorderPainted(true); // 테두리 설정

            // 툴팁 설정
            radioButton[i].setToolTipText(name[i] + "을(를) 선택하시겠습니까?");
            int index = i;
            radioButton[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                        radioButton[index].setToolTipText(name[index] + "이(가) 선택됨");
                    else
                        radioButton[index].setToolTipText(name[index] + "을(를) 선택하시겠습니까?");
                    calculatePrice(); // 가격 계산
                }
            });
            g.add(radioButton[i]); // 버튼 그룹에 추가
            panel2.add(radioButton[i]); // 패널에 추가
        }
        c.add(panel2);

        // 세번째 패널: 총 가격 정보
        JPanel panel3 = new JPanel();
        sumLabel = new JLabel("현재 0원 입니다.");
        panel3.add(sumLabel);
        c.add(panel3);

        // 이미지의 크기에 따라 최대한 유동적으로 창의 크기를 결정함(최대 해상도: 1920x1080)
        if(w*5 > 1920)
        	w = 384; // w*5<1920를 만족하는 최대 w의 크기
        if(h*4 > 1080)
        	h = 270; // h*4<1080을 만족하는 최대 h의 크기
        setSize(w*5,h*4);

        setVisible(true); // 창 보이기
        sc.close(); // 스캐너 닫기
    }

    // 가격 계산 메소드
    public void calculatePrice() {
        int sum = 0;
        for (int i = 0; i < radioButton.length; i++) {
            if (radioButton[i].isSelected()) {
                switch (i) {
                    case 0:
                        sum += 100; // 사과 선택
                        break;
                    case 1:
                        sum += 500; // 배 선택
                        break;
                    case 2:
                        sum += 20000; // 체리 선택
                        break;
                }
            }
        }
        sumLabel.setText("선택한 과일은 " + sum + "원 입니다."); // 가격 레이블 업데이트
    }

    public static void main(String[] args) {
        new Grade_2019111679_021();
    }
}
