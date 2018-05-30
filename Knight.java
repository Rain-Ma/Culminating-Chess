
/**
 * 
 *
 * @author Justin & Shawn
 * @version May 24th, 2018
 */
public class Knight extends ChessPiece
{
    /**
     * Constructor for objects of class Knight
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

                    moves[counter][0] = getRow()+1;
                    moves[counter][1] = getCol()+2;
                    counter++;

                }
            }
            if(getCol()-2 <=7 && getCol()-2 >=0)//checks if the column exists
            {
                if(board[getRow()+1][getCol()-2] instanceof ChessPiece && 
                board[getRow()+1][getCol()-2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+1][getCol()-2] == null)//if the square is empty of occupied by an opponent piece
                {
                    moves[counter][0] = getRow()+1;
                    moves[counter][1] = getCol()-2;
                    counter++;
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
                    moves[counter][0] = getRow()-1;
                    moves[counter][1] = getCol()+2;
                    counter++;
                }
            }
            if(getCol()-2 <=7 && getCol()-2 >=0)//checks if the column exists
            {
                if((board[getRow()-1][getCol()-2] instanceof ChessPiece && 
                    board[getRow()-1][getCol()-2].getIsWhite() != board[getRow()][getCol()].getIsWhite())
                || board[getRow()-1][getCol()-2] == null)//if the square is empty of occupied by an opponent piece
                {
                    moves[counter][0] = getRow()-1;
                    moves[counter][1] = getCol()-2;
                    counter++;
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
                    moves[counter][0] = getRow()+2;
                    moves[counter][1] = getCol()+1;
                    counter++;
                }
            }
            if(getCol()-1 <=7 && getCol()-1 >=0)//checks if the column exists
            {
                if((board[getRow()+2][getCol()-1] instanceof ChessPiece && 
                    board[getRow()+2][getCol()-1].getIsWhite() != board[getRow()][getCol()].getIsWhite())
                || board[getRow()+2][getCol()-1] == null)//if the square is empty of occupied by an opponent piece
                {
                    moves[counter][0] = getRow()+2;
                    moves[counter][1] = getCol()-1;
                    counter++;
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
                    moves[counter][0] = getRow()-2;
                    moves[counter][1] = getCol()+1;
                    counter++;
                }
            }
            if(getCol()-1 <=7 && getCol()-1 >=0)//checks if the column exists
            {
                if(board[getRow()-2][getCol()-1] instanceof ChessPiece && 
                board[getRow()-2][getCol()-1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-2][getCol()-1] == null)//if the square is empty of occupied by an opponent piece
                {
                    moves[counter][0] = getRow()-2;
                    moves[counter][1] = getCol()-1;
                    counter++;
                }
            }
        }

        int[][] finaleMoves = new int[counter][2];
        for(int i =0; i <finaleMoves.length;i++)
        {
            for(int j=0;j<2;j++)
            { 
                finaleMoves[i][j] = moves[i][j];
            }
        }
        newMoves(finaleMoves);
    }

    /**
     * @param the chessboard because the value of a piece depend on the position of other pieces.
     * @return the value of the chessPiece
     */
    public double evaluate(ChessPiece[][] board)
    {
        double value = 3;
        if(getIsWhite())
        {
            return value;
        }
        else
        {
            return value*-1;
        }
    }

    @Override
    /**
     * @return the memory adress of the Knight png
     */
    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteKnight.png";
        else 
            return "ChessPieceIcons/BlackKnight.png";
    }
}
