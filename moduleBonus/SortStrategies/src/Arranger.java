import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of data, entered one at a time. This class
 * offers an operation to return the data in sorted order. It uses a strategy
 * for the sorting operation.
 * @param <T> the type of data managed by this arranger. It must implement
 *           the Comparable interface, so that an ordering is defined.
 */

public class Arranger<T extends Comparable<T>> {
  private List<T> data;

  //strategy for sorting
  private Sorter sorterStrategy;

  /**
   * Construct a new Arranger object that uses the provided sorting strategy.
   * @param sorter the sorting strategy to be used by this arranger
   */
  public Arranger(Sorter sorter) {
    data = new ArrayList<T>();
    this.sorterStrategy = sorter;
  }

  /**
   * Add a new element to the data in this arranger
   * @param d the data to be added
   */
  public void add(T d) {
    data.add(d);
  }

  /**
   * Return a copy of the entered data, sorted in non-descending order.
   * @return a copy of the entered data so far, sorted in non-descending order
   */
  public List<T> arrange() {
    sorterStrategy.sort(data,0,data.size()-1);
    return new ArrayList<T>(data);
  }
}
