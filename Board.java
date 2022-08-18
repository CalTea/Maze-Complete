//Callum Thomson
//Sokoban game, maze with crates to be pushed into specific locations
//version 1.0

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Board extends JPanel implements ActionListener
{
    private Timer timer;
    private Map map;
    private Player player;
    private Crate crate, crate1;

    private boolean win = false;
    private Font font = new Font("serif", Font.BOLD, 48);
    public int level = 1;
    private int moves = 0;

    //construcor for board, setting default values and enabling user interaction
    public Board()
    {
        map = new Map("map.txt");
        player = new Player();
        crate = new Crate();
        crate.setTileX(5);
        crate.setTileY(5);
        crate1 = new Crate();
        crate1.setTileX(6);
        crate1.setTileY(4);
        addKeyListener(new Al());
        setFocusable(true);
        timer = new Timer(25, this);
        timer.start();

    }

    public void actionPerformed(ActionEvent e){
        //if both crates reach the final square "x", then level will progress
        if (map.getMap(crate.getTileX(),crate.getTileY()).equals("x") && map.getMap(crate1.getTileX(),crate1.getTileY()).equals("x")) {
            level++; 
            //sets details about future levels and object positions
            if (level == 2)
            {
                map = new Map("map2.txt");
                player = new Player();
                crate = new Crate();
                crate1 = new Crate();
                crate.setTileX(5);
                crate.setTileY(5);
                crate1.setTileX(2);
                crate1.setTileY(3);            
            }
            else if (level == 3)
            {
                map = new Map("map3.txt");
                player = new Player();
                crate.setTileX(5);
                crate.setTileY(3);
                crate1.setTileX(4);
                crate1.setTileY(2);                  
            }
            else if (level == 4) 
            {
                map = new Map("map4.txt");
                player = new Player();                 
                crate.setTileX(4);
                crate.setTileY(5);
                crate1.setTileX(4);
                crate1.setTileY(3);
            }
            else if (level == 5)
            {
                map = new Map("map5.txt");
                player = new Player();
                player.setTileX(3);
                crate.setTileX(5);
                crate.setTileY(5);
                crate1.setTileX(8);
                crate1.setTileY(6);     
            }
            else {
                win = true;
            }
        }

        repaint();
    }

    /**
     * Method paint
     *
     * @param g A parameter
     */
    public void paint(Graphics g){
        super.paint(g);
        if (!win)
        {     
            //draws the map, read from text file
            for (int y = 0; y < 16; y++)
            {
                for (int x = 0; x <16; x++)
                {
                    if (map.getMap(x,y).equals ("f"))
                    {
                        g.drawImage(map.getFloor(),x*32,y*32, null);
                    }
                    if (map.getMap(x,y).equals ("w"))
                    {
                        g.drawImage(map.getWall(),x*32,y*32, null);
                    }
                    if (map.getMap(x,y).equals ("x"))
                    {
                        g.drawImage(map.getFinish(),x*32,y*32, null);
                    }
                }
            }
            //draws the player and crates
            g.drawImage(player.getPlayer(),player.getTileX()*32,player.getTileY()*32, null);          
            g.drawImage(crate.getCrate(),crate.getTileX()*32,crate.getTileY()*32, null);
            g.drawImage(crate1.getCrate(),crate1.getTileX()*32,crate1.getTileY()*32, null);

        }
        //if the player wins then a winner message is displayed
        if(win)
        {
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("Winner!!!", 150, 300);

        }
    }

    //method which will save the game
    public void saveLevel() {
        try
        {
            //creates a new file for the save to take place
            FileWriter saveFile = new FileWriter("saveGame.txt");
            BufferedWriter out = new BufferedWriter(saveFile);

            //writes to the text file the current level the user is on
            out.write("Level:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(level));
            out.write(System.getProperty("line.separator"));

            //writes to the text file the players x axis position
            out.write("Player X:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(player.getTileX()));
            out.write(System.getProperty("line.separator"));

            //writes to the text file the players y axis position
            out.write("Player Y:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(player.getTileY()));
            out.write(System.getProperty("line.separator"));

            //writes to the text file the first crates x axis position
            out.write("Crate X:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(crate.getTileX()));
            out.write(System.getProperty("line.separator"));

            //writes to the text file the first crates y axis position
            out.write("Crate Y:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(crate.getTileY()));
            out.write(System.getProperty("line.separator"));

            //writes to the text file the second crates x axis position
            out.write("Crate1 X:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(crate1.getTileX()));
            out.write(System.getProperty("line.separator"));

            //writes to the text file the second crates y position
            out.write("Crate1 Y:");
            out.write(System.getProperty("line.separator"));
            out.write(String.valueOf(crate1.getTileY()));
            out.write(System.getProperty("line.separator"));
            out.close();

        }
        //exception handling incase game cannot be saved
        catch (Exception e)
        {
            System.out.println("File Error");
        }
    }

    //method which will allow the user to load the game from its current save state
    public void loadLevel()
    {
        String[] list1 = new String[14];
        String u_line = null;
        File f1 = new File ("saveGame.txt");
        //if the game can detect a save file
        if (f1.exists())
        {
            try
            {
                //initialise file reader so that the file can be processed
                FileReader fileReader1 = new FileReader(f1);
                BufferedReader bufferedFileReader1 = new BufferedReader(fileReader1);
                int count = 0;
                //goes through each line of the level
                while ((u_line = bufferedFileReader1.readLine()) != null)
                {
                    list1[count] = u_line;
                    count++;
                }

                bufferedFileReader1.close();
            } //exception called if the file could not be found
            catch (FileNotFoundException ex)
            {
                System.out.println("Unable to open file");

            }//exception if there is any other reason that the file could not be utilised
            catch (IOException ex)
            {
                System.out.println("Error reading file");
            }
            //reads the buffered reader to obtain information about objects position
            level = Integer.parseInt(list1[1]);
            player.setTileX(Integer.parseInt(list1[3]));
            player.setTileY(Integer.parseInt(list1[5]));
            crate.setTileX(Integer.parseInt(list1[7]));
            crate.setTileY(Integer.parseInt(list1[9]));
            crate1.setTileX(Integer.parseInt(list1[11]));
            crate1.setTileY(Integer.parseInt(list1[13]));
            //depending on the saved level, appropriate one will be loaded
            if (level == 1)
            {
                map = new Map ("map.txt");
            }
            else if (level == 2)
            {
                map = new Map ("map2.txt");
            }
            else if (level == 3)
            {
                map = new Map("map3.txt");
            }
            else if (level == 4)
            {
                map = new Map ("map4.txt");
            }
            else if (level == 5)
            {
                map = new Map ("map5.txt"); 
            }
            repaint();
        }
    }

    //method which will restart the level to its initial starting state
    public void restartLevel() {
        //sets the objects back to their default position depending on the level
        if (level == 2)
        {
            map = new Map("map2.txt");
            player = new Player();
            crate = new Crate();
            crate1 = new Crate();
            crate.setTileX(5);
            crate.setTileY(5);
            crate1.setTileX(2);
            crate1.setTileY(3);            
        }
        else if (level == 3)
        {
            map = new Map("map3.txt");
            player = new Player();
            crate.setTileX(5);
            crate.setTileY(3);
            crate1.setTileX(4);
            crate1.setTileY(2);                  
        }
        else if (level == 4) 
        {
            map = new Map("map4.txt");
            player = new Player();                 
            crate.setTileX(4);
            crate.setTileY(5);
            crate1.setTileX(4);
            crate1.setTileY(3);
        }
        else if (level == 5)
        {
            map = new Map("map5.txt");
            player = new Player();
            player.setTileX(3);
            crate.setTileX(5);
            crate.setTileY(5);
            crate1.setTileX(8);
            crate1.setTileY(6);     
        } //unless the player is on level 1, then the game will just reset
        else {
            restartGame();
        }
    }

    //method which will completley reset the game back to its default state
    public void restartGame() {
        //setting the level, map and objects positions back to default values
        level = 1;
        map = new Map("map.txt");
        player = new Player();
        crate = new Crate();
        crate.setTileX(5);
        crate.setTileY(5);
        crate1 = new Crate();
        crate1.setTileX(6);
        crate1.setTileY(4);
        repaint();

    }
    public class Al extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            int keycode = e.getKeyCode();
            if(keycode == KeyEvent.VK_UP) //if the up arrowkey is pressed
            {
                //moves the player
                player.move(0,-1);
                if(map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate.getTileX(),crate.getTileY()))){ //if player position is same as crate then move crate also
                    crate.move(0,-1);
                }
                if((crate1 != null) && (map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){ //if player position is same as crate1 then move crate also
                    crate1.move(0,-1);
                }
                if(map.getMap(player.getTileX(),player.getTileY()).equals("w")){ //if player is against wall stop player
                    player.move(0,1);
                }
                if(map.getMap(crate.getTileX(),crate.getTileY()).equals("w")){ //if crate is against wall stop crate
                    crate.move(0,1);
                    player.move(0,1);
                }
                if((crate1 != null) && (map.getMap(crate1.getTileX(),crate1.getTileY()).equals("w"))){ 
                    crate1.move(0,1);
                    player.move(0,1);
                }
                if((crate1 != null) && (map.getMap(crate.getTileX(),crate.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){
                    crate1.move(0,1);
                    player.move(0,1);
                }
            }
            if(keycode == KeyEvent.VK_DOWN) //if the down arrowkey is pressed
            {
                //moves the player
                player.move(0,1);          
                if(map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate.getTileX(),crate.getTileY()))) { //if player position is same as crate then move crate also
                    crate.move(0,1);
                }
                if((crate1 != null) && (map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){ //if player position is same as crate1 then move crate also
                    crate1.move(0,1);
                }
                if(map.getMap(player.getTileX(),player.getTileY()).equals("w")){ //if player is against wall then stop player
                    player.move(0,-1);
                }
                if(map.getMap(crate.getTileX(),crate.getTileY()).equals("w")){  //if crate is against wall then stop crate
                    crate.move(0,-1);
                    player.move(0,-1);
                }
                if((crate1 != null) && (map.getMap(crate1.getTileX(),crate1.getTileY()).equals("w"))){ 
                    crate1.move(0,-1);
                    player.move(0,-1);
                }
                if((crate1 != null) && (map.getMap(crate.getTileX(),crate.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){
                    crate1.move(0,-1);
                    player.move(0,-1);
                }
            }
            if(keycode == KeyEvent.VK_LEFT) //if the left arrowkey is pressed
            {
                //moves the player
                player.move(-1,0);
                if(map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate.getTileX(),crate.getTileY()))){ //if player position is same as crate then move crate also
                    crate.move(-1,0);
                }
                if((crate1 != null) && (map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){ 
                    crate1.move(-1,0);
                }
                if(map.getMap(player.getTileX(),player.getTileY()).equals("w")){ //if player against wall then stop player
                    player.move(1,0);
                }
                if(map.getMap(crate.getTileX(),crate.getTileY()).equals("w")){ //if crate against wall then stop crate
                    crate.move(1,0);
                    player.move(1,0);
                }
                if(( crate1 != null) && (map.getMap(crate1.getTileX(),crate1.getTileY()).equals("w"))){ 
                    crate1.move(1,0);
                    player.move(1,0);
                }
                if((crate1 != null) && (map.getMap(crate.getTileX(),crate.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){
                    crate1.move(1,0);
                    player.move(1,0);
                }
            }
            if(keycode == KeyEvent.VK_RIGHT) //if the right arrowkey is pressed
            {
                //moves the player
                player.move(1,0);
                if(map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate.getTileX(),crate.getTileY()))){ //if player position is same as crate then move crate also
                    crate.move(1,0);
                }
                if((crate1 != null) && (map.getMap(player.getTileX(),player.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){ 
                    crate1.move(1,0);
                }
                if(map.getMap(player.getTileX(),player.getTileY()).equals("w")){ //if player hits wall stop player
                    player.move(-1,0);
                }
                if(map.getMap(crate.getTileX(),crate.getTileY()).equals("w")){ //if crate against wall stop crate
                    crate.move(-1,0);
                    player.move(-1,0);
                }
                if((crate1 != null) && (map.getMap(crate1.getTileX(),crate1.getTileY()).equals("w"))){ 
                    crate1.move(-1,0);
                    player.move(-1,0);
                }
                if((crate1 != null) && (map.getMap(crate.getTileX(),crate.getTileY()) == (map.getMap(crate1.getTileX(),crate1.getTileY())))){
                    crate1.move(-1,0);
                    player.move(-1,0);
                }
            }
        }
    }
}

