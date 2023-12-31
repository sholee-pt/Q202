package DGU;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Grade_2019111679_027 extends JFrame {
    JSlider[] sl = new JSlider[3]; // RGB 값을 조절할 세 개의 슬라이더
    JButton button; // 색상을 표시할 버튼

    public Grade_2019111679_027() {
        setTitle("슬라이더"); // 창의 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 프로그램 종료 설정
        Container c = getContentPane();
        c.setLayout(new FlowLayout()); // 컨테이너의 레이아웃 설정

        // 각 슬라이더 초기화 및 설정
        for (int i = 0; i < sl.length; i++) {
            sl[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 128); // 슬라이더 생성 및 설정
            sl[i].setPaintLabels(true); // 라벨 표시
            sl[i].setPaintTicks(true); // 눈금 표시
            sl[i].setPaintTrack(true); // 트랙 표시
            sl[i].setMajorTickSpacing(50); // 주 눈금 간격 설정
            sl[i].setMinorTickSpacing(10); // 부 눈금 간격 설정
            sl[i].addChangeListener(new MyChangeListener()); // 변경 리스너 추가
            c.add(sl[i]); // 슬라이더를 컨테이너에 추가
        }

        // 슬라이더의 전경색 설정
        sl[0].setForeground(Color.RED);   // Red 슬라이더
        sl[1].setForeground(Color.GREEN); // Green 슬라이더
        sl[2].setForeground(Color.BLUE);  // Blue 슬라이더

        // 버튼 초기화 및 설정
        button = new JButton("초기화"); // 버튼 생성
        button.setFont(new Font("맑은 고딕", Font.BOLD, 15)); // 버튼 폰트 설정
        button.setOpaque(true); // 버튼 불투명도 설정
        button.setBackground(new Color(128, 128, 128)); // 초기 버튼 배경색 설정
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼 클릭 시 이벤트 처리
                for (JSlider slider : sl) {
                    slider.setValue(128); // 슬라이더 값을 초기값으로 설정
                }
                button.setBackground(new Color(128, 128, 128)); // 버튼 색상 초기화
            }
        });
        c.add(button); // 버튼을 컨테이너에 추가

        setSize(300, 230); // 창 크기 설정
        setVisible(true); // 창을 보이게 설정
    }

    // 슬라이더 값 변경 시 호출되는 리스너 클래스
    class MyChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            // 슬라이더 값이 변경될 때 버튼의 배경색 업데이트
            int r = sl[0].getValue();
            int g = sl[1].getValue();
            int b = sl[2].getValue();
            button.setBackground(new Color(r, g, b)); // 버튼 배경색을 슬라이더 값에 따라 변경
        }
    }

    public static void main(String[] args) {
        new Grade_2019111679_027();
    }
}
