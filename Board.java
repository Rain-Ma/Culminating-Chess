
/**
 * Write a description of class board here.
 *
 * @author Rain Ma
 * @version 03/05/2018
 */
public class Board
{
    // instance variables - replace the example below with your own
    private ChessPiece[][] GameBoard ; // the entire board
    private ChessPiece[] blackPieces;  // the black Pieces
    private ChessPiece[] whitePieces;  // the white pieces

    /**
     * Constructor for objects of class board
     */
    public Board()
    {
    }
    
    /**
     * @return the 2d array of pieces which represents the 8 by 8 chess board
     */
    public ChessPiece[][] getBoard()
    {
        return GameBoard;
    }
    
    /**
     * @return the black pieces
     */
    public ChessPiece[] getWhitePieces()
    {
        return whitePieces;
    }
    
    /**
     * @return the black pieces still on the board
     */
    public ChessPiece[] getBlackPieces()
    {
        return blackPieces;

    }
    
    /**
     * @param ChessPiece is the piece being moved
     * @param row and col is where the piece is being moved to in GameBoard
     */
    public void move(ChessPiece piece, int row, int col)
    {
        
        
        
    }
}
