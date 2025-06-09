package FallingSand;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, MouseListener, MouseMotionListener{

    final int tileSize = 10; 
    //final int scale = 2;
    public final int finalTileSize = tileSize;

    public final int maxScreenCol = 50; // 16 - 2, since there is no border
    public final int maxScreenRow = 75;
    public final int screenWidth = finalTileSize * maxScreenCol;
    public final int screenHeight = finalTileSize * maxScreenRow;
    int sandTick = 0;

    int[][] grid;

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
    
    public void drawGrid(Graphics g){
        
        for(int row = 0; row < maxScreenRow; row++){
            for(int col = 0; col < maxScreenCol; col++){

                int x = col * tileSize;
                int y = row * tileSize;

                if (grid[row][col] == 1) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(x, y, tileSize, tileSize);
                    
                }
                g.setColor(Color.WHITE);
                g.drawRect(x, y, tileSize, tileSize);
            }
        }
    }

    void updateTile(MouseEvent e) {
        int col = e.getX() / tileSize;
        int row = e.getY() / tileSize;

        if (row >= 0 && row < maxScreenRow && col >= 0 && col < maxScreenCol) {
            grid[row][col] = 1;
        }
    }

    void fallingSandAnimation(){

        int[][] newGrid = new int[maxScreenRow][maxScreenCol];

        for(int i = 0; i < maxScreenRow; i++){
            for(int j = 0; j < maxScreenCol; j++){
                newGrid[i][j] = grid[i][j];
            }
        }

        for(int i = maxScreenRow - 2; i >= 0; i--){
            for(int j = 0; j < maxScreenCol; j++){
                int checkValue = newGrid[i][j];
                if(checkValue == 1){
                    int belowR = grid[i+1][j+1];
                    int belowL = grid[i+1][j-1];
                    if(i < maxScreenRow - 1){
                        if(newGrid[i + 1][j] == 0 ){
                            newGrid[i][j] = 0;
                            newGrid[i + 1][j] = 1;
                        }else if(belowR == 0){
                            newGrid[i][j] = 0;
                            newGrid[i][j + 1] = 1;
                        }else if(belowL == 0){
                            newGrid[i][j] = 0;
                            newGrid[i][j - 1] = 1;
                        }
                    }

                    if (grid[i][j] == 1 && newGrid[i][j] == 1) {
                        newGrid[i][j] = 1;
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