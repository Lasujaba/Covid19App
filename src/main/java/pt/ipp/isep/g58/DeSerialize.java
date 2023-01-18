package pt.ipp.isep.g58;

import java.io.*;

import java.util.ArrayList;

import java.util.List;

public class DeSerialize {

    public static void main() throws IOException, ClassNotFoundException {

        DeSerializeEmployee();

        DeSerializeVaccine();

        DeSerializeVaccineType();

        DeSerializeVaccinationCenters();

        DeSerializeUsers();

        DeSerializeAppointments();

    }

    public static void DeSerializeEmployee() throws IOException, ClassNotFoundException {
        Employee e = null;
        try {
            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Employee> employeeList = (List<Employee>) in.readObject();
            for (Employee emp : employeeList) {
                Database.employeeTable.add(emp);
            }
        } catch (Exception i) {
            System.out.println("Couldn't retrieve Employees");
        }
    }

    public static void DeSerializeVaccine() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("vaccines.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Vaccine> VaccineList = (List<Vaccine>) in.readObject();
            for (Vaccine vac : VaccineList) {
                Database.vaccineTable.add(vac);
            }
        } catch (Exception i) {
            System.out.println("Couldn't retrieve vaccines");
        }
    }

    public static void DeSerializeVaccineType() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("vaccineTypes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<VaccineType> VaccineTypeList = (List<VaccineType>) in.readObject();
            for (VaccineType vac : VaccineTypeList) {
                Database.vaccineTypes.add(vac);
            }
        } catch (Exception i) {
            System.out.println("Couldn't retrieve VaccineTypes");
        }
    }

    public static void DeSerializeVaccinationCenters() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("vaccinationCenters.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<VaccinationCenter> VaccineCenters = (List<VaccinationCenter>) in.readObject();
            for (VaccinationCenter vc : VaccineCenters) {
                Database.centerTable.add(vc);
            }
        } catch (Exception i) {
            System.out.println("Couldn't retrieve centers");
        }
    }

    public static void DeSerializeUsers() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("Users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<User> users = (List<User>) in.readObject();
            for (User user : users) {
                Database.userTable.add(user);
            }
        } catch (Exception i) {
            System.out.println("Couldn't retrieve users");
        }
    }

    public static void DeSerializeAppointments() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("Appointments.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Appointment> apps = (List<Appointment>) in.readObject();
            for (Appointment app : apps) {
                Database.appointments.add(app);
            }
        } catch (Exception i) {
            System.out.println("Couldn't retrieve Appointments");
        }
    }
}