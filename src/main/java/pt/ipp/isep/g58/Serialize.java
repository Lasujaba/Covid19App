package pt.ipp.isep.g58;

import java.io.*;

import java.util.ArrayList;

import java.util.List;

public class Serialize {
    public static void main(){
        SerializeUsers();
        SerializeVaccine();
        SerializeAppointments();
        SerializeVaccineType();
        SerializeEmployee();
        SerializeVaccinationCenter();
        System.out.println("Serialization complete!");
    }

    public static void SerializeEmployee(){
        try{
            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Database.employeeTable);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void SerializeVaccine(){
        try{
            FileOutputStream fileOut = new FileOutputStream("vaccines.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Database.vaccineTable);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void SerializeVaccineType(){
        try{
            FileOutputStream fileOut = new FileOutputStream("vaccineTypes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Database.vaccineTypes);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void SerializeVaccinationCenter() {
        try {
            FileOutputStream fileOut = new FileOutputStream("vaccinationCenters.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Database.centerTable);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void SerializeUsers(){
        try{
            FileOutputStream fileOut = new FileOutputStream("Users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Database.userTable);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void SerializeAppointments(){
        try{
            FileOutputStream fileOut = new FileOutputStream("Appointments.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Database.appointments);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}