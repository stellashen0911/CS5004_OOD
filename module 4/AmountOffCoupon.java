package coupon;

/**
 * This class represents a AmountOffCoupon. It defines all the operations mandated by
 * the Coupon interface.
 */
public class AmountOffCoupon extends AbstractCoupon {
  private double amount;
  
  /**
   * Constructor for an amount off coupon.
   * @param item the item that the coupon applies
   * @param stackable true or false the couple can be stacked
   * @param amountOff The amount of money off per quantity of item
   */
  public AmountOffCoupon(String item, 
                         boolean stackable, 
                         double amountOff) throws IllegalArgumentException {
    /* the discount off cannot be negative number. */
    if (amountOff < 0) {
      throw new IllegalArgumentException("Amount off should be positive number");
    }
    this.item = new Item(item);
    this.stackable = stackable;
    this.amount = amountOff;
  }
  
  /**
   * Returns the amount off per quantity of this coupon.
   * @return the amount off per quantity
   */
  public double getAmount() {
    return this.amount;
  }
  
  @Override
  public String getDescription() {
    return String.format("$%.2f off item %s", this.amount, this.item.getType());
  }
  
  @Override
  public double getDiscountedPrice(double pricePerQuantity, 
                                   int num) throws IllegalArgumentException {
    /* The quantity of the items should be positive number,
     * so we throw an error if the number of quantity is negative number.
     */
    if (num < 0) {
      throw new IllegalArgumentException("Number of items should be greater or equal to 0.");
    }
    /* If the price per quantity is less than the amount off by the coupon, this means
     * we can get this item for free for the certain number of items.
     * */
    double totalDiscount = 0;
    if (Double.compare(pricePerQuantity, this.amount) < 0) {
      totalDiscount = pricePerQuantity * num;
    } else {
      totalDiscount = this.amount * num;
    }
    double totalPriceBeforeCoupon = pricePerQuantity * num;
    double discountedPrice = totalPriceBeforeCoupon - totalDiscount;
    return discountedPrice;
  }
  
  /**
   * Default comparator of "this" to other coupon.
   * If they are all AmountOffCoupon, the comparator that compare two 
   * AmountOffCoupon method will be invoked.
   * @param other the other coupon that compares with "this"
   * @return true or false this couple is the same type as the other coupon
   */
  public boolean equals(Coupon other) {
    if (other instanceof AbstractCoupon) {
      AbstractCoupon acoupon = (AbstractCoupon) other;
      return acoupon.equalsAmountOff(this);
    }
    return false;
  }
  
  /**
   * AmountOffCoupon specific comparator of "this" to other coupon.
   * @param other the other coupon that compares with "this"
   * @return true or false this couple is the same type as the other coupon
   */
  public boolean equalsAmountOff(AmountOffCoupon other) {
    if (other instanceof AmountOffCoupon) {
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
    double newAmountOff = this.amount + ((AmountOffCoupon) other).getAmount();
    AmountOffCoupon newAmountOffCoupon = new AmountOffCoupon(this.item.getType(), 
                                                             this.stackable, 
                                                             newAmountOff);
    return newAmountOffCoupon;
  }
  
}
