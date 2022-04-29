import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import hofs.Sentence;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains all the unit tests for sentence class.
 */
public class SentenceTest {
  private Sentence sentenceWithNoInput;
  private Sentence sentenceStartWithString;
  private Sentence sentenceDuplicate;
  private Sentence mergedSentence;
  
  /**
   * setting up the values for different sentences.
   */
  @Before
  public void setup() {
    sentenceWithNoInput = new Sentence();
    mergedSentence = new Sentence();
    String input = "My books are all good books, please read them!";
    sentenceStartWithString = new Sentence(input);
    sentenceDuplicate = new Sentence(sentenceStartWithString);
  }

  /**
   * testing the constructors and the sentence get methods.
   */
  @Test
  public void testConstructor() {
    assertEquals(0, sentenceWithNoInput.getListSize());
    Iterator<String> itr1 = sentenceStartWithString
                          .getList()
                          .iterator();
    
    Iterator<String> itr2 = sentenceDuplicate
                          .getList()
                          .iterator();
    
    while (itr1.hasNext() && itr2.hasNext()) {
      String curr1 = itr1.next();
      String curr2 = itr2.next();
      assertEquals(curr1, curr2);
    }
  }
  
  /**
   * testing the add and toString methods.
   */
  @Test
  public void testAdd() {
    sentenceWithNoInput.add("My");
    sentenceWithNoInput.add("name");
    sentenceWithNoInput.add("is");
    sentenceWithNoInput.add(":");
    sentenceWithNoInput.add("Goddard");
    sentenceWithNoInput.add(".");
    assertEquals(6, sentenceWithNoInput.getListSize());
    assertEquals("My name is: Goddard.", sentenceWithNoInput.toString());
  }
  
  /**
   * testing the add method exceptions.
   */
  @Test
  public void testAddException1() {
    try {
      sentenceWithNoInput.add("My.");
    } catch (IllegalArgumentException e) {
      fail("Must contain only punctuation or word");
    }
  }
  
  /**
   * testing the add method exceptions.
   */
  @Test
  public void testAddException2() {
    try {
      sentenceWithNoInput.add("My name");
    } catch (IllegalArgumentException e) {
      fail("Must contain only one word");
    }
  }
  
  /**
   * testing the add method exceptions.
   */
  @Test
  public void testAddException3() {
    try {
      sentenceWithNoInput.add("hello!?");
    } catch (IllegalArgumentException e) {
      fail("Must contain only one punctuation");
    }
  }
  
  /**
   * testing the get word and punctuation method.
   */
  @Test
  public void testGetNumberOfWordAndPunc() {
    assertEquals((long) 2, sentenceStartWithString.getNumberOfMarks());
    assertEquals((long) 9, sentenceStartWithString.getNumberOfWords());
    assertEquals((long) 2, sentenceDuplicate.getNumberOfMarks());
    assertEquals((long) 9, sentenceDuplicate.getNumberOfWords());
  }

  /**
   * testing the longest method.
   */
  @Test
  public void testLongestWord() {
    assertEquals("please", sentenceStartWithString.longestWord());
  }
  

  /**
   * testing the countWordsWith method.
   */
  @Test
  public void testCountWordsWith() {
    assertEquals((long) 4, sentenceStartWithString.countWordsWith("a"));
    assertEquals((long) 3, sentenceStartWithString.countWordsWith("oo"));
    assertEquals((long) 2, sentenceStartWithString.countWordsWith("book"));
    assertEquals((long) 0, sentenceStartWithString.countWordsWith("bookesa"));
  }
  
  /**
   * testing the merge method.
   */
  @Test
  public void testMerge() {
    mergedSentence = sentenceStartWithString.merge(sentenceDuplicate);
    assertEquals(22, mergedSentence.getListSize());
    assertEquals(4, mergedSentence.getNumberOfMarks());
  }
  
  /**
   * testing the toString method.
   */
  @Test
  public void testToString() {
    mergedSentence = sentenceStartWithString.merge(sentenceDuplicate);
    assertEquals("My books are all good books, please read them!", 
                  sentenceStartWithString.toString());
    assertEquals("My books are all good books, please read "
               + "them! My books are all good books, please read them!", 
                  mergedSentence.toString());
  }
  
  /**
   * testing the to pigLatin and toString method.
   */
  @Test
  public void testPigLatin() {
    String pigLatinVersion = "ymay ooksbay areyay "
        + "allyay oodgay ooksbay, easeplay eadray emthay!";
    assertEquals(pigLatinVersion, 
                 sentenceStartWithString.toPigLatin().toString());
  }
  
}
