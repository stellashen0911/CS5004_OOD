import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class implements the sorting strategy using a top-down recursive
 * implementation of the merge sort algorithm.
 */
public class MergeSorter implements Sorter {

  @Override
  public <T extends Comparable<T>> void sort(List<T> data,int left,int right) {
    List<T> temp = new ArrayList(data);
    sort(data,temp,left,right);
  }

  protected <T extends Comparable<T>> void sort(List<T> data,List<T> temp,
                                              int left,int right) {
    if (left<right) {
      int mid = (left+right)/2;
      sort(data,temp,left,mid);
      sort(data,temp,mid+1,right);
      merge(data,temp,left,mid,right);
    }
  }

  private <T extends Comparable<T>> void merge(List<T> data,List<T> temp,
                                               int left,int mid,int right) {
    int i=left;
    int j=mid+1;
    int k=left;

    while ((i<=mid) && (j<=right)) {
      if (data.get(i).compareTo(data.get(j))<0) {
        temp.set(k,data.get(i));
        i++;
      }
      else {
        temp.set(k,data.get(j));
        j++;
      }
      k++;
    }

    for (;i<=mid;i++,k++) {
      temp.set(k,data.get(i));
    }

    for (;j<=right;j++,k++) {
      temp.set(k,data.get(j));
    }

    for (i=left;i<=right;i++) {
      data.set(i,temp.get(i));
    }
  }
}
