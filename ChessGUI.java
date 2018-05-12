
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

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");
    }

    /**
     * Runs the game
     */
    public void runGame()
    {
        pane = (JPanel)getContentPane();
        pane.setLayout(new BorderLayout());

        JPanel BoardPane = new JPanel();
        BoardPane.setLayout(new GridLayout(8,8));

        JPanel NumberPane = new JPanel();
        NumberPane.setLayout(new GridLayout(8,1));

        JPanel LetterPane = new JPanel();
        LetterPane.setLayout(new GridLayout(1,8));

        JButton[][] buttons = new JButton[8][8];
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                //String loc = Integer.toString(r) + ", " + Integer.toString(c+1);
                if(images[r][c]==null)
                {
                    buttons[r][c] = new JButton();
                }
                else
                {
                    buttons[r][c] = new JButton(new ImageIcon("ChessPieceIcons/BlackBishop.png"));//ChessPiece.toString()
                }
                if(r % 2 != 0&& (c+1)%2 != 0)
                {
                    buttons[r][c].setBackground(new Color(5, 115, 56));
                    buttons[r][c].setFocusPainted(false); //removes show icon border when pressed
                }
                else if(r % 2 == 0&& (c+1)%2 == 0)
                {
                    buttons[r][c].setBackground(new Color(5, 115, 56));
                    buttons[r][c].setFocusPainted(false); //removes show icon border when pressed
                }
                else
                {
                    buttons[r][c].setBackground(new Color(251, 244, 225));
                    buttons[r][c].setFocusPainted(false); //removes show icon border when pressed
                }
                BoardPane.add(buttons[r][c]);
            }
        }

        for(int i = 0; i <8; i++)
        {
            String number = " " + Integer.toString(Math.abs(i-8)) + " ";
            JLabel numbers = new JLabel(number);
            numbers.setFont(new Font("Monospaced", Font.BOLD, 24));
            NumberPane.add(numbers);

            String letter = "     " + Character.toString(Character.toUpperCase((char)(i+97)));
            JLabel letters = new JLabel(letter);
            letters.setFont(new Font("Monospaced", Font.BOLD, 24));
            LetterPane.add(letters);
        }

        JPanel NumberRight = new JPanel(new GridLayout(8,1));
        JPanel LetterBottom = new JPanel(new GridLayout(1,8));
        for(int i = 0; i <8; i++)
        {
            String number = " " + Integer.toString(Math.abs(i-8)) + " ";
            JLabel numbers = new JLabel(number);
            numbers.setFont(new Font("Monospaced", Font.BOLD, 24));
            NumberRight.add(numbers);

            String letter = "     " + Character.toString(Character.toUpperCase((char)(i+97)));
            JLabel letters = new JLabel(letter);
            letters.setFont(new Font("Monospaced", Font.BOLD, 24));
            LetterBottom.add(letters);
        }

        pane.add(NumberPane, BorderLayout.WEST);
        pane.add(LetterPane, BorderLayout.NORTH);
        pane.add(NumberRight, BorderLayout.EAST);
        pane.add(LetterBottom, BorderLayout.SOUTH);
        pane.add(BoardPane, BorderLayout.CENTER);
        //Frame here is implicit
        setSize(1000,1000);
        setVisible(true);
        setResizable(false);
    }

    public void getBoard(ChessPiece[][] board)
    {
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                images[r][c] = board[r][c];
            }
        }
        runGame();
    }

    /**
     */
    public void actionPerformed (ActionEvent e)
    {

    }
}

