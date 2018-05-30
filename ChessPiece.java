import java.util.*;
/**
 * 
 *
 * @author Rain Ma
 * @version 26/05/2018
 */
public abstract class ChessPiece
{
    // instance variables - replace the example below with your own
    private int[][] moves; //array of squares that the chessPiece attacks
    private boolean isWhite;//  color of chess piece
    private int row;//row of square chess piece is on
    private int col;//col of square chess piece is on
    private double value;// extra. currently no implemtation
    private int moveNumber;//the last time the piece was moved
    private static int gameMoveNumber;//the current move number
    /**
     * Constructor for objects of class ChessPiece
     * @param row This is the row num of the square that the ChessPiece is starting on
     * @param col This is the column num of the square that the ChessPiece is starting on
     * @param isWhite This is the color of the ChessPiece. if isWhite is true, the ChessPiece is white
     */
    public ChessPiece(int row, int col, boolean isWhite)
    {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;

    }
    
    /**
     * an empty constructor for objects of class ChessPiece
     */
    public ChessPiece()
    {
        
    }
    
     /**
     * a Constructor that clones another ChessPiece
     * 
     * @param piece This is the ChessPiece that is being cloned
     */
    public ChessPiece(ChessPiece piece)
    {
        this.row = piece.row;
        this.col = piece.col;
        this.isWhite = piece.isWhite;
        this.moves = piece.moves;
        this.value = piece.value;
        
        
    }
    
    /**
     * finds all the squares that the chess piece is attacking or all squares that the chess piece can move to
     */
    public abstract void findMove(ChessPiece[][] board, boolean attacks);

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
     * returns the value of the piece in pawns. Currently has no implemetation
     * @return the value of piece 
     */
    public abstract double evaluate(ChessPiece[][] board);

    /**
     * accessor method for the instacne variable row
     * @return row
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * accessor method for the instacne variable col
     * @return col
     */
    public int getCol()
    {

        return col;
    }

    /**
     * checks if the chessPiece is attacking the square
     * @param row This is the row of the square being checked
     * @param col This is the column of the square being checked
     */
    public boolean attacks(int row,int col)
    {
        
        if(moves!=null)// so the program doesnt throw nullPointerExceptions
        {
            for(int i=0;i<moves.length;i++)
            {
                if(moves[i][0]==row&&moves[i][1]==col)
                {

                    return true;
                }

            }
            
        }
        return false;

    }
    
    /**
     * mutator method of the instace variable moveNumber
     * @param moveNumber This is the new value for moveNumber
     */
    public void setMoveNumber(int moveNumber)
    {
        this.moveNumber = moveNumber;
    }
    
    /**
     * accessor method for the instance variable moveNumber
     * @return moveNumber
     */
    public int getMoveNumber()
    {
        return moveNumber;
    }
    
    /**
     * @return gameMoverNumber then increasing it by one so that no two pieces on a board have the same move number
     */
    public int getGameMoveNumber()
    {
        gameMoveNumber++;
        return gameMoveNumber;
    }
    
}
