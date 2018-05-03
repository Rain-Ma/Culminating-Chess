import java.util.*;
/**
 * Write a description of class asdf here.
 *
 * @author Rain Ma
 * @version 03/05/2018
 */
public abstract class ChessPiece
{
    // instance variables - replace the example below with your own
    private ArrayList[][] moves;
    private boolean isWhite;
    int row;
    int col;
    double value;// extra
    /**
     * Constructor for objects of class ChessPiece
     */
    public ChessPiece(int row, int col, boolean isWhite)
    {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;

    }

    public abstract void findMove();
    
    /**
     * @return the squares that the chess piece can move to [i][0] is row [i][1] is col
     */
    public ArrayList[][] getMoves()
    {
        return moves;
    }

    /**
     * @return if the chess piece is white
     */
    public boolean getIsWhite()
    {
        return isWhite;
    }

    /**
     * @param row is the new value for row number
     * mutator. changes int row of chess piece
     */
    public void moveRow(int row)
    {
        this.row = row;

    }

    /**
     * @param col is the new value for column number
     * mutator. changes int
     */
    public void moveCol()
    {

        this.col = col;

    }
    /**
     * @param the new list of possible moves the chess piece can make
     */
    public void newMoves(ArrayList[][] moves)
    {
        this.moves = moves;
    }
    
    /**
     * leave it blank for now
     * @return the value of piece 
     */
    public abstract double evaluate();
}
