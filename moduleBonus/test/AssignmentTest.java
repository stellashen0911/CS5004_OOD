import static org.junit.Assert.assertEquals;

import assignments.AlphabeticalSchedulingStrategy;
import assignments.AssignedSchedulingStrategy;
import assignments.Assignment;
import assignments.AssignmentList;
import assignments.DeadlineSchedulingStrategy;
import assignments.DifficultySchedulingStrategy;
import java.time.LocalDate;
import org.junit.Test;

/** Class that tests the tasks. */
public class AssignmentTest {

  /** Testing assignment constructor and toString(). */
  @Test
  public void testAssignConstructor() {
    LocalDate now = LocalDate.now();
    Assignment t1 = new Assignment("task 1");
    assertEquals("task 1, starting " + now + ", ending " + now, t1.toString());
    Assignment t2 = new Assignment("task 2");
    t2.setDeadline(3, 4, 2025);
    assertEquals("task 2, starting " + now + ", ending 2025-03-04", t2.toString());
  }
  
  /** Testing assignment list constructor and toString(). */
  @Test
  public void testAssignListConstructor() {
    LocalDate now = LocalDate.now();
    Assignment t1 = new Assignment("task 1");
    Assignment t2 = new Assignment("task 2");
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    assertEquals("Ordered by assigned\n1 -- task 1, starting " 
        + now + ", ending " + now + "\n", testList.toString());
    testList.add(t2);
    assertEquals("Ordered by assigned\n1 -- task 1, starting " 
        + now + ", ending " + now + "\n" + "2 -- task 2, starting " 
        + now + ", ending " + now + "\n", testList.toString());
  }
  
  /** Testing AssignedSchedulingStrategy and toString(). */
  @Test
  public void testAssignStrategy() {
    Assignment t1 = new Assignment("task 1");
    Assignment t2 = new Assignment("task 2");
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    AssignedSchedulingStrategy assignedStrategy = new AssignedSchedulingStrategy();
    testList.scheduleAssignments(assignedStrategy);
    LocalDate now = LocalDate.now();
    assertEquals("Ordered by assigned\n1 -- task 1, starting " 
        + now + ", ending " + now + "\n" + "2 -- task 2, starting " 
        + now + ", ending " + now + "\n", testList.toString());
  }
  
  /** Testing AssignedSchedulingStrategy after we use alphabetical strategy and toString(). */
  @Test
  public void testAssignStrategyAfterAlpha() {
    Assignment t1 = new Assignment("good task 1");
    Assignment t2 = new Assignment("bad task 2");
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    AlphabeticalSchedulingStrategy alphaStrategy = new AlphabeticalSchedulingStrategy();
    testList.scheduleAssignments(alphaStrategy);
    AssignedSchedulingStrategy assignedStrategy = new AssignedSchedulingStrategy();
    testList.scheduleAssignments(assignedStrategy);
    LocalDate now = LocalDate.now();
    assertEquals("Ordered by assigned\n1 -- good task 1, starting " 
        + now + ", ending " + now + "\n" + "2 -- bad task 2, starting " 
        + now + ", ending " + now + "\n", testList.toString());
  }
  
  /** Testing AlphabeticalSchedulingStrategy and toString(). */
  @Test
  public void testAlphaStrategy() {
    Assignment t1 = new Assignment("good task 1");
    Assignment t2 = new Assignment("bad task 2");
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    AlphabeticalSchedulingStrategy alphaStrategy = new AlphabeticalSchedulingStrategy();
    testList.scheduleAssignments(alphaStrategy);
    LocalDate now = LocalDate.now();
    assertEquals("Ordered by alphabetical\n1 -- bad task 2, starting " 
        + now + ", ending " + now + "\n" + "2 -- good task 1, starting " 
        + now + ", ending " + now + "\n", testList.toString());
  }
  
  /** Testing AlphabeticalSchedulingStrategy after we use assigned strategy and toString(). */
  @Test
  public void testAlphaStrategyAfterAssigned() {
    Assignment t1 = new Assignment("good task 1");
    Assignment t2 = new Assignment("bad task 2");
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    AssignedSchedulingStrategy assignedStrategy = new AssignedSchedulingStrategy();
    testList.scheduleAssignments(assignedStrategy);
    AlphabeticalSchedulingStrategy alphaStrategy = new AlphabeticalSchedulingStrategy();
    testList.scheduleAssignments(alphaStrategy);
    LocalDate now = LocalDate.now();
    assertEquals("Ordered by alphabetical\n1 -- bad task 2, starting " 
        + now + ", ending " + now + "\n" + "2 -- good task 1, starting " 
        + now + ", ending " + now + "\n", testList.toString());
  }
  
