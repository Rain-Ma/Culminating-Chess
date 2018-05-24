
/**
 * 
 *
 * @author Rain Ma
 * @version 21/05/2018
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

            if(board[getRow()-1][getCol()]==null)
            {
                moves[count][0] = getRow()-1;
                moves[count][1] = getCol();
                count++;
                if(!moved&&board[getRow()-2][getCol()]==null)
                {
                    moves[count][0] = getRow()-2;
                    moves[count][1] = getCol();
                    count++;
                }
            }
            if(getCol()>0)
            {
                if(board[getRow()-1][getCol()-1]!=null)
                {
                    if(board[getRow()-1][getCol()-1].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = getRow()-1;
                        moves[count][1] = getCol()-1;
                        count++;
                    }
                }
                else if(board[getRow()][getCol()-1]!=null)
                {
                    if(board[getRow()][getCol()-1] instanceof Pawn && board[getRow()][getCol()-1].getIsWhite()!=getIsWhite())
                    {
                        Pawn pawn = (Pawn)board[getRow()][getCol()-1];
                        if(pawn.canBeEnPassant())
                        {
                            moves[count][0] = getRow()-1;
                            moves[count][1] = getCol()-1;
                            count++;
                        }

                    }

                }

            }

            if(getCol()<7)
            {
                if(board[getRow()+1][getCol()+1]!=null)
                {
                    if(board[getRow()+1][getCol()+1].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()+1;
                        count++;
                    }
                }
                else if(board[getRow()][getCol()+1]!=null)
                {
                    if(board[getRow()][getCol()+1] instanceof Pawn && board[getRow()][getCol()+1].getIsWhite()!=getIsWhite())
                    {
                        Pawn pawn = (Pawn)board[getRow()][getCol()+1];
                        if(pawn.canBeEnPassant())
                        {
                            moves[count][0] = getRow()-1;
                            moves[count][1] = getCol()+1;
                            count++;
                        }

                    }

                }
            }
        }
        else
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
                if(board[getRow()+1][getCol()-1]!=null)
                {
                    if(board[getRow()+1][getCol()-1].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()-1;
                        count++;
                    }
                }
                else if(board[getRow()][getCol()-1]!=null)
                {
                    if(board[getRow()][getCol()-1]instanceof Pawn&&board[getRow()][getCol()-1].getIsWhite()!=getIsWhite())
                    {
                        Pawn pawn = (Pawn)board[getRow()][getCol()-1];
                        if(pawn.canBeEnPassant())
                        {
                            moves[count][0] = getRow()+1;
                            moves[count][1] = getCol()-1;
                        }
                    }
                }
            }
            if(getCol()<7)
            {
                if(board[getRow()+1][getCol()+1]!=null)
                {
                    if(board[getRow()+1][getCol()+1].getIsWhite()!=getIsWhite())
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol()+1;
                        count++;
                    }
                }
                else if(board[getRow()][getCol()+1]!=null)
                {
                    if(board[getRow()][getCol()+1]instanceof Pawn&&board[getRow()][getCol()+1].getIsWhite()!=getIsWhite())
                    {
                        Pawn pawn = (Pawn)board[getRow()][getCol()+1];
                        if(pawn.canBeEnPassant())
                        {
                            moves[count][0] = getRow()+1;
                            moves[count][1] = getCol()+1;
                        }
                    }
                }

            }

        }
        int[][] finaleMoves = new int[count][2];
        for(int i =0; i <finaleMoves.length;i++)
        {
            for(int j=0;j<2;j++)
            { 
                finaleMoves[i][j] = moves[i][j];
            }
        }
        newMoves(finaleMoves);
    }

    /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 1;
        if(getIsWhite())
        {
            return value;
        }
        else
        {
            return value*-1;
        }
        
    }

    public boolean hasMoved()
    {
        return moved;
    }

    public boolean canBeEnPassant()
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
            return "ChessPieceIcons/WhitePawn.png";
        }
        else 
        {
            return "ChessPieceIcons/BlackPawn.png";
        }

    }
}
