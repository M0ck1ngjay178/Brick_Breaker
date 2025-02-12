//import java.awt.font.GraphicAttribute;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
public class Map {
    
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    // Array of rainbow colors
    private Color[] rainbowColors = {
        Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA
    };

    public Map(int row, int col){
       map = new int[row][col];
       for(int i = 0; i < map.length; i++)
       {
            for(int j =0; j< map[0].length; j++)
            {
                map[i][j] = 1;
            }
       } 
       brickWidth = 540/col;
       brickHeight = 150/row;
    }

    public void draw(Graphics2D g ){
        for(int i = 0; i < map.length; i++)
       {
            for(int j =0; j< map[0].length; j++)
            {
                if(map[i][j] > 0){
                    // Use rainbow color cycle
                    Color brickColor = rainbowColors[(i * map[0].length + j) % rainbowColors.length];
                    g.setColor(brickColor);
                    //g.setColor(Color.WHITE);
                    g.fillRect(j* brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j* brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);
                }
            }
       } 

    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
        
    }

}
