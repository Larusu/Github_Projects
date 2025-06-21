package Calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JPanel{

    final int maxWidth = 350;
    final int maxHeight = 450;

    char[] operator = {'+', '-', '*', '/'};
    int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    JButton[] btnNum = new JButton[10];

    Calculator(){
        this.setPreferredSize(new Dimension(maxWidth, maxHeight));
        this.setLayout(new BorderLayout());
        
        for(int i = 0; i < btnNum.length; i++){
            btnNum[i] = new JButton(Integer.toString(i));
        }
        
        display(this);
    }

    void display(JPanel panel){

        JPanel button = displayBtn();
        button.setPreferredSize(new Dimension(50, 20));
        panel.add(button);
    }

    JPanel displayBtn(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3, 5, 5));
        for(int i = 9; i >= 0; i--){
            panel.add(btnNum[i]);
        }

        return panel;
    }
    JPanel displayNumbers(int min, int max){

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(; min <= max; min++){
            panel.add(btnNum[min]);
        }
        return panel;
    }
}
