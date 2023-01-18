package pt.ipp.isep.g58;

import java.util.Scanner;

public class Validation {

    /**
     * @param password, to be checked
     * @return a number representing the errors found in the password
     * 0 -> no error
     * 1 -> length
     * 2 -> lower letters
     * 4 -> upper letter
     * 8 -> special characters
     */
    public static double isValidPassword(String password){
        final int passLength = 6;
        final int leastLowerCase = 1;
        final int leastUpperCase = 1;
        final int leastSpecialChar = 1;
        int leastLowerCount;
        int leastUpperCount;
        int leastSpecialCount;
        int errorCode = 0;
        String specialChars = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";

        leastLowerCount = 0;
        leastUpperCount = 0;
        leastSpecialCount = 0;
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if(Character.isLowerCase(c)){
                leastLowerCount++;
            }else if(Character.isUpperCase(c)){
                leastUpperCount++;
            }
        }
        for(int i = 0; i < password.length(); i++){

            for(int j = 0; j < specialChars.length(); j++){
                char c = password.charAt(i);
                char k = specialChars.charAt(j);
                if(k == c){
                    leastSpecialCount++;
                }
            }
        }


        if(password.length() < passLength) {
            errorCode += 1;
        }


        if (leastLowerCount < leastLowerCase) {
            errorCode += 2;
        }

        if (leastUpperCount < leastUpperCase) {
            errorCode += 4;
        }

        if(leastSpecialCount < leastSpecialChar){
            errorCode += 8;
        }
        return errorCode;
    }

    /**
     *
     * @param SNSNumber the number to be checked
     * @return the error code :
     * 0 -> no errors
     * 16 -> incorrect number
     */
    public static double isValidSNSNumber(String SNSNumber){
        int errorCode = 0;
        Scanner read = new Scanner(System.in);
        if(SNSNumber.isEmpty() || SNSNumber.equals(null) || !SNSNumber.matches("^[0-9]*$") || SNSNumber.length() != 9){
            errorCode += 16;
        }
        return errorCode;
    }

    public static String isValidScheduleTime(String time){
        Scanner read = new Scanner(System.in);
        if(time.isEmpty() || time.equals(null) || !time.matches("^(\\d\\d\\d\\d/\\d\\d/\\d\\d \\d\\d:\\d\\d)")){
            System.out.println("Try again, the correct format was yyyy/MM/dd HH:mm");
            time = null;
            time = read.nextLine();
            isValidTime(time);
        }
        return time;
    }


    /**
     * Checks that the name doesn't contain numbers or special characters
     * @param name
     */
    public static double isValidName(String name){
        if (name.isEmpty() || !name.matches("^[a-öA-Ö]*$")) {
            return Math.pow(2,5);
        }
        return 0;
    }



    /**
     * Checks if given email contains @ and .
     * @param email
     */
    public static double isValidEmail(String email){
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                ")*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        if (email.isEmpty() || !email.matches(regex)) {
            return Math.pow(2,6);
        }
        return 0;
    }

    /**
     *
     * @param number
     * @return if the number respects the portugal phone number
     */

    public static double isValidPhoneNumber(String number) {
        if (number.isEmpty() || !number.matches("^[0-9]{9}$")) {
            return Math.pow(2,7);
        }
        return 0;
    }

    /**
     *
     * @param number
     * @return if the fax number cointains only numbers
     */
    public static double isValidFaxNumber(String number) {
        if (number.isEmpty() || !number.matches("^[0-9]{1,15}")) {
            return Math.pow(2,8);
        }
        return 0;
    }

    /**
     *
     * @param website
     * @return if th website is correct
     */

    public static double isValidWebsite(String website) {
        if (website.isEmpty() || !website.matches("^[\\w\\.]+\\w\\.[a-zA-Z]+$")) { //^www\.[a-zA-Z]+[0-9]+\..*$
            return Math.pow(2,9);
        }
        return 0;
    }

    /**
     * checks if time format is right
     * @param time
     * @return
     */
    public static double isValidTime(String time) {
        if (time.isEmpty() || !time.matches("^[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})?$")) {
            return Math.pow(2,10);
        }
        return 0;
    }
    public static double isValidVaccineTypeCode(String code){
        if(code.isEmpty() || code.equals(null) || code.length() < 4 || code.length() > 8 || !code.matches("^[0-9]{4,8}?$")){
            return Math.pow(2,11);
        }
        return 0;
    }

    /**
     * checks if vaccine LOT number is correct
     * @param LOT vaccine LOT number
     * @return correct or not
     */
    public static boolean isValidLOT(String LOT){
        if(LOT.isEmpty() || LOT.equals(null) || !LOT.matches("^[A-Z0-9]{5}-[0-9]{2}$")){
            return false;
        }
        return true;
    }
}


