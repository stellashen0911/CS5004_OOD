import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import polynomial.Polynomial;
import polynomial.PolynomialImpl;

/**
 * This is a test file for the polynomial implementation class.
 */
public class PolynomialImplTest {

  /**
   * testing the polynomial constructor with string input.
   * and the toString method
   */
  @Test
  public void testConstructorString() { 
    Polynomial polyTest1 = new PolynomialImpl("5x^2 +4x^1 -2");
    String outputTest1 = polyTest1.toString();
    assertEquals("5x^2 +4x^1 -2", outputTest1);
    
    Polynomial polyTest2 = new PolynomialImpl("-50x^3 +1x^2 +3");
    String outputTest2 = polyTest2.toString();
    assertEquals("-50x^3 +1x^2 +3", outputTest2);
    
    Polynomial polyTest3 = new PolynomialImpl("3");
    String outputTest3 = polyTest3.toString();
    assertEquals("3", outputTest3);
    
    Polynomial polyTest4 = new PolynomialImpl("");
    String outputTest4 = polyTest4.toString();
    assertEquals("0", outputTest4);
  }

  /**
   * testing the polynomial constructor with no parameter.
   * and the addTerm method.
   */
  @Test
  public void testConstructor() { 
    Polynomial polyTest1 = new PolynomialImpl();
    String outputEmpty = polyTest1.toString();
    assertEquals("0", outputEmpty);
    
    polyTest1.addTerm(5, 2); //"5x^2"
    polyTest1.addTerm(3, 6); //"3x^6"
    polyTest1.addTerm(4, 6); //"4x^6"
    polyTest1.addTerm(-5, 0); //"-5"
    polyTest1.addTerm(3, 6); //"3x^6"
    String outputWithTerm = polyTest1.toString();
    assertEquals("10x^6 +5x^2 -5", outputWithTerm);
  }
  
  /**
   * testing the polynomial constructor with string parameter.
   * but show exception.
   */
  @Test
  public void testConstructorStringException() { 
    assertThrows(IllegalArgumentException.class, () -> {
      Polynomial polyTest1 = new PolynomialImpl("5x^2 +5x^-5");
    });
  }
  
  
  /**
   * testing the add two polynomial method.
   */
  @Test
  public void testAdd() { 
    Polynomial polyTest1 = new PolynomialImpl();
    Polynomial polyTest2 = new PolynomialImpl();
    
    polyTest1.addTerm(5, 2); //"5x^2"
    polyTest2.addTerm(-5, 2); //"-5x^2"
    polyTest1.addTerm(3, 6); //"3x^6"
    polyTest2.addTerm(3, 6); //"3x^6"
    polyTest1.addTerm(4, 6); //"4x^6"
    polyTest2.addTerm(4, 5); //"4x^5"
    polyTest1.addTerm(-5, 0); //"-5"
    polyTest2.addTerm(-2, 0); //"-2"
    String outputTest1 = polyTest1.toString();
    String outputTest2 = polyTest2.toString();
    
    assertEquals("3x^6 +4x^5 -5x^2 -2", outputTest2);
    assertEquals("7x^6 +5x^2 -5", outputTest1);
    
    polyTest1 = polyTest1.add(polyTest2);
    String outputAfterAdd = polyTest1.toString();
    assertEquals("10x^6 +4x^5 -7", outputAfterAdd);
  }
  
  /**
   * testing the polynomial isSame method.
   */
  @Test
  public void testIsSame() { 
    Polynomial polyTest1 = new PolynomialImpl();
    polyTest1.addTerm(5, 2); //"5x^2"
    polyTest1.addTerm(3, 6); //"3x^6"
    polyTest1.addTerm(4, 6); //"4x^6"
    polyTest1.addTerm(-5, 0); //"-5"
    polyTest1.addTerm(3, 6); //"3x^6"
    String outputTest1 = polyTest1.toString();
    Polynomial polyTest2 = new PolynomialImpl("10x^6 +5x^2 -5");
    String outputTest2 = polyTest2.toString();
    assertEquals("10x^6 +5x^2 -5", outputTest1);
    assertEquals("10x^6 +5x^2 -5", outputTest2);
    String result = "";
    if (polyTest1.isSame(polyTest2)) {
      result += "true ";
    }
    if (polyTest2.isSame(polyTest1)) {
      result += "true";
    }
    assertEquals("true true", result);
    String falseResult = "";
    Polynomial polyTest3 = new PolynomialImpl("10x^7 +5x^3 -9");
    if (!polyTest1.isSame(polyTest3)) {
      falseResult += "false";
    }
    assertEquals("false", falseResult);
  }
  
  /**
   * testing the polynomial evaluate method.
   */
  @Test
  public void testEvaluate() { 
    Polynomial polyTest2 = new PolynomialImpl("10x^6 +5x^2 -5");
    String outputTest2 = polyTest2.toString();
    assertEquals("10x^6 +5x^2 -5", outputTest2);
    double result = polyTest2.evaluate(1); //10.00
    double resultWithDoubleInput = polyTest2.evaluate(1.6); //175.57216
    assertEquals(10.00000, result, 0.001);
    assertEquals(175.57216, resultWithDoubleInput, 0.001);
  }
  
  /**
   * testing the polynomial getCoefficient method.
   */
  @Test
  public void testGetCoefficient() { 
    Polynomial polyTest2 = new PolynomialImpl("10x^6 +5x^2 -5");
    String outputTest2 = polyTest2.toString();
    assertEquals("10x^6 +5x^2 -5", outputTest2);
    int coefficientWithPower6 = polyTest2.getCoefficient(6);
    int coefficientWithPower2 = polyTest2.getCoefficient(2);
    int coefficientWithPower0 = polyTest2.getCoefficient(0);
    int coefficientWithPowerNone = polyTest2.getCoefficient(3);
    assertEquals(10, coefficientWithPower6);
    assertEquals(5, coefficientWithPower2);
    assertEquals(-5, coefficientWithPower0);
    assertEquals(0, coefficientWithPowerNone);
  }
  
  /**
   * testing the polynomial getDegree method.
   */
  @Test
  public void testGetDegree() { 
    Polynomial polyTest2 = new PolynomialImpl("10x^6 +5x^2 -5");
    Polynomial polyTest1 = new PolynomialImpl("-5");
    int degree = polyTest2.getDegree();
    int degreeConstant = polyTest1.getDegree();
    assertEquals(6, degree);
    assertEquals(0, degreeConstant);
  }
  
  /**
   * testing the polynomial toString method.
   */
  @Test
  public void testToString() { 
    Polynomial polyTest2 = new PolynomialImpl("10x^6 +5x^2 -5");
    String outputTest2 = polyTest2.toString();
    assertEquals("10x^6 +5x^2 -5", outputTest2);
    
    Polynomial polyTest1 = new PolynomialImpl();
    assertEquals("0", polyTest1.toString());
        
  }
 
  /**
   * testing the addTerm with negative power.
   * but show exception.
   */
  @Test
  public void testAddTermNegative() { 
    assertThrows(IllegalArgumentException.class, () -> {
      Polynomial polyTest1 = new PolynomialImpl();
      polyTest1.addTerm(5, -3);
    });
  }
}
