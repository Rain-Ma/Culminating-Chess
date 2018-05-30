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
    private JPanel mainMenu = (JPanel)getContentPane(); //Main menu JPanel
    private JPanel options = new JPanel(new FlowLayout()); //JPanel to but menu buttons in
    private Font titleFont = new Font("Sans-Serif", Font.BOLD,175); //The font of the title
    private Font menuFont = new Font("Sans-Serif", Font.BOLD,100); //The font of the menu buttons
    private JButton newGame = new JButton("New Game"); //New game button
    private JLabel img = new JLabel(); //Background image JLabel
    private JPanel imgPanel = new JPanel(); //Background image JPanel
    private JLabel credits = new JLabel("By: Justin C, Rain M, Shawn B", SwingConstants.CENTER); //game credits JLabel

    private JLayeredPane layeredPane; //Layout manager for JPanel layers

    private JPanel pane = new JPanel(new BorderLayout()); //Main game JPanel
    private JPanel boardPane = new JPanel(new GridLayout(8,8)); //Chess board JPanel
    private JPanel numberRight = new JPanel(new GridLayout(8,1)); //Numbers on the right of chess board
    private JPanel letterBottom = new JPanel(new GridLayout(1,8)); //Letters on the bottom of chess board
    private JPanel letterPane = new JPanel(new GridLayout(1,8)); //Letters on the top of chess board
    private JPanel numberPane = new JPanel(new GridLayout(8,1)); //Numbers on the left of chess board
    private Font labeling = new Font("Monospaced", Font.BOLD, 24); //Font of the chess board labels

    private int choice = 1; //Different 'choices' to be displayed in score panel
    private JPanel score = new JPanel(new GridLayout(3,1)); //score panel to the right of board panel
    private JPanel whiteContainer = new JPanel(new BorderLayout()); //Bottom of score panel
    private JPanel whiteSide = new JPanel(new GridLayout(3,5)); //JPanel of eaten white pieces
    private JPanel blackContainer = new JPanel(new BorderLayout()); //Top of score panel
    private JPanel blackSide = new JPanel(new GridLayout(3,5)); //JPanel of eaten black stores
    private JPanel middle= new JPanel(new FlowLayout()); //middleof score panel
    private JButton exit = new JButton("Exit"); //Button to exit game
    private Font scoreFont = new Font("Sans-Serif", Font.BOLD, 24); //Font of options buttons
    private Font displayFont = new Font("Sans-Serif", Font.BOLD,40); //Font used to notify players
    private JButton whiteResign = new JButton("White Resign"); //Button for white player to resign
    private JButton draw = new JButton("Draw"); //Button for both players to draw
    private JButton blackResign = new JButton("Black Resign"); //Button for black player to resign

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
    private Integer bottom = new Integer(0);

    private JLabel promoteTitle = new JLabel("Promote Pawn To: ", SwingConstants.CENTER);//Promoting pawn JLabel
    private JPanel piecesPanel = new JPanel(new GridLayout(1,4));//Promoting pieces JPanel
    //JButtons for pieces pawns can promote to
    private JButton whiteKnight = new JButton(new ImageIcon("ChessPieceIcons/WhiteKnight.png"));
    private JButton whiteBishop = new JButton(new ImageIcon("ChessPieceIcons/WhiteBishop.png"));
    private JButton whiteRook = new JButton(new ImageIcon("ChessPieceIcons/WhiteRook.png"));
    private JButton whiteQueen = new JButton(new ImageIcon("ChessPieceIcons/WhiteQueen.png"));
    private JButton blackKnight = new JButton(new ImageIcon("ChessPieceIcons/BlackKnight.png"));
    private JButton blackBishop = new JButton(new ImageIcon("ChessPieceIcons/BlackBishop.png"));
    private JButton blackRook = new JButton(new ImageIcon("ChessPieceIcons/BlackRook.png"));
    private JButton blackQueen = new JButton(new ImageIcon("ChessPieceIcons/BlackQueen.png"));

    //Panels that can go inside score panel
    private JPanel gameOverPanel1 = new JPanel();
    private JPanel gameOverPanel2 = new JPanel();

    private JLabel checkMessage = new JLabel("Check!"); //Message displayed when a king is in check
    private JLabel drawMessage = new JLabel("It's a draw!"); //Message displayed when a draw happens
    private JLabel drawMessage1 = new JLabel("It's a draw!"); //Message displayed when a draw happens
    private JLabel cantMove = new JLabel("Can't move there!"); //Message displayed when a piece cant move somewhere
    private JLabel whiteWin = new JLabel("White Wins!");
    private JLabel blackLose = new JLabel("Black Loses!");
    private JLabel blackWin = new JLabel("Black Wins!");
    private JLabel whiteLose = new JLabel("White Loses!");

    private Board board; //Board object

    /**
     * Constructor for GameFrame class
     */
    public GameFrame()
    {
        //Using JFrame's constructor, "Chess Game" will be the name of the window
        super("Chess Game");
    }

    /**
     * Sets up the game GUI
     */
    public void startGame()
    {
        board = new Board(); //Creates a new Board object
        board.SetGame(); //Sets up the chess board
        gamePane(); //Refreshes the game JPanel

        layeredPane = getLayeredPane(); //New layered pane
        layeredPane.setVisible(true); //Make it visible

        choice = 1; //Set score panel choice to 1

        //Set up main menu JPanel       
        mainMenu.setLayout(new GridLayout(3,1));
        mainMenu.setOpaque(false);
        //Add background image
        imgPanel.setBounds(0,0,1200,800);
        img.setIcon(new javax.swing.ImageIcon(getClass().getResource("ChessPieceIcons/ChessImage.jpeg")));
        imgPanel.add(img);
        //Set menu title
        JLabel Title = new JLabel("Chess Game", SwingConstants.CENTER);
        Title.setBackground(new Color(0,0,0,0));
        Title.setFont(titleFont);
        Title.setForeground(Color.WHITE);
        //Set up new game button
        newGame.setFont(menuFont);
        newGame.addActionListener(this);
        newGame.setBackground(Color.WHITE);
        newGame.setForeground(Color.BLACK);
        newGame.setPreferredSize(new Dimension(700,200)); 
        newGame.setFocusPainted(false);
        //Set up menu options JPanel
        options.removeAll();
        options.setBackground(new Color(0,0,0,0));
        options.add(newGame);
        //Set up credits JLabel
        credits.setFont(displayFont);
        credits.setForeground(Color.WHITE);
        //Add them to main menu
        mainMenu.add(Title);
        mainMenu.add(options);
        mainMenu.add(credits);

        //Set up options JPanel
        whiteResign.addActionListener(this);
        blackResign.addActionListener(this);
        draw.addActionListener(this);
        exit.addActionListener(this);
        //Set up white resign button
        whiteResign.setBackground(Color.WHITE);
        whiteResign.setFont(scoreFont);    
        whiteResign.setPreferredSize(new Dimension(220,70)); 
        whiteResign.setFocusPainted(false);
        //Set up draw button
        draw.setBackground(Color.WHITE);
        draw.setFont(scoreFont);    
        draw.setPreferredSize(new Dimension(150,70)); 
        draw.setFocusPainted(false);
        //Set up black resign button
        blackResign.setBackground(Color.WHITE);
        blackResign.setFont(scoreFont);    
        blackResign.setPreferredSize(new Dimension(220,70)); 
        blackResign.setFocusPainted(false);
        //Set up exit button
        exit.setBackground(Color.WHITE);
        exit.setFont(scoreFont);    
        exit.setPreferredSize(new Dimension(160,70)); 
        exit.setFocusPainted(false);
        //Add them to middle of score panel
        middle.add(whiteResign);
        middle.add(draw);
        middle.add(blackResign);
        middle.add(exit);
        middle.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        //Set up main panel
        pane.setBounds(0, 0, 800, 760);
        pane.setBorder(BorderFactory.createRaisedBevelBorder());
        //Set up chess board side labels
        for(int i = 0; i <8; i++)
        {
            String number = "\u2008" + Integer.toString(Math.abs(i-8)) + "\u2008"; //u2008 is small space for formatting
            JLabel numbers = new JLabel(number);
            numbers.setFont(labeling);
            numberPane.add(numbers);

            String letter = "   " + Character.toString(Character.toUpperCase((char)(i+97)));
            JLabel letters = new JLabel(letter);
            letters.setFont(labeling);
            letterPane.add(letters);
        }
        for(int i = 0; i <8; i++)
        {
            String number = "\u2008" + Integer.toString(Math.abs(i-8)) + "\u2008"; //u2008 is small space for formatting
            JLabel numbers = new JLabel(number);
            numbers.setFont(labeling);
            numberRight.add(numbers);

            String letter = "   " + Character.toString(Character.toUpperCase((char)(i+97)));
            JLabel letters = new JLabel(letter);
            letters.setFont(labeling);
            letterBottom.add(letters);
        }

        //Set up score panel
        score.setBounds(800, 0, 390, 760);
        score.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
        //Set up promote buttons and label
        promoteTitle.setFont(displayFont);
        //Set up promote to white knight button
        whiteKnight.setBackground(new Color(5, 115, 56));
        whiteKnight.setActionCommand("Knight");
        whiteKnight.addActionListener(this);
        whiteKnight.setFocusPainted(false);
        //Set up promote to white bishop button
        whiteBishop.setBackground(new Color(5, 115, 56)); 
        whiteBishop.setActionCommand("Bishop");
        whiteBishop.addActionListener(this);
        whiteBishop.setFocusPainted(false);
        //Set up promote to white rook button
        whiteRook.setBackground(new Color(5, 115, 56));
        whiteRook.setActionCommand("Rook");
        whiteRook.addActionListener(this);
        whiteRook.setFocusPainted(false);
        //Set up promote to white queen button
        whiteQueen.setBackground(new Color(5, 115, 56));
        whiteQueen.setActionCommand("Queen");
        whiteQueen.addActionListener(this);
        whiteQueen.setFocusPainted(false);
        //Set up promote to black knight button
        blackKnight.setBackground(new Color(251, 244, 225));
        blackKnight.setActionCommand("Knight");
        blackKnight.addActionListener(this);
        blackKnight.setFocusPainted(false);
        //Set up promote to black bishop button
        blackBishop.setBackground(new Color(251, 244, 225));
        blackBishop.setActionCommand("Bishop");
        blackBishop.addActionListener(this);
        blackBishop.setFocusPainted(false);
        //Set up promote to black rook button
        blackRook.setBackground(new Color(251, 244, 225));
        blackRook.setActionCommand("Rook");
        blackRook.addActionListener(this);
        blackRook.setFocusPainted(false);
        //Set up promote to black queen button
        blackQueen.setBackground(new Color(251, 244, 225));
        blackQueen.setActionCommand("Queen");
        blackQueen.addActionListener(this);
        blackQueen.setFocusPainted(false);
        //Set up score panel messages
        drawMessage1.setFont(displayFont);
        drawMessage.setFont(displayFont);
        checkMessage.setFont(displayFont);
        cantMove.setFont(displayFont);
        whiteWin.setFont(displayFont);
        blackLose.setFont(displayFont);
        blackWin.setFont(displayFont);
        whiteLose.setFont(displayFont);
        //Set up score panel container borders
        whiteContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        blackContainer.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        //Set up JPanel layers
        layeredPane.add(mainMenu, top, 0);
        layeredPane.add(imgPanel, top, 1);
        layeredPane.add(pane, middle, 2);
        layeredPane.add(scorePanel(), bottom, 3);
    }

    /**
     * Sets up and returns chess board JPanel
     * 
     * @return Chess board JPanel
     */
    public JPanel boardPanel()
    {
        boardPane.removeAll(); //Prevents things from stacking up in JPanel
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
                boardPane.add(buttons[r][c]);
            }
        }
        return boardPane; //return chess board JPanel
    }

    /**
     * Creates the chess board
     */
    public void gamePane()
    {
        //Combine JPanels together to create entire game pane
        pane.removeAll();
        pane.add(numberPane, BorderLayout.WEST);
        pane.add(letterPane, BorderLayout.NORTH);
        pane.add(numberRight, BorderLayout.EAST);
        pane.add(letterBottom, BorderLayout.SOUTH);
        pane.add(boardPanel(), BorderLayout.CENTER);

        scorePanel(); //Update score panel

        //frame is implicit
        setSize(1200,800);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Side panel to the right of board pane panel - shows options, pieces eaten, promotion, winner, etc.
     * 
     * @return the side panel beside the chess board
     */
    public JPanel scorePanel()
    {
        //remove pre-existing components in JPanels to preven overlapping
        score.removeAll();
        blackContainer.removeAll();
        whiteContainer.removeAll();
        gameOverPanel1.removeAll();
        gameOverPanel2.removeAll();
        piecesPanel.removeAll();
        whiteSide.removeAll();
        blackSide.removeAll();

        if(choice == 1) //if 1 - show black and white pieces eaten 
        {
            //for white pieces eaten
            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] instanceof ChessPiece) 
                    whiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));//add icons of pieces eaten to JPanel
                else
                    break;
            }
            whiteContainer.add(whiteSide, BorderLayout.CENTER);

            //for black pieces eaten
            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] instanceof ChessPiece)
                    blackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));//add icons of pieces eaten to JPanel
                else
                    break;
            }
            blackContainer.add(blackSide, BorderLayout.CENTER);
        }
        else if(choice == 2) //if 2 - show white promotion panel and black pieces eaten
        {
            //add promote to piece buttons
            piecesPanel.add(whiteQueen);
            piecesPanel.add(whiteRook);
            piecesPanel.add(whiteBishop);
            piecesPanel.add(whiteKnight);

            whiteContainer.add(promoteTitle, BorderLayout.CENTER);
            whiteContainer.add(piecesPanel, BorderLayout.SOUTH);

            //for black pieces eaten
            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] == null)
                    break;
                else
                    blackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));//add icons of pieces eaten to JPanel
            }
            blackContainer.add(blackSide, BorderLayout.CENTER);
        }
        else if(choice == 3) //if 3 - show black promotion panel and white pieces eaten
        {
            //add promote to piece buttons
            piecesPanel.add(blackQueen);
            piecesPanel.add(blackRook);
            piecesPanel.add(blackBishop);
            piecesPanel.add(blackKnight);

            blackContainer.add(promoteTitle, BorderLayout.CENTER);
            blackContainer.add(piecesPanel, BorderLayout.SOUTH);

            //for white pieces eaten
            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] == null)
                    break;
                else
                    whiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));//add icons of pieces eaten to JPanel
            }
            whiteContainer.add(whiteSide, BorderLayout.SOUTH);
        }
        else if(choice == 4) //if 4 - white wins, black loses
        {
            gameOverPanel1.add(whiteWin, BorderLayout.CENTER);
            blackContainer.add(gameOverPanel1, BorderLayout.CENTER);
            gameOverPanel2.add(blackLose, BorderLayout.CENTER);
            whiteContainer.add(gameOverPanel2, BorderLayout.CENTER);
        }
        else if(choice == 5) //if 5 - black wins, white loses
        {
            gameOverPanel1.add(blackWin, BorderLayout.CENTER);        
            whiteContainer.add(gameOverPanel1, BorderLayout.CENTER);
            gameOverPanel2.add(whiteLose, BorderLayout.CENTER);
            blackContainer.add(gameOverPanel2, BorderLayout.CENTER);
        }
        else if(choice == 6) //if 6 - its a draw
        {
            gameOverPanel1.add(drawMessage, BorderLayout.CENTER);
            gameOverPanel2.add(drawMessage1, BorderLayout.CENTER);
            whiteContainer.add(gameOverPanel1, BorderLayout.CENTER);
            blackContainer.add(gameOverPanel2, BorderLayout.CENTER);
        }
        else if(choice == 7) //if 7 - white is in check
        {
            choice = 1;
            gameOverPanel1.add(checkMessage, BorderLayout.CENTER);
            blackContainer.add(gameOverPanel1, BorderLayout.CENTER);

            //for white pieces eaten
            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] == null)
                    break;
                else
                    whiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));//add icons of pieces eaten to JPanel
            }
            whiteContainer.add(whiteSide, BorderLayout.SOUTH);
        }
        else if(choice == 8) //if 8 - black is in check
        {
            choice = 1;
            gameOverPanel1.add(checkMessage, BorderLayout.CENTER);
            whiteContainer.add(gameOverPanel1, BorderLayout.CENTER);

            //for black pieces eaten
            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] == null)
                    break;
                else
                    blackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));//add icons of pieces eaten to JPanel
            }
            blackContainer.add(blackSide, BorderLayout.CENTER);
        }
        else if(choice == 9) //white cant move somewhere
        {
            choice = 1;
            gameOverPanel1.add(cantMove, BorderLayout.CENTER);
            blackContainer.add(gameOverPanel1, BorderLayout.CENTER);

            //for white pieces eaten
            for(int i = 0; i < 32; i++) 
            {
                if(board.getWhiteEaten()[i] == null)
                    break;
                else
                    whiteSide.add(new JLabel(new ImageIcon(board.getWhiteEaten()[i].toString())));//add icons of pieces eaten to JPanel
            }
            whiteContainer.add(whiteSide, BorderLayout.SOUTH);
        }
        else if(choice == 10)
        {
            choice = 1;
            gameOverPanel1.add(cantMove, BorderLayout.CENTER);
            whiteContainer.add(gameOverPanel1, BorderLayout.CENTER);

            //for black pieces eaten
            for(int i = 0; i < 32; i++)
            {
                if(board.getBlackEaten()[i] == null)
                    break;
                else
                    blackSide.add(new JLabel(new ImageIcon(board.getBlackEaten()[i].toString())));//add icons of pieces eaten to JPanel
            }
            blackContainer.add(blackSide, BorderLayout.CENTER);
        }
        //add these to side panel
        score.add(whiteContainer);
        score.add(middle);
        score.add(blackContainer);

        if(isWhitesTurn) //if it's white's turn
        {
            blackContainer.add(new JLabel (new ImageIcon("ChessPieceIcons/Dot.png")), BorderLayout.NORTH); //display a green dot
            whiteContainer.add(new JLabel(new ImageIcon("ChessPieceIcons/NotDot.png")), BorderLayout.NORTH); //display an empty dot
        }
        else //if it's blacks turn
        {
            whiteContainer.add(new JLabel(new ImageIcon("ChessPieceIcons/Dot.png")), BorderLayout.NORTH); //display a green dot
            blackContainer.add(new JLabel(new ImageIcon("ChessPieceIcons/NotDot.png")), BorderLayout.NORTH); //display an empty dot
        }

        return score;
    }

    /**
     * When button is pressed
     * 
     * @param the action event performed
     */
    public void actionPerformed (ActionEvent e)
    {
        if(winner == false)//if there is no winner
        {
            for(int r = 0; r < 8; r++)
            {
                for(int c = 0; c < 8; c++)
                {
                    if(buttons[r][c] == e.getSource()) //finds which JButton was pressed
                    {
                        if(firstClick || board.getBoard()[r][c] instanceof ChessPiece && board.getBoard()[r][c].getIsWhite() == isWhitesTurn)
                        //if its a 'first click' on a piece, and it's the player's turn
                        {
                            if(board.getBoard()[r][c] instanceof ChessPiece) 
                            {
                                //if it's the player's turn
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
                            if(!promoting) //if not promoting a pawn
                            {
                                if(board.move(board.getBoard()[oldRow][oldCol], r, c)) //if a piece can move somewhere
                                {
                                    if(board.getBoard()[r][c] instanceof Pawn) //if the piece is a pawn
                                    {
                                        if(board.getBoard()[r][c].getIsWhite() && board.getBoard()[r][c].getRow() == 0)
                                        //if its a white pawn and at the end of the board
                                        {
                                            //promote it
                                            choice = 2;
                                            promoting = true;
                                            promoteRow = r;
                                            promoteCol = c;
                                            gamePane();
                                        }
                                        else if(!board.getBoard()[r][c].getIsWhite() && board.getBoard()[r][c].getRow() == 7)
                                        //if its a black pawn and at the end of the board
                                        {
                                            //promote it
                                            choice = 3;
                                            promoting = true;
                                            promoteRow = r;
                                            promoteCol = c;
                                            gamePane();  
                                        }
                                    }
                                    if(isWhitesTurn) //if it's whites turn
                                    {
                                        if(board.checkCheckMate(false)) //if black is checkmated
                                        {
                                            winner = true; //there is a winner
                                            choice = 4;
                                        }
                                        else if(board.checkStaleMate(false)) //if black is stalemated
                                        {
                                            winner = true; //there is a 'winner'
                                            choice = 6;
                                        }
                                        isWhitesTurn = false; //no longer white's turn
                                    }
                                    else //if it's black's turn
                                    {
                                        if(board.checkCheckMate(true)) //if white is checkmated
                                        {
                                            winner = true;//there is a winner
                                            choice = 5;                                          
                                        }
                                        else if(board.checkStaleMate(true)) //if white is stalemated
                                        {
                                            winner = true; //there is a 'winner'
                                            choice = 6;                                            
                                        }
                                        isWhitesTurn = true; //no longer black's turn
                                    }
                                    firstClick = true; //reset click counter
                                    gamePane(); //refresh chess board
                                }
                                else
                                {
                                    if(board.getBoard()[oldRow][oldCol].getIsWhite()) //if white can't move somewhere
                                        choice = 9; //tell them
                                    else //if black can't move somewhere
                                        choice = 10; //tell them
                                    gamePane();
                                }
                            }
                            firstClick = true; //reset click counter
                        }
                    }
                }
            }
            //look through board for king
            for(int r = 0; r < 8; r++) 
            {
                for(int c = 0; c < 8; c++)
                {
                    if(board.getBoard()[r][c] instanceof King) //if king is found
                    {
                        King king = (King)board.getBoard()[r][c];//casting to use King's methods
                        if(king.checked()) //if king is checked
                        {
                            if(!board.checkCheckMate(king.getIsWhite()))//if it's not a checkmate
                            {
                                if(king.getIsWhite())//if king is white
                                    choice = 7; //tell them
                                else //if king is black
                                    choice = 8; //tell them
                                gamePane(); //refresh game board
                            }
                        }
                    }
                }
            }

            if(e.getActionCommand().equals("Queen")) //if pawn wants to be promoted to queen
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Queen");
                gamePane();
            }
            else if(e.getActionCommand().equals("Rook")) //if pawn wants to be promoted to rook
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Rook");
                gamePane();
            }
            else if(e.getActionCommand().equals("Bishop")) //if pawn wants to be promoted to bishop
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Bishop");
                gamePane();
            }
            else if(e.getActionCommand().equals("Knight")) //if pawn wants to be promoted to knight
            {
                choice = 1;
                promoting = false;
                board.promote(board.getBoard()[promoteRow][promoteCol], "Knight");
                gamePane();
            }

            if(e.getActionCommand().equals("White Resign")) //if white resigns 
            {
                winner = true;
                choice = 5;
                gamePane();
            }

            if(e.getActionCommand().equals("Black Resign")) //if black resigns
            {
                winner = true;
                choice = 4;
                gamePane();
            }

            if(e.getActionCommand().equals("Draw")) //if it's a draw
            {
                winner = true;
                choice = 6;
                gamePane();
            }
        }

        if(e.getActionCommand().equals("Exit")) //if they exit the game
        {
            layeredPane.removeAll();
            layeredPane.add(mainMenu, top, 0);
            layeredPane.add(imgPanel, middle, 1);
        }

        if(e.getActionCommand().equals("New Game")) //if new game is clicked
        {
            //start a new game; reset variables
            winner = false;
            choice = 1;
            isWhitesTurn = true;
            
            board = new Board();
            board.SetGame(); //sets up the chess board
            
            gamePane();
            layeredPane.removeAll();
            layeredPane.add(pane, top, 0);
            layeredPane.add(scorePanel(), top, 1); 
        }
    }

}
