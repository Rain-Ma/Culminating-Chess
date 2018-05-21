import java.util.*;
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Bishop
     */
    public Bishop(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    //public abstract void findMove();
    
    public void findMove(ChessPiece[][] moves)
    {
        boolean ur = true;
        boolean dr = true;
        boolean ul = true;
        boolean dl = true;
        int[][] move = new int[13][2];
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
                        counter++;
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
                        counter++;
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
                        counter++;
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
                        counter++;
                    }
                }
            }
            if(counter>13)
                break;
        }
        int[][] finaleMoves = new int[counter][2];
        super.allMoves(finaleMoves);
        newMoves(finaleMoves)
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
