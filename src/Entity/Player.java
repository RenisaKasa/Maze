
package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import maze_draft.GamePanel;
import maze_draft.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
     int speed;

     
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        
         solidArea=new Rectangle();
    solidArea.x=10;
    solidArea.y=16;
    solidArea.width=20;
    solidArea.height=30;
     String direction;
        
       setDefaultValues();
       getPlayerImage();
     
    }
    
    //na duhet vetem nje pjese e lojatrit solid jo e gjitha se ndryshe sdo te leviz dot
    public void setDefaultValues(){
        x=0; //pozicioni i fillimit do te jete 0,0
        y=0;
         speed=4;
    direction= "down";
    }
    
    public void getPlayerImage(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/Player/Y_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/Player/Y_2.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/Player/Y_3.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/Player/Y_4.png"));
           left1= ImageIO.read(getClass().getResourceAsStream("/Player/X_3.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/Player/X_4.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/Player/X_1.png"));                  
            right2= ImageIO.read(getClass().getResourceAsStream("/Player/X_2.png"));
                  
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        //ndryshojme pozicionin e lojtarit
        //cdo pozicion do te ndryshoje me 4 pixels
        
        if(keyH.upPressed==true|| keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){
        if(keyH.upPressed==true){
            direction="up";
           //kur zbritet lojatri shkon posht
        }
        
        else if(keyH.downPressed ==true){
            direction="down";
             //do te shkoje posht sepse teknikisht y rritet (vertikalisht poshte y eshte me i madh)          
        }
        
        else if(keyH.leftPressed==true){
            direction="left";
          
        }
        else if(keyH.rightPressed==true){
            direction="right";
        }
        }
        
        
        //chechk tile collision
        collisionOn=false;
        gp.cChecker.checkTile(this);
        
        //if collision is false player can moe
        if(collisionOn==true){
        switch(direction){
            case"up":
                 y-=speed;
                break;
            case"down":
                y+=speed;
                break;
            case"left":
                  x-=speed;
                  break;
            case"right":
                 x+=speed;
                 break;
                 default:
                 break;
        }
    }
        
        spriteCounter++;
        if(spriteCounter>16){
            if(spriteNum==1){
                spriteNum=2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }
    
    public void draw(Graphics2D g2) {
 //   g2.setColor(Color.white);
   // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

    BufferedImage image= null;
    
    switch(direction){
        case "up":
            if(spriteNum==1){
            image=up1;
        }
            if(spriteNum==2){
            image=up2;
        }
            break;
        case "down":
            if(spriteNum==1){
            image=down1;
            }
            if(spriteNum==2){
            image=down2;
            }
            break;
        case "left":
            if(spriteNum==1){
                image=left1;
            }
            if(spriteNum==2){
                image=left2;
            }
            break;
        case "right":
           if(spriteNum==1){
               image=right1;
           }
           if(spriteNum==2){
               image=right2;
           }
            break;
    }
    
    g2.drawImage(image,x,y, gp.tileSize, gp.tileSize, null);
    }
}
