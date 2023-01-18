package pt.ipp.isep.g58;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class DataFromLegacy implements Serializable {
    private String SNSNumber;
    String userName;
    private String VaccineName;
    private String Dose;
    private String LotNumber;
    private LocalDateTime ScheduledDate;
    private LocalDateTime ArrivalDate;
    private LocalDateTime NurseAdministratorTime;
    private  LocalDateTime LeavingDate;

    /**
     *
     * @param SNSNumber
     * @param vaccineName
     * @param dose
     * @param lotNumber
     * @param scheduledDate
     * @param arrivalDate
     * @param nurseAdministratorTime
     * @param leavingDate
     */
    public DataFromLegacy(String SNSNumber, String vaccineName, String dose, String lotNumber, LocalDateTime scheduledDate, LocalDateTime arrivalDate, LocalDateTime nurseAdministratorTime, LocalDateTime leavingDate) {
        this.SNSNumber = SNSNumber;
        VaccineName = vaccineName;
        Dose = dose;
        LotNumber = lotNumber;
        ScheduledDate = scheduledDate;
        ArrivalDate = arrivalDate;
        NurseAdministratorTime = nurseAdministratorTime;
        LeavingDate = leavingDate;
    }

    /**
     *
     * @param SNSNumber
     * @param userName
     * @param vaccineName
     * @param dose
     * @param lotNumber
     * @param scheduledDate
     * @param arrivalDate
     * @param nurseAdministratorTime
     * @param leavingDate
     */
    public DataFromLegacy(String SNSNumber,String userName, String vaccineName, String dose, String lotNumber, LocalDateTime scheduledDate, LocalDateTime arrivalDate, LocalDateTime nurseAdministratorTime, LocalDateTime leavingDate) {
        this.SNSNumber = SNSNumber;
        VaccineName = vaccineName;
        Dose = dose;
        LotNumber = lotNumber;
        ScheduledDate = scheduledDate;
        ArrivalDate = arrivalDate;
        NurseAdministratorTime = nurseAdministratorTime;
        LeavingDate = leavingDate;
        this.userName = userName;
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
     * @return VaccineName
     */
    public String getVaccineName() {
        return VaccineName;
    }

    /**
     *
     * @param vaccineName
     */
    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }

    /**
     *
     * @return dose
     */
    public String getDose() {
        return Dose;
    }

    /**
     *
     * @param dose
     */
    public void setDose(String dose) {
        Dose = dose;
    }

    /**
     *
     * @return lotNumber
     */
    public String getLotNumber() {
        return LotNumber;
    }

    /**
     *
     * @param lotNumber
     */
    public void setLotNumber(String lotNumber) {
        LotNumber = lotNumber;
    }

    /**
     *
     * @return ScheduledDate
     */
    public LocalDateTime getScheduledDate() {
        return ScheduledDate;
    }

    /**
     *
     * @param scheduledDate
     */
    public void setScheduledDate(LocalDateTime scheduledDate) {
        ScheduledDate = scheduledDate;
    }

    /**
     *
     * @return ArrivalDate
     */
    public LocalDateTime getArrivalDate() {
        return ArrivalDate;
    }

    /**
     *
     * @param arrivalDate
     */
    public void setArrivalDate(LocalDateTime arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    /**
     *
     * @return NurseAdministratorTime
     */
    public LocalDateTime getNurseAdministratorTime() {
        return NurseAdministratorTime;
    }

    /**
     *
     * @param nurseAdministratorTime
     */
    public void setNurseAdministratorTime(LocalDateTime nurseAdministratorTime) {
        NurseAdministratorTime = nurseAdministratorTime;
    }

    /**
     *
     * @return leavingDate
     */
    public LocalDateTime getLeavingDate() {
        return LeavingDate;
    }

    /**
     *
     * @param leavingDate
     */
    public void setLeavingDate(LocalDateTime leavingDate) {
        LeavingDate = leavingDate;
    }
}
