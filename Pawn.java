
/**
 * Write a description of class pawn here.
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
        int[][] moves =  new int[4][2];
        int count=0;
        if(getIsWhite())
        {

            if(board[getRow()+1][getCol()]==null)
            {
                moves[count][0] = getRow()+1;
                moves[count][1] = getCol();
                count++;
                if(!moved&&board[getRow()+2][getCol()]==null)
                {
                    moves[count][0] = getRow()+2;
                    moves[count][1] = getCol();
                    count++;
                }
            }
            if(getCol()>0)
            {
                if(board[getRow()+1][getCol()+1]!=null)
                {
                    if(board[getRow()+1][getCol()+1].getIsWhite()==getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()+1;
                        count++;
                    }
                }

            }
            if(getCol()<7)
            {
                if(board[getRow()+1][getCol()-1]!=null)
                {
                    if(board[getRow()+1][getCol()-1].getIsWhite()==getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()-1;
                        count++;
                    }
                }

            }
        }
        else
        {
             if(board[getRow()-1][getCol()]==null)
            {
                moves[count][0] = getRow()-1;
                moves[count][1] = getCol();
                count++;
                if(!moved&&board[getRow()-2][getCol()]==null)
                {
                    moves[count][0] = getRow()- -2;
                    moves[count][1] = getCol();
                    count++;
                }
            }
            if(getCol()>0)
            {
                if(board[getRow()+1][getCol()+1]!=null)
                {
                    if(board[getRow()+1][getCol()+1].getIsWhite()==getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()+1;
                        count++;
                    }
                }

            }
            if(getCol()<7)
            {
                if(board[getRow()+1][getCol()-1]!=null)
                {
                    if(board[getRow()+1][getCol()-1].getIsWhite()==getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()-1;
                        count++;
                    }
                }

            }

        }
        int[][] finaleMoves = new int[counter][2];
        super.allMoves(finaleMoves);
        newMoves(finaleMoves);
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
        {
            return "ChessPieceIcons/WhitePawn";
        }
        else 
        {
            return "ChessPieceIcons/BlackPawn";
        }
        
        
    }
}
