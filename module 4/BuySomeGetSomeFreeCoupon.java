package coupon;

/**
 * This class represents a BuySomeGetSomeFreeCoupon. 
 * It defines all the operations mandated by
 * the Coupon interface.
 */
public class BuySomeGetSomeFreeCoupon extends AbstractCoupon {
  private int minimumQuantity;
  private int numberOfFree;
  
  /**
   * Constructor for a BuySomeGetSomeFree coupon.
   * @param item the item that the coupon applies
   * @param stackable true or false the couple can be stacked
   * @param minQuantity The minimum quantity for this coupon to be useful
   * @param numFree the number of free items for this coupon
   */
  public BuySomeGetSomeFreeCoupon(String item, 
                                  boolean stackable, 
                                  int minQuantity, 
                                  int numFree) throws IllegalArgumentException {
    /* the percentage off cannot be negative number. */
    if (minQuantity < 0 || numFree < 0) {
      throw new IllegalArgumentException("Minimum quantity off and number free "
                                          + "should be positive number");
    }
    this.item = new Item(item);
    this.stackable = stackable;
    this.minimumQuantity = minQuantity;
    this.numberOfFree = numFree;
  }
  
  /**
   * Returns the minimum quantity for this coupon to be useful.
   * @return the minimum quantity for this coupon to be useful
   */
  public int getMinQuantity() {
    return this.minimumQuantity;
  }
  
  /**
   * Returns the number of free items for using this coupon.
   * @return the number of free items
   */
  public int getNumFree() {
    return this.numberOfFree;
  }
  
  @Override
  public String getDescription() {
    return String.format("Buy %d get %d free for item %s", 
                          this.minimumQuantity, 
                          this.numberOfFree,
                          this.item.getType());
  }
  
  @Override
  public double getDiscountedPrice(double pricePerQuantity, 
                                   int num) throws IllegalArgumentException {
    /* if price per quantity is less than 0, 
     * this will cause error. Since It must be ensured that a customer cannot earn
     * money by choosing to buy an item and applying coupons towards the purchase.
     */
    if (pricePerQuantity < 0) {
      throw new IllegalArgumentException("Price per quantity should be positive number.");
    }
    if (num < 0) {
      throw new IllegalArgumentException("Number of items should be greater or equal to 0.");
    }
    
    double discountedPrice = 0;
    
    if (num == 0) {
      return discountedPrice;
    }
    
    /* if the number of item is less than or equal to the minimum coupon quantity.
     * there will be no free items to get by this coupon.
     */
    if (num <= this.minimumQuantity) {
      discountedPrice += pricePerQuantity * num;
      return discountedPrice;
    } else {
      int buyAndFreeNum = this.minimumQuantity + this.numberOfFree;
      /* if the number of item is greater than the minimum quantity of this coupon, 
       * but the total number of items bought are less than the free items plus the
       *minimum quantity of items,
       * For example, if the coupon is buy 3 get 2 free, but you buy 4 items, 
       * which means you pay for 3 items, and get the extra 1 for free.
       */
      if (num > this.minimumQuantity && num <= buyAndFreeNum) {
        discountedPrice += this.minimumQuantity * pricePerQuantity;
        return discountedPrice;
      } else {
        /* if the number of item is greater than the minimum quantity plus free
         * items quantity,
         * this means this coupon will be used for multiple times
         * For example, if the coupon is buy 3 get 2 free, but you buy 8 items, 
         * which means you pay for 3 items,
         * and get the extra 2 for free. Then you still have 3 items that 
         * you need to pay, the total value will
         * be 3 + 3 = 6 items that you should pay.
         */
        int moreItemsForCoupon = num - buyAndFreeNum;
        discountedPrice += this.minimumQuantity * pricePerQuantity;
        discountedPrice += this.getDiscountedPrice(pricePerQuantity, moreItemsForCoupon);
      }
    }
    return discountedPrice;
  }
  
  /**
   * Default comparator of "this" to other coupon.
   * If they are all BuySomeGetSomeFree, the comparator that compare two
   * BuySomeGetSomeFree method will be invoked.
   * @param other the other coupon that compares with "this"
   * @return true or false this couple is the same type as the other coupon
   */
  public boolean equals(Coupon other) {
    if (other instanceof AbstractCoupon) {
      AbstractCoupon acoupon = (AbstractCoupon) other;
      return acoupon.equalsBuySomeGetSome(this);
    }
    return false; 
  }
  
  /**
   * BuySomeGetSomeFree specific comparator of "this" to other coupon.
   * @param other the other coupon that compares with "this"
   * @return true or false this couple is the same type as the other coupon
   */
  public boolean equalsBuySomeGetSome(BuySomeGetSomeFreeCoupon other) {
    if (other instanceof BuySomeGetSomeFreeCoupon) {
      return true;
    }
    return false;
  }
  
  @Override
  public Coupon stack(Coupon other) {
    /* two coupons should both be stackable, otherwise return null */
    if (this.stackable == false || other.isStackable() == false) {
      return null;
    }
    /* two coupons should applicable on the same item, otherwise return null */
    if (!this.getItem().equals(other.getItem())) {
      return null;
    }
    /* two coupons should be of the same type, otherwise return null */
    if (!this.equals(other)) {
      return null;
    }
    /* here is the ratio of minimum quantity and number of free items for this coupon */
    double ratioThis = this.numberOfFree / this.minimumQuantity; 
    
    /* here is the ratio of minimum quantity and number of free items for other coupon */
    double ratioOther = ((BuySomeGetSomeFreeCoupon) other).getNumFree() 
                        / ((BuySomeGetSomeFreeCoupon) other).getMinQuantity();
    
    int newMinQuantity = 0;
    int newNumFree = 0;
    
    /* If the first coupon offers a better discount (ratio of y and x) than the 
     * second coupon, then it is chosen, else the other is chosen. 
     * If both discounts are the same, then it chooses the coupon that requires 
     * fewer items to be bought to avail of the free items.
     */
    if (Double.compare(ratioThis, ratioOther) < 0) {
      newMinQuantity = ((BuySomeGetSomeFreeCoupon) other).getMinQuantity();
      newNumFree = ((BuySomeGetSomeFreeCoupon) other).getNumFree();
    } else if (Double.compare(ratioThis, ratioOther) > 0) {
      newMinQuantity = this.getMinQuantity();
      newNumFree = this.getNumFree();
    } else if (Double.compare(ratioThis, ratioOther) == 0) {
      if (this.getMinQuantity() <= ((BuySomeGetSomeFreeCoupon) other).getMinQuantity()) {
        newMinQuantity = this.getMinQuantity();
        newNumFree = this.getNumFree();
      } else {
        newMinQuantity = ((BuySomeGetSomeFreeCoupon) other).getMinQuantity();
        newNumFree = ((BuySomeGetSomeFreeCoupon) other).getMinQuantity();
      }
    }
    
    BuySomeGetSomeFreeCoupon newBuySomeGetSomeCoupon = new BuySomeGetSomeFreeCoupon(
                                                                  this.item.getType(), 
                                                                  this.stackable, 
                                                                  newMinQuantity,
                                                                  newNumFree);
    return newBuySomeGetSomeCoupon;
  }
  
}
