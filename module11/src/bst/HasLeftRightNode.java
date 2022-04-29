package bst;

/**
 * Representation of an internal node in the BST tree.
 * This node is allow to have left and right child nodes.
 *
 * @param <T> the type of element in the tree.
 */
public class HasLeftRightNode<T extends Comparable<T>> extends AbstractTreeNodeBst<T> {
  private TreeNodeBst<T> left;
  private TreeNodeBst<T> right;
  
  /**
   * Constructor.
   * 
   * @param data the data for this node
   */
  public HasLeftRightNode(T data) {
    super(data);
    // initialize the two empty leaf node
    this.left = new LeafNode<T>();
    this.right = new LeafNode<T>();
  }
  
  /**
   * Get the left node of the current node.
   * 
   * @return the left node of the current node.
   */
  public TreeNodeBst<T> getLeftNode() {
    return this.left;
  }
  
  /**
   * Get the right node of the current node.
   * 
   * @return the right node of the current node.
   */
  public TreeNodeBst<T> getRightNode() {
    return this.right;
  }

  @Override
  public boolean findData(T data) {
    if (this.getData().equals(data)) {
      return true;
    }
    return this.left.findData(data) || this.right.findData(data);
  }

  @Override
  public TreeNodeBst<T> add(T data) {
    if (this.greaterThan(data)) {
      this.left = this.left.add(data);
      return this;
    } else {
      this.right = this.right.add(data);
      return this;
    }
  }

  @Override
  public int getHeight() {
    int heightOfLeft = this.left.getHeight();
    int heightOfRight = this.right.getHeight();
    if (heightOfLeft >= heightOfRight) {
      return heightOfLeft + 1;
    } else {
      return heightOfRight + 1;
    }
  }

  @Override
  public T findMin() {
    TreeNodeBst<T> current = this;
    while (current.hasLeft() == true) {
      HasLeftRightNode<T> tempCurr = (HasLeftRightNode<T>) current;
      current = tempCurr.getLeftNode();
    }
    return current.getData();
  }

  @Override
  public T findMax() {
    TreeNodeBst<T> current = this;
    while (current.hasRight() == true) {
      HasLeftRightNode<T> tempCurr = (HasLeftRightNode<T>) current;
      current = tempCurr.getRightNode();
    }
    return current.getData();
  }

  @Override
  public boolean isLeafNode() {
    return false;
  }

  @Override
  public boolean hasLeft() {
    if (this.left.isLeafNode()) {
      LeafNode<T> left = (LeafNode<T>) this.left;
      if (left.isEmpty() == true) {
        return false;
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  @Override
  public boolean hasRight() {
    if (this.right.isLeafNode()) {
      LeafNode<T> right = (LeafNode<T>) this.right;
      if (right.isEmpty() == true) {
        return false;
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  @Override
  public String preOrderString() {
    String empty = "";
    String result = this.data.toString().trim();
    String leftSubString = this.left.preOrderString().trim();
    if (leftSubString.equals(empty) == false) {
      result = result + " " + leftSubString;
    }
    String rightSubString = this.right.preOrderString().trim();
    if (rightSubString.equals(empty) == false) {
      result = result + " " + rightSubString;
    }
    result = result.trim();
    return result;
  }

  @Override
  public String inOrderString() {
    String result = this.left.inOrderString().trim() + " "
          + this.data.toString().trim() + " " 
          + this.right.inOrderString().trim();
    result = result.trim();
    return result;
  }

  @Override
  public String postOrderString() {
    String empty = "";
    String result = "";
    String leftSubString = this.left.postOrderString().trim();
    if (leftSubString.equals(empty) == false) {
      result = result + leftSubString + " ";
    }
    String rightSubString = this.right.postOrderString().trim();
    if (rightSubString.equals(empty) == false) {
      result = result + rightSubString + " ";
    }
    result += this.data.toString().trim();
    result = result.trim();
    return result;
  }
  
}
