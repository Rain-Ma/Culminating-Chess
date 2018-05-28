//import statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.Random;
/**
 * GUI layout for our chess game
 *
 * @author Justin Chu
 * @version May 27th, 2018
 */
class GameFrame extends JFrame implements ActionListener
{
    //instance variables

    private Font WinFont = new Font("Sans-Serif", Font.BOLD,40);
    private JButton WhiteResign = new JButton("White Resign");
    private JButton Draw = new JButton("Draw");
    private JButton BlackResign = new JButton("Black Resign");

    private JPanel MainMenu = (JPanel)getContentPane(); //main menu JPanel
    private JPanel Options = new JPanel(new FlowLayout());
    private Font TitleFont = new Font("Sans-Serif", Font.BOLD,150);
    private Font MenuFont = new Font("Sans-Serif", Font.BOLD,52);
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
    private int choice = 1;

    private JPanel Score = new JPanel(new GridLayout(3,1));
    private JPanel WhiteContainer = new JPanel(new BorderLayout());
    private JPanel WhiteSide = new JPanel(new GridLayout(4,8));
    private JPanel BlackContainer = new JPanel(new BorderLayout());
    private JPanel BlackSide = new JPanel(new GridLayout(4,8));
    private JPanel Middle = new JPanel(new FlowLayout());
    private JButton Exit = new JButton("Exit");
    private Font ScoreFont = new Font("Sans-Serif", Font.BOLD, 24);
    private ChessPiece[] DeadBlack = new ChessPiece[32];
    private ChessPiece[] DeadWhite = new ChessPiece[32];

    private JButton[][] buttons = new JButton[8][8]; //new JButton array to put on chess board

    private int oldRow;
    private int oldCol;
    private int newRow;
    private int newCol;
    private int promoteRow;
    private int promoteCol;
    private boolean firstClick = true;
    private boolean isWhitesTurn = true;
    private boolean winner = false;

    private Integer top = new Integer(0); //overlay layers
    private Integer middle = new Integer(0);
    private Integer bottom = new Integer(0);

    private JLabel PromoteTitle = new JLabel("Promote Pawn To: ", SwingConstants.CENTER);
    private JPanel PiecesPanel = new JPanel(new GridLayout(1,4));
    private JButton WhiteKnight = new JButton(new ImageIcon("ChessPieceIcons/WhiteKnight.png"));
    private JButton WhiteBishop = new JButton(new ImageIcon("ChessPieceIcons/WhiteBishop.png"));
    private JButton WhiteRook = new JButton(new ImageIcon("ChessPieceIcons/WhiteRook.png"));
    private JButton WhiteQueen = new JButton(new ImageIcon("ChessPieceIcons/WhiteQueen.png"));
    private JButton BlackKnight = new JButton(new ImageIcon("ChessPieceIcons/BlackKnight.png"));
    private JButton BlackBishop = new JButton(new ImageIcon("ChessPieceIcons/BlackBishop.png"));
    private JButton BlackRook = new JButton(new ImageIcon("ChessPieceIcons/BlackRook.png"));
    private JButton BlackQueen = new JButton(new ImageIcon("ChessPieceIcons/BlackQueen.png"));

    private JPanel GameOverPanel1 = new JPanel();
    private JPanel GameOverPanel2 = new JPanel();

