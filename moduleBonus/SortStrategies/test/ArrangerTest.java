import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * We write one test that should be executed for different strategies. We do
 * this by writing one test function in an abstract test class that uses an
 * abstract method to create the strategy. Subclasses then use specific
 * strategies.
 */
public abstract class ArrangerTest {

  protected abstract Sorter getSortingStrategy();

  @Test
  public void testSort() {
    List<Integer> data = new ArrayList<Integer>();
    Arranger<Integer> arranger = new Arranger<Integer>(getSortingStrategy());
    Random r = new Random(100);
    for (int i=0;i<10000;i++) {
      Integer a = r.nextInt();
      data.add(a);
      arranger.add(a);
    }
    Collections.sort(data);
    List<Integer> arrangedData = arranger.arrange();
    assertEquals(data,arrangedData);
  }

  public static class InsertionSorting extends ArrangerTest {
    @Override
    protected Sorter getSortingStrategy() {
      return new InsertionSorter();
    }
  }


  public static class MergeSorting extends ArrangerTest {
    @Override
    protected Sorter getSortingStrategy() {
      return new MergeSorter();
    }
  }

  public static class HybridSorting extends ArrangerTest {
    @Override
    protected Sorter getSortingStrategy() {
      return new HybridMergeSorter();
    }
  }
}