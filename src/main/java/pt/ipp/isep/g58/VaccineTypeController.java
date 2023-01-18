package pt.ipp.isep.g58;

public class VaccineTypeController {
    public static void addVaccineType(VaccineType vacc){Database.vaccineTypes.add(vacc);}

    public static String getVaccineDescription(VaccineType vacc){
        return vacc.getDescription();
    }

    /**
     * Creates a couple of vaccine types and adds them to the database for test purposes
     */
    public static void createExample(){
        Database.vaccineTypes.add(new VaccineType("1", "Vaccine 1", "This is the first vaccine"));
        Database.vaccineTypes.add(new VaccineType("2", "Vaccine 2", "This is the second vaccine"));
    }
}
