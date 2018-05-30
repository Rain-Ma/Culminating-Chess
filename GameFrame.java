//import statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * GUI layout for our chess game
 *
 * @author Justin Chu
 * @version May 27th, 2018
 */
class GameFrame extends JFrame implements ActionListener
{
    //instance variables
    private JPanel MainMenu = (JPanel)getContentPane(); //Main menu JPanel
    private JPanel Options = new JPanel(new FlowLayout()); //JPanel to but menu buttons in
    private Font TitleFont = new Font("Sans-Serif", Font.BOLD,150); //The font of the title
    private Font MenuFont = new Font("Sans-Serif", Font.BOLD,80); //The font of the menu buttons
    private JButton NewGame = new JButton("New Game"); //New game button

    private JLayeredPane LayeredPane; //Layout manager for JPanel layers

    private JPanel Pane = new JPanel(new BorderLayout()); //Main game JPanel
    private JPanel BoardPane = new JPanel(new GridLayout(8,8)); //Chess board JPanel
    private JPanel NumberRight = new JPanel(new GridLayout(8,1)); //Numbers on the right of chess board
    private JPanel LetterBottom = new JPanel(new GridLayout(1,8)); //Letters on the bottom of chess board
    private JPanel LetterPane = new JPanel(new GridLayout(1,8)); //Letters on the top of chess board
    private JPanel NumberPane = new JPanel(new GridLayout(8,1)); //Numbers on the left of chess board
    private Font Labeling = new Font("Monospaced", Font.BOLD, 24); //Font of the chess board labels

    private int choice = 1; //Different 'choices' to be displayed in score panel
    private JPanel Score = new JPanel(new GridLayout(3,1)); //Score panel to the right of board panel
    private JPanel WhiteContainer = new JPanel(new BorderLayout()); //Bottom of score panel
    private JPanel WhiteSide = new JPanel(new GridLayout(3,5)); //JPanel of eaten white pieces
    private JPanel BlackContainer = new JPanel(new BorderLayout()); //Top of score panel
    private JPanel BlackSide = new JPanel(new GridLayout(3,5)); //JPanel of eaten black stores
    private JPanel Middle = new JPanel(new FlowLayout()); //Middle of score panel
    private JButton Exit = new JButton("Exit"); //Button to exit game
    private Font ScoreFont = new Font("Sans-Serif", Font.BOLD, 24); //Font of options buttons
    private Font DisplayFont = new Font("Sans-Serif", Font.BOLD,40); //Font used to notify players
    private JButton WhiteResign = new JButton("White Resign"); //Button for white player to resign
    private JButton Draw = new JButton("Draw"); //Button for both players to draw
    private JButton BlackResign = new JButton("Black Resign"); //Button for black player to resign

    private JButton[][] buttons = new JButton[8][8]; //New JButton array to put on chess board

    private int oldRow; //Row of first square clicked
    private int oldCol; //Column of first square clicked
    private int promoteRow; //Row of pawn being promoted
    private int promoteCol; //Column of pawn being promoted
    private boolean firstClick = true; //If piece is selected
    private boolean isWhitesTurn = true; //If it is whites turn
    private boolean winner = false; //If there is a winner
    private boolean promoting = false; //If someone is promoting a pawn 

    //Overlay layers
    private Integer top = new Integer(0);
    private Integer middle = new Integer(0);
    private Integer bottom = new Integer(0);

    private JLabel PromoteTitle = new JLabel("Promote Pawn To: ", SwingConstants.CENTER);//Promoting pawn JLabel
    private JPanel PiecesPanel = new JPanel(new GridLayout(1,4));//Promoting pieces JPanel
    //JButtons for pieces pawns can promote to
    private JButton WhiteKnight = new JButton(new ImageIcon("ChessPieceIcons/WhiteKnight.png"));
    private JButton WhiteBishop = new JButton(new ImageIcon("ChessPieceIcons/WhiteBishop.png"));
    private JButton WhiteRook = new JButton(new ImageIcon("ChessPieceIcons/WhiteRook.png"));
    private JButton WhiteQueen = new JButton(new ImageIcon("ChessPieceIcons/WhiteQueen.png"));
    private JButton BlackKnight = new JButton(new ImageIcon("ChessPieceIcons/BlackKnight.png"));
    private JButton BlackBishop = new JButton(new ImageIcon("ChessPieceIcons/BlackBishop.png"));
    private JButton BlackRook = new JButton(new ImageIcon("ChessPieceIcons/BlackRook.png"));
    private JButton BlackQueen = new JButton(new ImageIcon("ChessPieceIcons/BlackQueen.png"));

