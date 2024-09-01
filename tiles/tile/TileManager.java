
package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import maze_draft.GamePanel;

public class TileManager {
  GamePanel gp;
public  Tile[] tile;
    int[][] maze; // Represent the maze with 0s and 1s
public int mapTileNum[][];
  
  public TileManager(GamePanel gp)
  {
   this.gp=gp;
   tile= new Tile[5];
   getTileImage();
 loadRandomMaze();   
  initializeMapTileNum(); 
// generateRandomMaze();
  }
  
  
  public void getTileImage(){
      try{
          
          tile[0]= new Tile();
          tile[0].image=ImageIO.read(getClass().getResource("/tiles/tile_png.jpeg"));
    
           tile[1]= new Tile();
          tile[1].image=ImageIO.read(getClass().getResource("/tiles/grass_png.jpeg"));
     tile[1].collision=true;
     
     tile[4]=new Tile();
     tile[4].image=ImageIO.read(getClass().getResource("/tiles/door.png"));
      
      }catch(IOException e){
          e.printStackTrace();
      }
  }
  
  
   private void loadRandomMaze() {
        Random random = new Random();
        int mazeIndex = random.nextInt(5) + 1; // Assuming you have 15 maze files (named maze1.txt, maze2.txt, ..., maze15.txt)
      String mazeFileName = "/mazes/maze_" + mazeIndex + ".txt";

        try {
            maze = loadMazeFromFile(mazeFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
   public Tile getTile(int index) {
        if (index >= 0 && index < tile.length) {
            return tile[index];
        } else {
            return null;
        }
    }
 /*   private void generateRandomMaze() {
        int mazeRows = gp.maxScreenRow; // Adjust this based on your max screen rows
        int mazeCols = gp.maxScreenCol; // Adjust this based on your max screen columns
        maze = new int[mazeRows][mazeCols];
        Random random = new Random();

        // Fill the maze with random 0s and 1s
        for (int i = 0; i < mazeRows; i++) {
            for (int j = 0; j < mazeCols; j++) {
                maze[i][j] = random.nextInt(2); // Generates 0 or 1
            }
        }
    }

    public void draw(Graphics2D g2) {
        // Draw the maze based on the generated values
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                int x = j * gp.tileSize;
                int y = i * gp.tileSize;

                if (maze[i][j] == 0) {
                    // Draw tile (t_1)
                    g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
                } else {
                    // Draw tree (pem)
                    g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}*/
   
private int[][] loadMazeFromFile(String fileName) throws IOException {
    try (InputStream inputStream = getClass().getResourceAsStream(fileName)) {
        if (inputStream == null) {
            System.err.println("File not found: " + fileName);
            return null;
        }

        try (Scanner scanner = new Scanner(inputStream)) {
            // Read the dimensions of the maze
            int rows = 12;
            int cols = 16;

            int[][] maze = new int[rows][cols];

            // Read the maze content
            for (int i = 0; i < rows; i++) {
                if (scanner.hasNext()) {
                    String binaryString = scanner.next();
                    for (int j = 0; j < cols; j++) {
                        if (j < binaryString.length()) {
                            maze[i][j] = Character.getNumericValue(binaryString.charAt(j));
                        } else {
                            System.err.println("Insufficient data in the maze file.");
                            return null;
                        }
                    }
                } else {
                    System.err.println("Insufficient data in the maze file.");
                    return null;
                }
            }

            return maze;
        }
    }
}

    private void initializeMapTileNum() {
        if (maze != null) {
            int rows = maze.length;
            int cols = maze[0].length;
            mapTileNum = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    mapTileNum[i][j] = maze[i][j];
                }
            }
        }
    }



   public void draw(Graphics2D g2) {
        // Draw the maze based on the loaded values
        if (maze != null) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    int x = j * gp.tileSize;
                    int y = i * gp.tileSize;

                    if (maze[i][j] == 0) {
                        // Draw tile (t_1)
                        g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
                    }else if(maze[i][j]==4){
                        g2.drawImage(tile[4].image, x,y, gp.tileSize, gp.tileSize,null);
                    } 
                    else {
                        // Draw tree (pem)
                        g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
}
