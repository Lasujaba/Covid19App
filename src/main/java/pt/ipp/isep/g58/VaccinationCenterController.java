package pt.ipp.isep.g58;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class VaccinationCenterController {

/**
 * Acts as a middleman between the UI and database interface.
 */


    /**
     *
     * @return array of all vaccines possible
     */
    public static ArrayList<VaccineType> getVaccines(VaccinationCenter v) {
        ArrayList<VaccineType> vaccines = new ArrayList<>();
        vaccines.addAll(v.getAllVaccineTypes());
        return vaccines;
    }


    /**
     * Removes all vaccination centers from the database
     */
    public static void purgeVaccinationCenter() {
        Database.centerTable.clear();
    }

    /**
     *
     * @param v adds vaccination center to the database
     */
    public static void addVaccinationCenter(VaccinationCenter v) {
        Database.centerTable.add(v);
    }

    /**
     * checks if there is no vaccination centers
     * @return
     */
    public static boolean isEmptyVaccinationCenterDatabase(){return Database.centerTable.isEmpty();}

    public static int chooseVaccinationCenter() {
        if (VaccinationCenterController.isEmptyVaccinationCenterDatabase()) {
            System.out.println("Please register a center first");
            return -1;

        }
        Scanner read = new Scanner(System.in);
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
        return center;
    }

    /**
     *
     * @param center
     * @return the first appointment in the queue
     */
    public static Appointment firstInLine(int center) {
        return Database.centerTable.get(center).getQueue().peek();
    }


    public static VaccinationCenter getVaccinationCenter(int center) {return Database.centerTable.get(center);}

    /**
     * deletes the first element in the queue
     * @param center
     */
    public static void popFirst(int center) {
        Database.centerTable.get(center).getQueue().remove();
    }

    public static boolean isEmptyQueue(int center) {
        return Database.centerTable.get(center).getQueue().isEmpty();
    }

    /**
     * clears the queue
     * @param center
     */
    public static void purgeVaccinatedToday(int center) {
        Database.centerTable.get(center).getVaccinatedToday().clear();
    }

    /**
     *
     * @param name the name of the vaccination center
     * @return center with the name or null if not found
     */
    public static VaccinationCenter getByName(String name) {
        for (VaccinationCenter v : Database.centerTable) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }
    /**
     * Creates a couple of vaccination centers and adds them to the database for testing purposes
     */
    public static void createExample(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        ArrayList<VaccineType> vaccines = new ArrayList<>();
        vaccines.addAll(Database.vaccineTypes);
        try {
            VaccinationCenter vc = new VaccinationCenter("Vaccination Center 1", "Addr1", "123456789", "mail@mail.com", "123456789", "www.vc.com", dateFormat.parse("07:30:00"), dateFormat.parse("19:00:00"), 10, 5, 1, vaccines);
            Database.centerTable.add(vc);
            VaccinationCenter vc2 = new VaccinationCenter("Vaccination Center 2", "Addr2", "987654321", "mail2@mail.com", "987654321", "www.vc2.com", dateFormat.parse("07:30:00"), dateFormat.parse("19:00:00"), 10, 5, 1, vaccines);
            Database.centerTable.add(vc2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
