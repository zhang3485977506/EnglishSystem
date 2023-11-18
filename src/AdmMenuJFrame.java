

import Function.SearchWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AdmMenuJFrame {
    JFrame jFrame = new JFrame();
    JTabbedPane jTabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    public static void main(String[] args) {
        new AdmMenuJFrame().init();
    }

    public void init() {
        JPanel searchWord = new SearchWord().init();


        Font font = new Font("宋体", Font.BOLD, 30); // 创建一个新的字体对象，设置字体名称、样式和大小
        jTabbedPane.setFont(font); // 设置JTabbedPane的字体

        jTabbedPane.addTab("单词背诵", new ImageIcon(), new JLabel());

        jTabbedPane.addTab("单词查询", searchWord);


        ArrayList<Component> components = new ArrayList<>();
        components.add(jFrame);
        components.add(jTabbedPane);
        components.add(searchWord);
        /*



         */


        JMenu jMenu1 = new JMenu("风格");

        JMenuItem j1 = new JMenuItem(new AbstractAction("Metal 风格") {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStyle("javax.swing.plaf.metal.MetalLookAndFeel", components);

            }
        });
        JMenuItem j2 = new JMenuItem(new AbstractAction("Nimbus 风格") {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStyle("javax.swing.plaf.nimbus.NimbusLookAndFeel", components);

            }
        });
        JMenuItem j3 = new JMenuItem(new AbstractAction("Windows 风格") {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStyle("com.sun.java.swing.plaf.windows.WindowsLookAndFeel", components);

            }
        });
        JMenuItem j4 = new JMenuItem(new AbstractAction("Windows 经典风格") {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStyle("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", components);

            }
        });
        JMenuItem j5 = new JMenuItem(new AbstractAction("Motif 风格") {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveStyle("com.sun.java.swing.plaf.motif.MotifLookAndFeel", components);

            }
        });
        jMenu1.add(j1);
        jMenu1.add(j2);
        jMenu1.add(j3);
        jMenu1.add(j4);
        jMenu1.add(j5);


        JMenuBar jMenuBar=new JMenuBar();
        jMenuBar.add(jMenu1);
        jFrame.setJMenuBar(jMenuBar);

        jFrame.add(jTabbedPane);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void moveStyle(String style, ArrayList<Component> components) {
        try {
            UIManager.setLookAndFeel(style);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Component component : components) {
            SwingUtilities.updateComponentTreeUI(component);
        }
    }
}
