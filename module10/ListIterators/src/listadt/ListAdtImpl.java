package listadt;

import java.util.Iterator;
import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements the
 * listadt.ListADT interface. It represents a singly-linked list of data
 * elements, where every data node stores one piece of data and a reference to
 * the advance item in the list. The list ends with a sentinel empty node.
 * 
 * @param <T> the type of element in this list
 */
public class ListAdtImpl<T> implements ListAdt<T> {
  private GenericListAdtNode<T> head;

  /** Default constructor. */
  public ListAdtImpl() {
    head = new GenericEmptyNode<>();
  }

  /**
   * A protected constructor that is used internally (see map).
   * 
   * @param head the header for this list
   */
  protected ListAdtImpl(GenericListAdtNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  @Override
  public int getSize() {
    return head.count();
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }
  }

  @Override
  public <R> ListAdt<R> map(Function<T, R> converter) {
    if (converter == null) {
      throw new IllegalArgumentException("converter cannot be null");
    }
    return new ListAdtImpl<>(head.map(converter));
  }

  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }

  @Override
  public Iterator<T> iterator() {
    return new ListAdtIterator<>(head);
  }

  /**
   * Implementation of the iterator for this list.
   *
   * @param <E> the type of element in this list
   */
  private class ListAdtIterator<E> implements Iterator<E> {

    private GenericListAdtNode<E> current;

    /**
     * Constructor for the iterator.
     * 
     * @param head the starting point of the iteration
     */
    private ListAdtIterator(GenericListAdtNode<E> head) {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return current.canAdvance();
    }

    @Override
    public E next() {
      E element = current.get(0);
      current = current.advance();
      return element;
    }
  }
}
