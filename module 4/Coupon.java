package coupon;

/**
 * This interface contains all operations that all types of coupons should
 * support.
 */
public interface Coupon {
  /**
   * Returns the description of this coupon in string format.
   * @return the string format description of this coupon
   */
  String getDescription();
  
  /**
   * Returns the item on which the coupon applies.
   * @return the item object which the coupon applies.
   */
  String getItem();
  
  /**
   * Takes a price per quantity and the number of items being bought.
   * and returns the discounted price.
   * @param pricePerQuantity the price per quantity for the item that use this coupon
   * @param num the number of items being bought
   * @return the discounted price after using the coupon.
   */
  double getDiscountedPrice(double pricePerQuantity, int num);
  
  /**
   * Returns if the coupon can be stacked (combined) with another coupon.
   * @return true or false whether this coupon can be stacked with another coupon
   */
  boolean isStackable();
  
  /**
   * Determines whether this coupon can be stacked (combined) with another coupon.
   * If so, returns the combined coupon. 
   * If the coupons cannot be stacked, then the method returns null.
   * @param other the other coupon that will be stacked to this coupon
   * @return the combined coupon if this coupon and other coupon are Stackable, else NULL.
   */
  Coupon stack(Coupon other);
  
}

