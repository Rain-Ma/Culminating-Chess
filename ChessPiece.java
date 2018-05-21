import java.util.*;
/**
 * Write a description of class asdf here.
 *
 * @author Rain Ma
 * @version 17/05/2018
 */
public abstract class ChessPiece
{
    // instance variables - replace the example below with your own
    private int[][] moves;
    private boolean isWhite;
    private int row;
    private int col;
    private double value;// extra
    /**
     * Constructor for objects of class ChessPiece
     */
    public ChessPiece(int row, int col, boolean isWhite)
    {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;

    }

    public abstract void findMove(ChessPiece[][] board);

    /**
     * @return the squares that the chess piece can move to [i][0] is row [i][1] is col
     */
    public int[][] getMoves()
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
    public void moveCol(int col)
    {

        this.col = col;

    }

    /**
     * @param the new list of possible moves the chess piece can make
     */
    public void newMoves(int[][] moves)
    {
        this.moves = moves;
    }

    /**
     * leave it blank for now
     * @return the value of piece 
     */
    public abstract double evaluate(ChessPiece[][] board);

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {

        return col;
    }
    
    public boolean attacks(int row,int col)
    {
        for(int i=0;i<moves.length;i++)
        {
            if(moves[i][0]==row&&moves[i][1]==col)
            {
                
                return true;
            }
            
            
        }
        
        return false;
        
    }
    
    public void allMoves(int[][] moves)
    {
        int r= moves.length;
        int c= moves[0].length;
        this.moves= new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                    this.moves[i][j] = moves[i][j];
            }
        }
    }
}
