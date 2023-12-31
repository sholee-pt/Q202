package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grade_2019111679_016 extends JFrame{
	// 선택사항 이름
    String name[] = {"apple","pear","cherry"};
    
    // 선택해제되었을 때 이미지 아이콘
    ImageIcon icon[] = new ImageIcon[name.length];
    
    // 선택되었을 때 이미지 아이콘
    ImageIcon selectedIcon[] = new ImageIcon[name.length];
    
    // 체크박스
    JCheckBox checkBox[] = new JCheckBox[name.length];
    
    public Grade_2019111679_016(){
    	setTitle("checkbox"); // 타이틀 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫을 때 프로그램 종료 설정
        Container c = getContentPane(); // 컨테이너 가져오기
        c.setLayout(new FlowLayout()); // 레이아웃 설정
        
        // transformImageSize 클래스의 인스턴스 생성
        transformImageSize trans = new transformImageSize();
        
        // 선택되지 않았을 때의 이미지 파일 설정
        icon[0] = new ImageIcon("c:\\Q202\\images\\apple.png");
        icon[1] = new ImageIcon("c:\\Q202\\images\\pear.png");
        icon[2] = new ImageIcon("c:\\Q202\\images\\cherry.png");
        for(int i=0; i<3; i++) {
            icon[i] = trans.transformImageSize(icon[i], 50, 50); // 이미지 크기 변환
        }
        
        // 선택되었을 때의 이미지 파일 설정
        selectedIcon[0] = new ImageIcon("c:\\Q202\\images\\appleB.png");
        selectedIcon[1] = new ImageIcon("c:\\Q202\\images\\pearB.png");
        selectedIcon[2] = new ImageIcon("c:\\Q202\\images\\cherryB.png");
        for(int i=0; i<3; i++) {
            selectedIcon[i] = trans.transformImageSize(selectedIcon[i], 50, 50); // 이미지 크기 변환
        }
        
        // 체크박스 설정
        for(int i=0; i<name.length;i++) {
        	checkBox[i] = new JCheckBox(name[i], icon[i]); // 체크박스 생성
            checkBox[i].setSelectedIcon(selectedIcon[i]); // 선택된 아이콘 설정
            checkBox[i].setBorderPainted(true); // 테두리 그리기 설정
            
            checkBox[i].setToolTipText(name[i]+"을(를) 선택하시겠습니까?"); // 툴팁 설정
            int index = i;
            checkBox[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED)
                        checkBox[index].setToolTipText(name[index] + "을(를) 선택취소하시겠습니까?"); // 선택 취소시 툴팁 변경
                    else
                        checkBox[index].setToolTipText(name[index] + "을(를) 선택하시겠습니까?"); // 선택시 툴팁 변경
                }
            });
            c.add(checkBox[i]); // 컨테이너에 체크박스 추가
        }
        setSize(350,150); // 창 크기 설정
        setVisible(true); // 창 보이기 설정
    }
    
    public static void main(String[] args) {
        new Grade_2019111679_016();
    }
}
