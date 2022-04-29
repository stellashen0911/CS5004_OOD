import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import tiles.Coordinate;
import tiles.Rectangle;

/**
 * A JUnit test class for the Rectangle class.
 */
public class RectangleTest {
  private Rectangle r1;
  private Rectangle r2;
  private Rectangle r3;
  private Rectangle r4;
  private Coordinate corner;
  
  
  /**
   * setting up the values for different Rectangles.
   */
  @Before
  public void setUp() {
    r1 = new Rectangle(1, 1, 4, 3);
    r2 = new Rectangle(1, 1, 5, 4); 
    r3 = new Rectangle(2, 2, 4, 3);
    r4 = new Rectangle(-1, -1, 2, 1);
    corner = new Coordinate(1, 1);
  }

  /**
   * testing the toString method.
   */
  @Test
  public void testToString() {
    String expected = "x:1, y:1, w:4, h:3";
    String answer = r1.toString();
    assertEquals(expected, answer);
  }
  
  /**
   * testing the getHeight method.
   */
  @Test
  public void testHeight() {
    assertEquals(3, (int) r1.getHeight());
    assertEquals(4, (int) r2.getHeight());
    assertEquals(3, (int) r3.getHeight());
    assertEquals(1, (int) r4.getHeight());
  }
  
  /**
   * testing the getWidth method.
   */
  @Test
  public void testWidth() {
    assertEquals(4, (int) r1.getWidth());
    assertEquals(5, (int) r2.getWidth());
    assertEquals(4, (int) r3.getWidth());
    assertEquals(2, (int) r4.getWidth());
  }
  
  /**
   * testing the getX and getY method.
   */
  @Test
  public void testCorner() {
    assertEquals(corner.getX(), r1.getCorner().getX());
    assertEquals(corner.getY(), r1.getCorner().getY());
  }
  
  /**
   * testing the overlap method.
   */
  @Test
  public void testOverlap() {
    assertEquals(true, r1.overlap(r2));
    assertEquals(false, r1.overlap(r4));
  }
  
  /**
   * testing the intersect method.
   */
  @Test
  public void testIntersectVersion1() {
    assertEquals(r1, r1.intersect(r2));
  }

  /**
   * testing the intersect method.
   */
  @Test
  public void testIntersectVersion2() {
    Rectangle intersect = new Rectangle(2, 2, 3, 2);
    assertEquals(intersect.getHeight(), r1.intersect(r3).getHeight());
    assertEquals(intersect.getWidth(), r1.intersect(r3).getWidth());
    assertEquals(intersect.getCorner().getX(), r1.intersect(r3).getCorner().getX());
    assertEquals(intersect.getCorner().getY(), r1.intersect(r3).getCorner().getY());
  } 

  /**
   * testing the intersect method.
   */
  @Test
  public void testIntersectVersion3() {
    try {
      Rectangle intersect = new Rectangle(2, 2, 3, 2);
      assertEquals(intersect, r1.intersect(r4));
    } catch (NoSuchElementException e) {
      fail("An exception should not have been thrown");
    }
  }
  
  /**
   * testing the union method.
   */
  @Test
  public void testUnionVersion1() {
    assertEquals(r2, r1.union(r2));
  }
  
  /**
   * testing the union method.
   */
  @Test
  public void testUnionVersion2() {
    Rectangle union = new Rectangle(1, 1, 5, 4);
    assertEquals(union.getHeight(), r1.union(r3).getHeight());
    assertEquals(union.getWidth(), r1.union(r3).getWidth());
    assertEquals(union.getCorner().getX(), r1.union(r3).getCorner().getX());
    assertEquals(union.getCorner().getY(), r1.union(r3).getCorner().getY());
  }
  
  /**
   * testing the union method.
   */
  @Test
  public void testUionVersion3() {
    Rectangle union = new Rectangle(-1, -1, 6, 5);
    assertEquals(union.getHeight(), r1.union(r4).getHeight());
    assertEquals(union.getWidth(), r1.union(r4).getWidth());
    assertEquals(union.getCorner().getX(), r1.union(r4).getCorner().getX());
    assertEquals(union.getCorner().getY(), r1.union(r4).getCorner().getY());
  }
}