package pt.ipp.isep.g58;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TimerTask;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MenuController {

    private static Stage Window;

    public static void setWindow(Stage window) {
        Window = window;
    }

    public static Stage getWindow() {
        return Window;
    }

    private static EmployeeSearchUI employeeSearchUI = new EmployeeSearchUI();
    private static EmployeeRegistrationUI employeeRegistrationUI = new EmployeeRegistrationUI();
    private static UserRegistrationUI userRegistrationUI = new UserRegistrationUI();
    private static ScheduleVaccineUI scheduleVaccineUI = new ScheduleVaccineUI();
    private static VaccinationCenterUI vaccinationCenterUI = new VaccinationCenterUI();
    private static RegisterVaccineUI registerVaccineUI = new RegisterVaccineUI();
    private static RegisterArrivalUI registerArrivalUI = new RegisterArrivalUI();
    private static ConsultWaitingRoomUI consultWaitingRoomUI = new ConsultWaitingRoomUI();
    private static UserImportUI userImportUI = new UserImportUI();
    private static UserListUI userListUI = new UserListUI();

    private static VaccineTypeUI vaccineTypeUI = new VaccineTypeUI();
    private static DataFromlegacyUI dataFromlegacyUI = new DataFromlegacyUI();
    @FXML
    Button importLegacyID;
    @FXML
    Button EmployeeRegistration;
    @FXML
    Button ScheduleVaccID;

    @FXML
    private void saveData() {
        try {
            Serialize.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exportStatistics(ActionEvent event) {
        ExportStatistics.run();
    }

    @FXML
    private void showEmployeeSearchUI() {
        employeeSearchUI.main();
        showMenu();
    }

    @FXML
    private void showEmployeeRegistrationUI() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("EmployeeReg.fxml"));
        Stage window = (Stage) EmployeeRegistration.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    @FXML
    private void showUserRegistrationUI() throws IOException {
        userRegistrationUI.main();
        showMenu();
    }

    @FXML
    private void showScheduleVaccineUI() {
        try {
            scheduleVaccineUI.main();
            showMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void showVaccinationCenterUI() {
        try {
            vaccinationCenterUI.register();
            showMenu();
        } catch (Exception e) {

        }
        showMenu();
    }

    @FXML
    private void showRegisterVaccineUI() {
        registerVaccineUI.addVaccine();
        showMenu();
    }

    @FXML
    private void showRegisterArrivalUI() {
        try {
            registerArrivalUI.register();
            showMenu();
        } catch (Exception e) {

        }
        showMenu();
    }

    @FXML
    private void showConsultWaitingRoomUI() {
        try {
            consultWaitingRoomUI.consult();
            showMenu();
        } catch (Exception e) {
        }
        showMenu();
    }

    @FXML
    private void showUserImportUI() {
        userImportUI.main();
        showMenu();
    }

    @FXML
    private void showUserListUI() {
        userListUI.main();
        showMenu();
    }

    @FXML
    private void showVaccineAdministrationUI(){
        loadScene("VaccineAdministration.fxml");
    }

    /**
     * Changes scene to menu
     */
    public static void showMenu() {
        loadScene("main-menu.fxml");
    }

    private static void loadScene(String name) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(name));
            Window.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Couldn't load scene " + name);
            System.exit(-1);
        }
    }

    @FXML
    private void showVaccineTypeUI() {
        vaccineTypeUI.main();
        showMenu();
    }

    @FXML
    private void showDataFromLegacyUI() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("LegacyData.fxml"));
        Stage window = (Stage) importLegacyID.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}
