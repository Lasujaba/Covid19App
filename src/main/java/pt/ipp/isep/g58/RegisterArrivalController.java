package pt.ipp.isep.g58;

public class RegisterArrivalController {


    /**
     * adds appointment to the vaccination center queue that is on the centerIndex position
     * @param centerIndex
     * @param appointment
     */
    public void addToQueue(int centerIndex, Appointment appointment) {
        Database.centerTable.get(centerIndex).getQueue().add(appointment); //we add the appointment to the waiting list
    }
    /**
     * Registers all the appointments for test purposes
     */
    public static void regiserAll(){
        for (Appointment appointment : Database.appointments) {
            int centerIndex = Database.centerTable.indexOf(appointment.getVaccinationCenter());
            Database.centerTable.get(centerIndex).getQueue().add(appointment);
        }
    }
}
