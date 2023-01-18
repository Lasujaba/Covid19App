package pt.ipp.isep.g58;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Appointments
 */
public class Appointment implements Serializable {
    VaccinationCenter vaccinationCenter;
    LocalDateTime date;
    String SNSNumber;
    VaccineType vaccineType;
    Vaccine vaccineUsed;
    LocalDateTime vaccinationDate;


    /**
     *
     * @param SNSNumber = SNSNumber
     * @param inputVaccinationCenter = Vaccination center
     * @param date = date
     * @param inputVaccine = Vaccine
     */
    public Appointment(String SNSNumber, VaccinationCenter inputVaccinationCenter, LocalDateTime date,
                       VaccineType inputVaccine){
        this.SNSNumber = SNSNumber;
        this.vaccinationCenter = inputVaccinationCenter;
        this.date = date;
        this.vaccineType = inputVaccine;

    }


    /**
     *
     * @return vaccinationCenter
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     *
     * @param vaccinationCenter
     */
    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    /**
     *
     * @return SNSNumber
     */
    public String getSNSNumber() {
        return SNSNumber;
    }

    /**
     *
     * @param SNSNumber
     */
    public void setSNSNumber(String SNSNumber) {
        this.SNSNumber = SNSNumber;
    }

    /**
     *
     * @return date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     *
     * @param date sets date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     *
     * @return vaccineType
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     *
     * @param vaccineType sets vaccineType
     */
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    /**
     *
     * @return vaccineUsed
     */
    public Vaccine getVaccineUsed() {
        return vaccineUsed;
    }

    /**
     *
     * @param vaccineUsed
     */
    public void setVaccineUsed(Vaccine vaccineUsed) {
        this.vaccineUsed = vaccineUsed;
    }

    /**
     *
     * @return vaccinationDate
     */
    public LocalDateTime getVaccinationDate() {
        return vaccinationDate;
    }

    /**
     *
     * @param vaccinationDate sets the vaccinationDate
     */
    public void setVaccinationDate(LocalDateTime vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }


}
