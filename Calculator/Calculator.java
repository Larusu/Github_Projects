package Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Calculator extends JPanel{

    final int maxWidth = 300;
    final int maxHeight = 425;

    char[] operator = {'+', '-', '*', '/', '=', '.'};
    int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    JButton[] btnNum = new JButton[10];
    JButton[] btnOperator = new JButton[6];

    Calculator(){
        this.setPreferredSize(new Dimension(maxWidth, maxHeight));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        
        String[] strOps = new String[operator.length];
        for(int i = 0; i < operator.length; i++) strOps[i] = String.valueOf(operator[i]);
        String[] strNum = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++) strNum[i] = String.valueOf(numbers[i]);

        initializingButton(btnNum, strNum);
        initializingButton(btnOperator, strOps);
        
        // this.add(displayBtn(), BorderLayout.CENTER);
        display(this);
    }

    // where the JButton gets assigned with its fields and design
    void initializingButton(JButton[] btn, Object[] field){

        for(int i = 0; i < btn.length; i++){
            
            btn[i] = new JButton(field[i].toString());
            btn[i].setBackground(Color.LIGHT_GRAY);
            btn[i].setForeground(Color.BLACK);
            btn[i].setFont(new Font("Courier New", Font.BOLD, 30));
            btn[i].setFocusPainted(false);
            btn[i].setContentAreaFilled(true);
            btn[i].setOpaque(true);
            btn[i].setBorderPainted(false);
            btn[i].setBorder(new EmptyBorder(10, 20, 10, 20));
        }
    }

    void display(JPanel mainPanel){

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel button = setupDisplayBtn();
        JPanel textField = setupDisplayPanel();

        panel.add(textField, BorderLayout.NORTH);
        panel.add(button, BorderLayout.CENTER);

        mainPanel.add(panel);
    }
    JPanel setupDisplayPanel(){

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(new EmptyBorder(5, 2, 10, 2));

        JTextField textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(maxWidth, 65));
        textfield.setFont(new Font("Segoe UI", Font.PLAIN, 35));
        textfield.setBackground(new Color(200, 210, 210));
        textfield.setForeground(Color.BLACK);
        textfield.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 10));
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setEditable(false);

        panel.add(textfield, BorderLayout.CENTER);

        return panel;
    }

    JPanel setupDisplayBtn(){

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        panel.setLayout(new GridLayout(4, 4, 15, 15));

        panel.add(btnNum[7]);
        panel.add(btnNum[8]);
        panel.add(btnNum[9]);
        panel.add(btnOperator[3]); // '/'

        panel.add(btnNum[4]);
        panel.add(btnNum[5]);
        panel.add(btnNum[6]);
        panel.add(btnOperator[2]); // '*'

        panel.add(btnNum[1]);
        panel.add(btnNum[2]);
        panel.add(btnNum[3]);
        panel.add(btnOperator[1]); // '-'

        panel.add(btnNum[0]);
        panel.add(btnOperator[5]); // '.'
        panel.add(btnOperator[4]); // '='
        panel.add(btnOperator[0]); // '+'
        return panel;
    }
}
