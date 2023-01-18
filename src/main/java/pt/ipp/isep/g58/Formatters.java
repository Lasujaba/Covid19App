package pt.ipp.isep.g58;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Formatters {
    /**
     * dd/MM/yyyy HH:mm:ss format
     */
    public static DateTimeFormatter DateTimeSeconds = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /**
     * dd/MM/yyyy HH:mm format
     */
    public static DateTimeFormatter DateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    /**
     * dd/MM/yyyy format
     */
    public static DateTimeFormatter DateWithoutTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}