    //Panels that can go inside score panel
    private JPanel GameOverPanel1 = new JPanel();
    private JPanel GameOverPanel2 = new JPanel();

    private JLabel CheckMessage = new JLabel("Check!"); //Message displayed when a king is in check
    private JLabel DrawMessage = new JLabel("It's a draw!"); //Message displayed when a draw happens
    private JLabel DrawMessage1 = new JLabel("It's a draw!"); //Message displayed when a draw happens
    private JLabel CantMove = new JLabel("Can't move there!"); //Message displayed when a piece cant move somewhere

    private Board board; //Board object

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

    /**
     * Sets up the game GUI
     */
    public void startGame()
    {
        board = new Board(); //Creates a new Board object
        board.SetGame(); //Sets up the chess board
        GamePane(); //Refreshes the game JPanel

        LayeredPane = getLayeredPane(); //New layered pane
        LayeredPane.setVisible(true); //Make it visible

        choice = 1; //Set score panel choice to 1

        //Set up main menu JPanel       
        MainMenu.setLayout(new GridLayout(3,1));
        //Set up new game button
        NewGame.setFont(MenuFont);
        NewGame.addActionListener(this);
        NewGame.setBackground(Color.WHITE);
        NewGame.setPreferredSize(new Dimension(500,200)); 
        NewGame.setFocusPainted(false);
        //Set up menu options JPanel
        Options.removeAll();
        Options.setBackground(new Color(0,0,0,0));
        Options.add(NewGame);
        //Set menu title
        JLabel Title = new JLabel("Chess Game", SwingConstants.CENTER);
        Title.setBackground(new Color(0,0,0,0));
        Title.setFont(TitleFont);
        //Add them to main menu
        MainMenu.add(Title);
        MainMenu.add(Options);

        //Set up options JPanel
        WhiteResign.addActionListener(this);
        BlackResign.addActionListener(this);
        Draw.addActionListener(this);
        Exit.addActionListener(this);
        //Set up white resign button
        WhiteResign.setBackground(Color.WHITE);
        WhiteResign.setFont(ScoreFont);    
        WhiteResign.setPreferredSize(new Dimension(220,70)); 
        WhiteResign.setFocusPainted(false);
        //Set up draw button
        Draw.setBackground(Color.WHITE);
        Draw.setFont(ScoreFont);    
        Draw.setPreferredSize(new Dimension(150,70)); 
        Draw.setFocusPainted(false);
        //Set up black resign button
        BlackResign.setBackground(Color.WHITE);
        BlackResign.setFont(ScoreFont);    
        BlackResign.setPreferredSize(new Dimension(220,70)); 
        BlackResign.setFocusPainted(false);
        //Set up exit button
        Exit.setBackground(Color.WHITE);
        Exit.setFont(ScoreFont);    
        Exit.setPreferredSize(new Dimension(160,70)); 
        Exit.setFocusPainted(false);
        //Add them to middle of score panel
        Middle.add(WhiteResign);
        Middle.add(Draw);
        Middle.add(BlackResign);
        Middle.add(Exit);
        Middle.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        //Set up main panel
        Pane.setBounds(0, 0, 800, 760);
        Pane.setBorder(BorderFactory.createRaisedBevelBorder());
        //Set up chess board side labels
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

        //Set up score panel
        Score.setBounds(800, 0, 390, 760);
        Score.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        //Set up promote buttons and label
        PromoteTitle.setFont(DisplayFont);
        //Set up promote to white knight button
        WhiteKnight.setBackground(new Color(5, 115, 56));
        WhiteKnight.setActionCommand("Knight");
        WhiteKnight.addActionListener(this);
        WhiteKnight.setFocusPainted(false);
        //Set up promote to white bishop button
        WhiteBishop.setBackground(new Color(5, 115, 56)); 
        WhiteBishop.setActionCommand("Bishop");
        WhiteBishop.addActionListener(this);
        WhiteBishop.setFocusPainted(false);
        //Set up promote to white rook button
        WhiteRook.setBackground(new Color(5, 115, 56));
        WhiteRook.setActionCommand("Rook");
        WhiteRook.addActionListener(this);
        WhiteRook.setFocusPainted(false);
        //Set up promote to white queen button
        WhiteQueen.setBackground(new Color(5, 115, 56));
        WhiteQueen.setActionCommand("Queen");
        WhiteQueen.addActionListener(this);
        WhiteQueen.setFocusPainted(false);
        //Set up promote to black knight button
        BlackKnight.setBackground(new Color(251, 244, 225));
        BlackKnight.setActionCommand("Knight");
        BlackKnight.addActionListener(this);
        BlackKnight.setFocusPainted(false);
        //Set up promote to black bishop button
        BlackBishop.setBackground(new Color(251, 244, 225));
        BlackBishop.setActionCommand("Bishop");
        BlackBishop.addActionListener(this);
        BlackBishop.setFocusPainted(false);
        //Set up promote to black rook button
        BlackRook.setBackground(new Color(251, 244, 225));
        BlackRook.setActionCommand("Rook");
        BlackRook.addActionListener(this);
        BlackRook.setFocusPainted(false);
        //Set up promote to black queen button
        BlackQueen.setBackground(new Color(251, 244, 225));
        BlackQueen.setActionCommand("Queen");
        BlackQueen.addActionListener(this);
        BlackQueen.setFocusPainted(false);
        //Set up score panel messages
        DrawMessage1.setFont(DisplayFont);
        DrawMessage.setFont(DisplayFont);
        CheckMessage.setFont(DisplayFont);
        CantMove.setFont(DisplayFont);
        //Set up score panel container borders
        WhiteContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        BlackContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        //Set up JPanel layers
        LayeredPane.add(MainMenu, top, 0);
        LayeredPane.add(Pane, middle, 1);
        LayeredPane.add(ScorePanel(), middle, 2);
    }

