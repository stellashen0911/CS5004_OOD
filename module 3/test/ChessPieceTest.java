import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import chess.Bishop;
import chess.CellPosition;
import chess.ChessPiece;
import chess.Color;
import chess.King;
import chess.Knight;
import chess.Pawn;
import chess.Queen;
import chess.Rook;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains all the unit tests for various kinds of chess pieces.
 */
public class ChessPieceTest {
  private Bishop bishop1;
  private Bishop bishop2;
  private ChessPiece piece1;
  private ChessPiece piece2;
  private CellPosition cell1;
  private CellPosition cell2;
  private King king1;
  private King king2;
  private King king3;
  private Knight knight1;
  private Knight knight2;
  private Knight knight3;
  private Pawn pawn1;
  private Pawn pawn2;
  private Queen queen1;
  private Queen queen2;
  private Rook rook1;
  private Rook rook2;
  
  /**
   * setting up the values for different chess pieces.
   */
  @Before
  public void setUp() {
    bishop1 = new Bishop(1, 1, Color.BLACK);
    bishop2 = new Bishop(5, 4, Color.WHITE);
    
    piece1 = new Bishop(3, 6, Color.BLACK);
    piece2 = new Queen(7, 6, Color.BLACK);
    
    cell1 = new CellPosition(4, 5);
    cell2 = new CellPosition(1, 1);
    
    king1 = new King(3, 3, Color.WHITE);
    king2 = new King(1, 4, Color.BLACK);
    king3 = new King(5, 3, Color.WHITE);
    
    knight1 = new Knight(7, 2, Color.BLACK);
    knight2 = new Knight(0, 2, Color.WHITE);
    knight3 = new Knight(2, 4, Color.BLACK);
    
    pawn1 = new Pawn(1, 5, Color.WHITE);
    pawn2 = new Pawn(6, 2, Color.BLACK);
    
    queen1 = new Queen(4, 4, Color.BLACK);
    queen2 = new Queen(6, 7, Color.WHITE);
    
    rook1 = new Rook(1, 3, Color.WHITE);
    rook2 = new Rook(6, 3, Color.BLACK);
  }

  /**
   * testing the cell position get methods.
   */
  @Test
  public void testCellPosition() {
    assertEquals(4, cell1.getRow());
    assertEquals(5, cell1.getColumn());
    assertEquals(1, cell2.getColumn());
    assertEquals(1, cell2.getRow());
  }
  
  /**
   * testing the chess piece get methods.
   */
  @Test
  public void testChessPieceGet() {
    assertEquals(3, piece1.getRow());
    assertEquals(6, piece2.getColumn());
    assertEquals(1, piece1.getColorIndex(piece1.getColor()));
    assertEquals(1, bishop1.getColumn());
  }
  
  /**
   * testing the king canMove methods.
   */
  @Test
  public void testKingMove() {
    assertEquals(true, king1.canMove(3, 2));
    assertEquals(false, king1.canMove(6, 6));
    assertEquals(true, king2.canMove(1, 3));
    assertEquals(false, king2.canMove(1, 7));
  }
  
  /**
   * testing the king canKill methods.
   */
  @Test
  public void testKingKill() {
    assertEquals(false, king1.canKill(rook1));
    assertEquals(false, king2.canKill(piece1));
  }
   
  /**
   * testing the bishop canMove methods.
   */
  @Test
  public void testBishopMove() {
    assertEquals(true, bishop1.canMove(3, 3));
    assertEquals(false, bishop1.canMove(4, 1));
    assertEquals(true, bishop2.canMove(6, 3));
    assertEquals(false, bishop1.canMove(1, 7));
  }
  
  /**
   * testing the bishop canKill methods.
   */
  @Test
  public void testBishopKill() {
    assertEquals(true, bishop1.canKill(king1));
    assertEquals(false, bishop1.canKill(piece1));
  }
  
  /**
   * testing the queen canMove methods.
   */
  @Test
  public void testQueenMove() {
    assertEquals(true, queen1.canMove(5, 5));
    assertEquals(false, queen1.canMove(4, 4));
    assertEquals(true, queen2.canMove(6, 3));
    assertEquals(false, queen2.canMove(1, 1));
  }
  
  /**
   * testing the queen canKill methods.
   */
  @Test
  public void testQueenKill() {
    assertEquals(true, queen1.canKill(king1));
    assertEquals(false, queen2.canKill(king1));
  }
  
  /**
   * testing the Knight canMove methods.
   */
  @Test
  public void testKnightMove() {
    assertEquals(true, knight1.canMove(5, 1));
    assertEquals(false, knight1.canMove(1, 1));
    assertEquals(true, knight2.canMove(1, 4));
    assertEquals(false, knight2.canMove(1, 7));
  }
  
  /**
   * testing the Knight canKill methods.
   */
  @Test
  public void testKnightKill() {
    assertEquals(true, knight1.canKill(king3));
    assertEquals(false, knight2.canKill(king3));
  }
  
  /**
   * testing the Rook canMove methods.
   */
  @Test
  public void testRookMove() {
    assertEquals(true, rook1.canMove(1, 1));
    assertEquals(false, rook1.canMove(6, 5));
    assertEquals(true, rook2.canMove(4, 3));
    assertEquals(false, rook2.canMove(1, 7));
  }
  
  /**
   * testing the Rook canKill methods.
   */
  @Test
  public void testRookKill() {
    assertEquals(true, rook1.canKill(king2));
    assertEquals(false, rook2.canKill(king2));
  }
  
  /**
   * testing the Pawn canMove methods.
   */
  @Test
  public void testPawnMove() {
    assertEquals(true, pawn1.canMove(2, 5));
    assertEquals(false, pawn1.canMove(0, 5));
    assertEquals(true, pawn2.canMove(5, 2));
    assertEquals(false, pawn2.canMove(7, 7));
  }
  
  /**
   * testing the Pawn canKill methods.
   */
  @Test
  public void testPawnKill() {
    assertEquals(true, pawn1.canKill(knight3));
    assertEquals(false, pawn2.canKill(king2));
  }
  
  /**
   * testing the invalid input.
   */
  @Test
  public void testInvalidInput() {
    try {
      Bishop testBishop = new Bishop(-1, 6, Color.BLACK);
      assertEquals(true, testBishop.canMove(1, 1));
    } catch (IllegalArgumentException e) {
      fail("Chess piece must be on the board!");
    }
  }
  
}
