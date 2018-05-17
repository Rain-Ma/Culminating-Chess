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
    private JButton WhiteResign = new JButton("White Resign");
    private JButton Draw = new JButton("Draw");
    private JButton BlackResign = new JButton("Black Resign");

    private JPanel MainMenu = (JPanel)getContentPane(); //main menu JPanel
    private JPanel Options = new JPanel(new FlowLayout());
    private Font TitleFont = new Font("Sans-Serif", Font.BOLD,150);
    private Font MenuFont = new Font("Sans-Serif", Font.BOLD,52);
    private JButton Chess960 = new JButton("Chess960");
    private JButton Computer = new JButton("Computer");
    private JButton NewGame = new JButton("New Game");

    private JLayeredPane LayeredPane; //layout manager for JPanel layers

    private JPanel Pane = new JPanel(new BorderLayout()); //chess game JPanel
    private boolean firstTime = true;
    private JPanel NumberRight = new JPanel(new GridLayout(8,1));
    private JPanel LetterBottom = new JPanel(new GridLayout(1,8));
    private JPanel BoardPane = new JPanel(new GridLayout(8,8));
    private JPanel LetterPane = new JPanel(new GridLayout(1,8));
    private JPanel NumberPane = new JPanel(new GridLayout(8,1));
    private Font Labeling = new Font("Monospaced", Font.BOLD, 24);

    private JPanel Score = new JPanel(new GridLayout(3,1));
    private JPanel WhiteContainer = new JPanel(new BorderLayout());
    private JPanel WhiteSide = new JPanel(new GridLayout(4,8));
    private JPanel BlackContainer = new JPanel(new BorderLayout());
    private JPanel BlackSide = new JPanel(new GridLayout(4,8));
    private JPanel Middle = new JPanel(new FlowLayout());
    private JLabel WhiteEaten = new JLabel("White Pieces Eaten:", SwingConstants.CENTER);
    private JLabel BlackEaten = new JLabel("Black Pieces Eaten:", SwingConstants.CENTER);
    private JButton Exit = new JButton("Exit");
    private JButton Help = new JButton("Help");
    private Font ScoreFont = new Font("Sans-Serif", Font.BOLD, 30);
    private ChessPiece[] DeadBlack = new ChessPiece[32];
    private ChessPiece[] DeadWhite = new ChessPiece[32];

    private ChessPiece[][] GameBoard = new ChessPiece[8][8]; //new ChessPiece array for GameBoard
    private JButton[][] buttons = new JButton[8][8]; //new JButton array to put on chess board
    private boolean firstBoard = true;

    private int oldRow;
    private int oldCol;
    private int newRow;
    private int newCol;
    private int clickCounter = 0;

    private Integer top = new Integer(0); //overlay layers
    private Integer middle = new Integer(0);
    private Integer bottom = new Integer(0);

    private Board board = new Board();

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");
    }

    /**
     * Starts a new game
     */
    public void startGame()
    {
        //black pieces
        GameBoard[7][0] = new Rook(1,1,true);
        GameBoard[7][1] = new Knight(1,2,true);
        GameBoard[7][2] = new Bishop(1,3,true);
        GameBoard[7][3] = new Queen(1,4,true);
        GameBoard[7][4] = new King(1,5,true);
        GameBoard[7][5] = new Bishop(1,6,true);
        GameBoard[7][6] = new Knight(1,7,true);
        GameBoard[7][7] = new Rook(1,8,true);
        for(int i = 0; i < 8; i++)
        {
            GameBoard[6][i] = new Pawn(2, i+1, true);
        }

        //white pieces
        GameBoard[0][0] = new Rook(8,1,false);
        GameBoard[0][1] = new Knight(8,2,false);
        GameBoard[0][2] = new Bishop(8,3,false);
        GameBoard[0][3] = new Queen(8,4,false);
        GameBoard[0][4] = new King(8,5,false);
        GameBoard[0][5] = new Bishop(8,6,false);
        GameBoard[0][6] = new Knight(8,7,false);
        GameBoard[0][7] = new Rook(8,8,false);
        for(int i = 0; i < 8; i++)
        {
            GameBoard[1][i] = new Pawn(7, i+1, false);
        }

        GamePane();
    }

    public JPanel RunGUI()
    {
        BoardPane.removeAll();
        if(firstBoard)
        {
            for(int r = 0; r < 8; r++)
            {
                for(int c = 0; c < 8; c++)
                {
                    //String loc = Integer.toString(r) + ", " + Integer.toString(c+1);
                    if(GameBoard[r][c]==null)
                    {   
                        buttons[r][c] = new JButton();
                        buttons[r][c].addActionListener(this);
                    }
                    else
                    {
                        buttons[r][c] = new JButton(new ImageIcon(GameBoard[r][c].toString()));
                        buttons[r][c].addActionListener(this);
                    }
                    if(r % 2 != 0&& (c+1)%2 != 0 || r % 2 == 0&& (c+1)%2 == 0)
                    {
                        buttons[r][c].setBackground(new Color(5, 115, 56));
                        buttons[r][c].setFocusPainted(false); //removes button border when pressed
                    }
                    else
                    {
                        buttons[r][c].setBackground(new Color(251, 244, 225));
                        buttons[r][c].setFocusPainted(false); //removes show icon border when pressed
                    }
                    BoardPane.add(buttons[r][c]);
                }
            }
        }
        else
        {
            for(int r = 0; r < 8; r++)
            {
                for(int c = 0; c < 8; c++)
                {
                    //String loc = Integer.toString(r) + ", " + Integer.toString(c+1);
                    if(GameBoard[r][c]==null)
                    {   
                    }
                    else
                    {
                        buttons[r][c].setIcon(new ImageIcon(GameBoard[r][c].toString()));
                    }
                    if(r % 2 != 0&& (c+1)%2 != 0 || r % 2 == 0&& (c+1)%2 == 0)
                    {
                        buttons[r][c].setBackground(new Color(5, 115, 56));
                        buttons[r][c].setFocusPainted(false); //removes button border when pressed
                    }
                    else
                    {
                        buttons[r][c].setBackground(new Color(251, 244, 225));
                        buttons[r][c].setFocusPainted(false); //removes show icon border when pressed
                    }
                    BoardPane.add(buttons[r][c]);
                }
            }
        }
        return BoardPane;
    }

    /**
     * Runs the chess game GUI
     */
    public void GamePane()
    {
        //-------------------------------------------PANE JPANEL - CHESS GAME GUI CODE----------------------------------------
        Pane.setBounds(0, 0, 990, 958);
        Pane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        NumberPane.removeAll();
        LetterPane.removeAll();
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

        NumberRight.removeAll();
        LetterBottom.removeAll();
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
        Pane.removeAll();
        Pane.add(NumberPane, BorderLayout.WEST);
        Pane.add(LetterPane, BorderLayout.NORTH);
        Pane.add(NumberRight, BorderLayout.EAST);
        Pane.add(LetterBottom, BorderLayout.SOUTH);
        Pane.add(RunGUI(), BorderLayout.CENTER);

        LayeredPane = getLayeredPane(); //new layered pane
        LayeredPane.setVisible(true);
        /*if(firstTime == true)
        {
        LayeredPane.add(MenuPanel(), top, 0); 
        System.out.println("first");
        firstTime = false;
        }
         */
        firstTime = false;
        if(firstTime == false)
        {
            LayeredPane.add(Pane, top, 0);
            LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), middle, 1);
        }

        //Frame here is implicit
        setSize(1500,1000);
        setVisible(true);
        setResizable(false);
    }

    public JPanel MenuPanel()
    {
        //--------------------------------------MAINMENU JPANEL - MAIN MENU GUI CODE-------------------------------------------
        MainMenu.setLayout(new GridLayout(3,1));
        MainMenu.setBounds(-3,415,1002, 200);
        MainMenu.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        NewGame.setFont(MenuFont);
        NewGame.addActionListener(this);
        NewGame.setBackground(Color.WHITE);
        NewGame.setPreferredSize(new Dimension(320,150)); 
        NewGame.setFocusPainted(false);

        Chess960.setFont(MenuFont);
        Chess960.addActionListener(this);
        Chess960.setBackground(Color.WHITE);
        Chess960.setPreferredSize(new Dimension(320,150)); 
        Chess960.setFocusPainted(false);

        Computer.setFont(MenuFont);
        Computer.addActionListener(this);
        Computer.setBackground(Color.WHITE);
        Computer.setPreferredSize(new Dimension(320,150));
        Computer.setFocusPainted(false);

        Options.setBackground(new Color(0,0,0,0));
        Options.add(NewGame);
        Options.add(Chess960);
        Options.add(Computer);

        JLabel Title = new JLabel("Chess Game", SwingConstants.CENTER);
        Title.setBackground(new Color(0,0,0,0));
        Title.setFont(TitleFont);

        MainMenu.add(Title);
        MainMenu.add(Options);

        return MainMenu;
    }

    public JPanel ScorePanel(ChessPiece[] BlackPieces, ChessPiece[] WhitePieces)
    {
        Score.setBounds(987, 0, 505, 958);
        Score.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        for(int i = 0; i < 32; i++)
        {
            if(BlackPieces[i] == null)
                break;
            else
                BlackSide.add(new JButton(BlackPieces[i].toString()));

            if(WhitePieces[i] == null)
                break;
            else
                WhiteSide.add(new JButton(WhitePieces[i].toString()));
        }

        BlackEaten.setFont(ScoreFont);
        BlackContainer.add(BlackEaten, BorderLayout.NORTH);
        BlackContainer.add(BlackSide, BorderLayout.CENTER);
        BlackContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));

        WhiteEaten.setFont(ScoreFont);
        WhiteContainer.add(WhiteEaten, BorderLayout.NORTH);
        WhiteContainer.add(WhiteSide, BorderLayout.CENTER);
        WhiteContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));

        WhiteResign.addActionListener(this);
        BlackResign.addActionListener(this);
        Draw.addActionListener(this);
        Exit.addActionListener(this);
        Help.addActionListener(this);

        Middle.add(Help);
        Middle.add(WhiteResign);
        Middle.add(Draw);
        Middle.add(BlackResign);
        Middle.add(Exit);
        Middle.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));

        Score.add(WhiteContainer);
        Score.add(Middle);
        Score.add(BlackContainer);

        return Score;
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
                        if(GameBoard[r][c] instanceof ChessPiece)
                        {
                            oldRow = r;
                            oldCol = c;
                            clickCounter++;
                            if(clickCounter == 2)
                            {
                                clickCounter = 0;
                                //check if piece can move to that location
                            }
                        }
                        else if(clickCounter == 1)
                        {
                            newRow = r;
                            newCol = c;
                            clickCounter++;
                            if(clickCounter == 2)
                            {
                                clickCounter = 0;
                                if(board.move(GameBoard[oldRow][oldCol], newRow, newCol))
                                {
                                    GameBoard[newRow][newCol] = GameBoard[oldRow][oldCol];
                                    GameBoard[oldRow][oldCol] = null;
                                    GamePane();
                                }
                                else
                                {
                                    //say this move is not legal
                                }
                            }
                        }
                    }
                }
            }
        }

        if(e.getActionCommand().equals("Draw"))
        {
            LayeredPane.add(Pane, top, 0);  
            LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), top, 1);  
        }

        if(e.getActionCommand().equals("New Game"))
        {
            LayeredPane.add(Pane, top, 0);
            LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), top, 1);     
        }
    }
}
