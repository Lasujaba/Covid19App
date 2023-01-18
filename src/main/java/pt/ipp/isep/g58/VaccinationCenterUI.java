package pt.ipp.isep.g58;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//* US9 As an admin I want to register a vaccination center to respond to a certain pandemic

public class VaccinationCenterUI {
    void register() throws ParseException {

        ArrayList<VaccineType> vaccinesKnown = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        System.out.println("""
                Please enter a type:
                1 - Healthcare\s
                2 - Mass Vaccination Center(will require a vaccine to be selected)""");
        int typeOfCenter = read.nextInt();

        while (typeOfCenter > 2 && typeOfCenter != 0) {
            System.out.println("Invalid type");
            System.out.println("""
                Please enter a type:
                1 - Healthcare\s
                2 - Mass Vaccination Center(will require a vaccine to be selected)""");
            typeOfCenter = read.nextInt();
        }

        switch (typeOfCenter) {
            case 1 -> {
                vaccinesKnown.addAll(Database.vaccineTypes); // i think its done with a controller
            }
            case 2 -> {
                int contor = 1;
                System.out.println("Please select a vaccine for your Mass Vaccination Center:");
                for (VaccineType v : Database.vaccineTypes) { //display vaccines to be added
                    System.out.println(contor + ". " + v.designation);
                    contor++;
                }

                int number = read.nextInt();
                while (number > Database.vaccineTypes.size() || number < 1) { //checks for valid input
                    System.out.println("Please select a valid vaccine");
                    number = read.nextInt();
                }
                VaccineType target = Database.vaccineTypes.get(number - 1);
                vaccinesKnown.add(target);
            }
            default -> System.out.println("Error, input not known");
        }

        System.out.println("please enter a name");
        String name = read.nextLine();
        name = read.nextLine();
        while(Validation.isValidName(name) != 0){
            System.out.println("Please enter a valid name");
            name = read.nextLine();
        }

        System.out.println("please enter a address");
        String address = read.nextLine();
        while(address.isEmpty()){
            System.out.println("Please enter a valid address");
            address = read.nextLine();
        }

        System.out.println("please enter a phone number");
        String phoneNumber = read.nextLine();
        while(Validation.isValidPhoneNumber(phoneNumber) != 0){
            System.out.println("Please enter a valid phone number");
            phoneNumber = read.nextLine();
        }

        System.out.println("Please enter an email");
        String email = read.nextLine();
        while(Validation.isValidEmail(email) != 0){
            System.out.println("Please enter a valid email");
            email = read.nextLine();
        }

        System.out.println("Please enter a fax number");
        String faxNumber = read.nextLine();
        while(Validation.isValidFaxNumber(faxNumber) != 0){
            System.out.println("Please enter a valid fax number");
            faxNumber = read.nextLine();
        }


        System.out.println("Please enter a website");
        String website = read.nextLine();
        while(Validation.isValidWebsite(website) != 0){
            System.out.println("Please enter a valid website");
            website = read.nextLine();
        }

        String oHours = null;
        System.out.println("Please enter an opening hour in the format: HH:MM:SS");
        oHours = read.nextLine();
        while(oHours == null || Validation.isValidTime(oHours) != 0) {
            System.out.println("Please insert a valid time");
            oHours = read.nextLine();
        }
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date d = dateFormat.parse(oHours);


        String  cHours = null;
        System.out.println("Please enter a closing hour in the format: HH:MM:SS");
        cHours = read.nextLine();
        while(cHours == null || Validation.isValidTime(cHours) != 0) {
            System.out.println("Please insert a valid time");
            cHours = read.nextLine();
        }
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date d2 = dateFormat.parse(cHours);

        System.out.println("Please enter the slot duration");
        int slotDuration = read.nextInt();
        while (slotDuration == 0) {
            System.out.println("Please enter a valid slot duration");
            slotDuration = read.nextInt();
        }
        System.out.println("Please enter the maximum vaccines");
        int maxVaccines = read.nextInt();
        while (maxVaccines == 0) {
            System.out.println("Please enter a valid number of maximum vaccines");
            maxVaccines = read.nextInt();
        }

        VaccinationCenterController.addVaccinationCenter(new VaccinationCenter(name,address,phoneNumber,
                email,faxNumber,website,
                d,d2,
                slotDuration,maxVaccines,typeOfCenter,
                vaccinesKnown));
    }
}
