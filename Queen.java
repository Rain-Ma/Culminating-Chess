import java.util.Arrays;
/**
 * 
 * @author Shawn && Rain Ma && Justin Chu
 * @version 21/05/2018
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen
     * @param row This is the row num of the square that the Queen is starting on
     * @param col This is the column num of the square that the Queen is starting on
     * @param isWhite This is the color of the Queen. if isWhite is true, the Queen is white
     */
    public Queen(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

     /**
     * a Constructor that clones another Queen
     * 
     * @param piece This is the Queen that is being cloned
     */
    public Queen(ChessPiece piece)
    {
        super(piece);
    }
    
    @Override
    /**
     * @param board This is the chess board
     * @param attack Not appicable to Queen since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board, boolean attack)
    {
        int[][] moves = new int[27][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //count for possible moves
        //starts diagonally checking squares from the queen  to the top right corner of the board
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
        
        //starts diagonally checking squares from the queen to the top left corner of the board
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

        //starts diagonally checking squares from the queen to the bottom right corner of the board
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
        //starts diagonally checking squares from the queen  to the bottom left corner of the board
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
        
        //starts checking squares linearly from the queen to the top of the board
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

        //starts checking squares linearly from the queen to the bottom of the board
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

        //starts checking squares linearly from the queen to the right of the board
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

        //starts checking squares linearly from the queen to the left of the board
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
    
    @Override
    /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 9;
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
     * @return the memory adress of the queen png
     */
    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteQueen.png";
        else 
            return "ChessPieceIcons/BlackQueen.png";
    }
}
