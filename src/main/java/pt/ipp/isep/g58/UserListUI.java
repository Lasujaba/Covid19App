package pt.ipp.isep.g58;

public class UserListUI {
    public void main() {
        System.out.println(User.tableHeader());
        for (User user : Database.userTable) {
            System.out.println(user.tableRow());
        }
    }
}
