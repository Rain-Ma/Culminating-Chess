import java.util.Arrays;
/**
 * 
 *
 * @author Rain Ma
 * @version 27/05/2018
 */
public class Pawn extends ChessPiece
{
    // if the pawn can be enpassant by an opponent pawn
    boolean enPassant;
    /**
     * this is the constructor for objects of class Pawn
     * @param row This is the row num of the square that the rook is starting on
     * @param col This is the column num of the square that the rook is starting on
     * @param isWhite This is the color of the Pawn. if isWhite is true, the Pawn is white
     */
    public Pawn(int row,int col,boolean isWhite)
    {
        super(row,col,isWhite);

        enPassant = false;
    }
    /**
     *  a Constructor that clones another Pawn
     *  @param piece This is the Pawn that is being cloned
     */
    public Pawn(ChessPiece piece)
    {
        super(piece);
    }

    @Override
    /**
     * finds all the legal moves the pawn can make
     * @param board This is the chess board
     * @param attack This tells the pawn to ether find all squares that it can move to
     *          or to find all squares that it attacks
     *
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves =  new int[4][2];
        int count=0;
        if(getIsWhite())
        {
            
            if(getRow()>0)//so the program will not throw out of bounds errors
            {
                if(!attack)// if findMove is check for all moves
                {
                    if(board[getRow()-1][getCol()]==null)
                    {
                        moves[count][0] = getRow()-1;
                        moves[count][1] = getCol();
                        count++;
                        if(getRow()==6 && board[4][getCol()] == null)//if pawn has not moved yet it can move 2 squares at once
                        {
                            moves[count][0] = getRow()-2;
                            moves[count][1] = getCol();
                            count++;
                        }
                    }
                }

                if(getCol()>0)// checks if pawn can capture to the left
                {
                    if(board[getRow()-1][getCol()-1]!=null)//to see if the pawn can capture anything
                    {
                        if(board[getRow()-1][getCol()-1].getIsWhite()!=getIsWhite())
                        {
                            moves[count][0] = getRow()-1;
                            moves[count][1] = getCol()-1;
                            count++;
                        }
                    }
                    else if(board[getRow()][getCol()-1]!=null)// checks if pawn can en passant another pawn
                    {
                        if(board[getRow()][getCol()-1] instanceof Pawn && board[getRow()][getCol()-1].getIsWhite()!=getIsWhite())
                        {
                            Pawn pawn = (Pawn)board[getRow()][getCol()-1];
                            if(Math.abs(pawn.getMoveNumber()-getMoveNumber())==1&&pawn.getEnPassant())
                            {
                                moves[count][0] = getRow()-1;
                                moves[count][1] = getCol()-1;
                                count++;
                            }

                        }

                    }

                }

                if(getCol()<7)// checks if pawn can capture to the right
                {
                    if(board[getRow()-1][getCol()+1]!=null)
                    {
                        if(board[getRow()-1][getCol()+1].getIsWhite()!=getIsWhite())
                        {
                            moves[count][0] = getRow()-1;
                            moves[count][1] = getCol()+1;
                            count++;
                        }
                    }
                    else if(board[getRow()][getCol()+1]!=null)
                    {
                        if(board[getRow()][getCol()+1] instanceof Pawn && board[getRow()][getCol()+1].getIsWhite()!=getIsWhite())
                        {
                            Pawn pawn = (Pawn)board[getRow()][getCol()+1];
                            if(Math.abs(pawn.getMoveNumber()-getMoveNumber())==1&&pawn.getEnPassant())
                            {
                                moves[count][0] = getRow()-1;
                                moves[count][1] = getCol()+1;
                                count++;
                            }

                        }

                    }
                }
            }
        }
        else //if pawn is black
        {
            if(getRow()<7)
            {
                if(!attack)
                {
                    if(board[getRow()+1][getCol()]==null)
                    {
                        moves[count][0] = getRow()+1;
                        moves[count][1] = getCol();
                        count++;
                        if(getRow()==1&&board[getRow()+2][getCol()]==null)
                        {
                            moves[count][0] = getRow()+2;
                            moves[count][1] = getCol();
                            count++;
                        }
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
                            if(Math.abs(pawn.getMoveNumber()-getMoveNumber())==1&&pawn.getEnPassant())
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
                            if(Math.abs(pawn.getMoveNumber()-getMoveNumber())==1&&pawn.getEnPassant())
                            {
                                moves[count][0] = getRow()+1;
                                moves[count][1] = getCol()+1;
                            }
                        }
                    }

                }
            }

        }
        int[][] finaleMoves = Arrays.copyOf(moves,count);
        
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

    /**
     * @return the instance variable enPassant
     */    
    public boolean getEnPassant()
    {
        return enPassant;
    }
    
    /**
     * mutator method for the instance variable enPassant
     * @param enPassant This is the boolean that enPassant is being set to
     */
    
    public void setEnPassant(boolean enPassant)
    {
        this.enPassant = enPassant;
    }
    
    /**
     * @return the memory address of the pawn png as a string
     */
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
