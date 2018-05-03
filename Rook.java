import java.util.*;
public abstract class Rook
{
    // instance variables - replace the example below with your own
    private ArrayList[][] moves;
    private boolean isWhite;
    int row;
    int col;

    /**
     * Constructor for objects of class asdf
     */
    public Rook(int row, int col, boolean isWhite)
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
                if(r > 8 || r < 1 || c > 'h' || c < 'a') // checks if wanted move is on the board
                    break;          
                else if(row != r && col != c) // makes sure only either the row or column is changed
                    break;
                else if(row == r && col == c) // makes sure the piece is actually moved
                    break;
                else if(ChessPiece[r][c] instanceof ChessPiece)
                    break;
                else 
                    moves[r][c].add(r,c);
            }
        }
        this.moves = moves;
    }

    public boolean getIsWhite()
    {
        return isWhite;
    }

}
