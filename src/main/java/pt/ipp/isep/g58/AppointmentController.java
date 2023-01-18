package pt.ipp.isep.g58;

import java.util.Optional;

public class AppointmentController {
    /**
     *
     * @param SNSNumber
     * @param vaccineType
     * @return Returns the appointment of the patient with the given SNSNumber and the given vaccineType.
     */
    public static Optional<Appointment> getLastAppointment(String SNSNumber, VaccineType vaccineType) {
        return Database.appointments.stream().sorted((a1, a2) -> a1.getDate().compareTo(a2.getDate())).filter(a -> a.getVaccineType() == vaccineType && a.getSNSNumber() == SNSNumber && a.getVaccineUsed() != null).findFirst();
    }
}
