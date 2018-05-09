import java.util.*;
public abstract class Bishop extends ChessPiece
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class asdf
     */
    public Bishop(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    public abstract void findMove();
    
    public void newMoves(ChessPiece[][] moves)
    {
        boolean ur = true;
        boolean dr = true;
        boolean ul = true;
        boolean dl = true;
        int[][] move = new int[8][2];
        int counter = 0;
        for(int r = 0; r<8; r++)
        {           
            for(int c = 0; c<8; c++)
            {
                if(r>getRow() && c>getCol() && ur == true)
                {
                    if(moves[r][getCol()] instanceof ChessPiece){
                        ur = false;
                        break;
                    }else if(getRow() == r && getCol() == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(getRow() - r) != Math.abs(getCol() - c))
                        break;
                    else{
                        move[counter][0] = r;
                        move[counter][1] = c;
                    }
                }
                else if(r<getRow() && c>getCol() && dr == true)
                {
                    if(moves[r][getCol()] instanceof ChessPiece){
                        dr = false;
                        break;
                    }else if(getRow() == r && getCol() == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(getRow() - r) != Math.abs(getCol() - c))
                        break;
                    else{
                        move[counter][0] = r;
                        move[counter][1] = c;
                    }
                }
                else if(r>getRow() && c<getCol() && ul == true)
                {
                    if(moves[r][getCol()] instanceof ChessPiece){
                        ul = false;
                        break;
                    }else if(getRow() == r && getCol() == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(getRow() - r) != Math.abs(getCol() - c))
                        break;
                    else{
                        move[counter][0] = r;
                        move[counter][1] = c;
                    }
                }
                else if(r<getRow() && c<getCol() && dl == true)
                {
                    if(moves[r][getCol()] instanceof ChessPiece){
                        dl = false;
                        break;
                    }else if(getRow() == r && getCol() == c) // makes sure the piece is actually moved
                        break;
                    else if(Math.abs(getRow() - r) != Math.abs(getCol() - c))
                        break;
                    else{
                        move[counter][0] = r;
                        move[counter][1] = c;
                    }
                }
                counter++;
            }
        }
    }
}
