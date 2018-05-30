


import java.util.Arrays;
 /**
 *
 * @author Rain Ma
 * @version 29/05/2018
 */
public  class Rook extends ChessPiece
{

    private boolean moved; // to keep of wether the rook has moved or not
    /**
     * Constructor for objects of class Rook
     * @param row This is the row num of the square that the rook is starting on
     * @param col This is the column num of the square that the rook is starting on
     * @param isWhite This is the color of the rook. if isWhite is true, the rook is white
     */
    public Rook(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
        moved = false;
    }
    
    /**
     * a Constructor that clones another rook
     * 
     * @param piece This is the rook that is being cloned
     */
    public Rook(ChessPiece piece)
    {
        super(piece);
    }
    
    @Override
    /**
     * @param board This is the chess board
     * @param attack Not appicable to rook since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[14][2];
        int count = 0;
        //if the rook trys to move down the board
        if(getRow()<7)
        {
            for(int r = getRow()+1;r<8;r++)
            {
                if(board[r][getCol()] instanceof ChessPiece)
                {
                    if(board[r][getCol()].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = r;
                        moves[count][1] = getCol();
                        count++;
                        break;
                    }
                    else
                    {
                        break;
                    }
                }    
                moves[count][0] = r;
                moves[count][1] = getCol();
                count++;
            }
        }
        //if the rook trys to move up the board
        if(getRow()>0)
        {
            for( int r = getRow()-1;r>=0;r--)
            {
                if(board[r][getCol()] instanceof ChessPiece)
                {
                    if(board[r][getCol()].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = r;
                        moves[count][1] = getCol();
                        count++;
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
                moves[count][0] = r;
                moves[count][1] = getCol();
                count++;       
            }
        }
        //if the rook trys to move right
        if(getCol()<7)
        {
            for( int c = getCol()+1;c<8;c++)
            {
                if(board[getRow()][c] instanceof ChessPiece)
                {
                    if(board[getRow()][c].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = getRow();
                        moves[count][1] = c;
                        count++;
                        break;
                    }
                    else
                    {
                        break;
                    }

                }
                moves[count][0] = getRow();
                moves[count][1] = c;
                count++;   
            }
        }
        //if the rook trys to left
        if(getCol()>0)
        {
            for( int c = getCol()-1;c>=0;c--)
            {
                if(board[getRow()][c] instanceof ChessPiece)
                {
                    if(board[getRow()][c].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = getRow();
                        moves[count][1] = c;
                        count++;
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
                moves[count][0] = getRow();
                moves[count][1] = c;
                count++;   
            }
        }
        int[][] finaleMoves = Arrays.copyOf(moves,count);
       
        newMoves(finaleMoves);
    }

    /**
     * @return if whether or not the rook has been moved
     */
    public boolean hasMoved()
    {
        return moved;
    }

    /**
     * @return the memory address of the rook png
     */
    public String toString()
    {
        if(getIsWhite())
        {
            return "ChessPieceIcons/WhiteRook.png";
        }
        else 
        {
            return "ChessPieceIcons/BlackRook.png";
        }
    }
    
    /**
     * mutator method for the instance variable moved
     * @param moved This is the boolean moved is being set to
     */
    public void setMoved(boolean moved)
    {
        this.moved = moved;
    }
    
    /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 5;
        if(getIsWhite())
        {
            return value;
        }
        else
        {
            return value*-1;
        }
    }
}