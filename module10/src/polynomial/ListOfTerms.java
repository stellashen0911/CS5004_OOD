package polynomial;

/**
 * This interface represents all the operations to be supported by a list of
 * terms.
 */
public interface ListOfTerms {
  
  /**
   * Return the number of terms in this list.
   * 
   * @return the size of this list
   */
  int count();
  
  /**
   * Helper for the count method.
   * 
   * @param acc the accumulator
   * @return the count from here
   */
  int countHelper(int acc);
  
  /**
   * Create and return a string that can be used to print this list.
   * 
   * @return a string representing this list of term.
   */
  String toString();
  
  /**
   * Determines if this listOfTerms is the same as the parameter listofterms.
   * 
   * @param other the ListOfTerms to use
   * @return true if this ListOfTerms is of the same concrete type and has the same
   *         terms as the ListOfTerms, false otherwise
   */
  boolean equals(ListOfTerms other);
  
  /**
   * Add a new term to the list of terms.
   * With given coefficient and power.
   * 
   * @param term the new add term.
   * @return the list of terms after add the new term
   */
  ListOfTerms addTerm(Term term);
  
  /**
   * Sort the list of terms by power from large to small.
   * If the power is the same, sort by the coefficient from large to small.
   * 
   * @return the list of terms after add the new term
   */
  ListOfTerms sortByPower();
  
  /**
   * Add a existing list to the current list of terms.
   * 
   * @param other the new add list
   * @return the list of terms after add the new list
   */
  ListOfTerms addList(ListOfTerms other);
  
  /**
   * Evaluate the result of all terms at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial terms at x
   */
  double operationResultOfList(double x);
  
  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  int getGivenCoefficient(int power);
  
  /**
   * Get the degree of this polynomial.
   *
   * @param max to store the max value of power as the degree
   * @return the degree of this polynomial as a whole number
   */
  int getDegree(int max);
  
}
