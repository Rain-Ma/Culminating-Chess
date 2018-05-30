//import statement
import java.util.Arrays;
/**
 * Rook chess piece class
 * 
 * @author Rain Ma & Justin Chu
 * @version 29/05/2018
 */
public class Rook extends ChessPiece
{
    private boolean moved; //to keep track of whether the rook has moved or not
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
     * Finds all possible moves for rook objects
     * 
     * @param board This is the chess board
     * @param attack Not appicable to rook since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[14][2]; //the total number of possible moves a rook can have at once [most moves possible][coordinates]
        int count = 0; //number of possible moves
        //if the rook trys to move down the board
        if(getRow()<7)
        {
            for(int r = getRow()+1;r<8;r++)
            {
                if(board[r][getCol()] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[r][getCol()].getIsWhite()!=getIsWhite()) //if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = r;
                        moves[count][1] = getCol();
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is friendly piece
                    {
                        break;
                    }
                }    
                //add coordinates to array
                moves[count][0] = r;
                moves[count][1] = getCol();
                count++; //increase number of possible moves in array
            }
        }
        //if the rook trys to move up the board
        if(getRow()>0)
        {
            for(int r = getRow()-1;r>=0;r--)
            {
                if(board[r][getCol()] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[r][getCol()].getIsWhite()!=getIsWhite()) //if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = r;
                        moves[count][1] = getCol();
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a friendly piece
                    {
                        break;
                    }
                }
                //add coordinates to array
                moves[count][0] = r;
                moves[count][1] = getCol();
                count++; //increase number of possible moves in array
            }
        }
        //if the rook trys to move right
        if(getCol()<7)
        {
            for( int c = getCol()+1;c<8;c++)
            {
                if(board[getRow()][c] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[getRow()][c].getIsWhite()!=getIsWhite())//if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = getRow();
                        moves[count][1] = c;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a friendly piece
                    {
                        break;
                    }
                }
                //add coordinates to array
                moves[count][0] = getRow();
                moves[count][1] = c;
                count++; //increase number of possible moves in array
            }
        }
        //if the rook trys to move left
        if(getCol()>0)
        {
            for( int c = getCol()-1;c>=0;c--)
            {
                if(board[getRow()][c] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[getRow()][c].getIsWhite()!=getIsWhite()) //if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = getRow();
                        moves[count][1] = c;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a freindly piece
                    {
                        break;
                    }
                }
                //add coordinates to array
                moves[count][0] = getRow();
                moves[count][1] = c;
                count++; //increase number of possible moves in array
            }
        }
        int[][] finaleMoves = Arrays.copyOf(moves,count); //truncates size of array

        newMoves(finaleMoves); //make the array the new array of moves the rook can make
    }

    /**
     * Returns if the rook has moved
     * 
     * @return if whether or not the rook has been moved
     */
    public boolean hasMoved()
    {
        return moved;
    }

    /**
     * Returns the location of the rook image
     * 
     * @return the String address of the rook icon
     */
    public String toString()
    {
        if(getIsWhite()) //if piece is white, return white icon
        {
            return "ChessPieceIcons/WhiteRook.png";
        }
        else //if piece is black, return black icon
        {
            return "ChessPieceIcons/BlackRook.png";
        }
    }

    /**
     * Mutator method for the instance variable moved
     * 
     * @param moved This is the boolean moved is being set to
     */
    public void setMoved(boolean moved)
    {
        this.moved = moved;
    }
}
