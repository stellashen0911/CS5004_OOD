package polynomial;

import java.util.Scanner;

/**
 * This class represents a solid list of terms, with term and rest of list.
 * This class implements the listOfTerms interface.
 */
public class TermElement implements ListOfTerms {
  private Term term;
  private ListOfTerms rest;
  
  /**
   * Constructor that takes a term coefficient, term power and the rest of the term.
   * 
   * @param coefficient the coefficient of the term
   * @param power the power of the term
   * @param rest rest of the list of terms
   */
  public TermElement(int coefficient, int power, ListOfTerms rest) {
    Term newTerm = new Term(coefficient, power);
    this.term = newTerm;
    this.rest = rest;
  }
  
  /**
   * Constructor that takes a string as input to create a list of terms.
   * @param input the input string
   */
  public TermElement(String input) {
    Scanner scan = new Scanner(input);
    try {
      String currTermString = scan.next(); // "5x^2"
      this.term = this.fromStrToTerm(currTermString);
      if (scan.hasNext()) {
        this.rest = new TermElement(input.substring(input.indexOf(' ') + 1));
      } else {
        this.rest = new EmptyTerm();
      }
    } finally {
      scan.close();
    }
  }

  /**
   * Return the term of this list.
   *
   * @return the term of this term
   */
  public Term getTerm() {
    return this.term;
  }
  
  /**
   * Return the rest list of this list.
   *
   * @return the rest list of this list.
   */
  public ListOfTerms getRest() {
    return this.rest;
  }
  
  
  @Override
  public String toString() {
    String result = this.term.toString().trim() + " " + this.rest.toString().trim();
    result.trim(); //remove the last space from the string
    return result;
  }
  
  @Override
  public boolean equals(ListOfTerms other) {
    if (!(other instanceof TermElement)) {
      return false;
    }
    ListOfTerms selfAfterSort = this.sortByPower();
    ListOfTerms otherAfterSort = other.sortByPower();
    if (selfAfterSort.toString().equals(otherAfterSort.toString())) {
      return true;
    }
    return false;
  }

  @Override
  public ListOfTerms addTerm(Term term) {
    if (this.term.greatPowerThan(term)) {
      return new TermElement(this.term.getCoefficient(), this.term.getPower(),
          this.rest.addTerm(term));
    } else if (this.term.samePower(term)) {
      int newCoefficient = this.term.getCoefficient() + term.getCoefficient();
      this.term = new Term(newCoefficient, term.getPower());
      return this;
    }
    return new TermElement(term.getCoefficient(), term.getPower(), this);
  }

  @Override
  public int count() {
    // TODO Auto-generated method stub
    return countHelper(0);
  }
  
  public int countHelper(int acc) {
    return this.rest.countHelper(1 + acc);
  }

  @Override
  public ListOfTerms sortByPower() {
    return this.rest.sortByPower().addTerm(this.term);
  }

  @Override
  public ListOfTerms addList(ListOfTerms other) {
    if (!(other instanceof TermElement)) {
      return this;
    }
    TermElement otherList = (TermElement) other;
    ListOfTerms listAddOneTerm = this.addTerm(otherList.term);
    ListOfTerms toBeAddList = otherList.rest;
    return listAddOneTerm.addList(toBeAddList);
  }

  @Override
  public double operationResultOfList(double x) {
    double termResult = this.term.getCoefficient() * Math.pow(x, this.term.getPower());
    return termResult + this.rest.operationResultOfList(x);
  }

  @Override
  public int getGivenCoefficient(int power) {
    if (this.term.getPower() == power) {
      return this.term.getCoefficient();
    }
    return this.rest.getGivenCoefficient(power);
  }

  @Override
  public int getDegree(int max) {
    if (this.term.getPower() >= max) {
      max = this.term.getPower();
    }
    return this.rest.getDegree(max);
  }

  /**
   * Create a new term with given string representation.
   *
   * @param str the given string of list of terms
   * @return the new terms generated from the string
   */
  public Term fromStrToTerm(String str) {
    //if this term does not have x string, the power will be 0
    if (!str.contains("x") && !str.contains("^")) {
      int number = Integer.parseInt(str);
      Term newAdd = new Term(number, 0);
      return newAdd;
    }
      
    //if this string have multiple x or multiple ^, this is not valid
    int countX = 0;
    int countMult = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'x') {
        countX++;
      }
      if (str.charAt(i) == '^') {
        countMult++;
      }
    }
    if (countX > 1 || countMult > 1) {
      throw new NumberFormatException("one term must have only one x");
    }
        
    //if this term have only one x char and ^ char
    int index = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'x') {
        index = i;
      }
    }
    int coefficient = Integer.parseInt(str.substring(0, index));
    int power = 1;
    if (str.contains("^")) {
      power = Integer.parseInt(str.substring(index + 2));
    }
    if (power < 0) {
      throw new IllegalArgumentException("power cannot be less than 0.");
    }
    Term curr = new Term(coefficient, power);
    return curr;
  }
}

