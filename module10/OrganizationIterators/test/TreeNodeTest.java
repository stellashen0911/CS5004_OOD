import static org.junit.Assert.assertEquals;

import employees.Employee;
import employees.Gender;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import organization.IterableOrganization;
import organization.OrganizationImpl;



/** Test cases for the tree node. */
public class TreeNodeTest {
  private IterableOrganization ccis;
  private IterableOrganization startup;

  /** Setting up the organization so we can test it. */
  @Before
  public void setup() {
    ccis = new OrganizationImpl("Carla Brodley", 400000, Gender.FEMALE);
    ccis.addEmployee("Jay Aslam", 200000, Gender.MALE, "Carla Brodley");
    ccis.addEmployee("Rajmohan Rajaraman", 200000, Gender.MALE, "Jay Aslam");
    ccis.addEmployee("Frank Tip", 200000, Gender.MALE, "Jay Aslam");
    ccis.addEmployee("Alan Mislove", 100000, Gender.MALE, "Jay Aslam");
    ccis.addEmployee("Martin Schedlbauer", 100000, Gender.MALE, "Alan Mislove");
    ccis.addEmployee("Amit Shesh", 100000, Gender.MALE, "Frank Tip");
    ccis.addEmployee("Leena Razzaq", 120000, Gender.FEMALE, "Frank Tip");
    ccis.addEmployee("Bryan Lackaye", 150000, Gender.MALE, "Carla Brodley");
    ccis.addEmployee("Michelle Omerod", 100000, Gender.FEMALE, "Bryan Lackaye");
    ccis.addEmployee("Sarah Gale", 100000, Gender.FEMALE, "Bryan Lackaye");
    ccis.addEmployee("Catherine Gill", 200000, Gender.FEMALE, "Carla Brodley");
    ccis.addEmployee("Megan Barry", 100000, Gender.FEMALE, "Catherine Gill");
    startup = new OrganizationImpl("Bob", 50000, Gender.UNDISCLOSED);

    startup.addEmployee("Bill", 20000, Gender.MALE, "Bob");
    startup.addEmployee("Michelle", 30000, Gender.FEMALE, "Bob");
    startup.addContractEmployee("Mark", 10000, Gender.MALE, 1, 9, 2018, "Bill");
    startup.addEmployee("Amit", 10000, Gender.MALE, "Bill");
    startup.addContractEmployee("Chuck", 10000, Gender.UNDISCLOSED, 1, 12, 2018, "Michelle");

    startup.addContractEmployee("Tom", 10000, Gender.MALE, 15, 10, 2018, "Bill");
    startup.addContractEmployee("Tim", 5000, Gender.MALE, 15, 9, 2018, "Michelle");

  }

  /** Testing getSize(). */
  @Test
  public void testGetSize() {
    assertEquals(13, ccis.getSize());
    assertEquals(6, ccis.getSizeByGender(Gender.FEMALE));
    assertEquals(7, ccis.getSizeByGender(Gender.MALE));

    assertEquals(8, startup.getSize());
    assertEquals(1, startup.getSizeByGender(Gender.FEMALE));
    assertEquals(5, startup.getSizeByGender(Gender.MALE));
    assertEquals(2, startup.getSizeByGender(Gender.UNDISCLOSED));
  }

  /** Testing allEmployees(). */
  @Test
  public void testAllEmployees() {
    List<String> actualResult = ccis.allEmployees();
    String expected = "[Carla Brodley, Jay Aslam, Rajmohan Rajaraman, Frank "
        + "Tip, Amit Shesh, Leena Razzaq, Alan Mislove, Martin "
        + "Schedlbauer, Bryan Lackaye, Michelle Omerod, Sarah "
        + "Gale, Catherine Gill, Megan Barry]";
    assertEquals(expected, actualResult.toString());

    actualResult = startup.allEmployees();
    expected = "[Bob, Bill, Mark, Amit, Tom, Michelle, Chuck, Tim]";
    assertEquals(expected, actualResult.toString());
  }

  /** Testing countPayAbove(). */
  @Test
  public void testEmployeePay() {
    assertEquals(5, ccis.countPayAbove(150000));
    assertEquals(1, ccis.countPayAbove(300000));
  }

  /** Testing terminatedBefore. */
  @Test
  public void testTerminatedBy() {
    for (int i = 0; i < 1000; i++) {
      assertEquals(0, ccis.terminatedBefore((int) (Math.random() * 30) + 1,
          (int) (Math.random() * 11) + 1, (int) (Math.random() * 10 + 2000)));
    }

    assertEquals(1, startup.terminatedBefore(2, 9, 2018));
    assertEquals(2, startup.terminatedBefore(16, 9, 2018));
    assertEquals(3, startup.terminatedBefore(31, 10, 2018));
    assertEquals(4, startup.terminatedBefore(31, 12, 2018));
  }

  /** Testing iterator. */
  @Test
  public void testIterator() {
    List<String> names = new ArrayList<String>();
    for (Employee e : ccis) {
      names.add(e.getName());
    }
    Set<String> actual = new HashSet<String>(names);
    Set<String> expected = new HashSet<String>(ccis.allEmployees());

    assertEquals(expected, actual);
  }
}