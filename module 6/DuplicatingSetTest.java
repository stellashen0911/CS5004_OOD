import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import set.ConcreteDuplicatingSet;
import set.DuplicatingSet;

/**
 * This class contains all the unit tests for sentence class.
 */
public class DuplicatingSetTest {
  DuplicatingSet<Integer> intSet1;
  DuplicatingSet<Integer> intSet2;
  DuplicatingSet<Integer> intSet3;
  DuplicatingSet<String> stringSet1;
  DuplicatingSet<String> stringSet2;
  DuplicatingSet<String> stringSet3;
  
  /**
   * setting up the values for different sentences.
   */
  @Before
  public void setUp() {
    List<Integer> inputIntList = new ArrayList<Integer>();
    inputIntList.add(1);
    inputIntList.add(1);
    inputIntList.add(1);
    inputIntList.add(1);
    inputIntList.add(2);
    inputIntList.add(3);
    
    List<String> inputStringList = new ArrayList<String>();
    inputStringList.add("a");
    inputStringList.add("a");
    inputStringList.add("a");
    inputStringList.add("a");
    inputStringList.add("apple");
    inputStringList.add("banana");
    
    intSet1 = new ConcreteDuplicatingSet<Integer>(5);
    intSet2 = new ConcreteDuplicatingSet<Integer>(3);
    intSet3 = new ConcreteDuplicatingSet<Integer>(3, inputIntList);
    
    stringSet1 = new ConcreteDuplicatingSet<String>(5);
    stringSet2 = new ConcreteDuplicatingSet<String>(3);
    stringSet3 = new ConcreteDuplicatingSet<String>(2, inputStringList);

  }

  /**
   * testing the constructors with two params and the size methods.
   */
  @Test
  public void testTwoParamsConstructor() {
    assertEquals(5, intSet3.size());
    assertEquals(4, stringSet3.size());
  }
  
  /**
   * testing add methods.
   */
  @Test
  public void testAdd() {
    assertEquals(true, intSet2.add(1));
    assertEquals(true, intSet2.add(1));
    assertEquals(true, intSet2.add(1));
    assertEquals(false, intSet2.add(1));
    assertEquals(false, stringSet3.add("a"));
  }
  
  /**
   * testing clear methods.
   */
  @Test
  public void testClear() {
    intSet2.clear();
    assertEquals(0, intSet2.size());
  }
  
  /**
   * testing remove and contains methods.
   */
  @Test
  public void testRemove() {
    intSet3.remove(3);
    assertEquals(0, intSet3.contains(3));
    intSet3.remove(1);
    assertEquals(2, intSet3.contains(1));
  }
  
  /**
   * testing addAll methods.
   */
  @Test
  public void testAddAll() {
    List<Integer> inputIntList2 = new ArrayList<Integer>();
    inputIntList2.add(1);
    inputIntList2.add(1);
    inputIntList2.add(1);
    inputIntList2.add(1);
    inputIntList2.add(2);
    inputIntList2.add(3);

    assertEquals(2, intSet3.addAll(inputIntList2));
  }
  
  /**
   * testing isEmpty methods.
   */
  @Test
  public void testIsEmpty() {
    assertEquals(true, intSet1.isEmpty());
    assertEquals(false, intSet3.isEmpty());
    assertEquals(true, stringSet1.isEmpty());
    assertEquals(false, stringSet3.isEmpty());
  }
  

  /**
   * testing reset duplicate methods.
   */
  @Test
  public void testReset() {
    assertEquals(5, intSet3.size());
    intSet3.resetDuplicates(5);
    assertEquals(5, intSet3.size());
    intSet3.resetDuplicates(2);
    assertEquals(4, intSet3.size());
    intSet3.resetDuplicates(1);
    assertEquals(3, intSet3.size());
  }
  
  /**
   * testing removeAll methods.
   */
  @Test
  public void testRemoveAll() {
    assertEquals(3, intSet3.contains(1));
    assertEquals(3, intSet3.removeAll(1));
    assertEquals(0, intSet3.contains(1));
    assertEquals(1, intSet3.removeAll(3));
    assertEquals(0, intSet3.contains(3));
    assertEquals(0, intSet3.contains(1));
    
    assertEquals(2, stringSet3.contains("a"));
    stringSet3.removeAll("a");
    assertEquals(0, stringSet3.contains("a"));
  }
  
  /**
   * testing union methods.
   */
  @Test
  public void testUnion() {
    intSet1.add(1);
    intSet1.add(4);
    intSet1.add(2);
    intSet1.add(5);
    intSet1.add(1);
    
    DuplicatingSet<Integer> unionSet1Set3Int = intSet1.union(intSet3, 2);
    assertEquals(7, unionSet1Set3Int.size());
    
    stringSet1.add("a");
    stringSet1.add("a");
    stringSet1.add("acer");
    stringSet1.add("orange");
    
    DuplicatingSet<String> unionSet1Set3Str = stringSet1.union(stringSet3, 2);
    assertEquals(6, unionSet1Set3Str.size());
  }
  
  /**
   * testing intersection methods.
   */
  @Test
  public void testIntersection() {
    intSet1.add(1);
    intSet1.add(4);
    intSet1.add(2);
    intSet1.add(5);
    intSet1.add(1);
    intSet1.add(1);
    intSet1.add(1);
    
    DuplicatingSet<Integer> intersectSet1Set3Int = intSet1.intersection(intSet3, 2);
    assertEquals(3, intersectSet1Set3Int.size());
    
    stringSet1.add("a");
    stringSet1.add("a");
    stringSet1.add("a");
    stringSet1.add("a");
    stringSet1.add("apple");
    stringSet1.add("orange");
    
    DuplicatingSet<String> intersectSet1Set2Str = stringSet1.intersection(stringSet3, 2);
    assertEquals(3, intersectSet1Set2Str.size());
  }
  
  /**
   * testing toString methods.
   */
  @Test
  public void testToString() {
    assertEquals("{ 1, 1, 1, 2, 3 }", intSet3.toString());
    assertEquals("{  }", intSet1.toString());
    assertEquals("{ a, a, apple, banana }", stringSet3.toString());
  }
  
  /**
   * testing exception intersection methods.
   */
  @Test
  public void testIntersectException() {
    try {
      intSet1.intersection(intSet3, -5);
    } catch (IllegalArgumentException e) {
      fail("Number of duplicate cannot be negative!");
    }
  }
  
  /**
   * testing exception union methods.
   */
  @Test
  public void testUnionException() {
    try {
      intSet1.union(intSet3, -5);
    } catch (IllegalArgumentException e) {
      fail("Number of duplicate cannot be negative!");
    }
  }
  
  /**
   * testing exception resetDuplicates methods.
   */
  @Test
  public void testResetDuplicatesException() {
    try {
      intSet3.resetDuplicates(-2);
    } catch (IllegalArgumentException e) {
      fail("Number of duplicate cannot be negative!");
    }
  }
  
}
