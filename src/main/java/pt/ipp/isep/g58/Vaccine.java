package pt.ipp.isep.g58;

import java.io.Serializable;

public class Vaccine implements Serializable {
    private VaccineType type;
    private String brandName;
    private boolean ageGroup; // false for 5-18 true for18+
    private int doseNumber;
    private int dosage;
    private int timeInterval;
    private int dose;
    private String lotNumber;
    private String description;
    private final String DEFAULT_BRAND = "Default brand";
    private final boolean DEFAULT_ageGroup = true;
    private final int DEFAULT_DOSENUMBER = 1;
    private final int DEFAULT_DOSAGE = 0;
    private final int DEFAULT_TIMEINTERVAL = 1;

    /**
     *
     * @param brandName
     * @param ageGroup
     * @param doseNumber
     * @param dosage
     * @param timeInterval
     */

    public Vaccine(VaccineType type, String brandName,
                   boolean ageGroup, int doseNumber, int dosage, int timeInterval) {
        this(type,-1, null,brandName,ageGroup,doseNumber,dosage,timeInterval);
    }

    public Vaccine(VaccineType type, int Dose, String lotNumber,String brandName,boolean ageGroup,int doseNumber, int dosage, int timeInterval){
        this.type = type;
        this.dose = Dose;
        this.lotNumber = lotNumber;
        this.brandName = brandName;
        this.ageGroup = ageGroup;
        this.doseNumber = doseNumber;
        this.dosage = dosage;
        this.timeInterval = timeInterval;
    }

    public int getDose(){
        return dose;
    }

    public void setDose(int dose){this.dose = dose;}

    public String getlotNumber(){return lotNumber;}

    public void setLotNumber(String lotNum){this.lotNumber = lotNum;}

    public VaccineType getType(){return type;}

    public void setType(VaccineType type){this.type = type;}

    /**
     *
     * @return the brand name
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     *
     * @param brandName sets the brand name
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     *
     * @return true for age group 18+, false for 5-18
     */
    public boolean isAgeGroup() {
        return ageGroup;
    }

    /**
     *
     * @param ageGroup sets the age group
     */
    public void setAgeGroup(boolean ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     *
     * @return the dose number
     */
    public int getDoseNumber() {
        return doseNumber;
    }

    /**
     *
     * @param doseNumber sets the dose number
     */
    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    /**
     *
     * @return the dosage
     */
    public int getDosage() {
        return dosage;
    }

    /**
     *
     * @param dosage sets the dosage
     */
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    /**
     *
     * @return get the time interval for the vaccines
     */
    public int getTimeInterval() {
        return timeInterval;
    }

    /**
     *
     * @param timeInterval sets the time interval
     */
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     *
     * @return clone of the vaccine
     */
    public Vaccine clone(){
        return new Vaccine(this.type,this.dose,this.lotNumber,this.brandName,this.ageGroup,this.doseNumber,this.dosage,this.timeInterval);
    }

    public Boolean equal(Vaccine other){
        return  this.type.equals(other.type) &&
                this.dose == other.dose &&
                this.lotNumber != null && other.lotNumber != null && this.lotNumber.equals(other.lotNumber) &&
                this.brandName != null && other.brandName != null && this.brandName.equals(other.brandName) &&
                this.ageGroup == other.ageGroup &&
                this.doseNumber == other.doseNumber &&
                this.dosage == other.dosage &&
                this.timeInterval == other.timeInterval;
    }
}
