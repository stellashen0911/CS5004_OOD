package set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representation that implements the DuplicatingSet interface.
 * and allows up to N duplicates that are in sorted order.
 */
public class ConcreteDuplicatingSet<E extends Comparable<E>> implements DuplicatingSet<E> {
  private int duplicateNum;
  private List<E> setList;
  private Map<E, Integer> duplicateMap;
  
  /**
   * Constructs a ConcreteDuplicatingSet with no parameters.
   */
  public ConcreteDuplicatingSet() {
    this.duplicateNum = 1;
    this.setList = new ArrayList<E>();
    duplicateMap = new HashMap<E, Integer>();
  }
  
  /**
   * Constructs a ConcreteDuplicatingSet with parameter num.
   * Specify the number of duplicate element within the set.
   * @param num the number of duplicate element within the set.
   */
  public ConcreteDuplicatingSet(int num) throws IllegalArgumentException {
    /* number of duplicate is negative throw exception. */
    if (num < 0) {
      throw new IllegalArgumentException("Number of duplicate cannot be negative!");
    }
    this.duplicateNum = num;
    this.setList = new ArrayList<E>();
    duplicateMap = new HashMap<E, Integer>();
  }
  
  /**
   * Constructs a ConcreteDuplicatingSet with parameter num.
   * Specify the number of duplicate element within the set.
   * And a List of elements that are present in the 
   * set upon construction
   * @param num the number of duplicate element within the set.
   * @param presentSet a List of elements that are present in the set upon construction
   */
  public ConcreteDuplicatingSet(int num, List<E> presentSet) throws IllegalArgumentException {
    /* number of duplicate is negative throw exception. */
    if (num < 0) {
      throw new IllegalArgumentException("Number of duplicate cannot be negative!");
    }
    
    this.duplicateNum = num;
    this.setList = new ArrayList<E>();
    this.duplicateMap = new HashMap<E, Integer>();
    
    for (int i = 0; i < presentSet.size(); i++) {
      E curr = presentSet.get(i);
      //if the map does not contain the element, we add the element to the map
      //the number of this element is set to 1 as default.
      if (duplicateMap.containsKey(curr) == false) {
        duplicateMap.put(curr, 1);
        setList.add(curr);
      } else {
        //if the map already contains the element, this means we have duplicate
        //then we need to check how many duplicates we have now
        int currDuplicateNum = (int) duplicateMap.get(curr);
        if (currDuplicateNum >= this.duplicateNum) {
          continue;
        } else {
          //case when the number of duplicates less than num, we add element and 
          //increase the number of duplicate item.
          duplicateMap.put(curr, currDuplicateNum + 1);
          setList.add(curr);
        }
      }
    }
  }
  
  /**
   * Get the number of duplicates allowed in the set.
   * @return integer number of duplicates allowed in the set.
   */
  public int getDuplicate() {
    return this.duplicateNum;
  }
  
  /**
   * Get the array list of the set.
   * @return the list of object E.
   */
  public List<E> getListSet() {
    return this.setList;
  }
  
  /**
   * Get the map of the set.
   * @return the map of the set with key of object E and value int.
   */
  public Map<E, Integer> getSetMap() {
    return this.duplicateMap;
  }
  
  @Override
  public boolean add(E e) {
    //first we need to check whether this element is in the set or not
    //if this element is not in the set, we add this element, and set the 
    //default frequency to 1
    if (duplicateMap.containsKey(e) == false) {
      duplicateMap.put(e, 1);
      setList.add(e);
      return true;
    } else {
      int currDuplicateNum = (int) duplicateMap.get(e);
      //case when the number of duplicates exceed num, we don't add new element
      if (currDuplicateNum >= this.getDuplicate()) {
        return false;
      } else {
        //case when the number of duplicates less than num, we add element and 
        //increase the current number.
        duplicateMap.put(e, currDuplicateNum + 1);
        setList.add(e);
        return true;
      }
    }
  }
  
  @Override
  public int addAll(List<E> list) {
    int numAdd = 0;
    
    for (int i = 0; i < list.size(); i++) {
      E curr = list.get(i);
      //if the map does not contain the element, we add the element to the map
      //the number of this element is set to 1 as default.
      if (duplicateMap.containsKey(curr) == false) {
        duplicateMap.put(curr, 1);
        setList.add(curr);
        numAdd++;
      } else {
        //if the map already contains the element, this means we have duplicate
        //then we need to check how many duplicates we have now
        int currDuplicateNum = (int) duplicateMap.get(curr);
        //case when the number of duplicates exceed num, we don't add new element
        if (currDuplicateNum >= this.getDuplicate()) {
          continue;
        } else {
          //case when the number of duplicates less than num, we add element and 
          //increase the current number.
          duplicateMap.put(curr, currDuplicateNum + 1);
          setList.add(curr);
          numAdd++;
        }
      }
    }
    return numAdd;
  }
  
  @Override
  public void clear() {
    this.duplicateMap.clear();
    this.setList.clear();
    return;
  }
  
  @Override
  public int contains(E e) {
    //if the element is not in the set, return 0.
    if (duplicateMap.containsKey(e) == false) {
      return 0;
    } else {
      int currDuplicateNum = (int) duplicateMap.get(e);
      return currDuplicateNum;
    }
  }
  
