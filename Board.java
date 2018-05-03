
/**
 * Write a description of class board here.
 *
 * @author Rain Ma
 * @version 23/04/2018
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

    public ChessPiece[][] getBoard()
    {
        return GameBoard;
    }

    public ChessPiece[] getWhitePieces()
    {
        return whitePieces;
    }

    public ChessPiece[] getBlackPieces()
    {
        return blackPieces;

    }
    
    public void move(ChessPiece piece, int row, int col)
    {
        
        
        
    }
}
