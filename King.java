
/**
 * Write a description of class King here.
 *
 * @author Rain Ma
 * @version 21/05/2018
 */
public class King extends ChessPiece
{
    // instance variables - replace the example below with your own
    private boolean hasMoved;// in order to castle, it has to be the kings first move
    private boolean isInCheck;
    private int[][] opponentMove;
    /**
     * Constructor for objects of class King
     */
    public King(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
        hasMoved = false;
        isInCheck = false;
    }
    
    public King()
    {
        super();
        
    }
    
    @Override
    public void findMove(ChessPiece[][] board)
    {
        int[][] moves = new int[10][2];
        int i =0;
        if(!hasMoved)
        {
            if(board[getRow()][0] instanceof Rook&&board[getRow()][0].getIsWhite()==getIsWhite())
            {
                Rook rook = (Rook)board[getRow()][0];
                if(!rook.hasMoved()&&!attacked(getRow(),1)&&!attacked(getRow(),2)&&!attacked(getRow(),3))
                {
                    if(board[getRow()][1]==null&&board[getRow()][2]==null&&board[getRow()][3]==null)
                    {
                        moves[i][0] = getRow();
                        moves[i][1] = 2;
                        i++;
                    }
                }
            }

            if(board[getRow()][7] instanceof Rook&&board[getRow()][7].getIsWhite()==getIsWhite())
            {
                Rook rook = (Rook)board[getRow()][7];
                if(!rook.hasMoved()&&!attacked(getRow(),5)&&!attacked(getRow(),6))
                {
                    if(board[getRow()][5]==null&&board[getRow()][6]==null)
                    {
                        moves[i][0] = getRow();
                        moves[i][1] = 6;
                        i++;
                    }
                }
            }

        }
        for(int r = getRow()-1;r<8&&r<=getRow()+1;r++)
        {
            if(r>=0&&r<8)
            {
                for(int c = getCol()-1;c<8&&c<=getCol()+1;c++)
                {
                    if(c>=0&&c<8)
                    {
                        if(!opposition(board,r,c))
                        {
                            if(r==getRow()&&c==getCol()||attacked(r,c))
                            {

                            }
                            else if(board[r][c]==null )
                            {
                                moves[i][0] = r;
                                moves[i][1] = c;
                                i++;
                            }
                            else if(board[r][c].getIsWhite()!=getIsWhite())
                            {

                                moves[i][0] = r;
                                moves[i][1] = c;
                                i++;
                            }  
                        }
                    }
                }
            }
        }
        int[][] move = new int[i][2];
        for(int j=0;j<i;j++)
        {
            move[j] = moves[j];
        }
        newMoves(move);
    }

    /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 3.5;
        if(getIsWhite())
        {
            return value;
        }
        else
        {
            return value*-1;
        }
    }

    public void updateOpponentMove(int[][] opponentMove)
    {

        this.opponentMove = opponentMove;

    }

    public boolean attacked(int row,int col)
    {  
        if(opponentMove==null)
        {
            return false;
        }
        for(int i=0;i<opponentMove.length;i++)
        {
            if(opponentMove[i][0]==row&&opponentMove[i][1]==col)
            {
                return true;
            }

        }
        return false;
    }  

    public boolean hasMoved()
    {
        return hasMoved;

    }

    public void setHasMoved(boolean hasMoved)
    {
        this.hasMoved=hasMoved;
    }

    public boolean checked()
    {
        if(attacked(getRow(),getCol()))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    
    

    public boolean opposition(ChessPiece[][] board, int row,int col)
    {
        for(int r = row-1;r<8&&r<=row+1;r++)
        {
            for(int c = col-1;c<8&&c<=col+1;c++)
            {
                if(r>=0&&r<8)
                {
                    if(c>=0&&c<8)
                    {
                        if(board[r][c] instanceof King)
                        {
                            if(board[r][c].getIsWhite()!=getIsWhite())
                            {
                                return true;
                            }
                        }    
                    }
                }
            }            
        }
        return false;
    }

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteKing.png";
        else
            return "ChessPieceIcons/BlackKing.png";
    }    
}
