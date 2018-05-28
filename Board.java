import java.util.Arrays;
/**   
 * Write a description of class board here.
 *
 * @author Rain Ma, Justin Chu
 * @version 27/05/2018
 */
public class Board
{
    // instance variables - replace the example below with your own
    private ChessPiece[][] GameBoard;// the entire board
    private ChessPiece[] blackPieces;  // the black Pieces
    private ChessPiece[] whitePieces;  // the white pieces
    private ChessPiece[] deadBlack = new ChessPiece[16];  // the dead black pieces
    private ChessPiece[] deadWhite = new ChessPiece[16]; // the dead white pieces

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
    public ChessPiece[]  getWhitePieces()
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
            if(isLegal(piece,row,col))
            {
                piece.setMoveNumber(piece.getGameMoveNumber());
                if(piece instanceof King)
                {
                    King king = (King)piece;

                    if(!king.hasMoved())
                    {
                        if(col==2)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[king.getRow()][3] = GameBoard[king.getRow()][0];
                            GameBoard[king.getRow()][0] = null;
                            GameBoard[king.getRow()][3].moveCol(3);
                            king.setHasMoved(true);
                            return true;
                        }
                        else if(col==6)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[king.getRow()][5] = GameBoard[king.getRow()][7];
                            GameBoard[king.getRow()][7] = null;
                            GameBoard[king.getRow()][5].moveCol(5);
                            king.setHasMoved(true);
                            return true;
                        }
                        else
                        {
                            king.setHasMoved(true);
                            addPiecesEaten(GameBoard[row][col]);
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            return true;
                        }
                    }
                    else 
                    {
                        addPiecesEaten(GameBoard[row][col]);
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        return true;
                    }

                }

                else if(piece instanceof Pawn)
                {
                    Pawn pawn = (Pawn)piece;
                    if(piece.getCol()!=col)
                    {
                        if(GameBoard[row][col]==null)
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][col] = null;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            pawn.setEnPassant(false);
                            return true;
                        }
                        else
                        {
                            addPiecesEaten(GameBoard[row][col]);
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            pawn.setEnPassant(false);
                            return true;
                        }
                    }
                    else if(Math.abs(pawn.getRow()-row)==2)
                    {
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        pawn.setEnPassant(true);
                        return true;
                    }
                    else
                    {
                        
                    if(GameBoard[row][col] instanceof ChessPiece)
                        addPiecesEaten(GameBoard[row][col]);
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        pawn.setEnPassant(false);
                        return true;
                    }

                }
                else 
                {
                    if(piece instanceof Rook)
                    {
                        Rook rook = (Rook)piece;
                        rook.setMoved(true);
                    }
                    if(GameBoard[row][col] instanceof ChessPiece)
                        addPiecesEaten(GameBoard[row][col]);
                    GameBoard[row][col] = piece;
                    GameBoard[piece.getRow()][piece.getCol()] = null;
                    piece.moveRow(row);
                    piece.moveCol(col);
                    return true;
                }
            }
        }

        return false;
    }

    public int[][] whiteMoves()
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
                        GameBoard[r][c].findMove(GameBoard);
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());
                    }
                }

            }

        }
        return whiteMoves;
    }

    public int[][] whiteMoves(ChessPiece[][] GameBoard)
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
                        GameBoard[r][c].findMove(GameBoard);
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());
                    }
                }

            }

        }
        return whiteMoves;
    }

    public int[][] blackMoves()
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
                        GameBoard[r][c].findMove(GameBoard);
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());
                    }
                }

            }

        }
        return whiteMoves;
    }

    public int[][] blackMoves(ChessPiece[][] GameBoard)
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
                        GameBoard[r][c].findMove(GameBoard);
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());
                    }
                }

            }

        }
        return whiteMoves;
    }

    public int[][] add(int[][] a, int[][] b)
    {
        int moves[][] = new int[128][2];
        int counter=0;
        if(a.length>0)
        {
            for(int i=0;i<a.length;i++)
            {
                moves[i] = a[i];
                counter++;

            }
        }
        if(b!=null)
        {
            for(int i=0;i<b.length;i++)
            {
                if(!has(moves,b[i]))
                {
                    moves[counter] = b[i];
                    counter++;
                }

            }
        }
        int[][] finaleMoves = Arrays.copyOf(moves,counter);

        return finaleMoves;
    }

    public boolean isLegal(ChessPiece piece, int row, int col)
    {
        King king = new King();
        ChessPiece Piece = piece;
        ChessPiece[][] board = new ChessPiece[8][8];
        for(int i =0;i<8;i++)
        {

            board[i] = GameBoard[i].clone();

        }
        if(piece instanceof Pawn)
        {
            Piece = new Pawn(piece);
        }
        else if(piece instanceof King)
        {
            Piece = new King(piece);
        }
        else if(piece instanceof Queen)
        {
            Piece = new Queen(piece);
        }
        else if(piece instanceof Rook)
        {
            Piece = new Rook(piece);
        }
        else if(piece instanceof Knight)
        {
            Piece = new Knight(piece);
        }
        else if(piece instanceof Bishop)
        {
            Piece = new Bishop(piece);
        }
        board[row][col] = Piece;
        board[Piece.getRow()][Piece.getCol()] = null;
        Piece.moveRow(row);
        Piece.moveCol(col);
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(board[r][c]!=null)
                {
                    if(board[r][c] instanceof King&& board[r][c].getIsWhite()==Piece.getIsWhite())
                    {
                        king = (King)board[r][c];
                        break;
                    }
                }
            } 
        }
        if(piece.getIsWhite())
        {
            king.updateOpponentMove(blackMoves(board));
        }
        else
        {
            king.updateOpponentMove(whiteMoves(board));
        }
        if(king.checked())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public void promote(ChessPiece pawn, String piece)
    {
        if(piece.equals("Queen"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Queen(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
        else if(piece.equals("Rook"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Rook(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
        else if(piece.equals("Bishop"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Bishop(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
        else if(piece.equals("Knight"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Knight(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
    }

    public void addPiecesEaten(ChessPiece piece)
    {
        if(piece.getIsWhite())
        {
            for(int i = 0; i < 32; i++)
            {
                if(deadWhite[i] == null)
                {
                    deadWhite[i] = piece;
                    break;
                }
            }
        }
        else
        {
            for(int i = 0; i < 32; i++)
            {
                if(deadBlack[i] == null)
                {
                    deadBlack[i] = piece;
                    break;
                }
            }
        }
    }

    public ChessPiece[] getBlackEaten()
    {
        return deadBlack;
    }  

    public ChessPiece[] getWhiteEaten()
    {
        return deadWhite;
    }

    public boolean has(int[][] a,int[] b)
    {
        for(int i=0;i<a.length;i++)
        {
            if(a[i].equals(b))
            {
                return true;
            }

        }
        return false;
    }

    public boolean checkCheckMate(boolean white)
    { 
        King king = new King();
        boolean foundKing = false;
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c] instanceof King&& GameBoard[r][c].getIsWhite()==white)
                    {
                        king = (King)GameBoard[r][c];
                        foundKing = true;
                        break;
                    }
                }
            } 
            if(foundKing)
            {
                break;
            }
        }

        boolean noLegalMoves = true;
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c].getIsWhite() ==white)
                    {
                        GameBoard[r][c].findMove(GameBoard);
                        int[][] move = GameBoard[r][c].getMoves();
                        for(int i=0;i<move.length;i++)
                        {
                            if(isLegal(GameBoard[r][c],move[i][0],move[i][1]))
                            {
                                noLegalMoves = false;
                                break;
                            }
                        }
                    }
                }
            } 
            if(!noLegalMoves)
            {
                break;
            }
        }
        if(white)
        {
            king.updateOpponentMove(blackMoves());
        }
        else
        {
            king.updateOpponentMove(whiteMoves());
        }
        if(noLegalMoves)
        {
            if(king.checked())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }

    public boolean checkStaleMate(boolean white)
    {
        King king = new King();
        boolean foundKing = false;
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c] instanceof King&& GameBoard[r][c].getIsWhite()==white)
                    {
                        king = (King)GameBoard[r][c];
                        foundKing = true;
                        break;
                    }
                }
            } 
            if(foundKing)
            {
                break;
            }
        }

        boolean noLegalMoves = true;
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c].getIsWhite() ==white)
                    {
                        GameBoard[r][c].findMove(GameBoard);
                        int[][] move = GameBoard[r][c].getMoves();
                        for(int i=0;i<move.length;i++)
                        {
                            if(isLegal(GameBoard[r][c],move[i][0],move[i][1]))
                            {
                                noLegalMoves = false;
                                break;
                            }
                        }
                    }
                }
            } 
            if(!noLegalMoves)
            {
                break;
            }
        }
        if(white)
        {
            king.updateOpponentMove(blackMoves());
        }
        else
        {
            king.updateOpponentMove(whiteMoves());
        }
        if(noLegalMoves)
        {
            if(king.checked())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }

    }
}
