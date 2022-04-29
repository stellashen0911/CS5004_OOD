package organization;

import employees.Employee;

/**
 * Interface for iterable organizations.
 */
public interface IterableOrganization extends Organization, Iterable<Employee> {
}
