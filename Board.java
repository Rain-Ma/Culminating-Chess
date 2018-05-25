/**
 * Write a description of class board here.
 *
 * @author Rain Ma, Justin Chu
 * @version 24/05/2018
 */
public class Board
{
    // instance variables - replace the example below with your own
    private ChessPiece[][] GameBoard;// the entire board
    private ChessPiece[] blackPieces;  // the black Pieces
    private ChessPiece[] whitePieces;  // the white pieces

    /**
     * Constructor for objects of class board
     */
    public Board()
    {
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
     * Starts a new game
     */
    public void SetGame()
    {
        //white pieces
        GameBoard[7][0] = new Rook(7,0,true);
        GameBoard[7][1] = new Knight(7,1,true);
        GameBoard[7][2] = new Bishop(7,2,true);
        GameBoard[7][3] = new Queen(7,3,true);
        GameBoard[7][4] = new King(7,4,true);
        GameBoard[7][5] = new Bishop(7,5,true);
        GameBoard[7][6] = new Knight(7,6,true);
        GameBoard[7][7] = new Rook(7,7,true);
        for(int i = 0; i < 8; i++)
        {
            GameBoard[6][i] = new Pawn(6, i, true);
        }

        //black pieces
        GameBoard[0][0] = new Rook(0,0,false);
        GameBoard[0][1] = new Knight(0,1,false);
        GameBoard[0][2] = new Bishop(0,2,false);
        GameBoard[0][3] = new Queen(0,3,false);
        GameBoard[0][4] = new King(0,4,false);
        GameBoard[0][5] = new Bishop(0,5,false);
        GameBoard[0][6] = new Knight(0,6,false);
        GameBoard[0][7] = new Rook(0,7,false);
        for(int i = 0; i < 8; i++)
        {
            GameBoard[1][i] = new Pawn(1, i, false);
        }
    }

    /**
     * @param ChessPiece is the piece being moved
     * @param row and col is where the piece is being moved to in GameBoard
     */

    public boolean move(ChessPiece piece, int row, int col)
    {
        piece.findMove(GameBoard);
        if(piece.attacks(row,col))
        {
            if(piece instanceof King)
            {
                King king = (King)piece;
                if(king.getIsWhite())
                {
                    /*
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
                     */
                    GameBoard[row][col] = piece;
                    GameBoard[piece.getRow()][piece.getCol()] = null;
                    piece.moveRow(row);
                    piece.moveCol(col);
                    return true;
                }
                else
                {
                    /*
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
                     */
                    GameBoard[row][col] = piece;
                    GameBoard[piece.getRow()][piece.getCol()] = null;
                    piece.moveRow(row);
                    piece.moveCol(col);
                    return true;
                }
            }
            else if(piece instanceof Pawn)
            {
                if(col==piece.getCol())
                {
                    if(row==0)
                    {
                        // ChessPiece promotion = promoteWhitePawn();
                        // GameBoard[row][col] = promotion;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        return true;
                    }
                    else if(row==7)
                    {
                        //ChessPiece promotion = promoteWhitePawn();
                        // GameBoard[row][col] = promotion;
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
        else
        {
            /*
            for(int i = 0; i < piece.getMoves().length; i++)
            {
            }
            return true;
             */
        }

        return false;
    }

    public void whiteMoves()
    {
        int[][] whiteMoves = new int[0][2];
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c] !=null)
                {
                    if(GameBoard[r][c].getIsWhite())
                    {
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());
                    }
                }

            }

        }

    }

    public void blackMoves()
    {
        int[][] whiteMoves = new int[0][2];
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c] !=null)
                {
                    if(!GameBoard[r][c].getIsWhite())
                    {
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());
                    }
                }

            }

        }
    }

    public int[][] add(int[][] a, int[][] b)
    {
        int moves[][] = new int[64][2];
        int counter=0;
        for(int i=0;i<a.length;i++)
        {
            moves[i] = a[i];
            counter++;            
        }

        for(int i=0;i<b.length;i++)
        {
            if(moves[i][0]!=b[i][0]&&moves[i][1]!=b[i][1])
            {
                moves[counter] = b[i];
                counter++;
            }

        }
        int[][] finaleMoves = new int[counter][2];
        for(int i=0;i<finaleMoves.length;i++)
        {
            finaleMoves[i] = moves[i];

        }
        return finaleMoves;
    }

}
