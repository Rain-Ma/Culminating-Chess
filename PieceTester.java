
/**
 * Tests the ChessPiece subclasses
 *
 * @author Justin Chu
 * @version May 13th, 2018
 */
public class PieceTester
{
    public static void main(String[] args)
    {
        Bishop bishop = new Bishop(1,1,true);
        System.out.println(bishop.getMoves());
     }
}
