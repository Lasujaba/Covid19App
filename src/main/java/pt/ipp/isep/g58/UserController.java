package pt.ipp.isep.g58;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserController{
    private static final String NAME_HEADER = "Name";
    private static final String SEX_HEADER = "Sex";
    private static final String BDATE_HEADER = "Birth Date";
    private static final String ADDRESS_HEADER = "Address";
    private static final String PHONE_HEADER = "Phone Number";
    private static final String EMAIL_HEADER = "E-mail";
    private static final String SNS_HEADER = "SNS User Number";
    private static final String CCARD_HEADER = "Citizen Card Number";
    private static final String[] DEFAULT_HEADER = {NAME_HEADER, SEX_HEADER, BDATE_HEADER, ADDRESS_HEADER, PHONE_HEADER, EMAIL_HEADER, SNS_HEADER, CCARD_HEADER};

    /**
     * Add user to userTable database
     * @param user
     */
    public static void addUser(User user){Database.userTable.add(user);}

    /**
     * Removes all users from the database.
     */
    public static void purgeUsers() {
        Database.userTable.clear();
    }

    /**
     *
     * @param csvPath path to csv file
     * @return Map of users and their passwords
     */
    public static Map<User, String> loadUsers(String csvPath){
        //load users from csv file with auto-detection of delimiter
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically(',',';');
        CsvParser parser = new CsvParser(settings);
        List<User> userList;
        try {
            FileReader reader = new FileReader(csvPath);
            parser.beginParsing(reader);
            switch(parser.getDetectedFormat().getDelimiter()){
                case ',':
                    userList = loadUsers(parser, DEFAULT_HEADER);
                    break;
                case ';':
                    String[] header = parser.parseNext();
                    userList = loadUsers(parser, header);
                    break;
                default:
                    return null;
            }

        } catch (FileNotFoundException e) {
            return null;
        }
        Map<User, String> addedUsers = new HashMap<>();
        for (User user : userList){
            if (Database.userTable.stream().anyMatch(u -> u.getSNSNumber().equals(user.getSNSNumber()))){
                continue;
            }
            String password = generatePassword();
            user.setPassword(password);
            addedUsers.put(user, password);
            Database.userTable.add(user);
        }
        return addedUsers;
    }

    private static List<User> loadUsers(CsvParser parser, String[] header){
        String[] row;
        List<String> headerList = List.of(header);
        List<User> userList = new ArrayList<User>();

        int nameIndex = headerList.indexOf(NAME_HEADER);
        int sexIndex = headerList.indexOf(SEX_HEADER);
        int bdateIndex = headerList.indexOf(BDATE_HEADER);
        int addressIndex = headerList.indexOf(ADDRESS_HEADER);
        int phoneIndex = headerList.indexOf(PHONE_HEADER);
        int emailIndex = headerList.indexOf(EMAIL_HEADER);
        int snsIndex = headerList.indexOf(SNS_HEADER);
        int ccardIndex = headerList.indexOf(CCARD_HEADER);

        if(nameIndex == -1 || sexIndex == -1 || bdateIndex == -1 || addressIndex == -1 || phoneIndex == -1 || emailIndex == -1 || snsIndex == -1 || ccardIndex == -1){
            return userList;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date bday;
        while ((row = parser.parseNext()) != null) {
            try {
                bday = formatter.parse(row[bdateIndex]);
                userList.add(new User(row[nameIndex], row[emailIndex], row[snsIndex], "", row[sexIndex], bday, row[addressIndex], row[phoneIndex], row[ccardIndex]));
            } catch (java.text.ParseException e) {
                continue;
            } catch (ArrayIndexOutOfBoundsException e){
                continue;
            }
        }
        return userList;
    }

    private static String generatePassword(){
        //generate random password
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < 16; i++){
            password.append((char)('a' + Math.random() * 26));
        }
        return password.toString();
    }
    /**
     * Creates a couple of users and adds them to the database for testing purposes.
     */
    public static void createExample(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Database.userTable.add(new User("User1", "User1@email.com", "123456789", "", "male", formatter.parse("10/02/2005"), "user1Address", "123456789", "123456789"));
            Database.userTable.add(new User("User2", "User2@email.com", "987654321", "", "female", formatter.parse("05/10/2002"), "user2Address", "987654321", "987654321"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return gets the user with the given SNS number
     */
    public static User getFromSNS(String snsNumber){
        return Database.userTable.stream().filter(u -> u.getSNSNumber().equals(snsNumber)).findFirst().get();
    }
}

