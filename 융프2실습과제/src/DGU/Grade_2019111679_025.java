package DGU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grade_2019111679_025 extends JFrame {
    private int w, h; // 이미지의 가로, 세로 크기를 저장할 변수
    private String fpath = "c:\\Q202\\files\\fruits.txt"; // 과일 목록 파일의 경로
    private JComboBox<String> strCombo; // 과일을 선택할 수 있는 콤보 박스
    private ImageIcon[] images; // 각 과일에 해당하는 이미지를 저장할 배열
    private String[] fruits; // 파일에서 읽어온 과일 목록

    public Grade_2019111679_025() {
        Scanner sc = new Scanner(System.in);
        // 사용자로부터 이미지의 가로, 세로 크기 입력 받음
        System.out.print("이미지의 가로 길이를 입력하세요: ");
        w = sc.nextInt();
        System.out.print("이미지의 세로 길이를 입력하세요: ");
        h = sc.nextInt();
        
        // 파일에서 과일 목록을 읽어오고, 이미지를 연결함
        fruits = loadList(fpath).toArray(new String[0]);
        strCombo = new JComboBox<>(fruits);
        linkImages();

        // 프레임 설정
        setTitle("using file");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(strCombo);

        // 버튼 추가 및 액션 리스너 설정
        JButton reload = new JButton("새로고침");
        JButton add = new JButton("추가");
        JButton delete = new JButton("삭제");
        JButton view = new JButton("보기");
        c.add(add);
        c.add(delete);
        c.add(reload);
        c.add(view);

        // "새로고침" 버튼: 콤보 박스 내용을 새로고침
        reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });

        // "추가" 버튼: 새 과일을 목록에 추가
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newFruit = JOptionPane.showInputDialog("과일 이름을 입력하세요:");
                if (newFruit != null && !newFruit.trim().isEmpty()) {
                    addFruit(newFruit);
                    refresh();
                    strCombo.setSelectedIndex(fruits.length - 1);
                }
            }
        });

        // "삭제" 버튼: 선택된 과일을 목록에서 삭제
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = strCombo.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String selectedFruit = strCombo.getSelectedItem().toString();
                    int confirm = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteFruit(selectedFruit);
                        refresh();
                        strCombo.setSelectedIndex(Math.max(0, selectedIndex - 1));
                    }
                }
            }
        });

        // "보기" 버튼: 선택된 과일의 이미지를 새 창에서 보여줌
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = strCombo.getSelectedIndex();
                if (index >= 0 && index < images.length) {
                    ImageIcon image = images[index];
                    if (image.getImageLoadStatus() == MediaTracker.COMPLETE) {
                        showImage(image);
                    } else {
                        JOptionPane.showMessageDialog(null, "이미지를 찾을 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        setSize(300, 150);
        setVisible(true);
        sc.close();
    }

    public static void main(String[] args) {
        new Grade_2019111679_025();
    }

    // 파일에서 과일 목록을 읽어오는 메소드
    public List<String> loadList(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "파일을 불러오는 중 오류가 발생했습니다: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    // 새 과일을 파일에 추가하는 메소드
    public void addFruit(String newFruit) {
        try {
            Files.write(Paths.get(fpath), (newFruit + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "파일에 쓰는 중 오류가 발생했습니다: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 선택된 과일을 파일에서 삭제하는 메소드
    public void deleteFruit(String selectedFruit) {
        try {
            List<String> list = loadList(fpath);
            if (!list.remove(selectedFruit)) {
                JOptionPane.showMessageDialog(null, "선택한 과일이 목록에 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Files.write(Paths.get(fpath), list, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "파일에서 삭제하는 중 오류가 발생했습니다: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 콤보 박스를 새로고침하는 메소드
    public void refresh() {
        fruits = loadList(fpath).toArray(new String[0]);
        strCombo.setModel(new DefaultComboBoxModel<>(fruits));
        linkImages();
    }

    // 각 과일에 해당하는 이미지를 연결하는 메소드
    public void linkImages() {
        transformImageSize trans = new transformImageSize();
        images = new ImageIcon[fruits.length];
        for (int i = 0; i < fruits.length; i++) {
            ImageIcon original = new ImageIcon("c:\\Q202\\images\\" + fruits[i] + ".png");
            ImageIcon resized = trans.transformImageSize(original, w, h);
            images[i] = resized;
        }
    }

    // 선택된 이미지를 새 창에서 보여주는 메소드
    private void showImage(ImageIcon image) {
        JFrame iframe = new JFrame("이미지 보기");
        iframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iframe.setLayout(new FlowLayout());

        JLabel imageLabel = new JLabel(image);
        iframe.add(imageLabel);

        iframe.pack();
        iframe.setVisible(true);
    }
}
