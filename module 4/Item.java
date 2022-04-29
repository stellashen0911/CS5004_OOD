package coupon;

/**
 * This class represents an item.
 */
public class Item {
  private String type;
  private int quantity;
  
  /**
   * Constructs an item and initializes it to the given type. 
   * Initialize the item to quantity of 1.
   * @param type the string format that describe the type of the item
   */
  public Item(String type) {
    this.type = type;
    this.quantity = 1;;
  }
  
  /**
   * Get the type of the item.
   * @return the type
   */
  public String getType() {
    return this.type;
  }
  
  /**
   * Get the quantity of the item.
   * @return the quantity number
   */
  public int getQuantity() {
    return this.quantity;
  }
  
  /**
   * Set the type of the item.
   * @param type string format of the new type.
   */
  public void setType(String type) {
    this.type = type;
  }
  
  /**
   * Set the quantity of the item.
   * @param num integer number of the quantity of this item.
   */
  public void setQuantity(int num) {
    this.quantity = num;
  }
  
}
