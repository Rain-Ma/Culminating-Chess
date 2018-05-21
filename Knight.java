import java.util.*;
public class Knight extends ChessPiece
{
    /**
     * Constructor for objects of class Knight
     */
    public Knight(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    public void findMove(ChessPiece[][] moves)
    {
        int[][] move = new int[8][2];
        int counter = 0;
        for(int r = 0; r<8; r++)
        {
            for(int c = 0; c<8; c++)
            {
                if(moves[r][c] instanceof ChessPiece && moves[getRow()][getCol()].getIsWhite() == moves[r][c].getIsWhite())
                    break;
                else if((Math.abs(getRow() - r) == 2 && Math.abs(getCol() - c) == 1) || (Math.abs(getRow() - r) == 1 && Math.abs(getCol() - c) == 2)) 
                {
                    move[counter][0] = r;
                    move[counter][1] = c;  
                    counter++;
                }else
                    break;                
            }
            if(counter>8)
                break;
        }
        int[][] finaleMoves = new int[counter][2];
        super.allMoves(finaleMoves);
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
        double value =3;
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
            return "ChessPieceIcons/WhiteKnight.png";
        else 
            return "ChessPieceIcons/BlackKnight.png";
    }
}
