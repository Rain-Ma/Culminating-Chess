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
import java.util.Random;
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
    private int[] pieces = new int[8]; //pieces on the 960 board

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

    private JButton[][] buttons = new JButton[8][8]; //new JButton array to put on chess board

    private int oldRow;
    private int oldCol;
    private int newRow;
    private int newCol;
    private int clickCounter = 0;

    private Integer top = new Integer(0); //overlay layers
    private Integer middle = new Integer(0);
    private Integer bottom = new Integer(0);

    private JPanel PromotePane = new JPanel();

    private Board board = new Board();

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");

        //Frame here is implicit
        setSize(1200,800);
        setVisible(true);
        setResizable(false);
    }

    public void startGame()
    {
        LayeredPane = getLayeredPane(); //new layered pane
        LayeredPane.setVisible(true);

        board.SetGame(); //sets up the chess board
        GamePane();
        
        LayeredPane.add(MenuPanel(), top, 0);
        LayeredPane.add(Pane, middle, 1);
        LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), middle, 2);
    }

    public void start960()
    {
        /*
        //Setup pawns first because they are not randomized
        for(int i = 0; i < 8; i++)
        {
        board.getBoard()[6][i] = new Pawn(2, i+1, true);
        }
        for(int i = 0; i < 8; i++)
        {
        board.getBoard()[1][i] = new Pawn(7, i+1, false);
        }

        Random rand = new Random();

        //White Pieces randomized
        int bishop1 = rand.nextInt((3-0)+1)*2; //first bishop is in a random even location
        board.getBoard()[7][bishop1] = new Bishop(1, bishop1+1, true);
        pieces[0] = bishop1; //add first bishop's location to pieces
        int bishop2 = rand.nextInt((3-0)+1)*2+1; //second bishop location must be odd
        board.getBoard()[7][bishop2] = new Bishop(1, bishop2+1, true);
        pieces[1] = bishop2; //add second bishop's location to pieces

        board.getBoard()[7][RandomLoc(2)] = new Queen(1, RandomLoc(2)+1, true);
        board.getBoard()[7][RandomLoc(3)] = new Knight(1, RandomLoc(3)+1, true);
        board.getBoard()[7][RandomLoc(4)] = new Knight(1, RandomLoc(4)+1, true);
        board.getBoard()[7][RandomLoc(5)] = new Rook(1, RandomLoc(5)+1, true);

        GamePane();
         */
    }

    /**
     * Finds a random location for white pieces in the chess 960 game
     * @param the piece's number (for how many pieces are before it)
     * @return the piece's new location
     */
    public int RandomLoc(int pieceNum)
    {
        /*
        Random rand = new Random();

        boolean pieceLoc = false;
        while(pieceLoc == false) //while the piece has not found a location yet
        {
        int piece = rand.nextInt((7-0)+1); //random location for piece
        for(int i = 0; i < pieces.length; i++)
        {
        if(piece != pieces[i]) //if piece is not on any piece's location
        {
        pieceLoc = true; //piece is so far not on any piece's location
        }
        else
        pieceLoc = false; //piece is on another piece's location so find a new location
        }
        if(pieceLoc = true) //if piece has found a location
        {
        pieces[pieceNum] = piece; //add piece's location to pieces                
        }
        }
        */
        return pieces[pieceNum];
         
    }

    public JPanel BoardPanel()
    {
        BoardPane.removeAll();
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(board.getBoard()[r][c]==null)
                {   
                    buttons[r][c] = new JButton();
                    buttons[r][c].addActionListener(this);
                }
                else
                {
                    buttons[r][c] = new JButton(new ImageIcon(board.getBoard()[r][c].toString()));
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

        return BoardPane;
    }

    /**
     * Runs the chess game GUI
     */
    public void GamePane()
    {
        //-------------------------------------------PANE JPANEL - CHESS GAME GUI CODE----------------------------------------
        Pane.setBounds(0, 0, 800, 760);
        Pane.setBorder(BorderFactory.createRaisedBevelBorder());

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
        Pane.add(BoardPanel(), BorderLayout.CENTER);

        //frame is implicit
        setSize(1200,800);
        setVisible(true);
        setResizable(false);
    }

    public JPanel MenuPanel()
    {
        //--------------------------------------MAINMENU JPANEL - MAIN MENU GUI CODE-------------------------------------------
        MainMenu.removeAll();
        MainMenu.setLayout(new GridLayout(3,1));

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

        Options.removeAll();
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
        Score.removeAll();
        BlackContainer.removeAll();
        WhiteContainer.removeAll();
        Middle.removeAll();

        Score.setBounds(800, 0, 390, 760);
        Score.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));

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
        BlackContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        WhiteEaten.setFont(ScoreFont);
        WhiteContainer.add(WhiteEaten, BorderLayout.NORTH);
        WhiteContainer.add(WhiteSide, BorderLayout.CENTER);
        WhiteContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        WhiteResign.addActionListener(this);
        BlackResign.addActionListener(this);
        Draw.addActionListener(this);
        Exit.addActionListener(this);
        Help.addActionListener(this);

        WhiteResign.setBackground(Color.WHITE);
        WhiteResign.setFont(ScoreFont);    
        WhiteResign.setPreferredSize(new Dimension(220,70)); 
        WhiteResign.setFocusPainted(false);
        Draw.setBackground(Color.WHITE);
        Draw.setFont(ScoreFont);    
        Draw.setPreferredSize(new Dimension(150,70)); 
        Draw.setFocusPainted(false);
        BlackResign.setBackground(Color.WHITE);
        BlackResign.setFont(ScoreFont);    
        BlackResign.setPreferredSize(new Dimension(220,70)); 
        BlackResign.setFocusPainted(false);
        Exit.setBackground(Color.WHITE);
        Exit.setFont(ScoreFont);    
        Exit.setPreferredSize(new Dimension(160,70)); 
        Exit.setFocusPainted(false);
        Help.setBackground(Color.WHITE);
        Help.setFont(ScoreFont);    
        Help.setPreferredSize(new Dimension(160,70)); 
        Help.setFocusPainted(false);

        Middle.add(WhiteResign);
        Middle.add(Draw);
        Middle.add(BlackResign);
        Middle.add(Help);
        Middle.add(Exit);
        Middle.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        Score.add(WhiteContainer);
        Score.add(Middle);
        Score.add(BlackContainer);

        return Score;
    }

    public JPanel Promote()
    {
        PromotePane.setBounds(986, 0, 505, 958);
        PromotePane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));
        return PromotePane;
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
                        if(board.getBoard()[r][c] instanceof ChessPiece)
                        {
                            oldRow = r;
                            oldCol = c;
                            clickCounter++;
                            if(clickCounter == 2)
                            {
                                System.out.println("ok");
                                if(board.move(board.getBoard()[oldRow][oldCol], newRow, newCol))
                                {
                                    System.out.println("as");
                                    board.getBoard()[newRow][newCol] = board.getBoard()[oldRow][oldCol];
                                    board.getBoard()[oldRow][oldCol] = null;
                                    GamePane();
                                }
                                else
                                {
                                    //say this move is not legal
                                }
                                clickCounter = 0;
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
                                if(board.move(board.getBoard()[oldRow][oldCol], newRow, newCol))
                                {
                                    System.out.println("as");
                                    board.getBoard()[newRow][newCol] = board.getBoard()[oldRow][oldCol];
                                    board.getBoard()[oldRow][oldCol] = null;
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

        if(e.getActionCommand().equals("Exit"))
        {
            LayeredPane.removeAll();
            LayeredPane.add(MenuPanel(), top, 0);

            LayeredPane.add(Pane, middle, 1);
            LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), bottom , 2); 
        }

        if(e.getActionCommand().equals("New Game"))
        {
            LayeredPane.add(Pane, top, 0);
            LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), top, 1);   
        }

        if(e.getActionCommand().equals("Chess960"))
        {
            LayeredPane.add(Pane, top, 0);
            LayeredPane.add(ScorePanel(DeadBlack, DeadWhite), top, 1);   
            start960();
        }
    }
}
