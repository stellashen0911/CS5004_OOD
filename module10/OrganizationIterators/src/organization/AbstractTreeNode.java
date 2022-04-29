package organization;

import java.util.function.Predicate;

/**
 * Abstract class for shared code of the nodes of the tree. This class is
 * package-private because it is internal to the implementation to the
 * organization.
 *
 * @param <T> the type of element in the tree
 */
abstract class AbstractTreeNode<T> implements TreeNode<T> {
  protected T data;

  /**
   * Constructor for all of the tree nodes.
   * 
   * @param data the element in the node.
   */
  public AbstractTreeNode(T data) {
    this.data = data;
  }

  @Override
  public int count(Predicate<T> condition) {
    if (condition == null) {
      throw new IllegalArgumentException("condition cannot be null");
    }
    if (condition.test(this.data)) {
      return 1;
    }
    return 0;
  }

  /**
   * Accessor for the data in the node.
   * 
   * @return the element in the node
   */
  public T getData() {
    return data;
  }

  /**
   * Factory method for constructing nodes.
   * 
   * @param <R>  the type of the element for the node
   * @param data the element in the node
   * @return a new node with the element stored in it
   */
  protected abstract <R> TreeNode<R> createNode(R data);
}
