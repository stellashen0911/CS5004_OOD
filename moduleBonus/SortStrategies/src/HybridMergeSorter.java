import java.util.List;

/**
 * This class implements the sorting strategy using a hybrid sort.
 *
 * For small list sizes, the merge sort algorithm's performance is worse than
 * insertion sort. Therefore, a hybrid merge sort algorithm that defaults to
 * insertion sort for small sub-lists is more efficient than the pure merge
 * sort algorithm.
 *
 * This sorting strategy defaults to insertion sort when the size of the list
 * is below 30 (chosen arbitrarily).
 *
 * Design-wise, we implement this hybrid as a composite strategy. We override
 * the pure merge sort.
 */
public class HybridMergeSorter extends MergeSorter{

  private Sorter fallbackStrategy;

  public HybridMergeSorter() {
    this.fallbackStrategy = new InsertionSorter();
  }

  @Override
  protected <T extends Comparable<T>> void sort(List<T> data, List<T> temp,
                                                int left, int right) {
    if ((right-left+1)<30) {
      //if size is small enough, default to insertion sort
      fallbackStrategy.sort(data,left,right);
    }
    else {
      super.sort(data,temp,left,right);
    }
  }
}
