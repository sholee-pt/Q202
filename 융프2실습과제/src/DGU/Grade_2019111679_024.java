package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

public class Grade_2019111679_024 extends JFrame {
    private JTextField tf = new JTextField(10);
    private JComboBox<String> strCombo = new JComboBox<String>();
    private JLabel imgLabel = new JLabel();
    private JButton delete = new JButton("삭제");
    private int w, h;

    public Grade_2019111679_024() {
    	Scanner sc = new Scanner(System.in);
    	
        setTitle("combobox program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("과일 이름 입력 후 <Enter> 키"));
        c.add(tf);
        c.add(strCombo);
        c.add(imgLabel);
        c.add(delete); // 삭제 버튼 추가
        
        // 사용자로부터 이미지의 가로 길이와 세로 길이를 입력받음
        System.out.print("이미지의 가로 길이를 입력하세요: ");
        w = sc.nextInt();
        System.out.print("이미지의 세로 길이를 입력하세요: ");
        h = sc.nextInt();

        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField t = (JTextField)e.getSource();
                String input = t.getText().trim(); // 입력된 텍스트 양 끝의 공백 제거
                if (!input.isEmpty()) { // 입력된 텍스트가 비어 있지 않으면
                    strCombo.addItem(input); // 콤보 박스에 아이템 추가
                    t.setText(""); // 텍스트 필드 초기화
                    updateImage(input); // 이미지 업데이트
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selected = strCombo.getSelectedIndex();
                if (selected != -1) { // 선택된 항목이 있는 경우
                    strCombo.removeItemAt(selected); // 선택된 항목 제거
                    imgLabel.setIcon(null); // 이미지 레이블 초기화
                }
            }
        });

        strCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                String fruit = (String) cb.getSelectedItem(); // 선택된 과일 이름 가져오기
                if (fruit != null) {
                    updateImage(fruit); // 선택된 과일 이름으로 이미지 업데이트
                }
            }
        });

        // 프레임 크기를 조정하여 이미지 출력에 충분한 높이를 확보
        setSize(300, h + 100);
        setVisible(true);
        sc.close(); // 스캐너 닫기
    }

    // 이미지 업데이트 메소드
    private void updateImage(String input) {
    	transformImageSize trans = new transformImageSize();
        // 이미지 파일이 존재하는지 확인
        String imagePath = "c:\\Q202\\images\\" + input + ".png";
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            imgLabel.setIcon(trans.transformImageSize(imageIcon, w, h)); // 이미지 크기 조정하여 설정
        } else {
            imgLabel.setIcon(null);
            JOptionPane.showMessageDialog(null, "이미지를 찾을 수 없습니다.", "에러", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Grade_2019111679_024();
    }
}
