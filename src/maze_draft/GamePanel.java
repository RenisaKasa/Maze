
package maze_draft;
import Entity.Player;
import Entity.Entity;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //vednosim screen settings
   public  final int originalTilesSize=16;
   public final int scale=3;
   public  final int tileSize= originalTilesSize*scale;
   public final int maxScreenCol=16;
   public  final int maxScreenRow=12;
   public final int screenWidth= tileSize*maxScreenCol;
   public final int screenHeight= tileSize*maxScreenRow;

    
    int FPS= 60;
    private boolean isRunning;

         
    TileManager tileM= new TileManager(this);
    private int finishX = 15; // oordinatat e finishit.
    private int finishY = 11;
    
    
    KeyHandler keyH= new KeyHandler();
    Thread gameThread;
   public CollisionChecker cChecker= new CollisionChecker(this);
    
    Player player= new Player(this,keyH);
    
    //Set player deafult position
    
    int playerX= 100;
    int playerY= 100;
    int playerSpeed=4;
    
  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
    this.requestFocusInWindow();
    
     tileM = new TileManager(this);
             addKeyListener(keyH);
        setFocusable(true);
        requestFocus();
}

  
    public void startGameThread() {
        gameThread = new Thread((Runnable) this);
        gameThread.start();
    }


    @Override
   public void run() {
        
        //sleep method
        double drawInterval= 1000000000/FPS; //perdorim nanosekondar si calculation unit
        //ekrani vizatohet cdo 0.016 sekonda, e vizatojm ekranin 60 here per second
        
        double nextDrawTime= System.nanoTime()+drawInterval;
        
        
        // Implement the code that should run in the game loop
        while (gameThread != null) {
    
        update(); //updateon pozicionin e lojatrit dhe vete player
       repaint();
      
       
            try {
                double remainingTime= nextDrawTime- System.nanoTime();
               remainingTime= remainingTime/1000000;
                
               
               if(remainingTime<0){
                   remainingTime=0;
               }
               
                Thread.sleep((long)remainingTime);
                
                nextDrawTime+=drawInterval;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    
  /*  @Override
    public void run(){
     double drawInterval= 1000000000/FPS;
     double delta=6;
     long lastTime= System.nanoTime();
     long currentTime;
     long timer=6;
     int drawCount=0;
     
     while(gameThread !=null){
         currentTime= System.nanoTime();
         
         delta+=(currentTime -lastTime)/drawInterval;
         timer +=(currentTime-lastTime);
         lastTime=currentTime;
         
     }
     if(delta>=1){
         update();
         repaint();
         delta--;
         drawCount++;
     }
     if(timer>=1000000000){
         System.out.println("FPS: " +drawCount);
         drawCount=0;
         timer=0;
     }*/
    
    public void update(){
       player.update();
    }
    @Override
    public void paintComponent(Graphics g){
       
        super.paintComponent(g);
       
        Graphics2D g2=(Graphics2D)g;
       
        tileM.draw(g2); //duhet para player sepse eshte si nje layer 
        
        player.draw(g2);
     
        g2.dispose(); //dispose of this graphicss context and release any system recoures that is using
        
    }
}