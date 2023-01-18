package pt.ipp.isep.g58;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RegisterArrivalUI {

    /**
     * Registeres the arrival of a SNS User
     * @throws ParseException
     */

    public void register() throws ParseException {
        Scanner read = new Scanner(System.in);
        RegisterArrivalController controller = new RegisterArrivalController();

        if (VaccinationCenterController.isEmptyVaccinationCenterDatabase()) {
            System.out.println("Please register a center first");
            return;

        }

        System.out.println("Please select the vaccination center: ");
        int iterator = 1;
        for (VaccinationCenter v : Database.centerTable) {
            System.out.println(iterator + " " + v.getName());
            iterator++;
        }

        int center = Integer.valueOf(read.nextLine());
        while (center > Database.centerTable.size()) {
            System.out.println("Please select a center within range 1 - " + Database.centerTable.size());
            center = Integer.valueOf(read.nextLine());
        }

        center--; //because the counting starts at 0, the user inputs a bigger value

        System.out.println("You have selected " + Database.centerTable.get(center).getName());


        System.out.println("Please type the SNS Number");
        String SNSNumber = null;
        SNSNumber = read.nextLine();
        while(Validation.isValidSNSNumber(SNSNumber) != 0) {
            System.out.println("Please type a valid SNS number");
            SNSNumber = read.nextLine();
        }


        LocalDateTime tenMinutesLate = LocalDateTime.now().plusMinutes(10);
        LocalDateTime fifteenMinutesEarly = LocalDateTime.now().minusMinutes(15);


        boolean accepted = false;
        if (Database.appointments.isEmpty()) {
            System.out.println("No appointments!");
            return;
        }



        Appointment foundAppointment = null;

        for(Appointment a : Database.appointments) {
            if (a.getVaccineUsed() != null) {
                continue;
            }
            if (a.getSNSNumber().equals(SNSNumber)) { // checks for SNS Number match
                if (a.getDate().isAfter(tenMinutesLate)) {//check if user came too late
                    System.out.println("User came too late.");
                    System.out.println("Expected: " + a.getDate());
                    return;
                }
                if (a.getDate().isBefore(fifteenMinutesEarly)) { //he’s allowed to be 15 min early
                    System.out.println("User came too early.");
                    System.out.println("Expected: " + a.getDate());
                    return;
                }
                accepted = true; //accepted arrival
                foundAppointment = a;
                break;
            }
        }
        if (!accepted) {
            System.out.println("Invalid arrival");
            return;
        }

        System.out.println("User arrived in time");
        VaccinationCenter v = Database.centerTable.get(center);
        System.out.println("Target center: " + v.getName() + " with size of queue " + v.getQueue().size());

        if(v.getQueue().isEmpty()) {
            controller.addToQueue(center, foundAppointment); //using controller
        } else {
                //check if the user has already been registered
                if (v.getQueue().contains(foundAppointment)) {
                    System.out.println("User already in the queue");
                } else {
                    System.out.println("Registration complete");
                    controller.addToQueue(center, foundAppointment); //using controller
                }
            }
        System.out.println("Current queue: " + Database.centerTable.get(center).getQueue().size());
    }
}
