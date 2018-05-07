import java.util.*;
public abstract class Bishop extends ChessPiece
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class asdf
     */
    public Bishop(int row, int col, boolean isWhite)
    {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;

    }

    public abstract void findMove();

    public void newMoves(ArrayList[][] moves)
    {
        boolean ur = true;
        boolean dr = true;
        boolean ul = true;
        boolean dl = true;
        for(int r = 0; r<8; r++)
        {           
            for(int c = 0; c<8; c++)
            {
                if(r>row && c>col && ur == true)
                {
                    if(ChessPiece[r][col] instanceof ChessPiece){
                        ur = false;
                        break;
                    }else if(row == r && col == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(row - r) != Math.abs(col - c))
                        break;
                    else
                        moves[r][c].add(r,c);
                }
                else if(r<row && c>col && dr == true)
                {
                    if(ChessPiece[r][col] instanceof ChessPiece){
                        dr = false;
                        break;
                    }else if(row == r && col == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(row - r) != Math.abs(col - c))
                        break;
                    else
                        moves[r][c].add(r,c);
                }
                else if(r>row && c<col && ul == true)
                {
                    if(ChessPiece[r][col] instanceof ChessPiece){
                        ul = false;
                        break;
                    }else if(row == r && col == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(row - r) != Math.abs(col - c))
                        break;
                    else
                        moves[r][c].add(r,c);
                }
                else if(r<row && c<col && dl == true)
                {
                    if(ChessPiece[r][col] instanceof ChessPiece){
                        dl = false;
                        break;
                    }else if(row == r && col == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(row - r) != Math.abs(col - c))
                        break;
                    else
                        moves[r][c].add(r,c);
                }
            }
        }
        this.moves = moves;
    }

    public boolean getIsWhite()
    {
        return isWhite;
    }

}
