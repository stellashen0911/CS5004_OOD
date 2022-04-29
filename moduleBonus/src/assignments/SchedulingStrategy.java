package assignments;

/**
 * This is a interface of SchedulingStrategy, each strategy must implement this interface.
 */
public interface SchedulingStrategy {

  /**
   * This schedule method that takes a list of assignments.
   * and returns a string with the name of the strategy that was used.
   * @param listOfAssignment  a list of assignments as input
   * @return a string with the name of the strategy that was used
   * @throws IllegalArgumentException is the assginment list is not valid
   */
  public String schedule(AssignmentList listOfAssignment) throws IllegalArgumentException;
  
}
