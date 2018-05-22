/**
 * Write a description of class board here.
 *
 * @author Rain Ma, Justin Chu
 * @version 21/05/2018
 */
public class Board
{
    // instance variables - replace the example below with your own
    private ChessPiece[][] GameBoard;// the entire board
    private ChessPiece[] blackPieces;  // the black Pieces
    private ChessPiece[] whitePieces;  // the white pieces
    GameFrame game;//the JFrame in the GUI
    /**
     * Constructor for objects of class board
     */
    public Board()
    {
        game = new GameFrame(); //new GameFrame   
        GameBoard = new ChessPiece[8][8];
    }

    /**
     * @return the 2d array of pieces which represents the 8 by 8 chess board
     */
    public ChessPiece[][] getBoard()
    {
        return GameBoard;
    }

    /**
     * @return the white pieces
     */
    public ChessPiece[] getWhitePieces()
    {
        return whitePieces;
    }

    /**
     * @return the black pieces 
     */
    public ChessPiece[] getBlackPieces()
    {
        return blackPieces;
    }

   /**
     * @param ChessPiece is the piece being moved
     * @param row and col is where the piece is being moved to in GameBoard
     */

    public boolean move(ChessPiece piece, int row, int col)
    {
        if(piece.attacks(row,col))
        {
            if(piece instanceof King)
            {
                King king = (King)piece;
                if(king.getIsWhite())
                {

                    if(!king.hasMoved())
                    {
                        if(col==2)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[7][3] = GameBoard[7][0];
                            GameBoard[7][0] = null;
                            GameBoard[7][3].moveCol(3);
                            return true;
                        }
                        else if(col==6)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[7][5] = GameBoard[7][7];
                            GameBoard[7][7] = null;
                            GameBoard[7][5].moveCol(5);
                            return true;
                            
                        }
                        else
                        {
                            king.setHasMoved(true);
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            return true;
                        }
                    }
                    else 
                    {

                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        return true;
                    }

                }
                else
                {
                    if(!king.hasMoved())
                    {
                        if(col==2)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[0][3] = GameBoard[0][0];
                            GameBoard[0][0] = null;
                            GameBoard[0][3].moveCol(3);
                            return true;
                        }
                        else if(col==6)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[0][5] = GameBoard[0][7];
                            GameBoard[0][7] = null;
                            GameBoard[0][5].moveCol(5);
                            return true;
                        }
                        else
                        {
                            king.setHasMoved(true);
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            return true;
                        }
                    }
                    else 
                    {

                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        return true;
                    }

                }
            }
            else if(piece instanceof Pawn)
            {
                if(col==piece.getCol())
                {
                    if(row==0)
                    {
                        ChessPiece promotion = promoteWhitePawn();
                        GameBoard[row][col] = promotion;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        return true;
                    }
                    else if(row==7)
                    {
                        ChessPiece promotion = promoteWhitePawn();
                        GameBoard[row][col] = promotion;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        return true;
                    }
                    else
                    {
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        return true;
                    }
                }
                else
                {
                    if(GameBoard[row][col]!=null)
                    {
                        if(GameBoard[row][col].getIsWhite()!=piece.getIsWhite())
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            return true;
                        }
                    }

                }
            }
            else
            {
                GameBoard[row][col] = piece;
                GameBoard[piece.getRow()][piece.getCol()] = null;
                piece.moveRow(row);
                piece.moveCol(col);
                return true;
            }

        }

        return false;
    }
    public void updateMoves()
    {
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                
                if(GameBoard[r][c]!=null)
                {
                    GameBoard[r][c].findMove(GameBoard);
                }
            }
        }
                    
        
        
        
        
        
    }
}
