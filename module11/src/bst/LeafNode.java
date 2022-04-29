package bst;

/**
 * Implementation of the base case for the BST Tree.
 * 
 * @param <T> the type of the element in the tree.
 */
public class LeafNode<T extends Comparable<T>> extends AbstractTreeNodeBst<T> {
  private boolean isEmpty;
  
  /**
   * Constructor.
   * 
   * @param data the data for this node
   */
  public LeafNode(T data) {
    super(data);
    isEmpty = false;
  }
  
  /**
   * Constructor an empty leafNode.
   */
  public LeafNode() {
    isEmpty = true;
  }
  
  @Override
  public boolean findData(T data) {
    if (isEmpty == true) {
      return false;
    }
    if (this.getData().equals(data)) {
      return true;
    }
    return false;
  }

  @Override
  public TreeNodeBst<T> add(T data) {
    if (this.isEmpty == true) {
      this.data = data;
      this.isEmpty = false;
      return this;
    } else {
      //make the leaf node to normal node with left and right
      TreeNodeBst<T> newNode = new HasLeftRightNode<T>(this.getData());
      newNode.add(data);
      return newNode;
    }
  }

  @Override
  public int getHeight() {
    if (this.isEmpty == true) {
      return 0;
    } else {
      return 1;
    }
  }

  @Override
  public T findMin() {
    if (this.isEmpty == true) {
      return null;
    }
    return this.data;
  }

  @Override
  public T findMax() {
    if (this.isEmpty == true) {
      return null;
    }
    return this.data;
  }

  @Override
  public boolean isLeafNode() {
    return true;
  }

  @Override
  public boolean hasLeft() {
    return false;
  }

  @Override
  public boolean hasRight() {
    return false;
  }
  
  /**
   * return whether this leaf node is empty or not.
   * 
   * @return return whether this leaf node is empty or not
   */
  public boolean isEmpty() {
    return this.isEmpty;
  }

  @Override
  public String preOrderString() {
    if (this.isEmpty == true) {
      return "";
    } else {
      return this.data.toString();
    }
  }

  @Override
  public String inOrderString() {
    if (this.isEmpty == true) {
      return "";
    } else {
      return this.data.toString();
    }
  }

  @Override
  public String postOrderString() {
    if (this.isEmpty == true) {
      return "";
    } else {
      return this.data.toString();
    }
  }
}