  /** Testing DeadlineSchedulingStrategy and toString(). */
  @Test
  public void testDeadLineStrategy() {
    Assignment t1 = new Assignment("task 1");
    t1.setStart(3, 8, 1998);
    t1.setDeadline(3, 12, 1998);
    Assignment t2 = new Assignment("task 2");
    t2.setStart(3, 8, 1997);
    t2.setDeadline(3, 12, 1997);
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    DeadlineSchedulingStrategy deadlineStrategy = new DeadlineSchedulingStrategy();
    testList.scheduleAssignments(deadlineStrategy);
    assertEquals("Ordered by deadline\n1 -- task 2, starting " 
        + t2.getStartDate() + ", ending " + t2.getEndDate() + "\n" + "2 -- task 1, starting " 
        + t1.getStartDate() + ", ending " + t1.getEndDate() + "\n", testList.toString());
  }
  
  /** Testing DeadlineSchedulingStrategy after we use assigned strategy and toString(). */
  @Test
  public void testDeadLineStrategyAfterAssigned() {
    Assignment t1 = new Assignment("task 1");
    t1.setStart(3, 8, 1998);
    t1.setDeadline(3, 12, 1998);
    Assignment t2 = new Assignment("task 2");
    t2.setStart(3, 8, 1997);
    t2.setDeadline(3, 12, 1997);
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    AssignedSchedulingStrategy assignedStrategy = new AssignedSchedulingStrategy();
    testList.scheduleAssignments(assignedStrategy);
    DeadlineSchedulingStrategy deadlineStrategy = new DeadlineSchedulingStrategy();
    testList.scheduleAssignments(deadlineStrategy);
    assertEquals("Ordered by deadline\n1 -- task 2, starting " 
        + t2.getStartDate() + ", ending " + t2.getEndDate() + "\n" + "2 -- task 1, starting " 
        + t1.getStartDate() + ", ending " + t1.getEndDate() + "\n", testList.toString());
  }
  
  /** Testing DifficultySchedulingStrategy and toString(). */
  @Test
  public void testDiffucultyStrategy() {
    Assignment t1 = new Assignment("task 1");
    t1.setStart(3, 1, 1998);
    t1.setDeadline(3, 12, 1998);
    Assignment t2 = new Assignment("task 2");
    t2.setStart(3, 8, 1997);
    t2.setDeadline(3, 12, 1997);
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    DifficultySchedulingStrategy difficultyStrategy = new DifficultySchedulingStrategy();
    testList.scheduleAssignments(difficultyStrategy);
    assertEquals("Ordered by difficulty\n1 -- task 1, starting " 
        + t1.getStartDate() + ", ending " + t1.getEndDate() + "\n" + "2 -- task 2, starting " 
        + t2.getStartDate() + ", ending " + t2.getEndDate() + "\n", testList.toString());
  }
  
  /** Testing DifficultySchedulingStrategy after we use assigned strategy and toString(). */
  @Test
  public void testDiffucultyStrategyAfterAssigned() {
    Assignment t1 = new Assignment("task 1");
    t1.setStart(3, 1, 1998);
    t1.setDeadline(3, 12, 1998);
    Assignment t2 = new Assignment("task 2");
    t2.setStart(3, 8, 1997);
    t2.setDeadline(3, 12, 1997);
    AssignmentList testList = new AssignmentList();
    testList.add(t1);
    testList.add(t2);
    AssignedSchedulingStrategy assignedStrategy = new AssignedSchedulingStrategy();
    testList.scheduleAssignments(assignedStrategy);
    DifficultySchedulingStrategy difficultyStrategy = new DifficultySchedulingStrategy();
    testList.scheduleAssignments(difficultyStrategy);
    assertEquals("Ordered by difficulty\n1 -- task 1, starting " 
        + t1.getStartDate() + ", ending " + t1.getEndDate() + "\n" + "2 -- task 2, starting " 
        + t2.getStartDate() + ", ending " + t2.getEndDate() + "\n", testList.toString());
  }
  
  /**
   * testing the strategy with no valid input assignment list.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScheduleFail() {
    AssignmentList testList = new AssignmentList();
    AssignedSchedulingStrategy assignedStrategy = new AssignedSchedulingStrategy();
    testList.scheduleAssignments(null);
    LocalDate now = LocalDate.now();
    assertEquals("Ordered by assigned\n1 -- good task 1, starting " 
        + now + ", ending " + now + "\n" + "2 -- bad task 2, starting " 
        + now + ", ending " + now + "\n", testList.toString());
  }
}