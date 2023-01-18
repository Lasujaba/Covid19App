package pt.ipp.isep.g58;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AutomaticReviewController {


    /**
     * Method to be called periodically
     */
    public static void showReview() {
        try {
            CsvWriterSettings settings = new CsvWriterSettings();
            settings.getFormat().setDelimiter(';');
            File reportFile = new File("Report.csv");
            boolean fileExists = reportFile.exists();
            FileWriter fw = new FileWriter(reportFile, true);

            CsvWriter writer = new CsvWriter(fw, settings);
            if (!fileExists) {
                writer.writeHeaders("Date", "Vaccination Center", "Vaccinated");
            }
            if (!Database.centerTable.isEmpty()) {
                for(VaccinationCenter v: Database.centerTable) { //iterate through all vaccination centers
                    String formatDateTime = LocalDateTime.now().format(Formatters.DateTimeSeconds);
                    writer.writeRow(formatDateTime, v.getName(), v.getVaccinatedToday().size());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that runs another method once a period
     */
    static void runAutomaticReview() {
        int period = Config.getInt("exportInterval");
        Timeline automaticCaller = new Timeline(
                new KeyFrame(Duration.seconds(period),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                showReview();
                            }
                        }));
        automaticCaller.setCycleCount(Timeline.INDEFINITE);
        automaticCaller.play();
    }

}
