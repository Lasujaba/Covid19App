package pt.ipp.isep.g58;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DataFromlegacyUI {
    @FXML
    TextArea textAr;
    @FXML
    public TextField filePath;
    @FXML
    Button returnButton;

    @FXML
    public static void main(String[] args) {
    }

    /**
     * Sorts data by Arrivaltime
     * @param event
     * @throws ParseException
     */
    @FXML
    public void sortByArrival(ActionEvent event) {
        // Algorithm changed !!
        List<DataFromLegacy> list = null;
        try{
            list = DataFromLegacyController.loadDataFromLegacy(filePath.getText());
        }catch (Exception e){
            textAr.setText("Error occurred when trying to load data.");
            return;
        }
        Comparator<DataFromLegacy> comparator = Comparator.comparing(DataFromLegacy::getArrivalDate);
        int counter = 0;
        Collections.sort(list, comparator);
        StringBuilder str = new StringBuilder();
        for (DataFromLegacy lv : list) {
            String userName = DataFromLegacyController.getUserName(lv.getSNSNumber());
            String desc = DataFromLegacyController.getVaccineDescription(lv.getVaccineName());
            str.append("SNS Number: " + lv.getSNSNumber() + ", Name: " + userName + ", Vaccine name: " +
                    lv.getVaccineName() + ", description: " + desc + ", Dose: " + lv.getDose() + ", LotNumber: " + lv.getLotNumber() +
                    ", Scheduled Time: " + lv.getScheduledDate() + ", Arrival time: " + lv.getArrivalDate() +
                    ", NurseAdministrationTime: " + lv.getNurseAdministratorTime() + ", Leaving time: " + lv.getLeavingDate() + "\n");
        }
        textAr.setText(str.toString());
    }

    /**
     * Sorts data by leaving time
     * @throws ParseException
     */
    @FXML
    public void sortByLeaving() {
        List<DataFromLegacy> list = null;
        try {
            list = DataFromLegacyController.loadDataFromLegacy(filePath.getText());
        }catch (Exception e){
            textAr.setText("Error occurred when trying to load data.");
        }
        List<DataFromLegacy> list2 = insertionSort(list);
        StringBuilder str = new StringBuilder();
        for (DataFromLegacy lv : list2) {
            String userName = DataFromLegacyController.getUserName(lv.getSNSNumber());
            String desc = DataFromLegacyController.getVaccineDescription(lv.getVaccineName());
            str.append("SNS Number: " + lv.getSNSNumber() + ", Name: " + userName + ", Vaccine name: " +
                    lv.getVaccineName() + ", description: " + desc + ", Dose: " + lv.getDose() + ", LotNumber: " + lv.getLotNumber() +
                    ", Scheduled Time: " + lv.getScheduledDate() + ", Arrival time: " + lv.getArrivalDate() +
                    ", NurseAdministrationTime: " + lv.getNurseAdministratorTime() + ", Leaving time: " + lv.getLeavingDate() + "\n");
        }
        textAr.setText(str.toString());
    }

    /**
     * Returns to menu
     * @throws IOException
     */
    @FXML
    public void returnMenu(){
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("main-menu.fxml"));
        }catch (Exception e){
            textAr.setText("Error occurret loading main menu.");
        }
        Stage window = (Stage) returnButton.getScene().getWindow();
        window.setScene(new Scene(root, 320, 240));
    }

    /**
     * Clears textArea
     */
    @FXML
    public void clearField() {
        textAr.clear();
    }

    /**
     *
     * @param array
     * @return
     */
    public static List<DataFromLegacy> insertionSort(List<DataFromLegacy> array) {
        for (int i = 1; i < array.size(); i++) {
            DataFromLegacy current = array.get(i);
            int j = i - 1;
            while(j >= 0 && current.getLeavingDate().isBefore(array.get(j)
                    .getLeavingDate())) {
                array.set(j+1, array.get(j));
                j--;
            }
            array.set(j+1, current);
        }
        return array;
    }
}


