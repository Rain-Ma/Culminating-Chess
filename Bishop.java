import java.util.Arrays;
/**
 * 
 * @author Jutin Chu
 * @version May 25th, 2018
 */
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Bishop
     */
    public Bishop(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }
    
    public Bishop(ChessPiece piece)
    {
        super(piece);
    }
    
    public void findMove(ChessPiece[][] board)
    {
        int[][] moves = new int[13][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //counter for possible moves
 
        if(getRow() < 7 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()+i > 7)
                    break;
                if(board[getRow()+i][getCol()+i] instanceof ChessPiece)
                {
                    if(board[getRow()+i][getCol()+i].getIsWhite() != getIsWhite())
                    {
                        moves[count][0] = getRow()+i;
                        moves[count][1] = getCol()+i;
                        count++;
                        break;
                    }
                    else
                        break;
                }
                moves[count][0] = getRow()+i;
                moves[count][1] = getCol()+i;
                count++;
            }
        }
        
        if(getRow() < 7 && getCol() > 0)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()-i < 0)
                    break;
                if(board[getRow()+i][getCol()-i] instanceof ChessPiece)
                {
                    if(board[getRow()+i][getCol()-i].getIsWhite() != getIsWhite())
                    {
                        moves[count][0] = getRow()+i;
                        moves[count][1] = getCol()-i;
                        count++;
                        break;
                    }
                    else
                        break;
                }
                moves[count][0] = getRow()+i;
                moves[count][1] = getCol()-i;
                count++;
            }
        }
        
        if(getRow() > 0 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()-i < 0 || getCol()+i > 7)
                    break;
                if(board[getRow()-i][getCol()+i] instanceof ChessPiece)
                {
                    if(board[getRow()-i][getCol()+i].getIsWhite() != getIsWhite())
                    {
                        moves[count][0] = getRow()-i;
                        moves[count][1] = getCol()+i;
                        count++;
                        break;
                    }
                    else
                        break;
                }
                moves[count][0] = getRow()-i;
                moves[count][1] = getCol()+i;
                count++;
            }
        }
        
        if(getRow() > 0 && getCol() > 0)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()-i < 0 || getCol()-i < 0)
                    break;
                if(board[getRow()-i][getCol()-i] instanceof ChessPiece)
                {
                    if(board[getRow()-i][getCol()-i].getIsWhite() != getIsWhite())
                    {
                        moves[count][0] = getRow()-i;
                        moves[count][1] = getCol()-i;
                        count++;
                        break;
                    }
                    else
                        break;
                }
                moves[count][0] = getRow()-i;
                moves[count][1] = getCol()-i;
                count++;
            }
        }
        
        //trims size of move array to only have the number of possible moves
        int[][] finaleMoves = Arrays.copyOf(moves,count);
        newMoves(finaleMoves);
    } 

    /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 3.1;
        if(getIsWhite())
        {
            return value;
        }
        else
        {
            return value*-1;
        }
    }

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteBishop.png";
        else
            return "ChessPieceIcons/BlackBishop.png";
    }
}