    private Board board;

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");
        addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }
            }
        );
        //Frame here is implicit
        setSize(1200,800);
        setVisible(true);
        setResizable(false);
    }

    public void startGame()
    {
        board = new Board();
        board.SetGame(); //sets up the chess board
        GamePane();

        LayeredPane = getLayeredPane(); //new layered pane
        LayeredPane.setVisible(true);

        choice = 1;

        //set up menu panel
        MainMenu.setLayout(new GridLayout(3,1));

        NewGame.setFont(MenuFont);
        NewGame.addActionListener(this);
        NewGame.setBackground(Color.WHITE);
        NewGame.setPreferredSize(new Dimension(320,150)); 
        NewGame.setFocusPainted(false);

        Computer.setFont(MenuFont);
        Computer.addActionListener(this);
        Computer.setBackground(Color.WHITE);
        Computer.setPreferredSize(new Dimension(320,150));
        Computer.setFocusPainted(false);
        Computer.setEnabled(false);

        Options.removeAll();
        Options.setBackground(new Color(0,0,0,0));
        Options.add(NewGame);
        Options.add(Computer);

        JLabel Title = new JLabel("Chess Game", SwingConstants.CENTER);
        Title.setBackground(new Color(0,0,0,0));
        Title.setFont(TitleFont);

        MainMenu.add(Title);
        MainMenu.add(Options);

        //set up options panel
        WhiteResign.addActionListener(this);
        BlackResign.addActionListener(this);
        Draw.addActionListener(this);
        Exit.addActionListener(this);

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

        Middle.add(WhiteResign);
        Middle.add(Draw);
        Middle.add(BlackResign);
        Middle.add(Exit);
        Middle.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        //set up main panel
        Pane.setBounds(0, 0, 800, 760);
        Pane.setBorder(BorderFactory.createRaisedBevelBorder());

        //set up score panel
        Score.setBounds(800, 0, 390, 760);
        Score.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        PromoteTitle.setFont(WinFont);
        WhiteKnight.setBackground(new Color(5, 115, 56));
        WhiteKnight.setActionCommand("Knight");
        WhiteKnight.addActionListener(this);
        WhiteKnight.setFocusPainted(false);
        
        WhiteBishop.setBackground(new Color(5, 115, 56)); 
        WhiteBishop.setActionCommand("Bishop");
        WhiteBishop.addActionListener(this);
        WhiteBishop.setFocusPainted(false);
        
        WhiteRook.setBackground(new Color(5, 115, 56));
        WhiteRook.setActionCommand("Rook");
        WhiteRook.addActionListener(this);
        WhiteRook.setFocusPainted(false);
        
        WhiteQueen.setBackground(new Color(5, 115, 56));
        WhiteQueen.setActionCommand("Queen");
        WhiteQueen.addActionListener(this);
        WhiteQueen.setFocusPainted(false);
        
        BlackKnight.setBackground(new Color(251, 244, 225));
        BlackKnight.setActionCommand("Knight");
        BlackKnight.addActionListener(this);
        BlackKnight.setFocusPainted(false);
        
        BlackBishop.setBackground(new Color(251, 244, 225));
        BlackBishop.setActionCommand("Bishop");
        BlackBishop.addActionListener(this);
        BlackBishop.setFocusPainted(false);
        
        BlackRook.setBackground(new Color(251, 244, 225));
        BlackRook.setActionCommand("Rook");
        BlackRook.addActionListener(this);
        BlackRook.setFocusPainted(false);
        
        BlackQueen.setBackground(new Color(251, 244, 225));
        BlackQueen.setActionCommand("Queen");
        BlackQueen.addActionListener(this);
        BlackQueen.setFocusPainted(false);

        LayeredPane.add(MenuPanel(), top, 0);
        LayeredPane.add(Pane, middle, 1);
        LayeredPane.add(ScorePanel(), middle, 2);
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
     * Creates the chess board
     */
    public void GamePane()
    {
        //-------------------------------------------PANE JPANEL - CHESS GAME GUI CODE----------------------------------------
        NumberPane.removeAll();
        LetterPane.removeAll();
        for(int i = 0; i <8; i++)
        {
            String number = "\u2008" + Integer.toString(Math.abs(i-8)) + "\u2008"; //u2008 is small space for formatting
            JLabel numbers = new JLabel(number);
            numbers.setFont(Labeling);
            NumberPane.add(numbers);

            String letter = "   " + Character.toString(Character.toUpperCase((char)(i+97)));
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

            String letter = "   " + Character.toString(Character.toUpperCase((char)(i+97)));
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

        ScorePanel(); //Update score panel

        setSize(1200,800);
        setVisible(true);
        setResizable(false);
    }

    public JPanel MenuPanel()
    {
        //----------------------------------MAINMENU JPANEL - RETURNS MENU PANEL--------------------------------------
        return MainMenu;
    }

    /**
     * Side panel to the right of board pane panel - shows options, pieces eaten, promotion, winner
     *        if 1 - show black and white pieces eaten; if 2 - show white promotion panel; 
     *        if 3 - show black promotion panel; if 4 - White has won the game; if 5 - Black has won the game;
     *        if 6 - there is a draw
     */
    public JPanel ScorePanel()
    {
        Score.removeAll();
        BlackContainer.removeAll();
        WhiteContainer.removeAll();
        GameOverPanel1.removeAll();
        GameOverPanel2.removeAll();

        if(choice == 1)
        {
            Score.add(WhiteEatenPane(DeadWhite));
            Score.add(Middle);
            Score.add(BlackEatenPane(DeadBlack));
        }
        else if(choice == 2)
        {
            PiecesPanel.removeAll();
            PiecesPanel.add(WhiteQueen);
            PiecesPanel.add(WhiteRook);
            PiecesPanel.add(WhiteBishop);
            PiecesPanel.add(WhiteKnight);

            WhiteContainer.add(PromoteTitle, BorderLayout.CENTER);
            WhiteContainer.add(PiecesPanel, BorderLayout.SOUTH);

            Score.add(WhiteContainer);
            Score.add(Middle);
            Score.add(BlackEatenPane(DeadBlack));
        }
        else if(choice == 3)
        {
            PiecesPanel.removeAll();
            PiecesPanel.add(BlackQueen);
            PiecesPanel.add(BlackRook);
            PiecesPanel.add(BlackBishop);
            PiecesPanel.add(BlackKnight);

            BlackContainer.add(PromoteTitle, BorderLayout.CENTER);
            BlackContainer.add(PiecesPanel, BorderLayout.SOUTH);

            Score.add(WhiteEatenPane(DeadWhite));
            Score.add(Middle);
            Score.add(BlackContainer);
        }
        else if(choice == 4)
        {
            JLabel WhiteWin = new JLabel("White Wins!");
            WhiteWin.setFont(WinFont);
            JLabel BlackLose = new JLabel("Black Loses!");
            BlackLose.setFont(WinFont);

            GameOverPanel1.add(WhiteWin, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel1, BorderLayout.CENTER);
            GameOverPanel2.add(BlackLose, BorderLayout.CENTER);
            WhiteContainer.add(GameOverPanel2, BorderLayout.CENTER);

            Score.add(WhiteContainer);
            Score.add(Middle);
            Score.add(BlackContainer);
        }
        else if(choice == 5)
        {
            JLabel BlackWin = new JLabel("Black Wins!");
            BlackWin.setFont(WinFont);
            JLabel WhiteLose = new JLabel("White Loses!");
            WhiteLose.setFont(WinFont);

            GameOverPanel1.add(BlackWin, BorderLayout.CENTER);        
            WhiteContainer.add(GameOverPanel1, BorderLayout.CENTER);
            GameOverPanel2.add(WhiteLose, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel2, BorderLayout.CENTER);

            Score.add(WhiteContainer);
            Score.add(Middle);
            Score.add(BlackContainer);
        }
        else if(choice == 6)
        {
            JLabel GameDraw = new JLabel("It's a draw!"); //to be displayed in white container`
            GameDraw.setFont(WinFont);
            JLabel GameDraw1 = new JLabel("It's a draw!"); //to be displayed in black container
            GameDraw1.setFont(WinFont);

            GameOverPanel1.add(GameDraw, BorderLayout.CENTER);
            GameOverPanel2.add(GameDraw1, BorderLayout.CENTER);
            WhiteContainer.add(GameOverPanel1, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel2, BorderLayout.CENTER);

            Score.add(WhiteContainer);
            Score.add(Middle);
            Score.add(BlackContainer);
        }

        if(isWhitesTurn)
        {
            BlackContainer.add(new JLabel (new ImageIcon("ChessPieceIcons/Dot.png")), BorderLayout.NORTH);
            WhiteContainer.add(new JLabel(new ImageIcon("ChessPieceIcons/NotDot.png")), BorderLayout.NORTH);
        }
        else
        {
            WhiteContainer.add(new JLabel(new ImageIcon("ChessPieceIcons/Dot.png")), BorderLayout.NORTH);
            BlackContainer.add(new JLabel(new ImageIcon("ChessPieceIcons/NotDot.png")), BorderLayout.NORTH);
        }
        return Score;
    }

    public JPanel WhiteEatenPane(ChessPiece[] WhitePieces)
    {
        WhiteContainer.removeAll();

        WhiteContainer.add(WhiteSide, BorderLayout.CENTER);
        WhiteContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        for(int i = 0; i < 32; i++) 
        {
            if(WhitePieces[i] == null)
                break;
            else
                WhiteSide.add(new JButton(WhitePieces[i].toString()));
        }

        return WhiteContainer;
    }

    public JPanel BlackEatenPane(ChessPiece[] BlackPieces)
    {
        BlackContainer.removeAll();

        BlackContainer.add(BlackSide, BorderLayout.CENTER);
        BlackContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        for(int i = 0; i < 32; i++)
        {
            if(BlackPieces[i] == null)
                break;
            else
                BlackSide.add(new JButton(BlackPieces[i].toString()));
        }

        return BlackContainer;
    }

    /**
     * When button is pressed
     */
    public void actionPerformed (ActionEvent e)
    {
        if(winner == false)
        {
            if(e.getSource() instanceof JButton)
            {
                for(int r = 0; r < 8; r++)
                {
                    for(int c = 0; c < 8; c++)
                    {
                        if(buttons[r][c] == e.getSource())
                        {
                            if(firstClick || board.getBoard()[r][c] instanceof ChessPiece && board.getBoard()[r][c].getIsWhite() == isWhitesTurn)
                            {
                                if(board.getBoard()[r][c] instanceof ChessPiece)
                                {
                                    if(isWhitesTurn&&board.getBoard()[r][c].getIsWhite())
                                    {
                                        oldRow = r;
                                        oldCol = c;
                                        firstClick=false;
                                    }
                                    else if(!isWhitesTurn&&!board.getBoard()[r][c].getIsWhite())
                                    {
                                        oldRow = r;
                                        oldCol = c;
                                        firstClick=false;
                                    }
                                }
                            }
                            else
                            {             
                                if(board.move(board.getBoard()[oldRow][oldCol], r, c))
                                {
                                    if(board.getBoard()[r][c] instanceof Pawn)
                                    {
                                        if(board.getBoard()[r][c].getIsWhite() && board.getBoard()[r][c].getRow() == 0)
                                        {
                                            choice = 2;
                                            promoteRow = r;
                                            promoteCol = c;

                                            LayeredPane.removeAll();
                                            LayeredPane.add(Pane, middle, 0);
                                            LayeredPane.add(ScorePanel(), middle, 1);  
                                        }
                                        else if(!board.getBoard()[r][c].getIsWhite() && board.getBoard()[r][c].getRow() == 7)
                                        {
                                            choice = 3;
                                            promoteRow = r;
                                            promoteCol = c;

                                            LayeredPane.removeAll();
                                            LayeredPane.add(Pane, top, 0);
                                            LayeredPane.add(ScorePanel(), top, 1);   
                                        }
                                    }

                                    if(isWhitesTurn)
                                    {
                                        if(board.checkCheckMate(false))
                                        {
                                            winner = true;
                                            choice = 4;
                                        }
                                        else if(board.checkStaleMate(false))
                                        {
                                            winner = true;
                                            choice = 6;
                                        }
                                        isWhitesTurn = false;
                                    }
                                    else
                                    {
                                        if(board.checkCheckMate(true))
                                        {
                                            winner = true;
                                            choice = 5;                                          
                                        }
                                        else if(board.checkStaleMate(true))
                                        {
                                            winner = true;
                                            choice = 6;                                            
                                        }
                                        isWhitesTurn = true;
                                    }

                                    firstClick = true;
                                    GamePane();
                                }
                                firstClick = true;
                            }
                        }
                    }
                }
            }

            if(e.getActionCommand().equals("Queen"))
            {
                choice = 1;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Queen");
                GamePane();
            }
            else if(e.getActionCommand().equals("Rook"))
            {
                choice = 1;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Rook");
                GamePane();
            }
            else if(e.getActionCommand().equals("Bishop"))
            {
                choice = 1;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Bishop");
                GamePane();
            }
            else if(e.getActionCommand().equals("Knight"))
            {
                choice = 1;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Knight");
                GamePane();
            }

            if(e.getActionCommand().equals("White Resign"))
            {
                winner = true;
                choice = 5;

                LayeredPane.removeAll();
                LayeredPane.add(Pane, top, 0);
                LayeredPane.add(ScorePanel(), top, 1);  
            }

            if(e.getActionCommand().equals("Black Resign"))
            {
                winner = true;
                choice = 4;

                LayeredPane.removeAll();
                LayeredPane.add(Pane, top, 0);
                LayeredPane.add(ScorePanel(), top, 1);  
            }

            if(e.getActionCommand().equals("Draw"))
            {
                winner = true;
                choice = 6;

                LayeredPane.removeAll();
                LayeredPane.add(Pane, top, 0);
                LayeredPane.add(ScorePanel(), top, 1);  
            }
        }

        if(e.getActionCommand().equals("Exit"))
        {
            LayeredPane.removeAll();
            LayeredPane.add(MenuPanel(), top, 0);
        }

        if(e.getActionCommand().equals("New Game"))
        {
            winner = false;
            choice = 1;
            isWhitesTurn = true;
            board = new Board();
            board.SetGame(); //sets up the chess board
            GamePane();
            LayeredPane.removeAll();
            LayeredPane.add(Pane, top, 0);
            LayeredPane.add(ScorePanel(), top, 1);   
        }
    }

}
