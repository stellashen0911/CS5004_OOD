package coupon;

/**
 * An abstract class for different coupon that contains all of the code that is common to
 * all coupons that implement the Coupon interface.
 */
public abstract class AbstractCoupon implements Coupon {
  protected Item item;
  protected boolean stackable;
  
  /**
   * Constructor for a general coupon.
   * @param i the item that the coupon applies
   * @param stackable true or false the couple can be stacked
   */
  public AbstractCoupon(String i, boolean stackable) {
    this.item = new Item(i);
    this.stackable = stackable;
  }
  
  /**
   * Default Constructor for all coupon types. 
   * Since different coupon type need different specific constructors.
   */
  public AbstractCoupon() {}
  
  /**
   * Abstract coupon class has a method of testing equality of any coupon with.
   * a AmountOffCoupon Coupon.
   * By default the equalsAmountOff method of all coupons returns false. 
   * We override this method in the AmountOffCoupon class so that it compares 
   * two AmountOffCoupon coupons
   * (this and other ) more meaningfully.
   * @param other the other coupon to be compared with
   * @return true or false whether this coupon is the same as the other coupon
   */
  protected boolean equalsAmountOff(AmountOffCoupon other) {
    return false; //by default "this" coupon is not equal to a AmountOffCoupon.
  }
  
  /**
   * Abstract coupon class has a method of testing equality of any coupon with.
   * a PercentOffCoupon Coupon.
   * By default the equalsPercentOff method of all coupons returns false. 
   * We override this method in the PercentOffCoupon class so that it compares 
   * two PercentOffCoupon coupons
   * (this and other ) more meaningfully.
   * @param other the other coupon to be compared with
   * @return true or false whether this coupon is the same as the other coupon
   */
  protected boolean equalsPercentOff(PercentOffCoupon other) {
    return false; //by default "this" coupon is not equal to a PercentOffCoupon.
  }
  
  /**
   * Abstract coupon class has a method of testing equality of any coupon with.
   * a BuySomeGetSomeFree Coupon.
   * By default the equalsBuyTwoGetOne method of all coupons returns false. 
   * We override this method in the BuySomeGetSomeFree class so that it compares
   * two BuySomeGetSomeFree coupons
   * (this and other ) more meaningfully.
   * @param other the other coupon to be compared with
   * @return true or false whether this coupon is the same as the other coupon
   */
  protected boolean equalsBuySomeGetSome(BuySomeGetSomeFreeCoupon other) {
    return false; //by default "this" coupon is not equal to a BuySomeGetSomeFree coupon.
  }
  
  /**
   * Abstract method getDescription that need to be implemented by different coupon types.
   * Returns the description of this coupon in string format.
   * @return the string format description of this coupon
   */
  public abstract String getDescription();
  
  /**
   * Abstract method getDiscountedPrice that need to be implemented by different. 
   * coupon types.
   * Takes a price per quantity and the number of items being bought.
   * and returns the discounted price.
   * @param pricePerQuantity the price per quantity for the item that use this coupon
   * @param num the number of items being bought
   * @return the discounted price after using the coupon.
   */
  public abstract double getDiscountedPrice(double pricePerQuantity, int num);
  
  /**
   * Abstract method getDiscountedPrice that need to be implemented by different. 
   * coupon types.
   * Determines whether this coupon can be stacked (combined) with another coupon.
   * If so, returns the combined coupon. 
   * If the coupons cannot be stacked, then the method returns null.
   * @param other the other coupon that will be stacked to this coupon
   * @return the combined coupon if this coupon and other coupon are stackable, else NULL.
   */
  public abstract Coupon stack(Coupon other);
  
  @Override
  public boolean isStackable() {
    return this.stackable;
  }
  
  @Override
  public String getItem() {
    return this.item.getType();
  }
  
}
