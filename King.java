import java.util.Arrays;
/**
 * Write a description of class King here.
 *
 * @author Rain Ma
 * @version 29/05/2018
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
    
    /**
     * blank constructor for objects of class King
     */
    public King()
    {
        super();
        
    }
    
     /**
     * a Constructor that clones another King
     * 
     * @param piece This is the King that is being cloned
     */
    public King(ChessPiece piece)
    {
        super(piece);
    }
    
    @Override
    /**
     * @param board This is the chess board
     * @param attack Not appicable to King since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[10][2];
        int i =0;
        if(!hasMoved)//checks if the king can castle
        {
            if(board[getRow()][0] instanceof Rook&&board[getRow()][0].getIsWhite()==getIsWhite())//checks if the rooks have moved  
            {
                Rook rook = (Rook)board[getRow()][0];
                if(!rook.hasMoved()&&!attacked(getRow(),1)&&!attacked(getRow(),2)&&!attacked(getRow(),3))
                {
                    if(board[getRow()][1]==null&&board[getRow()][2]==null&&board[getRow()][3]==null)// checks if the king will be castling through check
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
        //checks if the king can move to the squares around it
        for(int r = getRow()-1;r<8&&r<=getRow()+1;r++)
        {
            if(r>=0&&r<8)
            {
                for(int c = getCol()-1;c<8&&c<=getCol()+1;c++)
                {
                    if(c>=0&&c<8)
                    {
                        if(!opposition(board,r,c))//if the square is also attacked by the opponent king
                        {
                            if(r==getRow()&&c==getCol()||attacked(r,c))// if the opponent pieces attack that square
                            {

                            }
                            else if(board[r][c]==null )//if board is empty
                            {
                                moves[i][0] = r;
                                moves[i][1] = c;
                                i++;
                            }
                            else if(board[r][c].getIsWhite()!=getIsWhite())//if board is occupied by an opponent piece
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
        int[][] move = Arrays.copyOf(moves,i);
        
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

    /**
     * mutator method for the instace variable opponentMove
     * @param opponentMove This is the new value for the instance variable opponentMove
     */
    public void updateOpponentMove(int[][] opponentMove)
    {

        this.opponentMove = opponentMove;

    }

    /**
     * checks if opponent pieces are attacking a square
     * @param row This is the row number of the square
     * @param col This is the column number of the square
     * @return if the square is being attacked by an opponent piece
     */
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

    /**
     * accessor method for the instance variable hasMoved
     * @return hasMoved
     */
    public boolean hasMoved()
    {
        return hasMoved;

    }

    /**
     * mutator method for the instance variable hasMoved
     * @param hasMoved This is the new value for the instance variable hasMoved
     */
    public void setHasMoved(boolean hasMoved)
    {
        this.hasMoved=hasMoved;
    }

    /**
     * @return if the square the king is on is being attacked by an opponent piece
     */
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
    
    

    /**
     * since kings cant touch there is a space between the kings known as the opposition
     * @param board This is the chess board
     * @param row This is the row num of the square that the king is trying to move to
     * @param col This is the column num of the square that the king is trying to move to
     * @return if the opponent king also attacks the square
     */
    public boolean opposition(ChessPiece[][] board, int row,int col)
    {
        //checks all the squares around board[row][col]
        for(int r = row-1;r<8&&r<=row+1;r++)
        {
            for(int c = col-1;c<8&&c<=col+1;c++)
            {
                if(r>=0&&r<8)
                {
                    if(c>=0&&c<8)
                    {
                        if(board[r][c] instanceof King)//if there is an opponent king, then there opposition exists
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

     @Override
    /**
     * @return the memory adress of the King png
     */
    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteKing.png";
        else
            return "ChessPieceIcons/BlackKing.png";
    }    
}
