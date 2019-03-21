package GUI;

import Tree.TreeElement;
import Validation.Validator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalcGUI extends JFrame {
    private JTextField tfInput = new JTextField("",8);

    public CalcGUI() {
        super("Simple CFT Calculator");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 550;
        int height = 250;
        int x = (int) ((dimension.getWidth() - width) / 2);
        int y = (int) ((dimension.getHeight() - height) / 2);
        this.setBounds(x,y, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        tfInput.setFont(font1);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2,2,2,2));
        JLabel label = new JLabel("Please, input your expression:");
        container.add(label);
        container.add(tfInput);
        JButton bCalculate = new JButton("Calculate");
        bCalculate.addActionListener( new ButtonEventListener());
        container.add(bCalculate);
        JButton bClear = new JButton("Clear");
        bClear.addActionListener(new ButtonClearListener());
        container.add(bClear);
        this.setVisible(true);
    }


    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Validator validator = new Validator(tfInput.getText());
            String message = validator.valudate();
            if (message.equals("")) {
                TreeElement tree = new TreeElement(tfInput.getText());
                message = tree.getResult();
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, message,"Output", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private class ButtonClearListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            tfInput.setText("");
        }
    }
}
