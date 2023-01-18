package pt.ipp.isep.g58;

import javafx.fxml.FXML;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Allows the user to search for employees. (US011)
 */
public class EmployeeSearchUI{
    /**
     * Coordinates the search and displays the results
     */
    public void main(){
        Scanner scanner = new Scanner(System.in);
        String[] roles = EmployeeController.getRoles();
        displayRoleList(roles);
        int roleIndex = -1;
        try{
            roleIndex = Integer.parseInt(scanner.nextLine());

        }catch(NumberFormatException e){}
        while(roleIndex < 0 || roleIndex >= roles.length){
            System.out.println("Invalid role. Please enter a valid role.");
            try{
                roleIndex = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e){}
        }
        Employee[] employees = EmployeeController.getEmployees(roles[roleIndex]);
        displayEmployees(employees);
    }

    /**
     * Displays the roles in the array
     * @param roles the roles to display
     */
    private void displayRoleList(String[] roles){
        System.out.println("Roles:");
        int i = 0;
        for(String role : roles){
            System.out.println(i+") "+role);
            i++;
        }
    }

    /**
     * Displays the employees in the array
     * @param employees the employees to display
     */
    public void displayEmployees(Employee[] employees){
        System.out.println("Employees:");
        System.out.println(Employee.tableHeader());
        for(Employee employee : employees){
            System.out.println(employee.tableRow());
        }
    }

}