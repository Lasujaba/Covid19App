package pt.ipp.isep.g58;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SMSController {
    private static String FILE_NAME = "SMS.txt";

    /**
     * @param message the message to be sent
     * @param SNSNumber SNS number of the recipient
     */
    public static void sendToSNS(String message, String SNSNumber){
        String phoneNumber = UserController.getFromSNS(SNSNumber).getPhone();
        sendSMS(message, phoneNumber);
    }

    /**
     * @param message the message to be sent
     * @param phoneNumber phone number of the recipient
     */
    public static void sendSMS(String message, String phoneNumber) {
        try{
            File file = new File(FILE_NAME);
            FileWriter writer = new FileWriter(file, true);
            writer.write("===== Message to " + phoneNumber + " =====\n" + message + "\n");
            writer.close();
        }catch(IOException e){
            throw new RuntimeException("Failed to send SMS");
        }
    }
}
