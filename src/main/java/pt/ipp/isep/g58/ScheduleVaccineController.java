package pt.ipp.isep.g58;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleVaccineController {

    Scanner read = new Scanner(System.in);
    VaccineController vaccineController = new VaccineController();

    /**
     * Empty constructor
     */
    public ScheduleVaccineController() {
    }

    /**
     * Gets Scheduled appointments
     */
    public void getAppointments() {
        for (Appointment i : Database.appointments) {
            System.out.println(i);
        }
    }

    /**
     * Adds new Appointment
     * @param schedule
     */
    public void setAppointment(Appointment schedule) {
        Database.appointments.add(schedule);
    }


    public void createTxtMessage(LocalDateTime date, VaccinationCenter center, String SNSNumber){
        String message = "Appointment date: " + date.toString() + "\nVaccination center: " + center.getName();
        SMSController.sendToSNS(message, SNSNumber);
    }
    
    /**
     * Checks if vaccination center has space for user.
     * @param center
     * @param date
     * @return
     */

    public boolean IsSpaceInCenter(VaccinationCenter center, LocalDateTime date, String SNS, Vaccine vaccine) {
        ArrayList<LocalDateTime> possibleHours = new ArrayList<>();

        int numberOfVaccines = 0;
        for (Appointment k : Database.appointments) { // goes through all appointments
            if (k.SNSNumber.equals(SNS) && k.getVaccineType().designation.equals(vaccine.getType().designation)) {
                numberOfVaccines++;
            }
        }


        return true;
    }

    /**
     * Schedules an appointment for all users in first vaccination center for first vaccineType (for testing purposes)
     */
    public static void scheduleExample() {
        VaccinationCenter vc = Database.centerTable.get(0);
        VaccineType vt = vc.getAllVaccineTypes().get(0);
        for (User u : Database.userTable) {
            Database.appointments.add(new Appointment(u.getSNSNumber(), vc, LocalDateTime.now(), vt));
        }
    }

}
