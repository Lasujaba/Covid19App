package pt.ipp.isep.g58;

import java.util.Scanner;

public class VaccineTypeUI {
    String code;
    String designation;
    String description;
    public void main() {
        Scanner read = new Scanner(System.in);
        System.out.println("Please enter code for the vaccineType (4-8 Digits): ");
        try {
            code = read.nextLine();
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return;
        }

        while (Validation.isValidVaccineTypeCode(code) != 0) {
            System.out.println("Invalid code, Try again: ");
            code = read.nextLine();
        }

        System.out.println("Please enter designation: (Covid-19)...");
        try{
            designation = read.nextLine();
        }catch (Exception e){
            System.out.println("Something went wrong");
            return;
        }
        System.out.println("Give a short description of the vaccine type: ");
        try{
            description = read.nextLine();
        }catch (Exception e){
            System.out.println("Something went wrong!");
            return;
        }

        VaccineType vacc = new VaccineType(code,designation, description);
        VaccineTypeController.addVaccineType(vacc);
    }
}
