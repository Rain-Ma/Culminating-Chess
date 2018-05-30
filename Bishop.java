import java.util.Arrays;
/**
 * Bishop chess piece class
 * 
 * @author Jutin Chu
 * @version May 25th, 2018
 */
public class Bishop extends ChessPiece
{
    /**
     * Constructor for objects of class Bishop
     * 
     * @param row This is the row num of the square that the Bishop is starting on
     * @param col This is the column num of the square that the Bishop is starting on
     * @param isWhite This is the color of the Bishop. if isWhite is true, the Bishop is white
     */
    public Bishop(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    /**
     * a Constructor that clones another Bishop
     * 
     * @param piece This is the Bishop that is being cloned
     */
    public Bishop(ChessPiece piece)
    {
        super(piece);
    }

    @Override
    /**
     * Finds all possible moves for bishop objects
     * 
     * @param board This is the chess board
     * @param attack Not appicable to Bishop since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[13][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //counter for possible moves

        //starts checking squares from the bishop to top right corner of the board
        if(getRow() < 7 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()+i > 7) //if the loop moves off the board
                    break;
                if(board[getRow()+i][getCol()+i] instanceof ChessPiece)// if the bishop hits a chess piece
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

        //starts checking squares from the bishop to top left corner of the board
        if(getRow() < 7 && getCol() > 0)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()+i > 7 || getCol()-i < 0) //if the loop moves off the board
                    break;
                if(board[getRow()+i][getCol()-i] instanceof ChessPiece) // if the bishop hits a chess piece
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

        //starts checking squares from the bishop to bottom right corner of the board
        if(getRow() > 0 && getCol() < 7)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()-i < 0 || getCol()+i > 7) //if the loop moves off the board
                    break;
                if(board[getRow()-i][getCol()+i] instanceof ChessPiece)// if the bishop hits a chess piece
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
        //starts checking squares from the bishop to bottem left corner of the board
        if(getRow() > 0 && getCol() > 0)
        {
            for(int i = 1; i < 7; i++)
            {                   
                if(getRow()-i < 0 || getCol()-i < 0)//if the loop moves off the board
                    break;
                if(board[getRow()-i][getCol()-i] instanceof ChessPiece)// if the bishop hits a chess piece
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

        int[][] finaleMoves = Arrays.copyOf(moves,count); //truncates size of move array to only have the number of possible moves
        newMoves(finaleMoves); //make the array the new array of moves the bishop can make
    }

    @Override
    /**
     * Returns String location of bishop icon
     * 
     * @return the String address of the bishop image
     */
    public String toString()
    {
        if(getIsWhite()) //if bishop is white
            return "ChessPieceIcons/WhiteBishop.png";
        else //if bishop is black
            return "ChessPieceIcons/BlackBishop.png";
    }
}