    /**
     * Sets up and returns chess board JPanel
     * @return Chess board JPanel
     */
    public JPanel BoardPanel()
    {
        BoardPane.removeAll(); //Prevents things from stacking up in JPanel
        for(int r = 0; r < 8; r++) //row in chess board JPanel
        {
            for(int c = 0; c < 8; c++) //column in chess board JPanel
            {
                if(board.getBoard()[r][c]==null) //if square has no chesspiece on it
                {   
                    buttons[r][c] = new JButton(); //create a new jbutton on it
                    buttons[r][c].addActionListener(this);
                }
                else //if there is a chesspiece on the square
                {
                    buttons[r][c] = new JButton(new ImageIcon(board.getBoard()[r][c].toString())); //add chesspiece icon on it
                    buttons[r][c].addActionListener(this);
                }
                //Setting up the colors of the squares
                if(r % 2 != 0&& (c+1)%2 != 0 || r % 2 == 0&& (c+1)%2 == 0)
                {
                    buttons[r][c].setBackground(new Color(5, 115, 56)); //Make square green 
                    buttons[r][c].setFocusPainted(false); //removes button border when pressed
                }
                else
                {
                    buttons[r][c].setBackground(new Color(251, 244, 225)); //Make square beige
                    buttons[r][c].setFocusPainted(false); //removes show icon border when pressed
                }
                BoardPane.add(buttons[r][c]);
            }
        }
        return BoardPane; //return chess board JPanel
    }

