import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import bst.BinarySearchTreeImpl;
import permutations.Permutations.PermutationIterator;

import org.junit.Test;

/**
 * This is a test file for the BinarySearchTree implementation class.
 */
public class BinarySearchTreeImplTest {

  /**
   * testing the BinarySearchTree constructor with no input.
   */
  @Test
  public void testConstructorNoParam() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    assertEquals(0, testBst.size());
    assertEquals("[]", testBst.toString());
  }
  
  /**
   * testing the BinarySearchTree constructor with data input.
   */
  @Test
  public void testConstructorWithParam() {
    int input = 5;
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>(input);
    assertEquals(1, testBst.size());
    assertEquals("[5]", testBst.toString());
  }
  
  /**
   * testing the BinarySearchTree add function and toString function.
   */
  @Test
  public void testAdd() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    testBst.add(5);
    assertEquals(1, testBst.size());
    assertEquals("[5]", testBst.toString());
    testBst.add(4);
    testBst.add(8);
    testBst.add(3);
    testBst.add(9);
    assertEquals(5, testBst.size());
    assertEquals("[3 4 5 8 9]", testBst.toString());
  }
  
  /**
   * testing the BinarySearchTree height function, size function.
   * and toString function.
   */
  @Test
  public void testHeight() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    assertEquals(0, testBst.height());
    testBst.add(5);
    assertEquals(1, testBst.height());
    testBst.add(4);
    testBst.add(8);
    testBst.add(3);
    testBst.add(9);
    assertEquals(3, testBst.height());
    assertEquals("[3 4 5 8 9]", testBst.toString());
  }
  
  /**
   * testing the BinarySearchTree present function.
   */
  @Test
  public void testPresent() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    int answer;
    if (testBst.present(5) == true) {
      answer = 0;
    } else {
      answer = 1;
    }
    assertEquals(1, answer);
    testBst.add(5);
    testBst.add(5);
    testBst.add(6);
    testBst.add(7);
    testBst.add(3);
    int findAnswer6;
    if (testBst.present(6) == true) {
      findAnswer6 = 0;
    } else {
      findAnswer6 = 1;
    }
    assertEquals(0, findAnswer6);
  }

  /**
   * testing the BinarySearchTree min function.
   */
  @Test
  public void testMin() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    testBst.add(5);
    testBst.add(4);
    testBst.add(8);
    testBst.add(3);
    testBst.add(9);
    int answer;
    if (testBst.minimum() == 3) {
      answer = 0;
    } else {
      answer = 1;
    }
    assertEquals(0, answer);
  }
  
  /**
   * testing the BinarySearchTree max function.
   */
  @Test
  public void testMax() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    testBst.add(5);
    testBst.add(4);
    testBst.add(8);
    testBst.add(3);
    testBst.add(9);
    int answer;
    if (testBst.maximum() == 9) {
      answer = 0;
    } else {
      answer = 1;
    }
    assertEquals(0, answer);
  }
  
  /**
   * testing the BinarySearchTree pre-order, in-order,
   * and post-order function.
   */
  @Test
  public void testStringOrder() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    testBst.add(5);
    testBst.add(4);
    testBst.add(8);
    testBst.add(3);
    testBst.add(9);
    assertEquals("[5 4 3 8 9]", testBst.preOrder());
    assertEquals("[3 4 9 8 5]", testBst.postOrder());
    assertEquals("[3 4 5 8 9]", testBst.inOrder());
    
    BinarySearchTreeImpl<Integer> testBst2 = new BinarySearchTreeImpl<Integer>();
    testBst2.add(5);
    testBst2.add(6);
    assertEquals("[5 6]", testBst2.preOrder());
    assertEquals("[6 5]", testBst2.postOrder());
    assertEquals("[5 6]", testBst2.inOrder());
    
    BinarySearchTreeImpl<Integer> testBst3 = new BinarySearchTreeImpl<Integer>();
    testBst3.add(810);
    testBst3.add(-647);
    testBst3.add(460);
    assertEquals("[810 -647 460]", testBst3.preOrder());
    assertEquals("[460 -647 810]", testBst3.postOrder());
    assertEquals("[-647 460 810]", testBst3.inOrder());
  }
  
  /**
   * testing the BinarySearchTree size function.
   */
  @Test
  public void testSize() {
    BinarySearchTreeImpl<Integer> testBst = new BinarySearchTreeImpl<Integer>();
    testBst.add(5);
    testBst.add(4);
    testBst.add(8);
    testBst.add(3);
    testBst.add(9);
    assertEquals(5, testBst.size());
    BinarySearchTreeImpl<Integer> testBstEmpty = new BinarySearchTreeImpl<Integer>();
    assertEquals(0, testBstEmpty.size());
  }
}







/**
 * Creates an implicit Iterable collection of all permutations of a string.
 * @param input  String to be permuted
 * @throws IllegalArgumentException if the string is empty
 */
public Permutations(String input) throws IllegalArgumentException {
  if (input.length() <= 0) {
    throw new IllegalArgumentException("the string must be more than one value");
  }
  this.startIndex = 1;
  this.input = input;
}

/**
 * Creates an implicit Iterable collection of all permutations of a string.
 * @param input  String to be permuted
 * @param start the start length of the permutation
 * @throws IllegalArgumentException if the string is empty, 
 * or if the start length is greater than the string or the start length is less than 0
 */
public Permutations(String input, int start) throws IllegalArgumentException {
  if (start > input.length()) {
    throw new IllegalArgumentException("the start length must be less than or equal to the length");
  }
  if (start <= 0) {
    throw new IllegalArgumentException("the start length must be greater than 0");
  }
  this.input = input;
  this.startIndex = start;
}

@Override
public Iterator<String> iterator() {
  return new PermutationIterator(input, startIndex);
}
