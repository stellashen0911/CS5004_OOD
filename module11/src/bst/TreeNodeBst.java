package bst;

/**
 * This interface represents all the operations that BST tree node should
 * support. It can store any type of data that implements the Comparable
 * interface.
 * 
 * @param <T> the type of element in the tree node of BST.
 */
public interface TreeNodeBst<T extends Comparable<T>> {

  /**
   * Get the current node data.
   * 
   * @return the data of the current node;
   */
  T getData();
  
  /**
   * Set the data value for the current node.
   * 
   * @param data the data for the current node.
   */
  void setNodeData(T data);
  
  /**
   * Determine whether this node is greater than the comp node.
   * If this current node is greater than the comp node, return true;
   * this means the comp node should be on the left side of this node;
   * If this current node is less than the comp node, return false;
   * this means the comp node should be on the right side of this node.
   * 
   * @param data data to be compared with this node.
   * @return true if this node is greater than the comp, false otherwise.
   */
  boolean greaterThan(T data);
  
  /**
   * Determine whether this node is the same as the comp node.
   * 
   * @param data data to be compared with this node.
   * @return true if this node has the same value of comp node, false otherwise.
   */
  boolean isSameNodeData(T data);
  
  /**
   * Determine whether this data is already exist in this BST tree.
   * 
   * @param data the target data we want to find.
   * @return true if this data node exist, false otherwise.
   */
  boolean findData(T data);
  
  /**
   * Determine whether this node is a leaf node or not.
   * 
   * @return true if this data node is a leaf node, false otherwise.
   */
  boolean isLeafNode();
  
  /**
   * Determine whether this node has a left sub node.
   * 
   * @return true if this data node has a left sub node. false otherwise.
   */
  boolean hasLeft();
  
  /**
   * Determine whether this node has a right sub node.
   * 
   * @return true if this data node has a right sub node, false otherwise.
   */
  boolean hasRight();
  
  /**
   * Add the data into the bst.
   * 
   * @param data the data we need to add to the existing tree
   * @return return the treeNode after the add opeartion
   */
  TreeNodeBst<T> add(T data);
  
  /**
   * Get the height of the current node.
   * 
   * @return the height of the tree node
   */
  int getHeight();
  
  /**
   * Get the minimum value of the bst.
   * 
   * @return the min value of the bst.
   */
  T findMin();
  
  /**
   * Get the maximum value of the bst.
   * 
   * @return the max value of the bst.
   */
  T findMax();
  
  /**
   * Returns a string that present all the data in the tree in post-order. sorted
   * in ascending order. The string is formatted as d1 d2 ... dn
   * 
   * @return a string containing the post-order traversal
   */
  String preOrderString();
  
  /**
   * Returns a string that present all the data in the tree in in-order. sorted
   * in ascending order. The string is formatted as d1 d2 ... dn
   * 
   * @return a string containing the inorder traversal
   */
  String inOrderString();
  
  /**
   * Returns a string that present all the data in the tree in post-order. sorted
   * in ascending order. The string is formatted as d1 d2 ... dn
   * 
   * @return a string containing the post-order traversal
   */
  String postOrderString();  
}
