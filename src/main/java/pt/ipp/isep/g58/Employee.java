package pt.ipp.isep.g58;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.exit;

public class Employee implements Serializable {
    private String name;
    private String surname;
    private String email;
    private EmployeeController.Role role;
    private byte[] passwordHash;

    /**
     * @param name employee's name
     * @param surname employee's surname
     * @param email employee's email
     * @param role employee's role (as enum)
     * @param password employee's plain text password
     */
    public Employee(String name, String surname, String email, EmployeeController.Role role, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.passwordHash = getPasswordHash(password);
    }

    /**
     * @param name employee's name
     * @param surname employee's surname
     * @param email employee's email
     * @param role employee's role (capital letters only)
     * @param password employee's plain text password
     */
    public Employee(String name, String surname, String email, String role, String password) {
        this(name, surname, email, EmployeeController.Role.valueOf(role), password);
    }

    /**
     * @return employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return employee's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname new surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return employee's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email new email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return employee's role
     */
    public EmployeeController.Role getRole() {
        return role;
    }

    /**
     * @param role new role to set
     */
    public void setRole(EmployeeController.Role role) {
        this.role = role;
    }

    /**
     * @return stored password hash
     */
    public byte[] getPasswordHash() {
        return passwordHash;
    }

    /**
     * sets new password hash
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }
    private byte[] getPasswordHash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No SHA-256 algorithm???");
            exit(1);
        }
        // Using email as salt
        return md.digest((email+password).getBytes());
    }

    /**
     * hashes the password and stores the hash
     * @param password new password to set
     */
    public void setPassword(String password) {
        this.passwordHash = getPasswordHash(password);
    }

    /**
     * hashes the password and compares it to the stored hash
     * @param password the password to check
     * @return is the password correct?
     */
    public boolean verifyPassword(String password) {
        return passwordHash.equals(getPasswordHash(password));
    }

    /**
     * @return a string with header for tableRow method (tab separated column names)
     */
    public static String tableHeader() {
        return "Name\tSurname\tEmail\tRole";
    }

    /**
     * @return a string with the employee's data separated by tabs
     */
    public String tableRow() {
        return name + "\t" + surname + "\t" + email + "\t" + role;
    }
}
