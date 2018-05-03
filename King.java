
/**
 * Write a description of class King here.
 *
 * @author Rain Ma
 * @version 03/05/2018
 */
public class King extends ChessPiece
{
    // instance variables - replace the example below with your own
    private boolean hasMoved;// in order to castle, it has to be the kings first move
    private boolean isInCheck;
    /**
     * Constructor for objects of class King
     */
    public King(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);

    }

    public void findMove()
    {
        for(int r = row-1;r<8;r++)
        {
            if(r>=0)
            {
                for(int c = col-1;c<8;c++)
                {
                    if(c>=0)
                    {
                        if(1==1)
                        {
                            
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
}
