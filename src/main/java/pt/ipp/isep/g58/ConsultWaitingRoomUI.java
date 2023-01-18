package pt.ipp.isep.g58;

import java.time.LocalDate;

public class ConsultWaitingRoomUI {

    public void consult() {
        int selectedCenter = VaccinationCenterController.chooseVaccinationCenter();
        if (selectedCenter == -1) {
            return;
        }

        if (VaccinationCenterController.isEmptyQueue(selectedCenter)) {
            System.out.println("The queue is empty right now");
            return;
        }
        System.out.println("Appointment Date\tVaccineType\t"+User.shortTableHeader());
        for (Appointment a: Database.centerTable.get(selectedCenter).getQueue()) {
            System.out.println(a.date.format(Formatters.DateTime) + "\t" + a.vaccineType.getDesignation() + "\t" +
                    UserController.getFromSNS(a.getSNSNumber()).getName() + "\t" +
                    UserController.getFromSNS(a.getSNSNumber()).getAge() + "\t" +
                    UserController.getFromSNS(a.getSNSNumber()).getSex() + "\t" +
                    a.getSNSNumber());
        }
    }
}