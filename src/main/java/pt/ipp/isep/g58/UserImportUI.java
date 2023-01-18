package pt.ipp.isep.g58;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * This UI is used to import users from a file.
 */
public class UserImportUI{
    /**
     * Asks for path, imports users from file and prints out the result.
     */
    public void main(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a path of csv to import:");
        String path = scanner.nextLine();
        Map<User, String> addedUsers = UserController.loadUsers(path);
        if (addedUsers == null || addedUsers.size() <= 0) {
            System.out.println("No users added");
            return;
        }
        System.out.println("Users added: ");
        for (Map.Entry<User, String> entry : addedUsers.entrySet()) {
            System.out.print(entry.getKey().getSNSNumber() + " " + entry.getKey().getName() + " " + entry.getKey().getEmail());
            System.out.println("\tpassword: " + entry.getValue());
        }
    }
}
