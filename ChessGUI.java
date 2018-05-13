
/**
 * GUI layout for our chess game
 *
 * @author Justin Chu
 * @version May 3rd, 2018
 */
//import statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
public class ChessGUI
{
    public static void main (String [] args)
    {
        //creates a new JFrame
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
    //instance variables
    private ChessPiece[][] images = new ChessPiece[8][8]; //new ChessPiece array for images
    private JButton[][] buttons = new JButton[8][8]; //new JButton array to put on chess board
    private int[][] moveLoc = new int[2][2]; //new int array which stores chess piece location on click and where it wants to go
    private int clickCounter = 0;

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");
    }

    /**
     * Runs the GUI
     */
    public void runGUI()
    {
        JPanel pane = (JPanel)getContentPane(); //new JPanel
        pane.setLayout(new BorderLayout());

        JPanel BoardPane = new JPanel();
        BoardPane.setLayout(new GridLayout(8,8));

        JPanel NumberPane = new JPanel();
        NumberPane.setLayout(new GridLayout(8,1));

        JPanel LetterPane = new JPanel();
        LetterPane.setLayout(new GridLayout(1,8));

        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                //String loc = Integer.toString(r) + ", " + Integer.toString(c+1);
                if(images[r][c]==null)
                {
                    buttons[r][c] = new JButton();
                    buttons[r][c].addActionListener(this);
                }
                else
                {
                    buttons[r][c] = new JButton(new ImageIcon(images[r][c].toString()));
                    buttons[r][c].addActionListener(this);
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

            String letter = "    " + Character.toString(Character.toUpperCase((char)(i+97)));
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

            String letter = "    " + Character.toString(Character.toUpperCase((char)(i+97)));
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

    /**
     * Updates the GUI board
     */
    public void changeBoard(ChessPiece[][] board)
    {
        images = board;
        runGUI();
    }

    /**
     * When button is pressed
     */
    public void actionPerformed (ActionEvent e)
    {
        if(e.getSource() instanceof JButton)
        {
            for(int r = 0; r < 8; r++)
            {
                for(int c = 0; c < 8; c++)
                {
                    if(buttons[r][c] == e.getSource())
                    {
                        if(images[r][c] instanceof ChessPiece)
                        {
                            moveLoc[clickCounter][0] = r;
                            moveLoc[clickCounter][1] = c;
                            System.out.println(r);
                            System.out.println(c);
                            clickCounter++;
                            if(clickCounter == 2)
                            {
                                clickCounter = 0;
                                //check if piece can move to that location
                            }
                        }
                        else if(clickCounter == 1)
                        {
                            moveLoc[clickCounter][0] = r;
                            moveLoc[clickCounter][1] = c;
                            System.out.println(r);
                            System.out.println(c);
                            clickCounter++;
                            if(clickCounter == 2)
                            {
                                clickCounter = 0;
                                //check if piece can move to that location
                            }   
                        }
                    }
                }
            }
        }
    }
}
