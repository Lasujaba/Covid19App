package pt.ipp.isep.g58;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserRegistrationUI {

    public void main() {
        UserController userController = new UserController();
        Scanner read = new Scanner(System.in);

        /**
         * Asks to enter the name of the user
         */
        System.out.println("please enter the name of the user");
        String name = read.nextLine();
        Validation.isValidName(name);

        /**
         * Asks to enter the email of the user
         */
        System.out.println("please enter the email of the user");
        String email = read.nextLine();
        Validation.isValidEmail(email);


        /**
         * Asks to enter the password of the user
         */
        System.out.println("please enter the password of the user (At least 6 characters long, must contain 1 upper- and 1 lowercase letter and 1 special character) ");
        String password = read.nextLine();
        Validation.isValidPassword(password);
        /**
         * Asks to enter the SNSNumber of the user
         */
        System.out.println("Please enter the SNSNumber of the user (9 numbers long) ");
        String SNSNumber = read.nextLine();
        Validation.isValidSNSNumber(SNSNumber);
        /**
         * Asks to enter the phoneNumber of the user
         */
        System.out.println("Please enter the phoneNumber of the user");
        String phoneNumber = read.nextLine();
        Validation.isValidPhoneNumber(phoneNumber);
        /**
         * Asks to enter the address of the user
         */
        System.out.println("Please enter the address of the user");
        String address = read.nextLine();
        /**
         * Asks to enter sex of the user
         */
        System.out.println("Please enter sex of the user");
        String sex = read.nextLine();
        /**
         * Asks to enter birthday of the user
         */
        System.out.println("Please enter birthday of the user dd/MM/yyyy");
        String bdayStr = read.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date bday;
        try {
            bday = formatter.parse(bdayStr);
        } catch (java.text.ParseException e) {
            return;
        }
        /**
         * Asks to enter the citizen card number of the user
         */
        System.out.println("Please enter the citizen card number of the user");
        String citizenCardNumber = read.nextLine();
        /**
         * Create new User
         */
        User user = new User(name, email, SNSNumber, password, sex, bday, address, phoneNumber, citizenCardNumber);
        userController.addUser(user);
    }

}
