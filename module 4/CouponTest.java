import static org.junit.Assert.assertEquals;

import coupon.AmountOffCoupon;
import coupon.BuySomeGetSomeFreeCoupon;
import coupon.Coupon;
import coupon.Item;
import coupon.PercentOffCoupon;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains all the unit tests for different kind of coupons under the Coupon interface,
 * and the item class.
 */
public class CouponTest {
  private AmountOffCoupon amountOffCoupon1;
  private AmountOffCoupon amountOffCoupon2;
  private AmountOffCoupon amountOffCoupon3;
  private BuySomeGetSomeFreeCoupon bsgsfCoupon1;
  private BuySomeGetSomeFreeCoupon bsgsfCoupon2;
  private BuySomeGetSomeFreeCoupon bsgsfCoupon3;
  private PercentOffCoupon percentOffCoupon1;
  private PercentOffCoupon percentOffCoupon2;
  private PercentOffCoupon percentOffCoupon3;
  private Item item1;
  
  /**
   * setting up the attributes for different coupons.
   */
  @Before
  public void setup() {
    amountOffCoupon1 = new AmountOffCoupon("box of Chocolate", true, 2);
    amountOffCoupon2 = new AmountOffCoupon("box of Chocolate", true, 1);
    amountOffCoupon3 = new AmountOffCoupon("box of Macaroon", false, 3);
    
    bsgsfCoupon1 = new BuySomeGetSomeFreeCoupon("box of Chocolate", true, 2, 1);
    bsgsfCoupon2 = new BuySomeGetSomeFreeCoupon("box of Chocolate", true, 4, 1);
    bsgsfCoupon3 = new BuySomeGetSomeFreeCoupon("bottles", false, 2, 1);
    
    percentOffCoupon1 = new PercentOffCoupon("box of Chocolate", true, 30);
    percentOffCoupon2 = new PercentOffCoupon("box of Chocolate", true, 20);
    percentOffCoupon3 = new PercentOffCoupon("piece of Cake", true, 0.1947);
    
    item1 = new Item("box of napkins");
  }
  
  /**
   * testing the item get method.
   */
  @Test
  public void testItemGet() {
    assertEquals("box of napkins", item1.getType());
    assertEquals("box of Chocolate", amountOffCoupon1.getItem());
  }
  
  /**
   * testing the getter methods for all three coupon classes.
   */
  @Test
  public void testGetMethod() {
    assertEquals(2.0, amountOffCoupon1.getAmount(), 0.1);
    assertEquals(30.0, percentOffCoupon1.getAmount(), 0.1);
    assertEquals(2, bsgsfCoupon1.getMinQuantity());
    assertEquals(1, bsgsfCoupon1.getNumFree());
  }
  
  /**
   * testing the getDescription methods for all three coupon classes.
   */
  @Test
  public void testGetDescription() {
    assertEquals("$2.00 off item box of Chocolate", amountOffCoupon1.getDescription());
    assertEquals("20.00% off item box of Chocolate", percentOffCoupon2.getDescription());
    assertEquals("0.19% off item piece of Cake", percentOffCoupon3.getDescription());
    assertEquals("Buy 2 get 1 free for item box of Chocolate", bsgsfCoupon1.getDescription());
  }
  
  /**
   * testing the getDiscountedPrice methods for all three coupon classes.
   */
  @Test
  public void testGetDiscountedPrice() {
    assertEquals(12.0, amountOffCoupon1.getDiscountedPrice(5, 4), 0.1);
    assertEquals(8.0, amountOffCoupon3.getDiscountedPrice(5, 4), 0.1);
    assertEquals(14.0, percentOffCoupon1.getDiscountedPrice(5, 4), 0.1);
    assertEquals(15.0, bsgsfCoupon1.getDiscountedPrice(5, 4), 0.1);
    assertEquals(10.0, bsgsfCoupon1.getDiscountedPrice(5, 2), 0.1);
    assertEquals(20.0, bsgsfCoupon1.getDiscountedPrice(5, 6), 0.1);
  }
  
  /**
   * testing the equals methods for all three coupon classes.
   */
  @Test
  public void testCouponEquals() {
    assertEquals(true, amountOffCoupon1.equals(amountOffCoupon2));
    assertEquals(true, amountOffCoupon2.equals(amountOffCoupon1));
    assertEquals(false, amountOffCoupon1.equals(percentOffCoupon2));
    assertEquals(false, bsgsfCoupon1.equals(amountOffCoupon2));
    assertEquals(false, percentOffCoupon1.equals(bsgsfCoupon1));
    assertEquals(true, bsgsfCoupon1.equals(bsgsfCoupon2));
    assertEquals(true, percentOffCoupon2.equals(percentOffCoupon1));
  }
  
  /**
   * testing the stack methods for all three coupon classes.
   */
  @Test
  public void testStack() {
    Coupon amountOffCoupon4 = amountOffCoupon1.stack(amountOffCoupon2);
    assertEquals(3.0, ((AmountOffCoupon) amountOffCoupon4).getAmount(), 0.1);
    assertEquals("box of Chocolate", ((AmountOffCoupon) amountOffCoupon4).getItem());
    
    Coupon percentOffCoupon4 = percentOffCoupon1.stack(percentOffCoupon2);
    assertEquals(50.0, ((PercentOffCoupon) percentOffCoupon4).getAmount(), 0.1);
    assertEquals("box of Chocolate", ((PercentOffCoupon) percentOffCoupon4).getItem());
    
    Coupon bsgsfCoupon4 = bsgsfCoupon1.stack(bsgsfCoupon2);
    assertEquals(2, ((BuySomeGetSomeFreeCoupon) bsgsfCoupon4).getMinQuantity());
    assertEquals(1, ((BuySomeGetSomeFreeCoupon) bsgsfCoupon4).getNumFree());
    assertEquals("box of Chocolate", ((BuySomeGetSomeFreeCoupon) bsgsfCoupon4).getItem());
  }

  /**
   * testing the invalid AmountOffCoupon Stack.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAmountOffStack() {
    amountOffCoupon1.stack(amountOffCoupon3);
  }
  
  /**
   * testing the invalid PercentOffCoupon Stack.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPercentOffStack() {
    percentOffCoupon1.stack(percentOffCoupon3);
  }
  
  /**
   * testing the invalid BuySomeGetSomeFree Stack.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBuySomeGetSomeFreeStack() {
    bsgsfCoupon1.stack(bsgsfCoupon3);
  }
  
}
