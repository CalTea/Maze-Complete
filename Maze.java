//Callum Thomson
//Sokoban game, maze with crates to be pushed into specific locations
//version 1.0

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Maze
{
    private final String[] choices = {"Load Game","Save Game","Restart Game","Restart Level", "Exit"};
    private JMenuBar bar;
    private JMenu optionsMenu;
    private JMenuItem[] optionsMenuItem = new JMenuItem[choices.length];
    private Board board;
    private JFrame frame;
    //main method in which we run the program
    public static void main(String[] args){
        new Maze();
    }
    
    //constructor for maze which sets the options menu and size of the window
    public Maze()
    {
        frame = new JFrame();
        frame.setTitle("Maze Game");
        bar = new JMenuBar();
        frame.setJMenuBar(bar);
        optionsMenu = new JMenu("Options");
        for (int i = 0; i < choices.length; i++)
        {
            optionsMenuItem[i] = new JMenuItem(choices[i]);
            optionsMenuItem[i].addActionListener(new MenuSelection());
            optionsMenu.add(optionsMenuItem[i]);
        }
        board = new Board();
        frame.getContentPane().add(board);
        bar.add(optionsMenu);
        frame.setSize(528,573);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   
    private class MenuSelection implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //Code responsible for taking action when options are selected
            if (e.getSource() == optionsMenuItem[0])
            {
                String message = "Would you like to load this save file?";
                String title = "Load Game";
                board.loadLevel();
            }
            if (e.getSource() == optionsMenuItem[1])
            {
                String message = "Would you like to save the game?";
                String title = "Save Game";
                board.saveLevel();
            }
            if (e.getSource() == optionsMenuItem[2])
            {
                String message = "Would you like to restart the game?";
                String title = "Restart Game";
                board.restartGame();
            }
            if (e.getSource() == optionsMenuItem[3])
            {
                String message = "Would you like to restart the level?";
                String title = "Restart Level";
                board.restartLevel();
            }
            if (e.getSource() == optionsMenuItem[4])
            {
                String message = "Do you really want to exit?";
                String title = "Exit?";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }               
        }  
    }
}

