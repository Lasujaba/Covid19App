package pt.ipp.isep.g58;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DataFromLegacyController {
    private static final String DEFAULT_SNS_HEADER = "SNS Number";
    private static final String DEFAULT_VACCINENAME_HEADER = "Vaccine name";
    private static final String DEFAULT_DOSEHEADER = "Dose name";
    private static final String DEFAULT_LOT_NUMBER_HEADER = "LotNumber";
    private static final String DEFAULT_SCHEDULE_DATE_HEADER = "Scheduled date";
    private static final String DEFAULT_ARRIVAL_DATE_HEADER = "Arrival time";
    private static final String DEFAULT_NURSE_ADMINISTERED_HEADER = "Administered time";
    private static final String DEFAULT_LEAVINGTIME_HEADER = "Leaving time";
    private static final String[] DEFAULT_HEADER = {DEFAULT_SNS_HEADER, DEFAULT_VACCINENAME_HEADER, DEFAULT_DOSEHEADER, DEFAULT_LOT_NUMBER_HEADER, DEFAULT_SCHEDULE_DATE_HEADER, DEFAULT_ARRIVAL_DATE_HEADER, DEFAULT_NURSE_ADMINISTERED_HEADER, DEFAULT_LEAVINGTIME_HEADER};
    private static final String DEFAULT_USERNAME = "User name";
    private static final String DEFAULT_VACCINATIONCENTER = "Vaccination Center";
    private static final String DEFAULT_DESCRIPTION = "Vaccination Description";
    private static final String DEFAULT_BDAY = "00/00/0000";
    private static int codeCounter = 1000;
    private static String[] VaccineData;
    @FXML
    static
    TextArea textAr = null;

    /**
     *
     * @Param SNS
     * @return
     */
    public static String getUserName(String SNS){
        if(Database.userTable.isEmpty()){
            return DEFAULT_USERNAME;
        }
        for(User usr : Database.userTable){
            if(SNS.equals(usr.getSNSNumber())){
                return usr.getName();
            }
        }
        return DEFAULT_USERNAME;
    }


    /**
     *
     * @param csvPath
     * @return List of DataFromLegacy objects
     * @throws ParseException
     */
    @FXML
    public static List<DataFromLegacy> loadDataFromLegacy(String csvPath) throws ParseException {
        //load users from csv file with auto-detection of delimiter
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically(';');
        CsvParser parser = new CsvParser(settings);
        String[] row;
        List<VaccineType> vaccineTypes = new ArrayList<>();
        List<Vaccine> vaccines = new ArrayList<>();
        List<String> userSNSList = new ArrayList<>();
        List<String> headerList = List.of(DEFAULT_HEADER);
        List<DataFromLegacy> dataList = new ArrayList<>();
        int SNSIndex = headerList.indexOf(DEFAULT_SNS_HEADER);
        int VaccineIndex = headerList.indexOf(DEFAULT_VACCINENAME_HEADER);
        int DoseIndex = headerList.indexOf(DEFAULT_DOSEHEADER);
        int lotIndex = headerList.indexOf(DEFAULT_LOT_NUMBER_HEADER);
        int ScheduleTimeIndex = headerList.indexOf(DEFAULT_SCHEDULE_DATE_HEADER);
        int ArrivalTimeIndex = headerList.indexOf(DEFAULT_ARRIVAL_DATE_HEADER);
        int AdministeredIndex = headerList.indexOf(DEFAULT_NURSE_ADMINISTERED_HEADER);
        int LeavingTimeIndex = headerList.indexOf(DEFAULT_LEAVINGTIME_HEADER);
        DateTimeFormatter smForm = DateTimeFormatter.ofPattern("M/dd/yyyy H:mm");
        int counter =0;
        try {
            FileReader reader = new FileReader(csvPath);
            parser.beginParsing(reader);
            while ((row = parser.parseNext()) != null) {
                counter++;
                if(counter > 1) {
                    try {
                        String schedule = row[ScheduleTimeIndex];
                        LocalDateTime Scheduledate = LocalDateTime.parse(schedule, smForm);
                        String Arrival = row[ArrivalTimeIndex];
                        LocalDateTime ArrivalDate = LocalDateTime.parse(Arrival, smForm);
                        String Administered = row[AdministeredIndex];
                        LocalDateTime NurseAdministratorTime = LocalDateTime.parse(Administered, smForm);
                        String Leave = row[LeavingTimeIndex];
                        LocalDateTime LeavingTime = LocalDateTime.parse(Leave, smForm);
                        DataFromLegacy data = new DataFromLegacy(row[SNSIndex], row[VaccineIndex], row[DoseIndex], row[lotIndex], Scheduledate, ArrivalDate, NurseAdministratorTime, LeavingTime);
                        dataList.add(data);
                        userSNSList.add(row[SNSIndex]);
                        VaccineType vt = new VaccineType(Integer.toString(codeCounter), row[VaccineIndex], DEFAULT_DESCRIPTION);
                        Vaccine v = new Vaccine(vt, doseTranslator(row[DoseIndex]), row[lotIndex], "Default brand", true, 1, 0, 1);
                        vaccines.add(v);
                        vaccineTypes.add(vt);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
        }
        addDataToDatabase(vaccineTypes, userSNSList,vaccines);
        return dataList;
    }

    private static int doseTranslator(String dose){
        switch(dose) {
            case "Primeira":
                return 1;
            case "Segunda":
                return 2;
            case "Terceira":
                return 3;
            case "Quarta":
                return 4;
            case "Quinta":
                return 5;
            case "Sexta":
                return 6;
            case "Sétima":
                return 7;
            case "Oitava":
                return 8;
            case "Nona":
                return 9;
            case "Décima":
                return 10;
            default:
                return -1;
        }
    }


    /**
     *
     * @param vaccineName
     * @return
     */
    public static String getVaccineDescription(String vaccineName){
        if(Database.vaccineTypes.isEmpty()){
            return DEFAULT_DESCRIPTION;
        }
        for(VaccineType vc : Database.vaccineTypes){
            if(vc.designation.equals(vaccineName)){
                return vc.getDescription();
            }
        }
        return DEFAULT_DESCRIPTION;
    }

    /**
     *
     * @param list
     * @param userList
     * @throws ParseException
     */
    public static void addDataToDatabase(List<VaccineType> list, List<String> userList, List<Vaccine> vaccineList) throws ParseException {

        /**
         * Adding new Users from legacy
         */
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(DEFAULT_BDAY);
        for (String SNS : userList) {
            if (Database.userTable.stream().anyMatch(u -> u.getSNSNumber().equals(SNS))) {
                continue;
            }
            User user = new User(DEFAULT_USERNAME, "DEFAULT@EMAIL.com", SNS, "Pass123", "male", date, "DefaultAddress3", "+351443140700", "1");
            UserController.addUser(user);


            /**
             * Iterating through the list we got from loadDataFromLegacy
             */
            for (VaccineType vt  : list) {
                if (Database.vaccineTypes.stream().anyMatch(u -> u.designation.equals(vt.designation))) {
                    continue;
                }
                codeCounter++;
                VaccineType vtype = new VaccineType(vt.code, vt.designation, vt.description);
                VaccineTypeController.addVaccineType(vtype);

            }

            /**
             * Add vaccine to Database
             */
            for (Vaccine v  : vaccineList) {
                if(Database.vaccineTable.stream().anyMatch(u -> u.getlotNumber() != null && u.getlotNumber().equals(v.getlotNumber()))) {
                    continue;
                }
                Vaccine vc = new Vaccine(v.getType(), v.getDose(), v.getlotNumber(), v.getBrandName(),v.isAgeGroup(),v.getDoseNumber(),v.getDosage(),v.getTimeInterval());
                VaccineController.addVaccine(vc);
            }


        }

    }
}
