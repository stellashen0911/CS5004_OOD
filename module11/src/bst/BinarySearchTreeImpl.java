package bst;

/**
 * This class represents all the operations implementation 
 * that a binary search tree should support. 
 * It can store any type of data that implements the Comparable
 * interface
 * 
 * @param <T> the type of element in the tree.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {
  private TreeNodeBst<T> root;
  private int size;
   
  /**
   * Constructor with data value of the root node.
   * 
   * @param data the data for this node
   */
  public BinarySearchTreeImpl(T data) {
    root = new LeafNode<T>(data);
    this.size = 1;
  }
   
  /**
   * Constructor without parameters.
   */
  public BinarySearchTreeImpl() {
    root = new LeafNode<T>();
    this.size = 0;
  }

  @Override
  public void add(T data) {
    //This is ignored if the data item is already present
    if (root.findData(data) == true) {
      return;
    }
    root = root.add(data);
    this.size += 1;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public int height() {
    return this.root.getHeight();
  }

  @Override
  public boolean present(T data) {
    return this.root.findData(data);
  }

  @Override
  public T minimum() {
    //when the bst is empty, there is no minimum value 
    if (this.size == 0) {
      return null;
    }
    return root.findMin();
  }

  @Override
  public T maximum() {
    //when the bst is empty, there is no minimum value 
    if (this.size == 0) {
      return null;
    }
    return root.findMax();
  }

  @Override
  public String preOrder() {
    return String.format("[" + this.root.preOrderString() + "]");
  }

  @Override
  public String inOrder() {
    return String.format("[" + this.root.inOrderString() + "]");
  }

  @Override
  public String postOrder() {
    return String.format("[" + this.root.postOrderString() + "]");
  }
  
  /**
   * Create and return a string that can be used to print this bst.
   * 
   * @return a string representing this bst.
   */
  public String toString() {
    return this.inOrder();
  }
  
}
