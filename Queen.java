import java.util.*;
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Rook
     */
    public Queen(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    public void findMove(ChessPiece[][] moves)
    {
        int[][] move = new int[27][2];
        int counter = 0;
        boolean ur = true;
        boolean dr = true;
        boolean ul = true;
        boolean dl = true;
        for(int r = 0; r<8; r++)
        {
            for(int c = 0; c<8; c++)
            {
                if(r>getRow() && c>getCol() && ur == true)
                {
                    if(moves[r][getCol()] instanceof ChessPiece){
                        ur = false;
                        break;
                    }else if(getRow() == r && getCol() == c) 
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
                    }else if(getRow() == r && getCol() == c) 
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
                    }else if(getRow() == r && getCol() == c) 
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
                    }else if(getRow() == r && getCol() == c) 
                        break;
                    else if(Math.abs(getRow() - r) != Math.abs(getCol() - c))
                        break;
                    else{
                        move[counter][0] = r;
                        move[counter][1] = c;
                        counter++;
                    }
                }else if(moves[r][getCol()] instanceof ChessPiece){
                    break;
                }else if(getRow() != r && getCol() != c) 
                    break;
                else if(getRow() == r && getCol() == c) 
                    break;
                else if(moves[r][c] instanceof ChessPiece)
                    break;
                else{
                    move[counter][0] = r;
                    move[counter][1] = c;
                    counter++;
                }
            }
            if(counter>27)
                break;
        }
        
        int[][] finaleMoves = new int[counter][2];
        super.allMoves(finaleMoves);
        newMoves(finaleMoves);
    }

    public void newMoves(ChessPiece[][] moves)
    {

    }

    public double evaluate(ChessPiece[][] board)
    {
        return 2;
    }

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteQueen.png";
        else 
            return "ChessPieceIcons/BlackQueen.png";
    }
}
