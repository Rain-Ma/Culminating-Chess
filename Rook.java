/**
 * 
 *
 * @author Rain Ma
 * @version 21/05/2018
 */
public class Rook extends ChessPiece
{
    private boolean moved;
    /**
     * Constructor for objects of class Rook
     */
    public Rook(int row, int col, boolean isWhite)
    {
        super(row,col,isWhite);
        moved = false;
    }

    public void findMove(ChessPiece[][] board) //Saves all possible moves that this can do at the moment
    {
        int[][] moves = new int[14][2]; //Array saves all moves possible for the piece [most moves possible][coordinates]
        int count = 0; //counter for possible moves
        for(int r = getRow()+1;r<8;r++) //Checks move availability to the right
        {
            if(board[r][getCol()] instanceof ChessPiece) //if theres a piece in the way
            {
                if(board[r][getCol()].getIsWhite()!=getIsWhite()) //if that piece is the oppisite colour
                {
                    moves[count][0] = r; //input row coordinate of possible move
                    moves[count][1] = getCol(); //input column coordinate of possible move
                    count++; //add to number of possible moves
                    break;
                }
                else
                {
                    break;
                }

            }
            moves[count][0] = r; //input row coordinate of possible move
            moves[count][1] = getCol(); //input column coordinate of possible move
            count++; //add to number of possible moves         
        }
        for( int r = getRow()-1;r>=0;r--) //Checks move availability to the left
        {
            if(board[r][getCol()] instanceof ChessPiece) //if theres a piece in the way
            {
                if(board[r][getCol()].getIsWhite()!=getIsWhite()) //if that piece is the oppisite colour
                {
                    moves[count][0] = r; //input row coordinate of possible move
                    moves[count][1] = getCol(); //input column coordinate of possible move
                    count++; //add to number of possible moves
                    break;
                }
                else
                {
                    break;
                }

            }
            moves[count][0] = r; //input row coordinate of possible move
            moves[count][1] = getCol(); //input column coordinate of possible move
            count++; //add to number of possible moves       
        }
        for( int c = getCol()+1;c<8;c++) //Checks move availability upwards
        {
            if(board[getRow()][c] instanceof ChessPiece) //if theres a piece in the way
            {
                if(board[getRow()][c].getIsWhite()!=getIsWhite()) //if that piece is the oppisite colour
                {
                    moves[count][0] = getRow(); //input row coordinate of possible move
                    moves[count][1] = c; //input column coordinate of possible move
                    count++; //add to number of possible moves
                    break;
                }
                else
                {
                    break;
                }

            }
            moves[count][0] = getRow(); //input row coordinate of possible move
            moves[count][1] = c; //input column coordinate of possible move
            count++; //add to number of possible moves
        }
        for( int c = getRow()-1;c<8;c++) //Checks move availability downwards
        {
            if(board[getRow()][c] instanceof ChessPiece) //if theres a piece in the way
            {
                if(board[getRow()][c].getIsWhite()!=getIsWhite()) //if that piece is the oppisite colour
                {
                    moves[count][0] = getRow(); //input row coordinate of possible move
                    moves[count][1] = c; //input column coordinate of possible move
                    count++; //add to number of possible moves
                    break;
                }
                else
                {
                    break;
                }
            }
            moves[count][0] = getRow(); //input row coordinate of possible move
            moves[count][1] = c; //input column coordinate of possible move
            count++; //add to number of possible moves
        }
        int[][] finaleMoves = new int[count][2]; //new array with a length of the possible moves
        for(int i =0; i <finaleMoves.length;i++) 
        {
            for(int j=0;j<2;j++)
            { 
                finaleMoves[i][j] = moves[i][j]; //inputs all possible moves in the appropriate length array
            }
        }
        newMoves(finaleMoves); //changes moves array to equal finaleMoves array
    }

    public boolean hasMoved()
    {
        return moved;
    }

     /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 5;
        if(getIsWhite())
        {
            return value;
        }
        else
        {
            return value*-1;
        }
    }

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteRook.png";
        else
            return "ChessPieceIcons/BlackRook.png";
    }
}
