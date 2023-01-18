package pt.ipp.isep.g58;

import java.util.Scanner;

public class RegisterVaccineUI {
    //* US13 As an admin I intend to specify a new vaccine and its administration process
    public void addVaccine() { // returns a Vaccine
        VaccineType type = null;
        Scanner read = new Scanner(System.in);
        System.out.println("Please select the vaccine: ");
        int counter = 1;
        for(VaccineType t : Database.vaccineTypes){
            System.out.println(counter + ". " + t.getDesignation());
            counter++;
        }
        int choice = Integer.valueOf(read.nextLine());
        try{
            type = Database.vaccineTypes.get(choice - 1);
        }catch (Exception e){
            System.out.println("No such Vaccinetype...");
            return;
        }

        System.out.println("Please enter the brand name of the vaccine");
        String brandName = read.nextLine();
        while (brandName.isEmpty()) {
            System.out.println("Please enter a brand name");
            brandName = read.nextLine();
        }
        System.out.println("Please select the age group of the vaccine");

        System.out.println("1. 5 -> 18");
        System.out.println("2. 18+");

        int ageRange = Integer.valueOf(read.nextLine());
        while (ageRange != 1 && ageRange != 2) {
            System.out.println("Please select a valid range ");
            System.out.println(ageRange);
            ageRange = read.nextInt();
        }
        boolean ageGroup;
        if (ageRange == 1) {
            ageGroup = false;
        } else {
            ageGroup = true;
        }
        
        System.out.println("Please enter the dose number of the vaccine");
        int doseNumber = read.nextInt();
        while (doseNumber == 0) {
            System.out.println("Please enter a valid dose number");
            doseNumber = read.nextInt();
        }
        System.out.println("Please enter the dosage of the vaccine(ml)");
        int dosage = read.nextInt();
        while (dosage == 0) {
            System.out.println("Please enter a valid dosage number(ml)");
            dosage = read.nextInt();
        }
        System.out.println("Please enter the time interval of the vaccine(months)");
        int timeInterval = read.nextInt();
        while (timeInterval == 0) {
            System.out.println("Please enter a valid time interval(months)");
            timeInterval = read.nextInt();
        }
        Vaccine vaccine = new Vaccine(type, brandName, ageGroup,
                    doseNumber, dosage,timeInterval);
        VaccineController.addVaccine(vaccine);
        }
    }