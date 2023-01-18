package pt.ipp.isep.g58;

import java.util.stream.Stream;

public class VaccineController {
    Database database;

    /**
     * Removes all vaccines from the database
     */
    public static void purgeVaccines() {
        Database.centerTable.clear();
    }

    /**
     *
     * @param vaccine adds vaccination center to the database
     */
    public static void addVaccine(Vaccine vaccine) {
        Database.vaccineTable.add(vaccine);
    }
    /**
     * Creates a couple of generic vaccines and adds them to the database for test purposes
     */
    public static void createExample(){
        Database.vaccineTable.add(new Vaccine(Database.centerTable.get(0).getAllVaccineTypes().get(0), "Brand1", false, 3, 12, 12));
        Database.vaccineTable.add(new Vaccine(Database.centerTable.get(0).getAllVaccineTypes().get(0), "Brand2", false, 3, 12, 12));
    }
    /**
     * @param type Vaccine type
     * @return Stream of vaccines of the given type
     */
    public static Stream<Vaccine> getByType(VaccineType type){
        return Database.vaccineTable.stream().filter(vaccine -> vaccine.getType() == type);
    }
    public static Vaccine findInDB(Vaccine vaccine){
        for(Vaccine v : Database.vaccineTable){
            if(v.equal(vaccine)){
                return v;
            }
        }
        return null;
    }
}
