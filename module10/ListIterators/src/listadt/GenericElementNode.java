package listadt;

import java.util.function.Function;

/**
 * This is a non-empty node in a generic list. It contains the object data and
 * the rest of the list.
 * 
 * @param <T> the type of element in the node
 */
class GenericElementNode<T> implements GenericListAdtNode<T> {
  protected T object;
  protected GenericListAdtNode<T> rest;

  /**
   * Constructor for an element node.
   * 
   * @param p    the data in the element node
   * @param rest the rest of the nodes
   */
  public GenericElementNode(T p, GenericListAdtNode<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  @Override
  public GenericListAdtNode<T> addFront(T object) {
    return new GenericElementNode<>(object, this);
  }

  @Override
  public GenericListAdtNode<T> addBack(T object) {
    this.rest = this.rest.addBack(object);
    return this;
  }

  @Override
  public GenericListAdtNode<T> add(int index, T object) {
    if (index == 0) {
      return addFront(object);
    } else {
      this.rest = this.rest.add(index - 1, object);
      return this;
    }
  }

  @Override
  public GenericListAdtNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.object;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public <R> GenericListAdtNode<R> map(Function<T, R> converter) {
    /*
     * Starting from this list of T, the resulting list of type R is an element that
     * contains this data converted to T, followed by the rest of the converted list
     */
    return new GenericElementNode<>(converter.apply(this.object), this.rest.map(converter));
  }

  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (rest.length() > 0) {
      return objstring + " " + rest;
    } else {
      return objstring;
    }
  }

  @Override
  public GenericListAdtNode<T> advance() {
    return this.rest;
  }

  @Override
  public boolean canAdvance() {
    return true;
  }
}
