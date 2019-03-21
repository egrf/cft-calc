package GUI;

import Parser.ExpressionParser;
import Validation.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcGUI extends JFrame {
    private JTextField tfInput = new JTextField("", 8);

    public CalcGUI() {
        super("Simple CFT Calculator");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 550;
        int height = 250;
        int x = (int) ((dimension.getWidth() - width) / 2);
        int y = (int) ((dimension.getHeight() - height) / 2);
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Font mainFont = new Font("SansSerif", Font.BOLD, 18);
        tfInput.setFont(mainFont);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 2, 2, 2));
        JLabel label = new JLabel("Please, input your expression:");
        label.setFont(mainFont);
        container.add(label);
        container.add(tfInput);
        JButton bCalculate = new JButton("Calculate");
        bCalculate.addActionListener(new ButtonEventListener());
        container.add(bCalculate);
        JButton bClear = new JButton("Clear");
        bClear.addActionListener(new ButtonClearListener());
        container.add(bClear);
        setVisible(true);
    }

    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Validator validator = new Validator(tfInput.getText());
            String message = validator.validate();
            if (message.equals("")) {
                ExpressionParser expressionParser = new ExpressionParser(tfInput.getText());
                message = expressionParser.getStringResult();
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private class ButtonClearListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            tfInput.setText("");
        }
    }
}
