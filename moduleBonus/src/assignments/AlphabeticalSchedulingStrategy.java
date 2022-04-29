package assignments;

import java.util.List;

/**
 * This class implements the SchedulingStrategy.
 * It sorts the given assignment in alphabetic order.
 */
public class AlphabeticalSchedulingStrategy implements SchedulingStrategy {
  
  /** Default constructor. */
  public AlphabeticalSchedulingStrategy() {}

  @Override
  public String schedule(AssignmentList listOfAssignment) throws IllegalArgumentException {
    if (listOfAssignment == null) {
      throw new IllegalArgumentException("The input assignment list should be valid.");
    }
    listOfAssignment.setOrdering("alphabetical");
    List<Assignment> currList = listOfAssignment.getList();
    int start = 0; //mark the start position
    int end = currList.size() - 1; //mark the end position
    
    //start the insertion sort
    for (int i = start + 1; i <= end; i++) {
      Assignment current = currList.get(i);
      int j = i - 1;
      while ((j >= start) 
          && (current.compareTo(currList.get(j)) < 0)) {
        Assignment temp = currList.get(j);
        currList.set(j + 1, temp);
        j--;
      }
      currList.set(j + 1, current);
    }
    
    //set the sorted list to the original assignmentlist
    listOfAssignment.setList(currList);
    StringBuilder sb = new StringBuilder("");    
    for (int i = 0; i < currList.size(); i++) {
      sb.append(i + 1).append(" -- ").append(currList.get(i)).append("\n");
    }
    return sb.toString();
  }
  
}

