import java.util.*;
public abstract class Rook extends ChessPiece
{
    /**
     * Constructor for objects of class Rook
     */
    public Rook(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    public abstract void findMove();

    public void newMoves(ChessPiece[][] moves)
    {
        int[][] move = new int[14][2];
        int counter = 0;
        for(int r = 0; r<8; r++)
        {
            if(moves[r][getCol()] instanceof ChessPiece != true)
            {
                for(int c = 0; c<8; c++)
                {
                    if(getRow() != r && getCol() != c) // makes sure only either the row or column is changed
                        break;
                    else if(getRow() == r && getCol() == c) // makes sure the piece is actually moved
                        break;
                    else if(moves[r][c] instanceof ChessPiece)
                        break;
                    else{
                        move[counter][0] = r;
                        move[counter][1] = c;
                        counter++;
                    }
                }
            }
            if(counter>14)
                break;
        }
    }
}