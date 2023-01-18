package pt.ipp.isep.g58;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class ExampleCreator {
    /**
     * Creates some example objects to test the functionality of the program.
     */
    public static void createExamples(){
        VaccineTypeController.createExample();
        VaccinationCenterController.createExample();
        UserController.createExample();
        ScheduleVaccineController.scheduleExample();
        RegisterArrivalController.regiserAll();
        VaccineController.createExample();
        addVaccinated();
    }

    private static void addVaccinated(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ScheduleVaccineController svc = new ScheduleVaccineController();
        RegisterArrivalController rac = new RegisterArrivalController();
        try {
            UserController.addUser(new User("Vaccinated", "vacc@ema.com", "234234234", "Pass", "Male", formatter.parse("12/12/2002"), "VaccAddr", "234234234", "234234234"));
            VaccinationCenter vc = Database.centerTable.get(0);
            VaccineType vt = vc.getAllVaccineTypes().get(0);
            Appointment ap = new Appointment("234234234", vc, LocalDateTime.now().minusMinutes(43200), vt);
            svc.setAppointment(ap);
            //Vaccine v = Database.vaccineTable.stream().filter(vs -> vs.getType().equals(vt)).findFirst().get();
            Vaccine v = new Vaccine(vt, 1, "E1C1D-21", "Brandx", true, 2, 12, 12);
            Database.vaccineTable.add(v);
            ap.setVaccineUsed(v);
            Appointment ap2 = new Appointment("234234234", vc, LocalDateTime.now(), vt);
            svc.setAppointment(ap2);
            rac.addToQueue(Database.centerTable.indexOf(vc), ap2);
            Database.vaccineTable.add(new Vaccine(vt, 1, null, "Brand3", true, 1, 12, 12));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
