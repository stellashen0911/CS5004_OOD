import java.util.List;

/**
 * This class implements the sorting strategy using the simple insertion sort
 * algorithm.
 */
public class InsertionSorter implements Sorter {

  @Override
  public <T extends Comparable<T>> void sort(List<T> data, int left,
                                             int right) {
    int i,j;

    for (i=left+1;i<=right;i++) {
      T temp = data.get(i);

      j=i-1;
      while ((j>=left) && (temp.compareTo(data.get(j))<0)) {
        data.set(j+1, data.get(j));
        j--;
      }
      data.set(j+1,temp);
    }
  }
}
