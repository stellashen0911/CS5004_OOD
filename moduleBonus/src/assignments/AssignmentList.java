package assignments;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of task that need to be completed.
 */
public class AssignmentList {

  private List<Assignment> tasks;
  private String ordering;

  /** Default constructor. */
  public AssignmentList() {
    tasks = new ArrayList<>();
    ordering = "assigned";
  }

  /**
   * Add a task to the task list.
   * 
   * @param t the task
   */
  public void add(Assignment t) {
    tasks.add(t);
  }
  
  /**
   * Add the specified ordering to the list.
   * 
   * @param newOrdering the new ordering
   */
  public void setOrdering(String newOrdering) {
    ordering = newOrdering;
  }
  
  /**
   * Add the ordered ordering to the task list.
   * 
   * @param newList the new ordering list
   */
  public void setList(List<Assignment> newList) {
    tasks = newList;
  }
  
  /**
   * Getter for the list of assginment.
   * 
   * @return a list of assginment
   */
  public List<Assignment> getList() {
    return this.tasks;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Ordered by ").append(ordering).append("\n");    
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(i + 1).append(" -- ").append(tasks.get(i)).append("\n");
    }
    return sb.toString();
  }
  
  /**
   * Uses it to schedule the items in its task list.
   * 
   * @param strategy the given strategy to schedule the assignment.
   * @throws throw an IllegalArgumentException if no scheduling strategy is provided.
   */
  public void scheduleAssignments(SchedulingStrategy strategy) throws IllegalArgumentException {
    if (strategy == null) {
      throw new IllegalArgumentException("A valid strategy must be given.");
    }
    strategy.schedule(this);
    return;
  }
}
