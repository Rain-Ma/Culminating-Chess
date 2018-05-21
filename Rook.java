/**
 * 
 *
 * @author Rain Ma
 * @version 09/05/2018
 */
public class Rook extends ChessPiece
{
    // instance variables - replace the example below with your own
    private boolean moved;
    /**
     * Constructor for objects of class asdf
     */
    public Rook(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
        moved = false;
    }

    public void findMove(ChessPiece[][] board)
    {
        int[][] moves = new int[14][2];
        int count = 0;
        for( int r = getRow()+1;r<8;r++)
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
        for( int c = getRow()-1;c<8;c++)
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

    public boolean hasMoved()
    {
        return moved;
    }

    public double evaluate(ChessPiece[][] board)
    {
        return 5;
    }

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteRook.png";
        else
            return "ChessPieceIcons/BlackRook.png";
    }
}
