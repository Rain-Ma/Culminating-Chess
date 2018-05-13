/**
 * Write a description of class board here.
 *
 * @author Rain Ma
 * @version 09/05/2018
 */
public class Board
{
    // instance variables - replace the example below with your own
    private ChessPiece[][] GameBoard;// the entire board
    private ChessPiece[] blackPieces;  // the black Pieces
    private ChessPiece[] whitePieces;  // the white pieces
    GameFrame game;//the JFrame in the GUI
    /**
     * Constructor for objects of class board
     */
    public Board()
    {
        game = new GameFrame(); //new GameFrame   
        GameBoard = new ChessPiece[8][8];
    }

    /**
     * @return the 2d array of pieces which represents the 8 by 8 chess board
     */
    public ChessPiece[][] getBoard()
    {
        return GameBoard;
    }

    /**
     * @return the white pieces
     */
    public ChessPiece[] getWhitePieces()
    {
        return whitePieces;
    }

    /**
     * @return the black pieces 
     */
    public ChessPiece[] getBlackPieces()
    {
        return blackPieces;
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
        
        game.changeBoard(GameBoard);
    }

    public void runGame()
    {
        game.changeBoard(GameBoard);
    }

    /**
     * @param ChessPiece is the piece being moved
     * @param row and col is where the piece is being moved to in GameBoard
     */
    /*
    public void move(ChessPiece piece, int row, int col)
    {
    if(piece.getClass() == King)
    {

    }

    else if(piece.attacks(row,col))
    {
    GameBoard[row][col] = piece;
    GameBoard[piece.getRow()][piece.getCol()] = null;
    piece.setRow(row);
    piece.setCol(col);

    }

    }
     */
}
