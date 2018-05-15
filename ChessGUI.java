
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
    private JPanel Overlay = new JPanel(new FlowLayout());
    private Font OverlayFont = new Font("Sans-Serif", Font.BOLD,40);

    private JPanel MainMenu = (JPanel)getContentPane(); //main menu JPanel
    private JPanel Options = new JPanel(new FlowLayout());
    private Font TitleFont = new Font("Sans-Serif", Font.BOLD,100);
    private Font MenuFont = new Font("Sans-Serif", Font.BOLD,40);
    private JButton Chess960 = new JButton("Chess960");
    private JButton Computer = new JButton("Computer");
    private JButton NewGame = new JButton("New Game");

    private JLayeredPane LayeredPane; //layout manager for JPanel layers

    private JPanel Pane = new JPanel(new BorderLayout());; //chess game JPanel
    private JPanel NumberRight = new JPanel(new GridLayout(8,1));
    private JPanel LetterBottom = new JPanel(new GridLayout(1,8));
    private JPanel BoardPane = new JPanel(new GridLayout(8,8));
    private JPanel LetterPane = new JPanel(new GridLayout(1,8));
    private JPanel NumberPane = new JPanel(new GridLayout(8,1));
    private Font Labeling = new Font("Monospaced", Font.BOLD, 24);

    private ChessPiece[][] images = new ChessPiece[8][8]; //new ChessPiece array for images
    private JButton[][] buttons = new JButton[8][8]; //new JButton array to put on chess board

    private int[][] moveLoc = new int[2][2]; //new int array which stores chess piece location on click and where it wants to go
    private int clickCounter = 0;

    private JButton[][] whiteEaten = new JButton[3][8];
    private JButton[][] blackEaten = new JButton[3][8];

    private Integer top = new Integer(0); //overlay layers
    private Integer middle = new Integer(0);
    private Integer bottom = new Integer(0);

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");
    }

    public void main()
    {
    }

    /**
     * Runs the chess game GUI
     */
    public void runGUI()
    {
        //-------------------------------------------PANE JPANEL - CHESS GAME GUI CODE----------------------------------------
        Pane.setBounds(0,0, 990, 958);

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
            String number = "\u2008" + Integer.toString(Math.abs(i-8)) + "\u2008"; //u2008 is small space for formatting
            JLabel numbers = new JLabel(number);
            numbers.setFont(Labeling);
            NumberPane.add(numbers);

            String letter = "    " + Character.toString(Character.toUpperCase((char)(i+97)));
            JLabel letters = new JLabel(letter);
            letters.setFont(Labeling);
            LetterPane.add(letters);
        }

        for(int i = 0; i <8; i++)
        {
            String number = "\u2008" + Integer.toString(Math.abs(i-8)) + "\u2008"; //u2008 is small space for formatting
            JLabel numbers = new JLabel(number);
            numbers.setFont(Labeling);
            NumberRight.add(numbers);

            String letter = "    " + Character.toString(Character.toUpperCase((char)(i+97)));
            JLabel letters = new JLabel(letter);
            letters.setFont(Labeling);
            LetterBottom.add(letters);
        }

        Pane.add(NumberPane, BorderLayout.WEST);
        Pane.add(LetterPane, BorderLayout.NORTH);
        Pane.add(NumberRight, BorderLayout.EAST);
        Pane.add(LetterBottom, BorderLayout.SOUTH);
        Pane.add(BoardPane, BorderLayout.CENTER);

        LayeredPane = getLayeredPane(); //new layered pane
        LayeredPane.add(MenuPanel(), top, 0); 
        LayeredPane.add(Pane, middle, 1); 
        LayeredPane.add(OverlayPanel(), bottom,2);
        LayeredPane.setVisible(true);

        //Frame here is implicit
        setSize(1000,1000);
        setVisible(true);
        setResizable(false);
    }

    public JPanel MenuPanel()
    {
        //--------------------------------------MAINMENU JPANEL - MAIN MENU GUI CODE-------------------------------------------
        MainMenu.setLayout(new GridLayout(3,1));
        MainMenu.setBounds(-3,415,1000, 200);

        NewGame.setFont(MenuFont);
        NewGame.addActionListener(this);
        NewGame.setBackground(Color.WHITE);

        Chess960.setFont(MenuFont);
        Chess960.addActionListener(this);
        Chess960.setBackground(Color.WHITE);

        Computer.setFont(MenuFont);
        Computer.addActionListener(this);
        Computer.setBackground(Color.WHITE);

        Options.add(NewGame);
        Options.add(Chess960);
        Options.add(Computer);

        JLabel Title = new JLabel("Chess Game", SwingConstants.CENTER);
        Title.setFont(TitleFont);
        
        MainMenu.add(Title);
        MainMenu.add(Options);

        return MainMenu;
    }

    public JPanel OverlayPanel()
    {
        //-----------------------------------------OVERLAY JPANEL - OVERLAY GUI CODE-------------------------------------------
        Overlay.setBounds(0,0,1000,1000);
        Overlay.setBackground(new Color(0,0,0,0));

        JButton whiteResign = new JButton("White Resign");
        whiteResign.setFont(OverlayFont);
        whiteResign.setBackground(Color.WHITE);
        whiteResign.setPreferredSize(new Dimension(300,120));       
        whiteResign.addActionListener(this);

        JButton draw = new JButton("Draw");
        draw.setFont(OverlayFont);
        draw.setBackground(Color.WHITE);
        draw.setPreferredSize(new Dimension(300,120));       
        draw.addActionListener(this);

        JButton blackResign = new JButton("Black Resign");
        blackResign.setFont(OverlayFont);
        blackResign.setBackground(Color.WHITE);
        blackResign.setPreferredSize(new Dimension(300,120));       
        blackResign.addActionListener(this);

        Overlay.add(blackResign);
        Overlay.add(draw);
        Overlay.add(whiteResign);

        return Overlay;
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

        if(e.getActionCommand().equals("Draw"))
        {
            LayeredPane.add(Pane, top, 0);  
            LayeredPane.add(MainMenu, middle, 1);
            LayeredPane.add(Overlay, bottom, 2);
        }

        if(e.getActionCommand().equals("New Game"))
        {
            LayeredPane.add(Pane, top, 0);
            LayeredPane.add(Overlay, middle, 1);  
            LayeredPane.add(MainMenu, bottom, 2);
        }
    }

}
