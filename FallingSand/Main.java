package FallingSand;

import javax.swing.JFrame;

public class Main extends JFrame{
    public static void main(String[] args) {
        Main display = new Main();
        display.setTitle("Falling sand simulation");
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        display.setResizable(false);

        Panel newPanel = new Panel();
        display.add(newPanel);
        newPanel.startGameThread();
        display.pack();
        
        display.setLocationRelativeTo(null); 
        display.setVisible(true);
        
    }
}