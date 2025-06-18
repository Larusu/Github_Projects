package Calculator;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Calculator extends JPanel{

    final int maxWidth = 350;
    final int maxHeight = 400;
    
    Calculator(){
        this.setPreferredSize(new Dimension(maxWidth, maxHeight));
    }
}
