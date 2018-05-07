
/**
 * Write a description of class King here.
 *
 * @author Rain Ma
 * @version 07/05/2018
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

    @Override
    public void findMove(ChessPiece[][] board)
    {
        int[][] moves;
        int i =0;
        if(!hasMoved)
        {
            if(board[getRow()][0].getClass()==Rook)
            {
                if(!board[getRow()][0].hasMoved()&&!attacked(getRow(),1)&&!attacked(getRow(),2)&&!attacked(getRow(),3))
                {
                    moves[i][0] = getRow();
                    moves[i][1] = 2;
                    i++;
                }
            }
            
            
        }
        for(int r = getRow()-1;r<8;r++)
        {
            if(r>=0)
            {
                for(int c = getCol()-1;c<8;c++)
                {
                    if(c>=0)
                    {
                        if(board[r][c].getIsWhite()!=getIsWhite())
                        {
                            if(1==1)
                            {
                                
                            }
                        }
                        
                        
                    }
                }
            }

        }
    }

    public double evaluate()
    {
        return 2;
    }
    
    public void updateOpponentMove(int[][] opponentMove)
    {
        
        this.opponentMove = opponentMove;
        
    }
    
    public boolean attacked(int row,int col)
    {
        
        for(int i=0;i<opponentMove.length;i++)
        {
            if(opponentMove[i][0]==row&&opponentMove[i][1]==col)
            {
                return true;
            }
            
        }
        return false;
        
    }
    
}
