package pt.ipp.isep.g58;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * Allows Admin to register employees
 * @return employee
 */
public class EmployeeRegistrationUI {
    final int passwordLength = 6;
    Validation validation;
    public TextField firstName, surName, emailField, passwd, roleField;
    @FXML
    TextArea errorArea;
    Text testField;
    @FXML
    Button submitButton, returnButton;
    String name = "";
    String surname = "";
    String email = "";
    String password = "";
    int roleChoise = 0;
    String role = null;
    public void main() {
    }
    @FXML
    private void handleSubmitButton() throws InterruptedException, IOException {

        try {
            name = firstName.getText();
        }catch (Exception e){
            System.out.println("Something went wrong");
            errorArea.setText("Couldn't read from the name field");
        }

        if(validation.isValidName(name) != 0){
            errorArea.setText("Your name is not valid");
            return;
        };

        /**
         * Asks the surname of the employee and validates it
         */
        try{
            surname = surName.getText();
        }catch (Exception e){
            System.out.println("Something went wrong");
            errorArea.setText("Couldn't read from the surname field");
        }
        if (Validation.isValidName(surname) != 0){
            errorArea.appendText("\nYour surname is not valid");
            return;
        }

        /**
         * Asks the email of the employee and validates it
         */
        try{
            email = emailField.getText();
        }catch (Exception e){
            System.out.println("Something went wrong");
            errorArea.setText("Couldn't read from email field");
        }

        if (Validation.isValidEmail(email) != 0){
            errorArea.appendText("\nYour email is not valid");
            return;
        }

        /**
         * Asks the password for the employee and validates it
         */
        try{
            password = passwd.getText();
        }catch (Exception e){
            System.out.println("Something went wrong");
            errorArea.setText("Couldn't read from password field");
        }

        if (Validation.isValidPassword(password) != 0){
            errorArea.appendText("Your password is not valid. It should contain 6 characters, one upper- and one lowercase letter, 1 number and one special character");
            return;
        }

        String[] roles = EmployeeController.getRoles();
        int roleIndex = -1;
        try{
            roleIndex = Integer.parseInt(roleField.getText());

        }catch(NumberFormatException e){}
        while(roleIndex < 0 || roleIndex >= roles.length){
            errorArea.setText("Role index wrong");
            try{
                roleIndex = Integer.parseInt(roleField.getText());
            } catch(NumberFormatException e){}
        }
        role = roles[roleIndex];


        Employee employee = new Employee(name,surname,email,role,password);
        EmployeeController.addEmployee(employee);
        firstName.clear();
        surName.clear();
        emailField.clear();
        roleField.clear();
        passwd.clear();
    }

    public void handleReturnButton() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("main-menu.fxml"));
        Stage window = (Stage)returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 320,240));
    }
}
