//Callum Thomson
//Sokoban game, maze with crates to be pushed into specific locations
//version 1.0

import java.awt.*;
import javax.swing.*;
public class Crate extends Player
{
    private int tileX, tileY;
    private Image crate;
    
    //constructor
    public Crate()
    {
        ImageIcon img = new ImageIcon("crate.png");
        crate = img.getImage();
       
    }
    
    //method returns crate image
    public Image getCrate()
    {
        return crate;
    }

 
}
