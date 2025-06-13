package FallingSand;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.awt.event.MouseEvent;


import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, MouseListener, MouseMotionListener{

    final int tileSize = 5; 
    //final int scale = 2;
    public final int finalTileSize = tileSize;

    public final int maxScreenCol = 100; // 16 - 2, since there is no border
    public final int maxScreenRow = 150;
    public final int screenWidth = finalTileSize * maxScreenCol;
    public final int screenHeight = finalTileSize * maxScreenRow;
    int sandTick = 0;

    int[][] grid;
    float[][] hueGrid = new float[maxScreenRow][maxScreenCol];

    Thread gameThread;
    
    public Panel(){

        grid = new int[maxScreenRow][maxScreenCol];

        for(int row = 0 ; row < maxScreenRow; row++){
            for(int col = 0; col < maxScreenCol; col++){
                grid[row][col] = 0;
            }
        }
                
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); 
        gameThread.start(); 
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
 
        drawGrid(g);
    }

    public void update(){
        fallingSandAnimation();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime = System.nanoTime(); 
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval; 
            lastTime = currentTime;            
            
            if(delta >= 1){
                update();
                repaint(); 
                delta--;
            }
        }
    }

    Color generateRainbowColor() {
        float hue = new Random().nextFloat();
        return Color.getHSBColor(hue, 1.0f, 1.0f);
    }
    
    public void drawGrid(Graphics g){
        
        for(int row = 0; row < maxScreenRow; row++){
            for(int col = 0; col < maxScreenCol; col++){

                int x = col * tileSize;
                int y = row * tileSize;

                if (grid[row][col] == 1) {
                    float hue = hueGrid[row][col];
                    g.setColor(Color.getHSBColor(hue, 1.0f, 1.0f)); 
                    g.fillRect(x, y, tileSize, tileSize);
                    
                }
                // g.setColor(Color.WHITE);
                // g.drawRect(x, y, tileSize, tileSize);
            }
        }
    }

    void updateTile(MouseEvent e) {
        int col = e.getX() / tileSize;
        int row = e.getY() / tileSize;

        if (row >= 0 && row < maxScreenRow && col >= 0 && col < maxScreenCol) {
            grid[row][col] = 1;
            grid[row+1][col+1] = 1;
            grid[row][col+1] = 1;
            grid[row][col-1] = 1;
            hueGrid[row][col] = 1; 
            hueGrid[row+1][col+1] = 1;
            hueGrid[row][col+1] = 1;
            hueGrid[row][col-1] = 1;
            repaint();
        }
    }

    void fallingSandAnimation(){

        int[][] newGrid = new int[maxScreenRow][maxScreenCol];
        Random r = new Random();   

        for(int i = 0; i < maxScreenRow; i++){
            for(int j = 0; j < maxScreenCol; j++){
                newGrid[i][j] = grid[i][j];
            }
        }
        for(int i = maxScreenRow - 2; i >= 0; i--){
            for(int j = 0; j < maxScreenCol; j++){
                if (grid[i][j] == 1) {
                    hueGrid[i][j] += 0.002f; 
                    if (hueGrid[i][j] > 1.0f) {
                        hueGrid[i][j] -= 1.0f; 
                    }
                }
                int checkValue = newGrid[i][j];
                int rand = r.nextInt(2) + 1;
                if(checkValue == 1){
                    int belowR = -1, belowL = -1;
                    if(j < maxScreenCol - 1){
                        belowR = grid[i+1][j+1];
                    }
                    if(j > 0){
                        belowL = grid[i+1][j-1];
                    }

                    if(i < maxScreenRow - 1){
                        if(newGrid[i + 1][j] == 0 ){
                            newGrid[i][j] = 0;
                            newGrid[i + 1][j] = 1;
                            hueGrid[i+1][j] = hueGrid[i][j];
                            hueGrid[i][j] = 0;
                        }else if(j < maxScreenCol && rand == 1 && belowR == 0){
                            newGrid[i][j] = 0;
                            newGrid[i][j + 1] = 1;
                            hueGrid[i+1][j+1] = hueGrid[i][j];
                            hueGrid[i][j] = 0;
                        }else if(j > 0 && rand == 2 && belowL == 0){
                            newGrid[i][j] = 0;
                            newGrid[i][j - 1] = 1;
                            hueGrid[i+1][j-1] = hueGrid[i][j];
                            hueGrid[i][j] = 0;
                        }
                    }
                }
            }
        }
        grid = newGrid;
    }

    
    @Override
    public void mousePressed(MouseEvent e) { updateTile(e);}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) {  }
    @Override
    public void mouseExited(MouseEvent e) {  }
    @Override
    public void mouseDragged(MouseEvent e) { updateTile(e);}
    @Override
    public void mouseMoved(MouseEvent e) { }
}