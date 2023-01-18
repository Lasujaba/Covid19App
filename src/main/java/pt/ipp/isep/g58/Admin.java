package pt.ipp.isep.g58;

public class Admin extends Employee{

    VaccinationCenterUI VaccinationCenterUI;
    EmployeeRegistrationUI employeeRegistrationUI;
    RegisterVaccineUI RegisterVaccineUI;

    public Admin(String name, String surname, String email, String password) {
        super(name, surname, email, EmployeeController.Role.ADMIN, password);
    }
}