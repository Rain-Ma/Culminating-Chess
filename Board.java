//import statements
import java.util.Arrays;
/**   
 * The board class creates the chess board and has methods that manipulate the board
 *
 * @author Rain Ma, Justin Chu
 * @version 27/05/2018
 */
public class Board
{
    // instance variables
    private ChessPiece[][] GameBoard;// the entire board

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
     * Returns GameBoard 2d array
     * 
     * @return the 2d array of pieces which represents the 8 by 8 chess board
     */
    public ChessPiece[][] getBoard()
    {
        return GameBoard;
    }

    /**
     * Sets up the board for a standard chess game
     */
    public void SetGame()
    {
        //Add white pieces to chess board
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

        //Add black pieces to chess board
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
     * This method moves the location of a piece on the board to a different "square"
     * 
     * @param piece This is the ChessPiece being moved
     * @param row This is row value of the square that piece is being moved to
     * @param col This is the column value of the square that piece is being moved to
     * 
     * @return true if changes were made to the board
     */

    public boolean move(ChessPiece piece, int row, int col)
    {
        piece.findMove(GameBoard,false);// finds all the moves piece can make
        if(piece.attacks(row,col))// checks piece can attack/ move to new square
        {

            if(isLegal(piece,row,col))// checks wether if moving piece to new square is legal.
            {
                piece.setMoveNumber(piece.getGameMoveNumber());// sets the last time moved number as this turn number 
                if(piece instanceof King)//if piece is a king
                {
                    King king = (King)piece;//Make piece's variable into King so King methods can be called
                    if(!king.hasMoved()) //if king hasn't moved yet
                    {
                        if(col==2)//castles queenside
                        {
                            //move the king and rook
                            GameBoard[row][col] = piece; 
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[king.getRow()][3] = GameBoard[king.getRow()][0];
                            GameBoard[king.getRow()][0] = null;
                            GameBoard[king.getRow()][3].moveCol(3);
                            king.setHasMoved(true); //king has moved
                            return true;
                        }
                        else if(col==6)//castles kingside
                        {
                            //move the king and rook
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveCol(col);
                            GameBoard[king.getRow()][5] = GameBoard[king.getRow()][7];
                            GameBoard[king.getRow()][7] = null;
                            GameBoard[king.getRow()][5].moveCol(5);
                            king.setHasMoved(true);//king has moved
                            return true;
                        }
                        else //moves the king
                        {
                            king.setHasMoved(true); //king has moved
                            if(GameBoard[row][col] !=null)//checks if the king is eating a piece or not
                            {
                                addPiecesEaten(GameBoard[row][col]); //add to array of pieces eaten
                            }
                            //move king
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            return true;
                        }
                    }
                    else 
                    {
                        if(GameBoard[row][col] instanceof ChessPiece)// checks if the king is eating a piece or not
                        {
                            addPiecesEaten(GameBoard[row][col]); //add to array of pieces eaten
                        }
                        //move king
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        return true;
                    }
                }

                else if(piece instanceof Pawn)// if piece is a pawn
                {
                    Pawn pawn = (Pawn)piece;//make piece's variable pawn so that Pawn methods can be called
                    if(piece.getCol()!=col)//if the pawn is capturing
                    {
                        if(GameBoard[row][col]==null)//checks if the pawn is enpassanting another pawn
                        {
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][col] = null;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            pawn.setEnPassant(false);
                            return true;
                        }
                        else // pawn captures a piece
                        {
                            addPiecesEaten(GameBoard[row][col]); //add to pieces eaten
                            //move pawn
                            GameBoard[row][col] = piece;
                            GameBoard[piece.getRow()][piece.getCol()] = null;
                            piece.moveRow(row);
                            piece.moveCol(col);
                            pawn.setEnPassant(false);
                            return true;
                        }
                    }
                    else if(Math.abs(pawn.getRow()-row)==2)// pawn is moving 2 squares forward
                    {
                        //move pawn
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        pawn.setEnPassant(true);
                        return true;
                    }
                    else// the pawn moves forawrd 1 square
                    {
                        if(GameBoard[row][col] instanceof ChessPiece) //if the pawn is attacking a piece
                            addPiecesEaten(GameBoard[row][col]); //add to pieces eaten
                        //move pawn
                        GameBoard[row][col] = piece;
                        GameBoard[piece.getRow()][piece.getCol()] = null;
                        piece.moveRow(row);
                        piece.moveCol(col);
                        pawn.setEnPassant(false);
                        return true;
                    }
                }
                else //moving/capture for all the other pieces
                {
                    if(piece instanceof Rook) // for castling purposes
                    {
                        Rook rook = (Rook)piece;
                        rook.setMoved(true);
                    }
                    if(GameBoard[row][col] instanceof ChessPiece) //if piece is attacking another piece
                        addPiecesEaten(GameBoard[row][col]); //add to pieces eaten
                    //move piece
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

    /**
     * Returns all the squares that white ChessPieces are attacking
     * 
     * @return whiteMoves This returns a 2D array that contains the row and col values
     *          of all the squares that the white pieces are attacking
     */
    public int[][] whiteMoves()
    {
        int[][] whiteMoves = new int[0][2];
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)//nested for loop to seach every square for a white piece
            {
                if(GameBoard[r][c] !=null)
                {
                    if(GameBoard[r][c].getIsWhite())
                    {
                        GameBoard[r][c].findMove(GameBoard,true);//finds all the square that piece is attacking 
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());//merges the two arrays
                    }
                }

            }

        }
        return whiteMoves;
    }

    /**
     * Returns all the squares that white ChessPieces are attacking
     * 
     * @param GameBoard This is the chess board that the method is searching
     * @return whiteMoves This returns a 2D array that contains the row and col values
     *          of all the squares that the white pieces are attacking
     */
    public int[][] whiteMoves(ChessPiece[][] GameBoard)
    {
        int[][] whiteMoves = new int[0][2];
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)//nested for loop to seach every square for a white piece
            {
                if(GameBoard[r][c] !=null)
                {
                    if(GameBoard[r][c].getIsWhite())
                    {
                        GameBoard[r][c].findMove(GameBoard,true);//finds all the square that piece is attacking 
                        whiteMoves = add(whiteMoves,GameBoard[r][c].getMoves());//merges the two arrays
                    }
                }
            }
        }
        return whiteMoves;
    }

    /**
     * Returns all the squares that white ChessPieces are attacking
     * 
     * @return blackMoves This returns a 2D array that contains the row and col values
     *          of all the squares that the black pieces are attacking
     */
    public int[][] blackMoves()
    {
        int[][] blackMoves = new int[0][2];
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)//nested for loop to seach every square for a black piece
            {
                if(GameBoard[r][c] !=null)
                {
                    if(!GameBoard[r][c].getIsWhite())
                    {
                        GameBoard[r][c].findMove(GameBoard,true);//finds all the square that piece is attacking 
                        blackMoves = add(blackMoves,GameBoard[r][c].getMoves());//merges the two arrays
                    }
                }
            }
        }
        return blackMoves;
    }

    /**
     * Returns all the squares that black ChessPieces are attacking
     * 
     * @param GameBoard This is the chess board that the method is searching
     * @return blackMoves This returns a 2D array that contains the row and col values
     *          of all the squares that the black pieces are attacking
     */
    public int[][] blackMoves(ChessPiece[][] GameBoard)
    {
        int[][] blackMoves = new int[0][2];
        for(int r=0;r<8;r++)
        {
            for(int c=0;c<8;c++)//nested for loop to seach every square for a black piece
            {
                if(GameBoard[r][c] !=null)
                {
                    if(!GameBoard[r][c].getIsWhite())
                    {
                        GameBoard[r][c].findMove(GameBoard,true);//finds all the square that piece is attacking 
                        blackMoves = add(blackMoves,GameBoard[r][c].getMoves());//merges the two arrays
                    }
                }
            }
        }
        return blackMoves;
    }

    /**
     * This method adds merges two arrays 
     * 
     * @param a This is the first parameter to add
     * @param b This is the second parameter to add
     * 
     * @return moves This is the merged array
     */
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
        int[][] finaleMoves = Arrays.copyOf(moves,counter); //truncate array

        return finaleMoves;
    }

    /**
     * Checks whether moving a chesspiece to a certain square would be legal
     * 
     * @param piece This is the piece being moved
     * @param row This is row value of the square that piece is being moved to
     * @param col This is the column value of the square that piece is being moved to
     * 
     * @return if move is legal
     */
    public boolean isLegal(ChessPiece piece, int row, int col)
    {
        King king = new King();//initailizes a King
        ChessPiece Piece = piece;//Initializes a ChessPiece;

        //creates a copy of the board that doesnt refer to the original
        ChessPiece[][] board = new ChessPiece[8][8];
        for(int i =0;i<8;i++)
        {
            board[i] = GameBoard[i].clone();
        }
        //clones piece into whatever chess piece they are
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
        //moves the piece
        board[row][col] = Piece;
        board[Piece.getRow()][Piece.getCol()] = null;
        Piece.moveRow(row);
        Piece.moveCol(col);
        //finds the king of piece's color
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
        //pass all the squares that the opponent is attacking to the king
        if(piece.getIsWhite())
        {
            king.updateOpponentMove(blackMoves(board));
        }
        else
        {
            king.updateOpponentMove(whiteMoves(board));
        }
        //see if the king is in check
        if(king.checked())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Promoting a pawn - replaces pawn with a new piece
     * 
     * @param pawn This is the pawn that has just reached the end of the board
     * @param piece This is the piece that the pawn is being promoted to
     */
    public void promote(ChessPiece pawn, String piece)
    {
        //makes a new piece at the location of pawn
        if(piece.equals("Queen"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Queen(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
        else if(piece.equals("Rook"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Rook(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
        else if(piece.equals("Bishop"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Bishop(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
        else if(piece.equals("Knight"))
            GameBoard[pawn.getRow()][pawn.getCol()] = new Knight(pawn.getRow(), pawn.getCol(), pawn.getIsWhite());
    }

    /**
     * Adds a piece to a list of eaten pieces
     * 
     * @param piece This is the piece getting captured
     */
    public void addPiecesEaten(ChessPiece piece)
    {
        if(piece.getIsWhite()) //if piece eaten is white
        {
            for(int i = 0; i < 32; i++)
            {
                if(deadWhite[i] == null) //add it to the first empty index
                {
                    deadWhite[i] = piece;
                    break;
                }
            }
        }
        else //if piece eaten is black
        {
            for(int i = 0; i < 32; i++)
            {
                if(deadBlack[i] == null) //add it to the first empty index
                {
                    deadBlack[i] = piece;
                    break;
                }
            }
        }
    }

    /**
     * Returns the array of dead black pieces
     * 
     * @return the black pieces that have been eaten
     */
    public ChessPiece[] getBlackEaten()
    {
        return deadBlack;
    }  

    /**
     * Returns the array of dead white pieces
     * 
     * @return the white pieces that have been eaten
     */
    public ChessPiece[] getWhiteEaten()
    {
        return deadWhite;
    }

    /**
     * Returns whether a 2d array contains another array
     * 
     * @param a This is the array of different squares
     * @param b This is a single square
     * 
     * @return true if a already contains b
     */
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

    /**
     * Checks if a color has been checkmated
     * 
     * @param white This tells the method which color it should check 
     * 
     * @return true if there is a checkmate on the board
     */
    public boolean checkCheckMate(boolean white)
    { 
        King king = new King();
        boolean foundKing = false;
        //find the king of the color same as the boolean white
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c] instanceof King && GameBoard[r][c].getIsWhite()==white) //if king is found
                    {
                        king = (King)GameBoard[r][c]; //cast the ChessPiece into a King to use its methods
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
        //checks if there are any legal moves to make for the color in question
        boolean noLegalMoves = true;
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c].getIsWhite() == white)
                    {
                        GameBoard[r][c].findMove(GameBoard,false);
                        int[][] move = GameBoard[r][c].getMoves();
                        for(int i=0;i<move.length;i++)
                        {
                            if(isLegal(GameBoard[r][c],move[i][0],move[i][1])) //if there is a legal move
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

        //checks if the king is in check or not
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

    /**
     * Checks if a color has been stalemated
     * 
     * @param white This tells the method color it should check
     * 
     * @return true if there is a stalemate on the board
     */
    public boolean checkStaleMate(boolean white)
    {
        King king = new King();
        boolean foundKing = false;
        //finds the king and store it into a varable
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c] instanceof King && GameBoard[r][c].getIsWhite()==white)// if king is found
                    {
                        king = (King)GameBoard[r][c];//cast the ChessPiece into a King to use its methods
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
        //checks if there are any legal moves for the color
        boolean noLegalMoves = true;
        for(int r =0;r<8;r++)
        {
            for(int c=0;c<8;c++)
            {
                if(GameBoard[r][c]!=null)
                {
                    if(GameBoard[r][c].getIsWhite() == white)
                    {
                        GameBoard[r][c].findMove(GameBoard,false);
                        int[][] move = GameBoard[r][c].getMoves();
                        for(int i=0;i<move.length;i++)
                        {
                            if(isLegal(GameBoard[r][c],move[i][0],move[i][1])) //if there is a legal move
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
            if(king.checked())//checks if the king is check or not
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
