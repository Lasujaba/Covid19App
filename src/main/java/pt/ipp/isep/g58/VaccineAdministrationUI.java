package pt.ipp.isep.g58;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VaccineAdministrationUI implements Initializable {
    @FXML
    private ComboBox vaccinationCenterComboBox;
    @FXML
    private TableView<Appointment> QueueTable;
    @FXML
    private TableView<Vaccine> VaccineTable;
    @FXML
    private TextField LOTTextField;
    @FXML
    private Button RegisterVaccineButton;
    @FXML
    private Button ExitButton;


    private VaccinationCenter selectedVaccinationCenter;
    private Appointment selectedAppointment;
    private Vaccine selectedVaccine;
    private int recoveryPeriod;

    /**
     * Initializes the GUI.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateCenters();
        initQueueTable();
        initVaccineTable();
        setRegisterVaccineButtonDisable();
        LOTTextField.textProperty().addListener((observable, oldValue, newValue) -> onLOTChange());
        recoveryPeriod = Config.getInt("recoveryPeriod");
    }
    private void initQueueTable() {
        QueueTable.getColumns().clear();
        TableColumn SNSNumberColumn = new TableColumn("SNSNumber");
        TableColumn vaccineColumn = new TableColumn("Vaccine Type");
        TableColumn dateColumn = new TableColumn("Appointment date");
        SNSNumberColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("SNSNumber"));
        SNSNumberColumn.prefWidthProperty().setValue(90);
        vaccineColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Appointment, String> cellDataFeatures) {
                return Bindings.createStringBinding(() -> cellDataFeatures.getValue().getVaccineType().designation);
            }
        });
        vaccineColumn.prefWidthProperty().setValue(100);
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Appointment, String> cellDataFeatures) {
                return Bindings.createStringBinding(() -> Formatters.DateTimeSeconds.format(cellDataFeatures.getValue().getDate()));
            }
        });
        dateColumn.prefWidthProperty().setValue(200);
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Appointment, String> cellDataFeatures) {
                return Bindings.createStringBinding(() -> UserController.getFromSNS(cellDataFeatures.getValue().getSNSNumber()).getName());
            }
        });
        nameColumn.prefWidthProperty().setValue(150);
        TableColumn ageColumn = new TableColumn("Age");
        ageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, Integer>, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Appointment, Integer> cellDataFeatures) {
                return Bindings.createIntegerBinding(() -> UserController.getFromSNS(cellDataFeatures.getValue().getSNSNumber()).getAge());
            }
        });
        QueueTable.getColumns().addAll(SNSNumberColumn, vaccineColumn, dateColumn, nameColumn, ageColumn);
        QueueTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> onSelectAppointment());
    }
    private void initVaccineTable() {
        VaccineTable.getColumns().clear();
        TableColumn BrandColumn = new TableColumn("Brand");
        BrandColumn.setCellValueFactory(new PropertyValueFactory<Vaccine, String>("brandName"));
        BrandColumn.prefWidthProperty().setValue(170);
        TableColumn DosageColumn = new TableColumn("Dosage");
        DosageColumn.setCellValueFactory(new PropertyValueFactory<Vaccine, String>("dosage"));
        DosageColumn.prefWidthProperty().setValue(70);
        VaccineTable.getColumns().addAll(BrandColumn, DosageColumn);
        VaccineTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> onSelectVaccine());
    }
    private void populateCenters(){
        for (VaccinationCenter vaccinationCenter: Database.centerTable) {
            vaccinationCenterComboBox.getItems().add(vaccinationCenter.getName());
        }
    }
    @FXML
    private void onCenterSelect(ActionEvent event) {
        selectedVaccinationCenter = VaccinationCenterController.getByName(vaccinationCenterComboBox.getValue().toString());
        populateQueue();
        VaccineTable.getItems().clear();
        selectedVaccine = null;
        selectedAppointment = null;
        setRegisterVaccineButtonDisable();
    }
    private void populateQueue(){
        QueueTable.getItems().clear();
        for (Appointment appointment: selectedVaccinationCenter.getQueue()) {
            QueueTable.getItems().add(appointment);
        }
    }

    private void onSelectAppointment(){
        int selectedIndex = QueueTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }
        selectedAppointment = QueueTable.getItems().get(selectedIndex);
        populateVaccineTable();
        selectedVaccine = null;
        setRegisterVaccineButtonDisable();
    }

    private void populateVaccineTable(){
        VaccineTable.getItems().clear();
        Optional<Appointment> previousAppointment = AppointmentController.getLastAppointment(selectedAppointment.getSNSNumber(), selectedAppointment.getVaccineType());
        if(previousAppointment.isPresent()){
            VaccineTable.getItems().add(previousAppointment.get().getVaccineUsed());
        }else{
            List<Vaccine> possibleVaccines = VaccineController.getByType(selectedAppointment.getVaccineType()).sorted((v1, v2) -> v1.getlotNumber()==null?-1:v2.getlotNumber()==null?1:0).toList();
            for (Vaccine vaccine: possibleVaccines) {
                if (VaccineTable.getItems().stream().anyMatch(v -> v.getBrandName().equals(vaccine.getBrandName()))) {
                    continue;
                }
                VaccineTable.getItems().add(vaccine);
            }
        }
    }
    private void onSelectVaccine(){
        int selectedIndex = VaccineTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }
        selectedVaccine = VaccineTable.getItems().get(selectedIndex);
        setRegisterVaccineButtonDisable();
    }

    @FXML
    private void onAdministerVaccine() {
        if(selectedAppointment == null || selectedVaccine == null){
            return;
        }

        Vaccine currentVaccine = selectedVaccine.clone();
        currentVaccine.setLotNumber(LOTTextField.getText());
        if(currentVaccine.getDose() == -1){
            currentVaccine.setDose(0);
        }
        currentVaccine.setDose(currentVaccine.getDose() + 1);
        Vaccine currentVaccineDB = VaccineController.findInDB(currentVaccine);
        if (currentVaccineDB != null) {
            currentVaccine = currentVaccineDB;
        } else {
            Database.vaccineTable.add(currentVaccine);
        }

        selectedAppointment.setVaccineUsed(currentVaccine);
        selectedAppointment.setVaccinationDate(LocalDateTime.now());

        scheduleSMS(selectedAppointment.getSNSNumber());

        selectedVaccinationCenter.getQueue().remove(selectedAppointment);
        populateQueue();
        VaccineTable.getItems().clear();
        selectedAppointment = null;
        selectedVaccine = null;
        setRegisterVaccineButtonDisable();
    }

    private void scheduleSMS(String snsNumber){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        SMSController.sendToSNS("You have finished your recovery period.", snsNumber);
                    }
                },
                recoveryPeriod * 1000
        );
    }

    private void onLOTChange(){
        setRegisterVaccineButtonDisable();
    }

    private void setRegisterVaccineButtonDisable() {
        if (selectedVaccine == null) {
            RegisterVaccineButton.setDisable(true);
            return;
        }
        if (selectedAppointment == null) {
            RegisterVaccineButton.setDisable(true);
            return;
        }
        if (Validation.isValidLOT(LOTTextField.getText())) {
            RegisterVaccineButton.setDisable(false);
        } else {
            RegisterVaccineButton.setDisable(true);
        }
    }

    @FXML
    private void onExit(){
        MenuController.showMenu();
    }
}