    /**
     * Creates the chess board
     */
    public void GamePane()
    {
        //
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
        WhiteSide.removeAll();
        BlackSide.removeAll();

        if(choice == 1)
        {
            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] instanceof ChessPiece)
                    WhiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));
                else
                    break;
            }
            WhiteContainer.add(WhiteSide, BorderLayout.CENTER);

            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] instanceof ChessPiece)
                    BlackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));
                else
                    break;
            }
            BlackContainer.add(BlackSide, BorderLayout.CENTER);
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

            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] == null)
                    break;
                else
                    BlackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));
            }
            BlackContainer.add(BlackSide, BorderLayout.CENTER);
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

            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] == null)
                    break;
                else
                    WhiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));
            }
            WhiteContainer.add(WhiteSide, BorderLayout.SOUTH);
        }
        else if(choice == 4)
        {
            JLabel WhiteWin = new JLabel("White Wins!");
            WhiteWin.setFont(DisplayFont);
            JLabel BlackLose = new JLabel("Black Loses!");
            BlackLose.setFont(DisplayFont);

            GameOverPanel1.add(WhiteWin, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel1, BorderLayout.CENTER);
            GameOverPanel2.add(BlackLose, BorderLayout.CENTER);
            WhiteContainer.add(GameOverPanel2, BorderLayout.CENTER);
        }
        else if(choice == 5)
        {
            JLabel BlackWin = new JLabel("Black Wins!");
            BlackWin.setFont(DisplayFont);
            JLabel WhiteLose = new JLabel("White Loses!");
            WhiteLose.setFont(DisplayFont);

            GameOverPanel1.add(BlackWin, BorderLayout.CENTER);        
            WhiteContainer.add(GameOverPanel1, BorderLayout.CENTER);
            GameOverPanel2.add(WhiteLose, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel2, BorderLayout.CENTER);
        }
        else if(choice == 6)
        {
            GameOverPanel1.add(DrawMessage, BorderLayout.CENTER);
            GameOverPanel2.add(DrawMessage1, BorderLayout.CENTER);
            WhiteContainer.add(GameOverPanel1, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel2, BorderLayout.CENTER);
        }
        else if(choice == 7)
        {
            choice = 1;

            GameOverPanel1.add(CheckMessage, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel1, BorderLayout.CENTER);

            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] == null)
                    break;
                else
                    WhiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));
            }
            WhiteContainer.add(WhiteSide, BorderLayout.SOUTH);
        }
        else if(choice == 8)
        {
            choice = 1;

            GameOverPanel1.add(CheckMessage, BorderLayout.CENTER);
            WhiteContainer.add(GameOverPanel1, BorderLayout.CENTER);

            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] == null)
                    break;
                else
                    BlackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));
            }
            BlackContainer.add(BlackSide, BorderLayout.CENTER);
        }
        else if(choice == 9)
        {
            choice = 1;

            GameOverPanel1.add(CantMove, BorderLayout.CENTER);
            BlackContainer.add(GameOverPanel1, BorderLayout.CENTER);

            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] == null)
                    break;
                else
                    WhiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));
            }
            WhiteContainer.add(WhiteSide, BorderLayout.SOUTH);
        }
        else if(choice == 10)
        {
            choice = 1;

            GameOverPanel1.add(CantMove, BorderLayout.CENTER);
            WhiteContainer.add(GameOverPanel1, BorderLayout.CENTER);

            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] == null)
                    break;
                else
                    BlackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));
            }
            BlackContainer.add(BlackSide, BorderLayout.CENTER);
        }
        Score.add(WhiteContainer);
        Score.add(Middle);
        Score.add(BlackContainer);

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
                                if(!promoting)
                                {
                                    if(board.move(board.getBoard()[oldRow][oldCol], r, c))
                                    {
                                        if(board.getBoard()[r][c] instanceof Pawn)
                                        {
                                            if(board.getBoard()[r][c].getIsWhite() && board.getBoard()[r][c].getRow() == 0)
                                            {
                                                choice = 2;
                                                promoting = true;
                                                promoteRow = r;
                                                promoteCol = c;
                                                GamePane();
                                            }
                                            else if(!board.getBoard()[r][c].getIsWhite() && board.getBoard()[r][c].getRow() == 7)
                                            {
                                                choice = 3;
                                                promoting = true;
                                                promoteRow = r;
                                                promoteCol = c;

                                                GamePane();  
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
                                    else
                                    {
                                        if(board.getBoard()[oldRow][oldCol].getIsWhite())
                                            choice = 9;
                                        else
                                            choice = 10;
                                        GamePane();
                                    }
                                }
                                firstClick = true;
                            }
                        }
                    }
                }
            }

            for(int r = 0; r < 8; r++)
            {
                for(int c = 0; c < 8; c++)
                {
                    if(board.getBoard()[r][c] instanceof King)
                    {
                        King king = (King)board.getBoard()[r][c];//temporary casting
                        if(king.checked())
                        {
                            if(!board.checkCheckMate(king.getIsWhite()))
                            {
                                if(king.getIsWhite())
                                    choice = 7;
                                else
                                    choice = 8;
                                GamePane();
                            }
                        }
                    }
                }
            }

            if(e.getActionCommand().equals("Queen"))
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Queen");
                GamePane();
            }
            else if(e.getActionCommand().equals("Rook"))
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Rook");
                GamePane();
            }
            else if(e.getActionCommand().equals("Bishop"))
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Bishop");
                GamePane();
            }
            else if(e.getActionCommand().equals("Knight"))
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Knight");
                GamePane();
            }

            if(e.getActionCommand().equals("White Resign"))
            {
                winner = true;
                choice = 5;
                GamePane();
            }

            if(e.getActionCommand().equals("Black Resign"))
            {
                winner = true;
                choice = 4;
                GamePane();
            }

            if(e.getActionCommand().equals("Draw"))
            {
                winner = true;
                choice = 6;
                GamePane();
            }
        }

        if(e.getActionCommand().equals("Exit"))
        {
            LayeredPane.removeAll();
            LayeredPane.add(MainMenu, top, 0);
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
