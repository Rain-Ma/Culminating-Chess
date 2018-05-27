
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
    
    public Knight(ChessPiece piece)
    {
        super(piece);
    }
    
    public void findMove(ChessPiece[][] board)
    {
        int[][] moves = new int[8][2];
        int counter = 0;

        if(getRow()+1 <=7 && getRow()+1 >= 0){
            if(getCol()+2 <=7 && getCol()+2 >=0)
            {
                if(board[getRow()+1][getCol()+2] instanceof ChessPiece &&board[getRow()+1][getCol()+2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+1][getCol()+2] == null)
                {

                    moves[counter][0] = getRow()+1;
                    moves[counter][1] = getCol()+2;
                    counter++;

                }
            }
            if(getCol()-2 <=7 && getCol()-2 >=0)
            {
                if(board[getRow()+1][getCol()-2] instanceof ChessPiece && 
                board[getRow()+1][getCol()-2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+1][getCol()-2] == null)
                {
                    moves[counter][0] = getRow()+1;
                    moves[counter][1] = getCol()-2;
                    counter++;
                }
            }
        }

        if(getRow()-1 <=7 && getRow()-1 >= 0){
            if(getCol()+2 <=7 && getCol()+2 >=0)
            {
                if(board[getRow()-1][getCol()+2] instanceof ChessPiece && 
                board[getRow()-1][getCol()+2].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-1][getCol()+2] == null)
                {
                    moves[counter][0] = getRow()-1;
                    moves[counter][1] = getCol()+2;
                    counter++;
                }
            }
            if(getCol()-2 <=7 && getCol()-2 >=0)
            {
                if((board[getRow()-1][getCol()-2] instanceof ChessPiece && 
                    board[getRow()-1][getCol()-2].getIsWhite() != board[getRow()][getCol()].getIsWhite())
                || board[getRow()-1][getCol()-2] == null)
                {
                    moves[counter][0] = getRow()-1;
                    moves[counter][1] = getCol()-2;
                    counter++;
                }
            }
        }

        if(getRow()+2 <=7 && getRow()+2 >= 0){
            if(getCol()+1 <=7 && getCol()+1 >=0)
            {
                if(board[getRow()+2][getCol()+1] instanceof ChessPiece && 
                board[getRow()+2][getCol()+1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()+2][getCol()+1] == null)
                {
                    moves[counter][0] = getRow()+2;
                    moves[counter][1] = getCol()+1;
                    counter++;
                }
            }
            if(getCol()-1 <=7 && getCol()-1 >=0)
            {
                if((board[getRow()+2][getCol()-1] instanceof ChessPiece && 
                    board[getRow()+2][getCol()-1].getIsWhite() != board[getRow()][getCol()].getIsWhite())
                || board[getRow()+2][getCol()-1] == null)
                {
                    moves[counter][0] = getRow()+2;
                    moves[counter][1] = getCol()-1;
                    counter++;
                }
            }
        }

        if(getRow()-2 <=7 && getRow()-2 >= 0){
            if(getCol()+1 <=7 && getCol()+1 >=0)
            {
                if(board[getRow()-2][getCol()+1] instanceof ChessPiece && 
                board[getRow()-2][getCol()+1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-2][getCol()+1] == null)
                {
                    moves[counter][0] = getRow()-2;
                    moves[counter][1] = getCol()+1;
                    counter++;
                }
            }
            if(getCol()-1 <=7 && getCol()-1 >=0)
            {
                if(board[getRow()-2][getCol()-1] instanceof ChessPiece && 
                board[getRow()-2][getCol()-1].getIsWhite() != board[getRow()][getCol()].getIsWhite()
                || board[getRow()-2][getCol()-1] == null)
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

    public String toString()
    {
        if(getIsWhite())
            return "ChessPieceIcons/WhiteKnight.png";
        else 
            return "ChessPieceIcons/BlackKnight.png";
    }
}
