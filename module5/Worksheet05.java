import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class that is implementing the questions of the lambda/stream exercise.
 */
public class Worksheet05 {

  /**
   * The starting point for our program.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    //examples();

    //question1();
    //question2();
    question3();
  }

  private static void examples() {
    // examples that are using a set range of numbers
    int total = IntStream.rangeClosed(1, 10).sum();
    int doubles = IntStream.rangeClosed(1, 10).map((int x) -> {
      return 2 * x;
    }).sum();
    System.out.println("total = " + total);
    System.out.println("doubles = " + doubles);
    
    // examples that are using random numbers
    Random randomNumbers = new Random();
    // printing out all of the elements using a lambda expression
    randomNumbers.ints(10, 1, 100).forEach(x -> System.out.println(x));
    // printing out all of the elements using a shorthand notation for a lambda 
    // expression that does nothing but call a method -- called a method reference
    randomNumbers.ints(10, 1, 100).forEach(System.out::println);
    
    // a bigger example using random numbers
    int[] values = new Random().ints(12, 1, 20).distinct().toArray();
    // print out the original values
    System.out.println("Original values:");
    System.out
        .println(IntStream.of(values).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    // print out the minimum value
    System.out.println("Min: " + IntStream.of(values).min().getAsInt());
    // print out the sum of all the values
    System.out.println("Sum with reduce:" + IntStream.of(values).reduce(0, (x, y) -> x + y));
    // print out the product of all the values, notice the different starting value
    System.out.println("Product of reduce:" + IntStream.of(values).reduce(1, (x, y) -> x * y));
    
    // a few more examples
    values = new Random().ints(100, 60, 100).toArray();
    // print out the number of distinct values
    System.out.println(IntStream.of(values).distinct().count());
    // print out the maximum value
    System.out.println(IntStream.of(values).max());
    // print out the sum of all the values using reduce
    System.out.println(IntStream.of(values).map(x -> x * x).reduce(0, (x, y) -> x + y));

    // print out the values in sorted order
    IntStream.of(values).sorted().forEach(System.out::println);
    // create a string of all the values in sorted order and print it out
    System.out.println(
        IntStream.of(values).sorted().mapToObj(String::valueOf).collect(Collectors.joining(" ")));
  }

  private static void question1() {
    // declaration of data so you have something in your data stream
    int[] values = { 2, 9, 5, 0, 3, 7, 1, 4, 8, 6 };

    // (a) print out all of the elements of value each on their own line
    System.out
    .println(IntStream.of(values).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
    
    // (b) convert values array into a `List<Integer> called `listValues`
    List<Integer> listValues = Arrays.stream(values).
                                boxed().
                                collect(Collectors.toList());
    System.out.println(listValues);

    // (c) print out the elements that are greater than 4 in sorted order
    System.out.println(Arrays.stream(values).
                       filter(x -> x > 4).
                       sorted().
                       mapToObj(String::valueOf).
                       collect(Collectors.joining(" ")));
  }

  private static void question2() {
    String[] strings = { "Red", "orange", "Yellow", "green", "blue", "indigo", "Violet" };

    // (a) print all the elements each on their own line
    for (String s : strings) {
      System.out.println(s.toString());
    }

    // (b) Create a List<String> that contains all of the elements of `strings
    // that have been made into all uppercase. Check out the methods of the
    // String class to find a method that can transform a string to upper case
    List<String> upperList = Arrays.stream(strings).
                             map(str -> str.toUpperCase()).
                             collect(Collectors.toList());

    // (c) Create a List<String> that contains all of the elements of `strings`
    // that come before "n" (case insensitive) sorted in ascending order
    List<String> allElementsOfStrComeBeforeN = Arrays.stream(strings).
                                              map(str -> {
                                                for (int i = 0; i < str.length(); i++) {
                                                  if (str.charAt(i) - 'n' > 0) {
                                                    return " ";
                                                  }
                                                }
                                                return str;
                                                }).
                                                collect(Collectors.toList());
    List<String> ascending = allElementsOfStrComeBeforeN.stream().
                            filter(str -> str != " ") 
                            .sorted(Comparator.reverseOrder())
                            .collect(Collectors.toList());

    // (d) Create a List<String> that contains all of the elements of `strings`
    // that come after "n" (case insensitive) sorted in descending order
    List<String> descending = Arrays.stream(strings).
                            map(str -> {
                              for (int i = 0; i < str.length(); i++) {
                                if (str.charAt(i) - 'n' < 0) {
                                  return " ";
                                }
                              }
                              return str;
                            })
                            .filter(str -> str != " ")
                            .collect(Collectors.toList());
    
  }

  private static void question3() {
    List<Employee> employees = List.of( //
        new Employee("Jason", "Red", 5000, "IT"), //
        new Employee("Ashley", "Green", 7600, "IT"), //
        new Employee("Dishant", "Indigo", 3587.5, "Sales"), //
        new Employee("James", "Indigo", 4700.77, "Marketing"), //
        new Employee("Yi", "Indigo", 6200, "IT"), //
        new Employee("Jason", "Blue", 3200, "Sales"), //
        new Employee("Artik", "Brown", 4236.4, "Marketing"));

    // (a) Calculate the total of all of the employees' salaries using reduce
    double totalSalary = employees.stream().map(b -> b.getSalary()).reduce((double) 0, (x, y) -> x + y);
    System.out.println(String.format("total salary = %.2f", totalSalary));

    // (b) Calculate the total of all of the employees' salaries using sum
    totalSalary = employees.stream().mapToDouble(b -> b.getSalary()).sum();
    System.out.println("total salary = " + totalSalary);

    // (c) Print the average of all of the employees' salaries
    double averageSalary = totalSalary /  employees.size();
    System.out.println(String.format("average salary = %.2f", averageSalary));
    
    // (d) Print only the first and last names of all of the employees
    employees.stream().map(b -> b.getFirstName()).forEach(System.out::println);
    employees.stream().map(b -> b.getLastName()).forEach(System.out::println);
    
    // (e) Print all of the unique last names of the employees 
    // sorted in ascending order
    employees.stream().map(b -> b.getLastName())
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);

    // (f) Write a lambda expression that returns true if an employee's salary
    // is in the range $4000 to $6000 inclusive and store it in the following variable
    Predicate<Employee> salaryRange = x -> (Double.compare(x.getSalary(), 4000) >= 0) 
                                        && (Double.compare(x.getSalary(), 6000) <= 0);

    
    // (g) Print all of the employees whose salaries are 
    // in the range $4000 to $6000 sorted by salary
    employees.stream().filter(salaryRange).
                      map(b -> b.getSalary()).
                      sorted().
                      forEach(System.out::println);
    
    // (h) Print the first employee with a salary that is within the range
    // $4000 to $6000
    System.out.println(employees.stream().filter(salaryRange).
                                mapToDouble(b -> b.getSalary()).
                                sorted().
                                min());
    
    // (i) Declare a comparator for comparing Employee instances 
    // by last name and then by first name
    Comparator<Employee> lastFirst = (e1, e2) -> {
                                  if (e1.getLastName().compareTo(e2.getLastName()) >= 0) {
                                    return e1.getFirstName().compareTo(e2.getFirstName());
                                  } else {
                                    return 1;
                                  }
                                };

    // (j) Sort employees by last name then by first name in ascending order
    employees.stream().sorted(lastFirst).forEach(System.out::println);
    
    // (k) Sort employees by last name, then by first name in descending order
    employees.stream().sorted(lastFirst.reversed()).forEach(System.out::println);
    
    // (l) **BONUS** Print the employees grouped by department
    employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    
    // (m) **BONUS** Print the number of employees in each department
    employees.stream().
    collect(Collectors.groupingByConcurrent(Employee::getDepartment, Collectors.counting()))
    .forEach(System.out::println);
                      
  }
}
