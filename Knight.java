//import statement
import java.util.Arrays;
/**
 * Knight chess piece class
 *
 * @author Justin & Shawn
 * @version May 24th, 2018
 */
public class Knight extends ChessPiece
{
    /**
     * Constructor for objects of class Knight
     * 
     * @param row This is the row num of the square that the Knight is starting on
     * @param col This is the column num of the square that the Knight is starting on
     * @param isWhite This is the color of the Knight. if isWhite is true, the Knight is white
     */
    public Knight(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
    }

    /**
     * a Constructor that clones another Knight
     * 
     * @param piece This is the Knight that is being cloned
     */
    public Knight(ChessPiece piece)
    {
        super(piece);
    }

    @Override
    /**
     * Finds all possible moves knight can make
     * 
     * @param board This is the chess board
     * @param attack Not appicable to Knight since moving and attacking is the thing 
     */
    public void findMove(ChessPiece[][] board,boolean attack)
    {
        int[][] moves = new int[8][2];//Array saves all moves possible for the piece [most moves possible][coordinates]
        int counter = 0; //count for possible moves

        if(getRow()+1 <=7 && getRow()+1 >= 0){// checks if the row exists
            if(getCol()+2 <=7 && getCol()+2 >=0)//checks if the column exists
            {
                if(board[getRow()+1][getCol()+2] instanceof ChessPiece &&board[getRow()+1][getCol()+2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+1][getCol()+2] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()+1;
                    moves[counter][1] = getCol()+2;
                    counter++;//increase number of possible moves in array
                }
            }
            if(getCol()-2 <=7 && getCol()-2 >=0)//checks if the column exists
            {
                if(board[getRow()+1][getCol()-2] instanceof ChessPiece && 
                board[getRow()+1][getCol()-2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+1][getCol()-2] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()+1;
                    moves[counter][1] = getCol()-2;
                    counter++; //increase number of possible moves in array
                }
            }
        }

        if(getRow()-1 <=7 && getRow()-1 >= 0){// checks if the row exists
            if(getCol()+2 <=7 && getCol()+2 >=0)//checks if the column exists
            {
                if(board[getRow()-1][getCol()+2] instanceof ChessPiece && 
                board[getRow()-1][getCol()+2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-1][getCol()+2] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()-1;
                    moves[counter][1] = getCol()+2;
                    counter++; //increase number of possible moves in array
                }
            }
            if(getCol()-2 <=7 && getCol()-2 >=0)//checks if the column exists
            {
                if((board[getRow()-1][getCol()-2] instanceof ChessPiece && 
                    board[getRow()-1][getCol()-2].getIsWhite() != board[getRow()][getCol()].getIsWhite())
                || board[getRow()-1][getCol()-2] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()-1;
                    moves[counter][1] = getCol()-2;
                    counter++; //increase number of possible moves in array
                }
            }
        }

        if(getRow()+2 <=7 && getRow()+2 >= 0){// checks if the row exists
            if(getCol()+1 <=7 && getCol()+1 >=0)//checks if the column exists
            {
                if(board[getRow()+2][getCol()+1] instanceof ChessPiece && 
                board[getRow()+2][getCol()+1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+2][getCol()+1] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()+2;
                    moves[counter][1] = getCol()+1;
                    counter++; //increase number of possible moves in array
                }
            }
            if(getCol()-1 <=7 && getCol()-1 >=0)//checks if the column exists
            {
                if((board[getRow()+2][getCol()-1] instanceof ChessPiece && 
                    board[getRow()+2][getCol()-1].getIsWhite() != board[getRow()][getCol()].getIsWhite())
                || board[getRow()+2][getCol()-1] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()+2;
                    moves[counter][1] = getCol()-1;
                    counter++; //increase number of possible moves in array
                }
            }
        }

        if(getRow()-2 <=7 && getRow()-2 >= 0){// checks if the row exists
            if(getCol()+1 <=7 && getCol()+1 >=0)//checks if the column exists
            {
                if(board[getRow()-2][getCol()+1] instanceof ChessPiece && 
                board[getRow()-2][getCol()+1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-2][getCol()+1] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()-2;
                    moves[counter][1] = getCol()+1;
                    counter++; //increase number of possible moves in array
                }
            }
            if(getCol()-1 <=7 && getCol()-1 >=0)//checks if the column exists
            {
                if(board[getRow()-2][getCol()-1] instanceof ChessPiece && 
                board[getRow()-2][getCol()-1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-2][getCol()-1] == null)//if the square is empty of occupied by an opponent piece
                {
                    //add coordinates to array
                    moves[counter][0] = getRow()-2;
                    moves[counter][1] = getCol()-1;
                    counter++; //increase number of possible moves in array
                }
            }
        }
        int[][] finaleMoves = Arrays.copyOf(moves,counter); //truncates size of array
        newMoves(finaleMoves); //make the array the new array of moves the rook can make
    }

    @Override
    /**
     * Return the location of the knight icon
     * 
     * @return the String address of the Knight image
     */
    public String toString()
    {
        if(getIsWhite()) //if it is white
            return "ChessPieceIcons/WhiteKnight.png";
        else //if it is black
            return "ChessPieceIcons/BlackKnight.png";
    }
}
