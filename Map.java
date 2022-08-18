//Callum Thomson
//Sokoban game, maze with crates to be pushed into specific locations
//version 1.0

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Map
{
    private String fileName;
    private Scanner readMap;
    private String[][] map = new String[16][16];
    private Image floor, wall, finish;
    
    //Constructor for map which tells the program which assets to use for the map
    public Map(String theFileName)
    {
        ImageIcon img = new ImageIcon("floor.gif");
        floor = img.getImage();
        img = new ImageIcon("wall.gif");
        wall = img.getImage();
        img = new ImageIcon("finish.gif");
        finish = img.getImage();
        fileName = theFileName;
        openFile();
        readFile();
        closeFile();
    }
    
    //method which returns the image for the floor asset
    public Image getFloor()
    {
        return floor;
    }
    
    //method which returns the image for the wall asset
    public Image getWall()
    {
        return wall;
    }
    
    //method which returns the image for the finish asset
    public Image getFinish()
    {
        return finish;
    }
    
    //method which returns the contents of the map (layout)
    public String getMap(int x, int y)
    {
        return map[x][y];
    }
    
    //method which opens appropriate file name
    public void openFile(){
        try{
            readMap = new Scanner(new File(fileName));
        }catch(Exception e){ //is map cannot be found or the layout is invalid, exception is thrown
            System.out.println("error loading map");
        }
    }
    
    //method which reads through the contents of the map line by line
    public void readFile(){
        while(readMap.hasNext())
        {
            for(int i = 0; i < 16 ; i++)
            {
               String line = readMap.next();
               for(int j = 0; j < 16; j++)
               {
                   String ch = line.substring(j, j+1);
                   map[j][i] = ch;
                }
            }
        }
    }
    
    //method which closes the file
    public void closeFile(){
        readMap.close();
    }
}
