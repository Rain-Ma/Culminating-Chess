
/**
 * GUI layout for our chess game
 *
 * @author Justin Chu
 * @version May 3rd, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
public class ChessGUI
{
    public static void main (String [] args)
    {
        GameFrame frame = new GameFrame();
        frame.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }
            }
        );
    }
}

//Essentially a JFrame, except it reacts to button events
class GameFrame extends JFrame implements ActionListener
{
    //We'll make our JPanel an instance variable so we can use it in multiple methods
    private JPanel pane;
    ChessPiece[][] images = new ChessPiece[8][8];

    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");

        //We are in the frame (ColourFrame) whose Content Pane that we want, we can't say frame.getContentPane()
        pane = (JPanel)getContentPane();
        pane.setLayout(new GridLayout(8,8));

        JButton[][] buttons = new JButton[8][8];
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                //String loc = Integer.toString(r) + ", " + Integer.toString(c+1);
                if(images[r][c]==null)
                {
                    buttons[r][c] = new JButton();
                    System.out.println("ok");    
                }
                else
                {
                    buttons[r][c] = new JButton(new ImageIcon("ChessPieceIcons/BlackPawn.png"));
                    System.out.println("no");    
                }
                if(r % 2 != 0&& (c+1)%2 != 0)
                {
                    buttons[r][c].setBackground(Color.BLACK);
                    buttons[r][c].setForeground(buttons[r][c].getBackground());
                }
                else if(r % 2 == 0&& (c+1)%2 == 0)
                {
                    buttons[r][c].setBackground(Color.BLACK);
                }
                else
                {
                    buttons[r][c].setBackground(Color.WHITE);
                }
                pane.add(buttons[r][c]);
            }
        }

        //Frame here is implicit
        setSize(1000,1000);
        setVisible(true);
    }

    public void getBoard(ChessPiece[][] board)
    {
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                images[r][c] = board[r][c];
                System.out.println(images[r][c]);
            }
        }
    }

    /**
     */
    public void actionPerformed (ActionEvent e)
    {

    }
}

