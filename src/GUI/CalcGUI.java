package GUI;

import Validation.Validator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CalcGUI extends JFrame {
    private JButton bCalculate = new JButton("Calculate");
    private JTextField tfInput = new JTextField("",8);
    private JLabel label = new JLabel("Please, input your expression:");

    public CalcGUI() {
        super("Simple CFT Calculator");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 550;
        int height = 250;
        int x = (int) ((dimension.getWidth() - width) / 2);
        int y = (int) ((dimension.getHeight() - height) / 2);
        this.setBounds(x,y, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font1 = new Font("SansSerif", Font.BOLD, 18);
        tfInput.setFont(font1);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(label);
        container.add(tfInput);
        bCalculate.addActionListener( new ButtonEventListener());
        container.add(bCalculate);
    }


    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Validator validator = new Validator(tfInput.getText());
            String message = validator.valudate();
            if (message.equals(""))
                JOptionPane.showMessageDialog(null, "OK!","Output", JOptionPane.PLAIN_MESSAGE);
            else{
                JOptionPane.showMessageDialog(null, message,"Output", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
