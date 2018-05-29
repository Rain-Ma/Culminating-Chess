import java.util.*;
/**
 * 
 * @author Shawn && Rain Ma && Justin Chu
 * @version 21/05/2018
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen
     */
    public Queen(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    public Queen(ChessPiece piece)
    {
        super(piece);
    }
    
    public void findMove(ChessPiece[][] board, boolean attack)
    {
        int[][] moves = new int[27][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //count for possible moves

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
        int[][] finaleMoves = new int[count][2];
        for(int i =0; i <finaleMoves.length;i++)
        {
            for(int j=0;j<2;j++)
            { 
                finaleMoves[i][j] = moves[i][j];
            }
        }
        newMoves(finaleMoves);
    }

    public void newMoves(ChessPiece[][] moves)
    {

    }

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

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteQueen.png";
        else 
            return "ChessPieceIcons/BlackQueen.png";
    }
}
