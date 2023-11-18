package Function;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

public class SearchWord {

    private final JComboBox<String>   comboBox = new JComboBox<>();
    private JTextField textField;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   jFrame.setContentPane(new JLabel(new ImageIcon("resources\\R-C.jpg")));
        jFrame.setLayout(new FlowLayout());
        JPanel init = new SearchWord().init();
        jFrame.add(init);


        jFrame.pack();
    }

    public JPanel init() {
        JPanel jPanel = new JPanel();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("resources\\EnglishWords.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        textField = new JTextField(20);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateComboBox(properties);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateComboBox(properties);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateComboBox(properties);
            }
        });


        JLabel jLabel1 = new JLabel("      ");
        JLabel jLabel2 = new JLabel("      ");
        JLabel jLabel3 = new JLabel("      ");

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getID() == ActionEvent.ACTION_PERFORMED && e.getModifiers() == 0) {
                    String actionCommand = e.getActionCommand();
                    String result = (String) properties.get(actionCommand);



                    String[] split = result.split("\\|");
                    jLabel1.setText("单词    \t"+actionCommand);
                    jLabel2.setText("音标    \t["+split[0]+"]");
                    jLabel3.setText("中文语义  \t"+split[1]);
                }
            }
        });



        JButton jButton = new JButton(new AbstractAction("查找") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                String result = (String) properties.get(text);




                String[] split = result.split("\\|");
                jLabel1.setText("单词    \t"+text);
                jLabel2.setText("音标    \t["+split[0]+"]");
                jLabel3.setText("中文语义  \t"+split[1]);
            }
        });




        Box horizontalBox = Box.createHorizontalBox();

        horizontalBox.add(textField);
        horizontalBox.add(jButton);


        Box verticalBox = Box.createVerticalBox();
        Font font = new Font("宋体", Font.BOLD, 20);
        JLabel jLabel = new JLabel("查单词");
        jLabel.setFont(font);
        verticalBox.add(jLabel);
        verticalBox.add(horizontalBox);
        verticalBox.add(comboBox);
        verticalBox.add(Box.createVerticalStrut(200));

        verticalBox.add(jLabel1);
        verticalBox.add(jLabel2);
        verticalBox.add(jLabel3);

        jPanel.setLayout(new FlowLayout());
        jPanel.add(verticalBox);

        return jPanel;
    }

    private void updateComboBox(Properties properties) {
        String text = textField.getText();

            comboBox.removeAllItems();


        if (!text.isEmpty()) {
            // 根据用户输入的内容获取预定义的选项
            // 这里使用一个简单的字符串数组作为预定义的选项

            Set<Object> objects = properties.keySet();

            for (Object o :  objects) {
                String option=(String) o;
                String s = ((String) properties.get(o)).split("\\|")[1];


                if (option.startsWith(text)) {

                    comboBox.addItem(option+"               \t"+s);
                }
            }
        }
        comboBox.setPopupVisible(comboBox.getItemCount() > 0);
    }
}