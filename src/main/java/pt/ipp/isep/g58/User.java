package pt.ipp.isep.g58;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;


public class User implements Serializable {
    private String name;
    private String email;
    private String SNSNumber;
    private String sex;
    private Date birthDate;
    private String address;
    private String phone;
    private String citizenNumber;
    private byte[] passwordHash;

    /**
     * @param name          = users name
     * @param email         = users email
     * @param SNSNumber     = users SNSNumber
     * @param password      = users password
     * @param sex           = users sex
     * @param birthDate     = users birth date
     * @param address       = users address
     * @param phone         = users phone
     * @param citizenNumber = users citizen card number
     */
    public User(String name, String email, String SNSNumber, String password, String sex, Date birthDate, String address, String phone, String citizenNumber) {
        this.name = name;
        this.email = email;
        this.SNSNumber = SNSNumber;
        this.passwordHash = getPasswordHash(password);
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.citizenNumber = citizenNumber;
    }

    public User(String name, String SNSNumber){
        this.name = name;
        this.SNSNumber = SNSNumber;
    }

    /**
     * Returns users name
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set users name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get users email
     *
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * set users email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get users snsnumber
     *
     * @return SNSNumber
     */
    public String getSNSNumber() {
        return SNSNumber;
    }

    /**
     * set users SNSNumber
     *
     * @param SNSNumber
     */
    public void setSNSNumber(String SNSNumber) {
        this.SNSNumber = SNSNumber;
    }

    /**
     * Returns passwordhash
     * @return
     */
    public byte[] getPasswordHash(){return passwordHash;}

    /**
     * Sets password hash
     * @param passwordHash
     */
    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * sets the password hash
     * @param password
     * @return
     */
    private byte[] getPasswordHash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No SHA-256 algorithm???");
            exit(1);
        }
        // Using email as salt
        return md.digest((email+password).getBytes());
    }

    /**
     * sets password
     * @param password
     */
    public void setPassword(String password) {
        this.passwordHash = getPasswordHash(password);
    }

    /**
     * returns sex of the user
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * set sex of user
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * gets birth date
     * @return birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * sets birth date
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * gets address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets citizen card number
     * @return citizenNumber
     */
    public String getCitizenNumber() {
        return citizenNumber;
    }

    /**
     * sets citizen card number
     * @param citizenNumber
     */
    public void setCitizenNumber(String citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    /**
     * @return a string with header for tableRow method (tab separated column names)
     */
    public static String tableHeader() {
        return "Name\tSex\tBirth Date\tAddress\tPhone Number\tE-mail\tSNS User Number\tCitizen Card Number";
    }

    public static String shortTableHeader() {
        return "Name\tAge\tSex\t SNS Number";
    }

    /**
     * @return a string with the employee's data separated by tabs
     */
    public String tableRow() {
        return name + "\t" + sex + "\t" + (new SimpleDateFormat("dd/MM/yyyy")).format(birthDate) + "\t" + address + "\t" + phone + "\t" + email + "\t" + SNSNumber + "\t" + citizenNumber;
    }

    /**
     *
     * @return returns the Age of the user
     */
    public int getAge() {
        return (int) ((new Date().getTime() - birthDate.getTime()) / 31536000000L);
    }
}