import java.util.*;
public abstract class Knight extends ChessPiece
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class asdf
     */
    public Knight(int row, int col, boolean isWhite)
    {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;

    }

    public abstract void findMove();

    public void newMoves(ArrayList[][] moves)
    {
        for(int r = 0; r<8; r++)
        {
            for(int c = 0; c<8; c++)
            {
                if(ChessPiece[r][c] instanceof ChessPiece && ChessPiece[row][col].isWhite != ChessPiece[r][c].iswhite)
                    break;
                else if(Math.abs(row - r) == 2 && Math.abs(col - c) == 1) 
                    moves[r][c].add(r,c);
                else if (Math.abs(row - r) == 1 && Math.abs(col - c) == 2) 
                    moves[r][c].add(r,c);
                else
                    break;
                this.moves = moves;
            }
        }
    }

    public boolean getIsWhite()
    {
        return isWhite;
    }

}
