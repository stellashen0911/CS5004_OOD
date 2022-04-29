import static org.junit.Assert.assertEquals;

import listadt.ListAdt;
import listadt.ListAdtImpl;
import org.junit.Before;
import org.junit.Test;

/** Tests for the list iterator. */
public class ListAdtImplTest {

  private ListAdt<String> stringList;

  /** Setting up the objects for the tests. */
  @Before
  public void setup() {
    stringList = new ListAdtImpl<String>();
  }

  /** Testing a list of strings. */
  @Test
  public void testStringList() {
    stringList.addFront("won");
    stringList.addFront("Patriots");
    stringList.addBack("Super");
    stringList.addBack("Bowl");
    stringList.add(2, "the");
    assertEquals("(Patriots won the Super Bowl)", stringList.toString());
    assertEquals(5, stringList.getSize());
    assertEquals("Super", stringList.get(3));

    stringList.remove("Patriots");
    stringList.addFront("Falcons");
    stringList.add(1, "did");
    stringList.add(2, "not");
    stringList.remove("won");
    stringList.add(3, "win");
    assertEquals("(Falcons did not win the Super Bowl)", stringList.toString());
    assertEquals(7, stringList.getSize());

  }

  /** Testing the map method. */
  @Test
  public void testMap() {
    // convert the list of strings above to a list that contains the length of
    // each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String[] words = sentence.split("\\s+");
    for (String w : words) {
      stringList.addBack(w);
    }

    ListAdt<Integer> wordLengths = stringList.map(s -> s.length());
    assertEquals("The mapped list's length does not match the original list!", stringList.getSize(),
        wordLengths.getSize());

    for (int i = 0; i < words.length; i++) {
      assertEquals(words[i].length(), (int) wordLengths.get(i));
    }

  }

  /** Testing iterable. */
  @Test
  public void testIterable() {
    // convert the list of strings above to a list that contains the length of
    // each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String[] words = sentence.split("\\s+");
    for (String w : words) {
      stringList.addBack(w);
    }
    StringBuilder sb = new StringBuilder();
    for (String s : stringList) {
      sb.append(s + " ");
    }
    assertEquals("The quick brown fox jumps over the lazy dog ", sb.toString());
  }
}