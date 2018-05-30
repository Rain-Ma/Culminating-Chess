//import statement
import java.util.Arrays;
/**
 * King chess piece class
 *
 * @author Rain Ma
 * @version 29/05/2018
 */
public class King extends ChessPiece
{
    //instance variables
    private boolean hasMoved;//in order to castle, it has to be the kings first move
    private boolean isInCheck; //if king is in check
    private int[][] opponentMove; //the opponent's moves
    /**
     * Constructor for objects of class King
     * 
     * @param row This is the row num of the square that the king is starting on
     * @param col This is the column num of the square that the king is starting on
     * @param isWhite This is the color of the king. if isWhite is true, the king is white
     */
    public King(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
        hasMoved = false;
        isInCheck = false;
    }

    /**
     * An empty constructor for objects of class King
     */
    public King()
    {
        super();
    }

    /**
     * a Constructor that clones another king
     * 
     * @param piece This is the king that is being cloned
     */
    public King(ChessPiece piece)
    {
        super(piece);
    }

    @Override
    /**
     * Finds all possible moves for king objects
     * 
     * @param board This is the chess board
     * @param attack Not appicable to king since moving and attacking are the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[10][2];//the total number of possible moves a king can have at once [most moves possible][coordinates]
        int i =0;//number of possible moves
        if(!hasMoved)//checks if the king can castle
        {
            if(board[getRow()][0] instanceof Rook&&board[getRow()][0].getIsWhite()==getIsWhite())//checks if the rooks have moved  
            {
                Rook rook = (Rook)board[getRow()][0];
                if(!rook.hasMoved()&&!attacked(getRow(),1)&&!attacked(getRow(),2)&&!attacked(getRow(),3))
                {
                    if(board[getRow()][1]==null&&board[getRow()][2]==null&&board[getRow()][3]==null)// checks if the king will be castling through check
                    {
                        //add coordinates to array
                        moves[i][0] = getRow();
                        moves[i][1] = 2;
                        i++;//increase number of possible moves in array
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
                        //add coordinates to array
                        moves[i][0] = getRow();
                        moves[i][1] = 6;
                        i++;//increase number of possible moves in array
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
                                //king cannot move to that square
                            }
                            else if(board[r][c]==null )//if board is empty
                            {
                                //add coordinates to array
                                moves[i][0] = r;
                                moves[i][1] = c;
                                i++;//increase number of possible moves in array
                            }
                            else if(board[r][c].getIsWhite()!=getIsWhite())//if board is occupied by an opponent piece
                            {
                                //add coordinates to array
                                moves[i][0] = r;
                                moves[i][1] = c;
                                i++;//increase number of possible moves in array
                            }  
                        }
                    }
                }
            }
        }
        int[][] move = Arrays.copyOf(moves,i);//truncates size of array
        newMoves(move);//make the array the new array of moves the king can make
    }

    /**
     * Mutator method for the instance variable opponentMove
     * 
     * @param opponentMove This is the new value for the instance variable opponentMove
     */
    public void updateOpponentMove(int[][] opponentMove)
    {
        this.opponentMove = opponentMove;
    }

    /**
     * Checks if opponent pieces are attacking a square
     * 
     * @param row This is the row number of the square
     * @param col This is the column number of the square
     * 
     * @return if the square is being attacked by an opponent piece
     */
    public boolean attacked(int row,int col)
    {  
        if(opponentMove==null) //if opponents dont have moves
        {
            return false;
        }
        for(int i=0;i<opponentMove.length;i++) //go through opponents moves
        {
            if(opponentMove[i][0]==row&&opponentMove[i][1]==col) //if opponents are attacking this square
            {
                return true;
            }
        }
        return false;
    }  

    /**
     * Accessor method for the instance variable hasMoved
     * 
     * @return hasMoved
     */
    public boolean hasMoved()
    {
        return hasMoved;
    }

    /**
     * Mutator method for the instance variable hasMoved
     * 
     * @param hasMoved This is the new value for the instance variable hasMoved
     */
    public void setHasMoved(boolean hasMoved)
    {
        this.hasMoved=hasMoved;
    }

    /**
     * Check if the king is being attacked
     * 
     * @return if the square the king is on is being attacked by an opponent piece
     */
    public boolean checked()
    {
        if(attacked(getRow(),getCol())) //if king is being attacked
        {
            return true;
        }
        else //if not
        {
            return false;
        }
    }

    /**
     * Since kings cant touch there is a space between the kings known as the opposition
     * 
     * @param board This is the chess board
     * @param row This is the row num of the square that the king is trying to move to
     * @param col This is the column num of the square that the king is trying to move to
     * 
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
                        if(board[r][c] instanceof King)//if there is an opponent king, then opposition exists
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

    /**
     * Returns the file location of the king image
     * 
     * @return the String address of the king icon
     */
    public String toString()
    {
        if(getIsWhite())//if king is white
            return "ChessPieceIcons/WhiteKing.png";
        else//if king is black
            return "ChessPieceIcons/BlackKing.png";
    }    
}
