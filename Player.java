//Callum Thomson
//Sokoban game, maze with crates to be pushed into specific locations
//version 1.0

import java.awt.*;
import javax.swing.*;
public class Player
{
    private int tileX, tileY;
    private Image player;
    
    //Constructor
    public Player()
    {
        ImageIcon img = new ImageIcon("rat.gif");
        player = img.getImage();
        tileX = 1;
        tileY = 1;
    }
    
    //method which returns the players image
    public Image getPlayer()
    {
              return player;
    }
    
    //method which sets the players position (x axis)
    public void setTileX(int x) {
     tileX = x;   
        
    }
    
    //returns the players position (x axis)
    public int getTileX()
    {
        return tileX;
        
    }
    
    //method which sets the players position (y axis)
    public void setTileY(int y) {
     tileY = y;   
        
    }
    
    //returns the players position (y axis)
    public int getTileY()
    {
        return tileY;
    }
    
    //method responsible for moving the player
    public void move(int dx, int dy)
    {
        tileX += dx;
        tileY += dy;
    }
}
