package permutations;

import java.lang.String;
import java.util.NoSuchElementException;

/**
 * An implicit immutable collection of all permutations of a string with an.
 * iterator over the permutations。
 */
public class Permutations implements BackAndForthIterator<String> {
  private int startLength;
  private int[] index;
  private int totalLength;
  private char[] array;

  /**
   * Creates an implicit Iterable collection of all permutations of a string.
   * @param input  String to be permuted
   * @throws IllegalArgumentException if the string is empty
   */
  public Permutations(String input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("the string must be valid");
    }
    this.array = input.toCharArray();
    this.totalLength = input.length();
    this.index = new int[totalLength];
    this.startLength = 1;
    for (int i = 0; i < startLength; i++) {
      index[i] = i;
    }
  }
  
  /**
   * Creates an implicit Iterable collection of all permutations of a string.
   * @param input  String to be permuted.
   * @param start the start length of the permutation.
   * @throws IllegalArgumentException if the string is empty, or start invalid.
   */
  public Permutations(String input, int start) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("input string must be valid");
    }
    if (start > input.length()) {
      throw new IllegalArgumentException("the start length must be less "
          + "than or equal to the length");
    }
    if (start <= 0) {
      throw new IllegalArgumentException("the start length must be greater than 0");
    }
    this.array = input.toCharArray();
    this.totalLength = input.length();
    this.index = new int[totalLength];
    this.startLength = start;
      
    for (int i = 0; i < startLength; i++) {
      index[i] = i;
    }
  }
    
  @Override
  public boolean hasNext() {
    return index != null;
  }

  @Override
  public String next() throws NoSuchElementException {
    if (index == null) {
      throw new NoSuchElementException();
    }
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    String output = "";
    for (int i = 0; i < startLength; i++) {
      output += Character.toString(array[index[i]]);
    }
    updateNext();
    return output;
  }
    
  /**
   * A helper function to update the next index list.
   */
  private void updateNext() {
    //if the index array is incremental
    int last = startLength - 1;
    if (index[last] < totalLength - 1) {
      index[last] += 1;
    } else if (index[last] == totalLength - 1) {
      if (this.full() == true) {
        startLength++;
        if (startLength > totalLength) {
          index = null;
          return;
        }
        for (int i = 0; i < startLength; i++) {
          index[i] = i;
        }
      } else {
        int incrementIndex = last;
        int largeNumber = totalLength - 1;
        while (incrementIndex >= 0) {
          if (index[incrementIndex] == largeNumber) {
            incrementIndex--;
            largeNumber--;
          } else {
            break;
          }
        }
        index[incrementIndex]++;
        for (int i = incrementIndex + 1; i <= last; i++) {
          index[i] = index[i - 1] + 1;
        }
      }
    }
  }
  
  /**
   * A helper function to decide whether the current list is the last.
   * of the given length of string.
   * @return true if the list index is the last of the given length of string.
   */
  private boolean full() {
    int target = totalLength - 1;
    for (int i = startLength - 1; i >= 0; i--) {
      if (index[i] == target) {
        target--;
      } else {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public String previous() throws NoSuchElementException {
    if (index == null) {
      throw new NoSuchElementException();
    }
    if (!hasPrevious()) {
      throw new NoSuchElementException();
    }
    updatePrevious();
    String output = "";
    for (int i = 0; i < startLength; i++) {
      output += Character.toString(array[index[i]]);
    }
    return output;
  }

  /**
   * A helper function to update the previous index list.
   */
  private void updatePrevious() {
    if (isBegin() && hasPrevious()) {
      startLength--;
      int target = totalLength - 1;
      for (int i = startLength - 1; i >= 0; i--) {
        index[i] = target;
        target--;
      }
    } else {
      int last = startLength - 1;
      if (isIncrement()) {
        index[0]--;
        int target = totalLength - 1;
        for (int i = last; i > 0; i--) {
          index[i] = target;
          target--;
        }
      } else {
        if (startLength == 1) {
          index[last]--;
        } else {
          if (index[last] - 1 <= index[last - 1]) {
            index[last - 1]--;
            index[last] = totalLength - 1;
          } else {
            index[last]--;
          }
        }
      } 
    }
  }
  
  /**
   * A helper function to decide whether the current list is increment.
   * of the given length of string.
   * @return true if the list index is increment given length of string.
   */
  private boolean isIncrement() {
    if (startLength == 1) {
      return false;
    }
    for (int i = 0; i < startLength - 1; i++) {
      if (index[i] != index[i + 1] - 1) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * A helper function to decide whether the current list is the beginning.
   * of the given length of string.
   * @return true if the list index is the beginning of the given length of string.
   */
  private boolean isBegin() {
    for (int i = 0; i < startLength; i++) {
      if (index[i] != i) {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public boolean hasPrevious() {
    if (startLength < 1 || (startLength == 1 && index[0] == 0)) {
      return false;
    }
    if (index == null) {
      return false;
    }
    return true;
  }
  
}
