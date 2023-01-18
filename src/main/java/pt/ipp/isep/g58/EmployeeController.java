package pt.ipp.isep.g58;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a middleman between the UI and database interface.
 */
public class EmployeeController {
    /**
     * @return array of possible roles as strings
     */
    public static String[] getRoles() {
        Role[] roles = Role.values();
        String[] rolesString = new String[roles.length];
        for (int i = 0; i < roles.length; i++) {
            rolesString[i] = roles[i].toString();
        }
        return rolesString;
    }

    /**
     * @param roleString role as an uppercase string
     * @return array of employees with the given role
     */
    public static Employee[] getEmployees(String roleString) {
        Role role = Role.valueOf(roleString);
        List<Employee> employees = new ArrayList<>();
        Database.employeeTable.forEach(employee -> {
            if (employee.getRole() == role) {
                employees.add(employee);
            }
        });
        return employees.toArray(new Employee[employees.size()]);
    }

    /**
     * Removes all employees from the database.
     */
    public static void purgeEmployees() {
        Database.employeeTable.clear();
    }

    /**
     * adds an employee to the database
     * @param employee employee to add
     */
    public static void addEmployee(Employee employee) {
        Database.employeeTable.add(employee);
    }

    /**
     * Converts enum role to string
     *
     * @param role role enum
     * @return role converted to string
     */
    public static String getRoleString(Role role) {
        return role.toString();
    }

    /**
     * Converts string to enum role
     *
     * @param roleString role as an uppercase string
     * @return role converted to enum
     */
    public static Role getRole(String roleString) {
        return Role.valueOf(roleString);
    }

    /**
     * enum of possible roles
     */
    public static enum Role{
        ADMIN,
        RECEPTIONIST,
        NURSE,
        COORDINATOR
    }
}