  @Override
  public boolean isEmpty() {
    return this.setList.isEmpty();
  }
  
  @Override
  public boolean remove(E e) {
    //If the set does not contain this element, return false.
    if (duplicateMap.containsKey(e) == false) {
      return false;
    } else {
      int currDuplicateNum = (int) duplicateMap.get(e);
      //case when the number of element is only one, we just delete this key and value
      if (currDuplicateNum == 1) {
        this.duplicateMap.remove(e);
        this.setList.remove(e);
        return true;
      } else {
        //case when the number of element is less than num, but greater than 1
        //we delete element by decrease duplicate
        //remove one element of e in the arrayList.
        for (int i = 0; i < this.setList.size(); i++) {
          E curr = setList.get(i);
          if (curr.equals(e)) {
            setList.remove(i);
            break;
          }
        }
        duplicateMap.put(e, currDuplicateNum - 1);
        return true;
      }
    }
  }
  
  @Override
  public int removeAll(E e) {
    //If the set does not contain this element, return 0.
    if (duplicateMap.containsKey(e) == false) {
      return 0;
    } else {
      int currDuplicateNum = (int) duplicateMap.get(e);
      int result = currDuplicateNum;
      //the number of element we need to remove.
      while (currDuplicateNum > 0) {
        this.setList.remove(e);
        currDuplicateNum--;
      }
      if (duplicateMap.containsKey(e) == true) {
        this.duplicateMap.remove(e);
      }
      return result;
    }
  }
  
  @Override
  public void resetDuplicates(int duplicates) throws IllegalArgumentException {
    /* number of duplicate is negative throw exception. */
    if (duplicates < 0) {
      throw new IllegalArgumentException("Number of duplicate cannot be negative!");
    }
    
    //if the new duplicates number is greater than the previous num
    //we don't need to delete any existing element
    if (duplicates >= this.getDuplicate()) {
      return;
    } else {
      for (int i = 0; i < this.setList.size(); i++) {
        E curr = this.setList.get(i);
        if (this.duplicateMap.containsKey(curr)) {
          int currDuplicateNum = (int) duplicateMap.get(curr);
          if (currDuplicateNum > duplicates) {
            duplicateMap.put(curr, duplicates);
          }
        }
      }
    }
    
    //update the array list
    this.setList.clear();
    for (E key : duplicateMap.keySet()) {
      int occurance = (int) duplicateMap.get(key);
      for (int i = 0; i < occurance; i++) {
        this.setList.add(key);
      }
    }
  }
  
  @Override
  public int size() {
    return this.setList.size();
  }
  
  @Override
  public DuplicatingSet<E> union(DuplicatingSet<E> other, 
        int duplicates) throws IllegalArgumentException {
    /* number of duplicate is negative throw exception. */
    if (duplicates < 0) {
      throw new IllegalArgumentException("Number of duplicate cannot be negative!");
    }
    DuplicatingSet<E> newSet = new ConcreteDuplicatingSet<E>(duplicates);
    newSet.addAll(this.setList);
    ConcreteDuplicatingSet<E> otherConcrete = (ConcreteDuplicatingSet<E>) other;
    newSet.addAll(otherConcrete.getListSet());
    return newSet;
  }
  
  @Override
  public DuplicatingSet<E> intersection(DuplicatingSet<E> other, 
      int duplicates) throws IllegalArgumentException {
    /* number of duplicate is negative throw exception. */
    if (duplicates < 0) {
      throw new IllegalArgumentException("Number of duplicate cannot be negative!");
    }
    DuplicatingSet<E> newSet = new ConcreteDuplicatingSet<E>(duplicates);
    ConcreteDuplicatingSet<E> otherConcrete = (ConcreteDuplicatingSet<E>) other;
    List<E> thisSet = this.getListSet();
    
    for (int i = 0; i < thisSet.size(); i++) {
      E curr = thisSet.get(i);
      if (otherConcrete.getSetMap().containsKey(curr)) {
        otherConcrete.remove(curr);
        newSet.add(curr);
      }
    }
    return newSet;
  }
  
  /**
   * Output the current state of the set as a string.
   * This string begins with a { followed by a space and end with a space followed by } 
   * and it contains all of the elements in the set in a comma delimited list.
   * @return a formated string of the elements in the set.
   */
  public String toString() {
    sortList(this.setList);
    String output = "{ ";
    if (this.setList.size() <= 0) {
      output += " }";
      return output;
    }
    for (int i = 0; i < this.setList.size() - 1; i++) {
      output += this.setList.get(i).toString() + ", ";
    }
    output += this.setList.get(this.setList.size() - 1).toString();
    output += " }";
    return output;
  }
  
  /**
   * Helper function to sort the set list in ascending order.
   * @param list the set list that need to be sorted
   * @param <E> the comparable object
   */
  public static <E extends Comparable<E>> void sortList(List<E> list) {
    for (int i = 0; i < list.size(); i++) {
      int minIndex = i;
      for (int j = i + 1; j < list.size(); j++) {
        if (((list.get(j)).compareTo(list.get(minIndex))) < 0) {
          minIndex = j;
        }
      }
      Collections.swap(list, i, minIndex);
    }
  }
 
}
