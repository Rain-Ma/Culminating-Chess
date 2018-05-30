import java.util.Arrays;
/**
 * 
 * @author Jutin Chu
 * @version May 25th, 2018
 */
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Rook
     * @param row This is the row num of the square that the Bishop is starting on
     * @param col This is the column num of the square that the Bishop is starting on
     * @param isWhite This is the color of the Bishop. if isWhite is true, the Bishop is white
     */
    public Bishop(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }
    
    /**
     * a Constructor that clones another Bishop
     * 
     * @param piece This is the Bishop that is being cloned
     */
    public Bishop(ChessPiece piece)
    {
        super(piece);
    }
    
    @Override
    /**
     * @param board This is the chess board
     * @param attack Not appicable to Bishop since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[13][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //counter for possible moves
        
        //starts checking squares from the bishop to top right corner of the board
        if(getRow() < 7 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()+i > 7) //if the loop moves off the board
                    break;
                if(board[getRow()+i][getCol()+i] instanceof ChessPiece)// if the bishop hits a chess piece
                {
                    if(board[getRow()+i][getCol()+i].getIsWhite() != getIsWhite())// if the chesspiece is of the opposite color, then it can be captured
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
        
        //starts checking squares from the bishop to top left corner of the board
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
        
        //starts checking squares from the bishop to bottom right corner of the board
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
        //starts checking squares from the bishop to bottem left corner of the board
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

    @Override
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
    
    @Override
    /**
     * @return the memory adress of the bishop png
     */
    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteBishop.png";
        else
            return "ChessPieceIcons/BlackBishop.png";
    }
}
