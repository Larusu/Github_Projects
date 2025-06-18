package Calculator;

import javax.swing.JFrame;

public class Main extends JFrame{
    
    public static void main(String[] args) {

        Main frame = new Main();
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Calculator calc = new Calculator();
        frame.add(calc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
