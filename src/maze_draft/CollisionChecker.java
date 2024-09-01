
package maze_draft;

import Entity.Entity;

public class CollisionChecker{
    
GamePanel gp;
public  CollisionChecker (GamePanel gp){
    this.gp=gp;
    
}

public void checkTile(Entity entity){
    
     if (entity == null) {
        // Handle the case where entity is null
        return;
    }
    //do kontrollon 4 brinjet e katrorit
    
    int entityLeftX=entity.x+entity.solidArea.x;
    int entityRightX= entity.x+ entity.solidArea.x+ entity.solidArea.width;
    int entityTopY= entity.y+ entity.solidArea.y;
    int entityBottomY= entity.y+entity.solidArea.y+ entity.solidArea.height;
    
    int entityLeftCol= entityLeftX/gp.tileSize;
    int entityRightCol= entityRightX/gp.tileSize;
    int entityTopRow= entityTopY/gp.tileSize;
    int entityBottomRow= entityBottomY/gp.tileSize;
    
    int tileNum1, tileNum2; //na duhet te kontrollojme vetem 2 tiles per cdo drejtim
    //nese lohtari shkon lart players left shoulder do prek the tile
    
    switch(entity.direction){
        case "up":
            //parashikojme pakashum ku do e jete lojtari
            //bejme diferencen per te qene me preciz
            //keshtu njohim cilin tile lojtari is trying to step in
            entityTopRow=(entityTopY-entity.speed)/gp.tileSize;
            tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
           if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true){
               entity.collisionOn=true;
           }
            
            break;
        case "down":
               entityBottomRow=(entityBottomY+entity.speed)/gp.tileSize;
            tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
           if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true){
               entity.collisionOn=true;
           }
            break;
        case "left":
    entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
        entity.collisionOn = true;
    }
    break;
           
        case "right":
               entityRightCol=(entityRightX+entity.speed)/gp.tileSize;
            tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
           if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true){
               entity.collisionOn=true;
           }
            break;
            
        default:
            break;
    }
    
    
}
}

