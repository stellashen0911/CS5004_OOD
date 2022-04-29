package coupon;

/**
 * This class represents a PercentOffCoupon. 
 * It defines all the operations mandated by
 * the Coupon interface.
 */
public class PercentOffCoupon extends AbstractCoupon {
  private double amount;
  
  /**
   * Constructor for an percent off coupon.
   * @param item the item that the coupon applies
   * @param stackable true or false the couple can be stacked
   * @param percentageOff The percentage of money off per quantity of item
   */
  public PercentOffCoupon(String item, 
                          boolean stackable, 
                          double percentageOff) throws IllegalArgumentException {
    /* the percentage off cannot be negative number. */
    if (percentageOff < 0) {
      throw new IllegalArgumentException("Percentage off should be positive number");
    }
    this.item = new Item(item);
    this.stackable = stackable;
    this.amount = percentageOff;
  }
  
  /**
   * Returns the percentage off per quantity of this coupon.
   * @return the percentage off per quantity
   */
  public double getAmount() {
    return this.amount;
  }
  
  @Override
  public String getDescription() {
    return String.format("%.2f%% off item %s", 
                        this.amount, 
                        this.item.getType());
  }
  
  @Override
  public double getDiscountedPrice(double pricePerQuantity, 
                                   int num) throws IllegalArgumentException {
    /* if price per quantity is less than 0, 
     * this will cause error. Since It must be ensured that a customer cannot earn
     * money by choosing to buy an item and applying coupons towards the purchase.
     * */
    if (pricePerQuantity < 0) {
      throw new IllegalArgumentException("Price per quantity should be positive number.");
    }
    if (num < 0) {
      throw new IllegalArgumentException("Number of items should be greater or equal to 0.");
    }
    double totalPriceBeforeCoupon = pricePerQuantity * num;
    double discountedPrice = totalPriceBeforeCoupon * (1 - (this.amount / 100));
    return discountedPrice;
  }
  
  /**
   * Default comparator of "this" to other coupon.
   * If they are all PercentOffCoupon, the comparator that compare 
   * two PercentOffCoupon method will be invoked.
   * @param other the other coupon that compares with "this"
   * @return true or false this couple is the same type as the other coupon
   */
  public boolean equals(Coupon other) {
    if (other instanceof AbstractCoupon) {
      AbstractCoupon acoupon = (AbstractCoupon) other;
      return acoupon.equalsPercentOff(this);
    }
    return false;
  }
  
  /**
   * PercentOffCoupon specific comparator of "this" to other coupon.
   * @param other the other coupon that compares with "this"
   * @return true or false this couple is the same type as the other coupon
   */
  public boolean equalsPercentOff(PercentOffCoupon other) {
    if (other instanceof PercentOffCoupon) {
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
    double newPercentOff = this.amount + ((PercentOffCoupon) other).getAmount();
    PercentOffCoupon newPercentOffCoupon = new PercentOffCoupon(this.item.getType(), 
                                                                this.stackable, 
                                                                newPercentOff);
    return newPercentOffCoupon;
  }
  
}
