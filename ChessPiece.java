import java.util.*;
/**
 * Write a description of class asdf here.
 *
 * @author Rain Ma
 * @version 23/04/2018
 */
public abstract class ChessPiece
{
    // instance variables - replace the example below with your own
    private int[][] moves;
    private boolean isWhite;
    int row;
    int col;
    
    /**
     * Constructor for objects of class asdf
     */
    public ChessPiece(int row, int col, boolean isWhite)
    {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        
    }
    
    public abstract void findMove();
    
    
    public int[][] getMoves()
    {
        return moves;
    }
    
    public boolean getIsWhite()
    {
        return isWhite;
    }
    
   
}
