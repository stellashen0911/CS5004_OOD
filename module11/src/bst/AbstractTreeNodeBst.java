package bst;

/**
 * Implementation of an abstract tree node bst to share code.
 * 
 * @param <T> the type of element in the node
 */
public abstract class AbstractTreeNodeBst<T extends Comparable<T>> implements TreeNodeBst<T> {
  //represent the data of the current node
  protected T data;
  
  /**
   * Constructor.
   * 
   * @param data the data for this node
   */
  public AbstractTreeNodeBst(T data) {
    this.data = data;
  }
  
  /**
   * Constructor without parameters.
   */
  public AbstractTreeNodeBst() {}
  
  
  @Override
  public T getData() {
    return this.data;
  }
  
  @Override
  public void setNodeData(T data) {
    this.data = data;
  }
  
  @Override
  public boolean greaterThan(T data) {
    if (this.getData().compareTo(data) > 0) {
      // this means this node has greater data than comp node
      return true;
    }
    return false;
  }
  
  @Override
  public boolean isSameNodeData(T data) {
    if (this.getData().equals(data)) {
      return true;
    }
    return false;
  }
  
}
