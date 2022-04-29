package set;

import java.util.List;

/**
 * A set representation that allows up to N duplicates that are in sorted order.
 */
public interface DuplicatingSet<E> {

  /**
   * Add an element to the set. Elements that are added that exceed the number of
   * duplicates allowed cannot be added to the set.
   * 
   * @param e the element to add
   * @return true if the element was added, false otherwise
   */
  boolean add(E e);

  /**
   * Add all the elements of the specified list. Elements that are added that exceed the number of
   * duplicates allowed are discarded.
   * 
   * @param list the list of elements to add
   * @return the number of elements that were successfully to this set
   */
  int addAll(List<E> list);

  /**
   * Removes all of the elements from this set.
   */
  void clear();

  /**
   * Determine the number of elements that exist in this set.
   * 
   * @param e the element
   * @return the number of times the element exists in this set
   */
  int contains(E e);

  /**
   * Determine if this set is empty.
   * 
   * @return true if the set is empty, false otherwise
   */
  boolean isEmpty();

  /**
   * Remove one copy of the specified element from this set if it is present.
   * 
   * @param e the element to remove
   * @return true if the element was removed, false otherwise
   */
  boolean remove(E e);

  /**
   * Remove all copies of the specified element from this set, if it is present.
   * 
   * @param e the element to remove
   * @return the number of elements that were removed
   */
  int removeAll(E e);

  /**
   * Reset the number of duplicates allowed in this set. When the new number of
   * duplicates is smaller than the previous value, any excess elements in the
   * discarded.
   * 
   * @param duplicates the new number of duplicates allowed by the set
   * @throws IllegalArgumentException if duplicates is negative
   */
  void resetDuplicates(int duplicates);

  /**
   * Returns the number of elements in this set.
   * 
   * @return the number of elements in this set
   */
  int size();

  /**
   * Compute the union of this set and the parameter set. The union is defined as
   * the set of all elements that belong to either set (or both). It discards
   * elements either set that are beyond the number of allowed duplicates in the
   * resulting set.
   * 
   * @param other      the other set
   * @param duplicates the number of duplicates allowed in the resulting set
   * @return a new set containing the union of this set and the parameter set
   * @throws IllegalArgumentException if duplicates is negative
   */
  DuplicatingSet<E> union(DuplicatingSet<E> other, int duplicates);

  /**
   * Compute the intersection of this set and the parameter set. The intersection
   * is defined as the set of all elements that belong to both sets. It discards
   * elements both set that are beyond the number of allowed duplicates in the
   * resulting set.
   * 
   * @param other      the other set
   * @param duplicates the number of duplicates allowed in the resulting set
   * @return a new set containing the union of this set and the parameter set
   * @throws IllegalArgumentException if duplicates is negative
   */
  DuplicatingSet<E> intersection(DuplicatingSet<E> other, int duplicates);
}
