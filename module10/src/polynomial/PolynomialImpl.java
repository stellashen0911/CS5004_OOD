package polynomial;

/**
 * This PolynomialImpl class implements all the operations offered by Polynomial Interface.
 * A polynomial is defined here as a function of one variable. The polynomial is a
 * weighted sum of terms (the weights are numeric). Each term may either be an
 * integer power of that variable, or some function in that variable, but never
 * both (i.e. (log x)^2 is not allowed).
 */
public class PolynomialImpl implements Polynomial {
  private ListOfTerms list;

  /**
   * Constructor with no parameters that create a polynomial with no terms.
   * i.e., the polynomial 0.
   */
  public PolynomialImpl() {
    this.list = new EmptyTerm();
  }
  
  /**
   * Constructor that takes a polynomial as a string.
   * parses it, and creates the polynomial accordingly
   * @param input the input string
   */
  public PolynomialImpl(String input) {
    if (input.length() == 0) {
      this.list = new EmptyTerm();
    } else {
      this.list = new TermElement(input);
    } 
  }
  
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("The input is not a concrete type.");
    }
    PolynomialImpl otherPoly = (PolynomialImpl) other;
    ListOfTerms otherList = otherPoly.list;
    ListOfTerms newList = this.list.addList(otherList);
    return new PolynomialImpl(newList.toString());
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    Term newTerm = new Term(coefficient, power);
    this.list = this.list.addTerm(newTerm);
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (!(poly instanceof PolynomialImpl)) {
      return false;
    }
    
    if (this == poly) {
      return true;
    }
    
    PolynomialImpl other = (PolynomialImpl) poly;
    return this.list.equals(other.list);
  }

  @Override
  public double evaluate(double x) {
    return this.list.operationResultOfList(x);
  }

  @Override
  public int getCoefficient(int power) {
    return this.list.getGivenCoefficient(power);
  }

  @Override
  public int getDegree() {
    return this.list.getDegree(0);
  }
  
  @Override
  public String toString() {
    String result = this.list.toString();
    if (result.length() <= 0) {
      return "0";
    }
    if (result.charAt(0) == '+') {
      result = result.substring(1); //remove the + sign in the beginning
    }
    result = result.trim();
    return result;
  }
  
}
