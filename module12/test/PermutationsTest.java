import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;
import org.junit.Test;
import permutations.Permutations;

/**
 * This is a test file for the permutations class.
 */
public class PermutationsTest {

  /**
   * testing the permutations constructor with valid input.
   */
  @Test
  public void testConstructorString() {
    Permutations test = new Permutations("abcdef");
    String result = "";
    if (test.hasNext()) {
      result = test.next();
    }
    assertEquals("a", result);
  }

  /**
   * testing the permutations constructor with valid input.
   */
  @Test
  public void testConstructorIndex() {
    Permutations test = new Permutations("abcdef", 2);
    String result = "";
    if (test.hasNext()) {
      result = test.next();
    }
    assertEquals("ab", result);
  }
  
  /**
   * testing the permutations constructor with no valid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorStringFail() {
    Permutations test = new Permutations("");
    String result = "";
    if (test.hasNext()) {
      result = test.next();
    }
    assertEquals("", result);
  }
  
  /**
   * testing the permutations constructor with no valid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIndexFail() {
    Permutations test = new Permutations("abc", 0);
    String result = "";
    if (test.hasNext()) {
      result = test.next();
    }
    assertEquals("", result);
  }
  
  /**
   * testing the permutations hasNext and next iterator function.
   */
  @Test
  public void testHasNext() {
    Permutations test = new Permutations("abcdef", 1);
    String result1 = "";
    if (test.hasNext()) {
      result1 = test.next();
    }
    assertEquals("a", result1);
    
    String result2 = "";
    if (test.hasNext()) {
      result2 = test.next();
    }
    assertEquals("b", result2);
    
    Permutations test2 = new Permutations("abcdef", 3);
    String result3 = "";
    if (test2.hasNext()) {
      result3 = test2.next();
    }
    assertEquals("abc", result3);
    
    String result4 = "";
    if (test2.hasNext()) {
      result4 = test2.next();
    }
    assertEquals("abd", result4);
  }
  
  /**
   * testing the permutations hasNext and next iterator function.
   */
  @Test(expected = NoSuchElementException.class)
  public void testHasNextFail() {
    Permutations test = new Permutations("abc", 3);
    test.next();
    String result = "";
    result = test.next();
    assertEquals("", result);
  }
  
  /**
   * testing the next fail.
   */
  @Test(expected = NoSuchElementException.class)
  public void testNextFail() {
    Permutations test = new Permutations("abc", 3);
    String result = "";
    test.next();
    result = test.next();
    assertEquals("", result);
  }
  
  /**
   * testing the permutations hasPrevious and previous iterator function.
   */
  @Test
  public void testHasPrevious() {
    Permutations test = new Permutations("abc", 3);
    String result1 = "";
    if (test.hasPrevious()) {
      result1 = test.previous();
    }
    assertEquals("bc", result1);
    
    String result2 = "";
    if (test.hasPrevious()) {
      result2 = test.previous();
    }
    assertEquals("ac", result2);
    
    Permutations test2 = new Permutations("abcdef", 2);
    String result3 = "";
    if (test2.hasPrevious()) {
      result3 = test2.previous();
    }
    assertEquals("f", result3);
    
    String result4 = "";
    if (test2.hasPrevious()) {
      result4 = test2.previous();
    }
    assertEquals("e", result4);
  }
  
  /**
   * testing the permutations hasPrevious and previous iterator function.
   */
  @Test(expected = NoSuchElementException.class)
  public void testHasPreviousFail() {
    Permutations test = new Permutations("abc", 1);
    test.previous();
    String result = "";
    result = test.previous();
    assertEquals("", result);
  }
  
  /**
   * testing the previous fail.
   */
  @Test(expected = NoSuchElementException.class)
  public void testPreviousFail() {
    Permutations test = new Permutations("abc", 1);
    String result = "";
    test.previous();
    result = test.previous();
    assertEquals("", result);
  }
  
}
