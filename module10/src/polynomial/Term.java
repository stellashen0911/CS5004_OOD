package polynomial;

import java.util.Objects;

/**
 * This class represents a term in the polynomial. 
 * A polynomial is defined here as a function of one variable. 
 * The polynomial is a weighted sum of terms (the weights are numeric). 
 * Each term may either be an integer power of that variable, 
 * or some function in that variable, but never
 * both (i.e. (log x)^2 is not allowed).
 * This Term class represents a term with coefficient and power.
 */
public class Term {
  private int coefficient;
  private int power;
  
  /**
   * Construct a term object that has the provided coefficient and power.
   *
   * @param coefficient  the coefficient of this term
   * @param power the power of this term
   * @throws IllegalArgumentException if power is less than 0
   */
  public Term(int coefficient, int power) {
    if (power < 0) {
      throw new IllegalArgumentException("power cannot be less than 0.");
    }
    this.coefficient = coefficient;
    this.power = power;
  }
  
  /**
   * Return the Coefficient of this term.
   *
   * @return the Coefficient of this term
   */
  public int getCoefficient() {
    return this.coefficient;
  }
  
  /**
   * Return the Power of this term.
   *
   * @return the Power of this term
   */
  public int getPower() {
    return this.power;
  }
  
  /**
   * Return whether the two terms have the same power.
   *
   * @param t the term to be compared with this term
   * @return true if the two term have the same power
   */
  public boolean samePower(Term t) {
    return this.getPower() == t.getPower();
  }
  
  /**
   * Return the comparison of power between this and the other term.
   * 
   * @param other the term to be compared with this term
   * @return true if this term has greater power than other term
   */
  public boolean greatPowerThan(Term other) {
    if (this.getPower() > other.getPower()) {
      return true;
    }
    return false;
  }
  
  @Override
  public String toString() {
    if (this.getCoefficient() > 0 && this.getPower() > 0) {
      return String.format("+%dx^%d", this.getCoefficient(), this.getPower());
    } else if (this.getCoefficient() < 0 && this.getPower() > 0) {
      int negToPos = -this.getCoefficient();
      return String.format("-%dx^%d", negToPos, this.getPower());
    } else if (this.getCoefficient() > 0 && this.getPower() == 0) {
      return String.format("+%d", this.getCoefficient());
    } else if (this.getCoefficient() < 0 && this.getPower() == 0) {
      int negToPos = -this.getCoefficient();
      return String.format("-%d", negToPos);
    }
    return String.format("");
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Term)) {
      return false;
    }
    
    if (this == o) {
      return true;
    }
    
    Term other = (Term) o;
    return this.getCoefficient() == other.getCoefficient()
        && this.getPower() == other.getPower();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(this.coefficient, this.power);
  }
}
