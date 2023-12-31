package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grade_2019111679_019 extends JFrame {
    // 선택사항 이름 배열
    String name[] = {"apple","pear","cherry"};
    
    // 선택되지 않았을 때의 이미지 아이콘 배열
    ImageIcon icon[] = new ImageIcon[name.length];
    
    // 선택됐을 때의 이미지 아이콘 배열
    ImageIcon selectedIcon[] = new ImageIcon[name.length];
    
    // 라디오 버튼 배열
    JRadioButton radioButton[] = new JRadioButton[name.length];
    
    public Grade_2019111679_019() {
        setTitle("radiobutton"); // 창 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기 시 프로그램 종료
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new FlowLayout()); // 레이아웃 설정
        
        // 이미지 크기 조정을 위한 transformImageSize 클래스의 인스턴스 생성
        transformImageSize trans = new transformImageSize();

        // 선택되지 않았을 때의 이미지 설정
        icon[0] = new ImageIcon("c:\\Q202\\images\\apple.png");
        icon[1] = new ImageIcon("c:\\Q202\\images\\pear.png");
        icon[2] = new ImageIcon("c:\\Q202\\images\\cherry.png");
        for(int i=0; i<3; i++) {
            icon[i] = trans.transformImageSize(icon[i], 50, 50); // 이미지 크기 변환
        }
        
        // 선택됐을 때의 이미지 설정
        selectedIcon[0] = new ImageIcon("c:\\Q202\\images\\appleB.png");
        selectedIcon[1] = new ImageIcon("c:\\Q202\\images\\pearB.png");
        selectedIcon[2] = new ImageIcon("c:\\Q202\\images\\cherryB.png");
        for(int i=0; i<3; i++) {
            selectedIcon[i] = trans.transformImageSize(selectedIcon[i], 50, 50); // 이미지 크기 변환
        }
        
        // 라디오 버튼 설정
        ButtonGroup g = new ButtonGroup(); // 버튼 그룹 생성
        for(int i=0; i<name.length; i++) {
            radioButton[i] = new JRadioButton(name[i], icon[i]); // 라디오 버튼 생성
            radioButton[i].setSelectedIcon(selectedIcon[i]); // 선택된 아이콘 설정
            radioButton[i].setBorderPainted(true); // 테두리 설정
            
            // 툴팁 설정
            radioButton[i].setToolTipText(name[i]+"을(를) 선택하시겠습니까?");
            int index = i;
            radioButton[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    // 라디오 버튼 상태 변경 시 툴팁 업데이트
                    if(e.getStateChange() == ItemEvent.SELECTED)
                        radioButton[index].setToolTipText(name[index] + "이(가) 선택됨");
                    else
                        radioButton[index].setToolTipText(name[index] + "을(를) 선택하시겠습니까?");
                }
            });
            g.add(radioButton[i]); // 버튼 그룹에 추가
            c.add(radioButton[i]); // 컨테이너에 추가
        }
        setSize(350,150); // 창 크기 설정
        setVisible(true); // 창 보이기
    }
   
    public static void main(String[] args) {
        new Grade_2019111679_019();
    }
}
