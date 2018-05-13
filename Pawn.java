
/**
 * Write a description of class Pawn here.
 *
 * @author Rain Ma
 * @version 09/05/2018
 */
public class Pawn extends ChessPiece
{
    boolean moved;
    boolean enPassant;
    public Pawn(int row,int col,boolean isWhite)
    {
        super(row,col,isWhite);
        moved = false;
        enPassant = false;
    }

    public void findMove(ChessPiece[][] board)
    {
        if(getIsWhite())
        {

            
        }
        else
        {

            
        }
    }
    public double evaluate()
    {

        return 2;
    }

    public boolean hasMoved()
    {
        return moved;
    }

    public boolean canEnPassant()
    {
        return enPassant;
    }

    public void setMoved(boolean moved)
    {
        this.moved = moved;
    }

    public void setEnPassant(boolean enPassant)
    {
        this.enPassant = enPassant;
    }

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhitePawn.png";
        else 
            return "ChessPieceIcons/BlackPawn.png";
    }
}
