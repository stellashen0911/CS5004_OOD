package employees;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class represents the data and operations of any employee. It defines all
 * the operations that either ought to be common to all implementations, or have
 * a reasonable default to be overridden by them.
 */

abstract class AbstractEmployee implements Employee {
  protected String name;
  protected double pay;
  protected Gender gender;

  /**
   * Constructor.
   * 
   * @param name   the name of this employee
   * @param pay    the pay for this employee
   * @param gender the gender of this employee
   * @throws IllegalArgumentException if name or gender is null
   */
  public AbstractEmployee(String name, double pay, Gender gender) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Employee must have a name");
    }
    if (gender == null) {
      throw new IllegalArgumentException("Employee must have a gender");
    }
    this.name = name;
    this.pay = pay;
    this.gender = gender;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Gender getGender() {
    return this.gender;
  }

  @Override
  public double getAnnualPay() {
    return this.pay;
  }

  /**
   * By default, there is no end date for an employee. Only contract employees
   * have an actual end date.
   * 
   * @return the end date for an employee
   */
  @Override
  public String getEmploymentEndDate() {
    return "XXXXXXXX";
  }

  @Override
  public int count(Predicate<Employee> condition) {
    if (condition.test(this)) {
      return 1;
    }
    return 0;
  }

  @Override
  public List<Employee> toList() {
    List<Employee> result = new ArrayList<Employee>();
    result.add(this);
    return result;
  }
}
