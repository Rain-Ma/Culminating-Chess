import java.util.Arrays;
/**
 * 
 * @author Shawn && Rain Ma && Justin Chu
 * @version 21/05/2018
 */
public class Queen extends ChessPiece
{
    /**
     * Constructor for objects of class Queen
     * @param row This is the row num of the square that the Queen is starting on
     * @param col This is the column num of the square that the Queen is starting on
     * @param isWhite This is the color of the Queen. if isWhite is true, the Queen is white
     */
    public Queen(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    /**
     * a Constructor that clones another Queen
     * 
     * @param piece This is the Queen that is being cloned
     */
    public Queen(ChessPiece piece)
    {
        super(piece);
    }

    @Override
    /**
     * @param board This is the chess board
     * @param attack Not appicable to Queen since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board, boolean attack)
    {
        int[][] moves = new int[27][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //count for possible moves
        //starts diagonally checking squares from the queen  to the top right corner of the board
        if(getRow() < 7 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()+i > 7) //if the loop moves off the board
                    break;
                if(board[getRow()+i][getCol()+i] instanceof ChessPiece)// if the queen hits a chess piece
                {
                    if(board[getRow()+i][getCol()+i].getIsWhite() != getIsWhite())//if the chesspiece is of the opposite color, then it can be captured
                    {
                        //add coordinates to array
                        moves[count][0] = getRow()+i;
                        moves[count][1] = getCol()+i;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a friendly chess piece
                        break;
                }
                //add coordinates to array
                moves[count][0] = getRow()+i;
                moves[count][1] = getCol()+i;
                count++; //increase number of possible moves in array
            }
        }

        //starts checking squares from the queen to top left corner of the board
        if(getRow() < 7 && getCol() > 0)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()-i < 0) //if the loop moves off the board
                    break;
                if(board[getRow()+i][getCol()-i] instanceof ChessPiece) // if the queen hits a chess piece
                {
                    if(board[getRow()+i][getCol()-i].getIsWhite() != getIsWhite())//if the chesspiece is of the opposite color, then it can be captured
                    {
                        //add coordinates to array
                        moves[count][0] = getRow()+i;
                        moves[count][1] = getCol()-i;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else//if it is a friendly chess piece
                        break;
                }
                //add coordinates to array
                moves[count][0] = getRow()+i;
                moves[count][1] = getCol()-i;
                count++; //increase number of possible moves in array
            }
        }

        //starts checking squares from the queen to bottom right corner of the board
        if(getRow() > 0 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()-i < 0 || getCol()+i > 7) //if the loop moves off the board
                    break;
                if(board[getRow()-i][getCol()+i] instanceof ChessPiece)// if the queen hits a chess piece
                {
                    if(board[getRow()-i][getCol()+i].getIsWhite() != getIsWhite())//if the chesspiece is of the opposite color, then it can be captured
                    {
                        //add coordinates to array
                        moves[count][0] = getRow()-i;
                        moves[count][1] = getCol()+i;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else//if it is a friendly chess piece
                        break;
                }
                //add coordinates to array
                moves[count][0] = getRow()-i;
                moves[count][1] = getCol()+i;
                count++; //increase number of possible moves in array
            }
        }
        //starts checking squares from the queen to bottem left corner of the board
        if(getRow() > 0 && getCol() > 0)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()-i < 0 || getCol()-i < 0)//if the loop moves off the board
                    break;
                if(board[getRow()-i][getCol()-i] instanceof ChessPiece)// if the queen hits a chess piece
                {
                    if(board[getRow()-i][getCol()-i].getIsWhite() != getIsWhite())//if the chesspiece is of the opposite color, then it can be captured
                    {
                        //add coordinates to array
                        moves[count][0] = getRow()-i;
                        moves[count][1] = getCol()-i;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else//if it is a friendly chess piece
                        break;
                }
                //add coordinates to array
                moves[count][0] = getRow()-i;
                moves[count][1] = getCol()-i;
                count++; //increase number of possible moves in array
            }
        }

        if(getRow()<7)
        {
            for(int r = getRow()+1;r<8;r++)
            {
                if(board[r][getCol()] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[r][getCol()].getIsWhite()!=getIsWhite()) //if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = r;
                        moves[count][1] = getCol();
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is friendly piece
                    {
                        break;
                    }
                }    
                //add coordinates to array
                moves[count][0] = r;
                moves[count][1] = getCol();
                count++; //increase number of possible moves in array
            }
        }
        //if the queen trys to move up the board
        if(getRow()>0)
        {
            for(int r = getRow()-1;r>=0;r--)
            {
                if(board[r][getCol()] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[r][getCol()].getIsWhite()!=getIsWhite()) //if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = r;
                        moves[count][1] = getCol();
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a friendly piece
                    {
                        break;
                    }
                }
                //add coordinates to array
                moves[count][0] = r;
                moves[count][1] = getCol();
                count++; //increase number of possible moves in array
            }
        }
        //if the queen trys to move right
        if(getCol()<7)
        {
            for( int c = getCol()+1;c<8;c++)
            {
                if(board[getRow()][c] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[getRow()][c].getIsWhite()!=getIsWhite())//if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = getRow();
                        moves[count][1] = c;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a friendly piece
                    {
                        break;
                    }
                }
                //add coordinates to array
                moves[count][0] = getRow();
                moves[count][1] = c;
                count++; //increase number of possible moves in array
            }
        }
        //if the queen trys to move left
        if(getCol()>0)
        {
            for( int c = getCol()-1;c>=0;c--)
            {
                if(board[getRow()][c] instanceof ChessPiece) //if there is a piece on that square
                {
                    if(board[getRow()][c].getIsWhite()!=getIsWhite()) //if it is an enemy piece
                    {
                        //add coordinates to array
                        moves[count][0] = getRow();
                        moves[count][1] = c;
                        count++; //increase number of possible moves in array
                        break;
                    }
                    else //if it is a freindly piece
                    {
                        break;
                    }
                }
                //add coordinates to array
                moves[count][0] = getRow();
                moves[count][1] = c;
                count++; //increase number of possible moves in array
            }
        }
        int[][] finaleMoves = Arrays.copyOf(moves,count); //truncate array of possible moves
        newMoves(finaleMoves); //make this the new array of moves the queen can make
    }

    @Override
    /**
     * Returns the location of the queen icon
     * 
     * @return the String adress of the queen image
     */
    public String toString()
    {
        if(getIsWhite()) //if queen is white
            return "ChessPieceIcons/WhiteQueen.png";
        else //if queen is black
            return "ChessPieceIcons/BlackQueen.png";
    }
}
