/**
 * Super-class of all ChessPiece objects
 *
 * @author Rain Ma
 * @version 26/05/2018
 */
public abstract class ChessPiece
{
    //instance variables
    private int[][] moves; //array of squares that the chessPiece attacks
    private boolean isWhite;//  color of chess piece
    private int row;//row of square chess piece is on
    private int col;//col of square chess piece is on
    private double value;// extra. currently no implemtation
    private int moveNumber;//the last time the piece was moved
    private static int gameMoveNumber;//the current move number
    /**
     * Constructor for objects of class ChessPiece
     * 
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
     * An empty constructor for objects of class ChessPiece
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
     * Finds all the squares that the chess piece is attacking or all squares that the chess piece can move to
     */
    public abstract void findMove(ChessPiece[][] board, boolean attacks);

    /**
     * Returns the squares a chess piece can move to
     * 
     * @return the squares that the chess piece can move to [i][0] is row [i][1] is col
     */
    public int[][] getMoves()
    {
        return moves;
    }

    /**
     * Return whether or not a chess piece is white
     * 
     * @return if the chess piece is white
     */
    public boolean getIsWhite()
    {
        return isWhite;
    }

    /**
     * Mutator - changes int row of chess piece
     * 
     * @param row is the new value for row number
     */
    public void moveRow(int row)
    {
        this.row = row;
    }

    /**
     * Mutator - changes int column of chess piece
     * 
     * @param col is the new value for column number
     */
    public void moveCol(int col)
    {
        this.col = col;
    }

    /**
     * The new array of a chess piece's possible moves
     * 
     * @param the new list of possible moves the chess piece can make
     */
    public void newMoves(int[][] moves)
    {
        this.moves = moves;
    }

    /**
     * Accessor method for a chess piece's row location
     * 
     * @return row of chess piece
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Accessor method for a chess piece's column location
     * 
     * @return column of chess piece
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Checks if the chessPiece is attacking the square
     * 
     * @param This is the row of the square being checked
     * @param This is the column of the square being checked
     * 
     * @return if a chess piece is attacking the square given
     */
    public boolean attacks(int row,int col)
    {
        if(moves!=null) //so the program doesnt throw nullPointerExceptions
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
     * Mutator method of the instance variable moveNumber
     * 
     * @param moveNumber This is the new value for moveNumber
     */
    public void setMoveNumber(int moveNumber)
    {
        this.moveNumber = moveNumber;
    }

    /**
     * Accessor method for the instance variable moveNumber
     * 
     * @return moveNumber
     */
    public int getMoveNumber()
    {
        return moveNumber;
    }

    /**
     * Gives piece move number
     * 
     * @return gameMoveNumber then increasing it by one so that no two pieces on a board have the same move number
     */
    public int getGameMoveNumber()
    {
        gameMoveNumber++;
        return gameMoveNumber;
    }

}
