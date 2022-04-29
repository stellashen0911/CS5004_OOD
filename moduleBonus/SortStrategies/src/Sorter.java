import java.util.List;

/**
 * This interface represents a sorting strategy.
 */
public interface Sorter {
  /**
   * Sort in-place the provided part of the provided data
   * @param data the data to be sorted
   * @param left the starting index for the part that must be sorted
   * @param right the ending index for the part that must be sorted
   * @param <T> the type of data, which must be Comparable
   */
  <T extends Comparable<T>> void  sort(List<T> data,int left,int right);
}
