package polynomial;

/**
 * This class represents an empter term node in the list.
 * This class implements the listOfTerms interface.
 */
public class EmptyTerm implements ListOfTerms {
  
  /**
   * Constructor of a empty node in the list of terms.
   */
  public EmptyTerm() {}
  
  @Override
  public String toString() {
    return String.format("");
  }

  @Override
  public boolean equals(ListOfTerms other) {
    if (!(other instanceof EmptyTerm)) {
      return false;
    }
    return true;
  }

  @Override
  public ListOfTerms addTerm(Term term) {
    return new TermElement(term.getCoefficient(), term.getPower(), new EmptyTerm());
  }

  @Override
  public int count() {
    return 0;
  }

  @Override
  public int countHelper(int acc) {
    return acc;
  }

  @Override
  public ListOfTerms sortByPower() {
    return new EmptyTerm();
  }

  @Override
  public ListOfTerms addList(ListOfTerms other) {
    return other;
  }

  @Override
  public double operationResultOfList(double x) {
    return 0.0;
  }

  @Override
  public int getGivenCoefficient(int power) {
    return 0;
  }

  @Override
  public int getDegree(int max) {
    return max;
  }

  
}
