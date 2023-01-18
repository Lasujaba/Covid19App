package pt.ipp.isep.g58;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;
import java.util.*;


public class VaccinationCenter implements Serializable {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String faxNumber;
    private String website;
    private Date openingHours; //might do a class for this
    private Date closingHours;
    private int slotDuration;
    private int maxVaccines;
    private int typeOfCenter; // 1 for mass vaccination 2 for healthcare
    private ArrayList<VaccineType> allVaccineTypes;
    private Queue<Appointment> queue;


    /**
     * @param address
     * @param phoneNumber
     * @param email
     * @param faxNumber
     * @param website
     * @param openingHours
     * @param closingHours
     * @param slotDuration
     * @param maxVaccines
     * @param allVaccineTypes
     */
    public VaccinationCenter(String name, String address, String phoneNumber,
                             String email, String faxNumber, String website,
                             Date openingHours, Date closingHours,
                             int slotDuration, int maxVaccines, int typeOfCenter,
                             ArrayList<VaccineType> allVaccineTypes) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.faxNumber = faxNumber;
            this.website = website;
            this.openingHours = openingHours;
            this.closingHours = closingHours;
            this.slotDuration = slotDuration;
            this.maxVaccines = maxVaccines;
            this.typeOfCenter = typeOfCenter;
            this.allVaccineTypes = allVaccineTypes;
            this.queue = new LinkedList<>();
        }

    public List<Appointment> getAllVaccinated () {
        return Database.appointments.stream().filter(a -> a.getVaccinationCenter().getName().equals(this.getName())
                && a.getVaccinationDate() != null).toList();
    }

    /**
     * @return a list of appointments for today date
     */
    public List<Appointment> getVaccinatedToday () {
            return Database.appointments.stream().filter(a -> a.getVaccinationCenter() == this && a.getVaccinationDate() != null &&
                    a.getVaccinationDate().toLocalDate().equals(LocalDate.now())).toList();
        }

    /**
     * @return the queue of appointments
     */

    public Queue<Appointment> getQueue () {
            return queue;
        }

        public void setQueue (Queue < Appointment > queue) {
            this.queue = queue;
        }

        /**
         *
         * @return the name of the vaccination center
         */
        public String getName () {
            return name;
        }

        /**
         *
         * @param name the name of the center
         */

        public void setName (String name){
            this.name = name;
        }

        /**
         *
         * @return the address of the center
         */
        public String getAddress () {
            return address;
        }


        /**
         *
         * @param address the address of the center
         */
        public void setAddress (String address){
            this.address = address;
        }


        /**
         *
         * @return the phone number of the center
         */
        public String getPhoneNumber () {
            return phoneNumber;
        }

        /**
         *
         * @param phoneNumber  the phone number of the center
         */
        public void setPhoneNumber (String phoneNumber){
            this.phoneNumber = phoneNumber;
        }

        /**
         *
         * @return the email of the center
         */
        public String getEmail () {
            return email;
        }

        /**
         *
         * @param email the email fo the center
         */
        public void setEmail (String email){
            this.email = email;
        }

        /**
         *
         * @return the fax number
         */
        public String getFaxNumber () {
            return faxNumber;
        }

        /**
         *
         * @param faxNumber the fax number of the center
         */
        public void setFaxNumber (String faxNumber){
            this.faxNumber = faxNumber;
        }


        /**
         *
         * @return the website of the center
         */
        public String getWebsite () {
            return website;
        }


        /**
         *
         * @param website sets the website of the center
         */
        public void setWebsite (String website){
            this.website = website;
        }

        /**
         *
         * @return the opening hours of the center
         */
        public Date getOpeningHours () {
            return openingHours;
        }


        /**
         *
         * @param openingHours sets the opening hours of the center
         */
        public void setOpeningHours (Date openingHours){
            this.openingHours = openingHours;
        }


        /**+
         *
         * @return the closing hours of the center
         */
        public Date getClosingHours () {
            return closingHours;
        }


        /**
         *
         * @param closingHours sets the closing hours of the center
         */
        public void setClosingHours (Date closingHours){
            this.closingHours = closingHours;
        }

        /**
         *
         * @return the slot duration
         */
        public int getSlotDuration () {
            return slotDuration;
        }

        /**
         *
         * @param slotDuration sets the slot duration
         */
        public void setSlotDuration ( int slotDuration){
            this.slotDuration = slotDuration;
        }

        /**
         *
         * @return maximum number of vaccines for the center
         */
        public int getMaxVaccines () {
            return maxVaccines;
        }

        /**
         *
         * @param maxVaccines sets the maximum vaccines for the center
         */
        public void setMaxVaccines ( int maxVaccines){
            this.maxVaccines = maxVaccines;
        }

        /**
         *
         * @return true if it is a healthcare center false for a mass vaccination
         */
        public int getTypeOfCenter () {
            return typeOfCenter;
        }

        /**
         *
         * @param typeOfCenter sets the type of center
         */
        public void setTypeOfCenter ( int typeOfCenter){
            this.typeOfCenter = typeOfCenter;
        }

        /**
         *
         * @return list of all vaccines found in the center
         */
        public ArrayList<VaccineType> getAllVaccineTypes() {
            return allVaccineTypes;
        }

        /**
         *
         * @param allVaccines sets the vaccines for the center
         */
        public void setAllVaccineTypes(ArrayList < VaccineType > allVaccines) {
            this.allVaccineTypes = allVaccines;
        }


    }


