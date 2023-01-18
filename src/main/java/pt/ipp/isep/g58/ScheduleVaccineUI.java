package pt.ipp.isep.g58;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ScheduleVaccineUI {
    private String diseaseName;
    private VaccineType type;
    Vaccine vaccine = null;
    Scanner read = new Scanner(System.in);
    VaccineController vaccineController = new VaccineController();
    int error = 0;
    User user = null;
    public void main() throws Exception {

        String pattern = "yyyy/MM/dd HH:mm";
        String pattern2 = "yyyy/MM/dd HH:mm:ss";
        Validation validation;
        ScheduleVaccineController scheduleVaccineController = new ScheduleVaccineController();
        String SNS = "";

        /**
         * Select SNS Number
         */
        System.out.println("Enter your SNS Number if you are a valid user(9 digits): ");
        SNS = read.nextLine();


        User usr = null;
        for (User user : Database.userTable) {
            if (user.getSNSNumber().equals(SNS)) {
                usr = user;
                break;
            }
        }
        if(usr == null){
            System.out.println("User doesn't exist");
            return;
        }

        /**
         * Select vaccination center
         */
        if(Database.centerTable.isEmpty()){
            System.out.println("There are no vaccination center");
            return;
        }
        int indexCenter = VaccinationCenterController.chooseVaccinationCenter();
        VaccinationCenter selectedCenter = VaccinationCenterController.getVaccinationCenter(indexCenter);


        /**
         * Ask for disease.
         * Returns string diseaseName from vaccine
         */
        type = selectedVaccineName();


        /**
         * Select date for the appointment.
         */
        System.out.println("Select the date, The format should be (yyyy/MM/dd HH:mm): ");
        String dateNotValidatedYet = read.nextLine();

        /**
         * Validates that date string is in the right format.
         */
        String dateValidated = Validation.isValidScheduleTime(dateNotValidatedYet);
        DateTimeFormatter smForm = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime date = LocalDateTime.parse(dateValidated, smForm);

        scheduleVaccineController.createTxtMessage(date, selectedCenter, SNS);
        Appointment appointment = new Appointment(SNS, selectedCenter, date, type);
        System.out.println(SNS + " " + selectedCenter.getName() + " " + date + " " + type.designation);
        scheduleVaccineController.setAppointment(appointment);
    }


    /**
     * Asks the user for disease and lists available diseases. Then uses switch to select users disease from database.
     * @return diseaseName
     */
    public VaccineType selectedVaccineName(){
        String diseaseName = "";
        System.out.println("What disease do you have? (Default: Covid-19)  ");
        int counter = 1;
        int vacc = 0;
        boolean okay = false;
        for (VaccineType v : Database.vaccineTypes) {
            System.out.println(counter + ". " + v.designation);
            counter++;
        }
        try {
            vacc = Integer.valueOf(read.nextLine());
            okay = true;
        }catch (Exception e){
            System.out.println("Something went wrong, try again.");
        }
        if(vacc == 0){
            type = Database.vaccineTypes.get(vacc);
        }
        else if (vacc > 0 && vacc <= Database.vaccineTypes.size()){
            type = Database.vaccineTypes.get(vacc - 1);
        }else{
            System.out.println("Invalid input");
            return null;
        }
        return type;
    }
}
