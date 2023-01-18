package pt.ipp.isep.g58;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ExportStatisticsController {
    public void export() {
        try {
            CsvWriterSettings settings = new CsvWriterSettings();
            settings.getFormat().setDelimiter(';');
            File reportFile = new File("VaccinationStatistics.csv");
            boolean fileExists = reportFile.exists();
            FileWriter fw = new FileWriter(reportFile, true);

            CsvWriter writer = new CsvWriter(fw, settings);
            if (!fileExists) {
                writer.writeHeaders("Vaccination Center", "Date", "Vaccinated");
            }
            if (!Database.centerTable.isEmpty()) {
                for(VaccinationCenter v: Database.centerTable) { //iterate through all vaccination centers
                    List<Appointment> app = v.getAllVaccinated().stream().
                            sorted(Comparator.comparing(Appointment::getVaccinationDate)).toList(); //sort by vaccination date
                    HashMap<LocalDate, Integer> map = new HashMap<>(); //create a hashmap to store the vaccination dates and the number of vaccinations
                    for (Appointment a : app) { //iterate through all appointments
                        if (a.getVaccineUsed().getDose() != a.getVaccineUsed().getDoseNumber()) {
                            continue;
                        }
                        LocalDate date = LocalDate.from(a.getVaccinationDate());

                        if (map.containsKey(date)) { //if the vaccination date is already in the hashmap
                            map.put(date, map.get(date) + 1); //increment the number of vaccinations
                        } else {
                            map.put(date, 1); //if the vaccination date is not in the hashmap, add it with 1 vaccination
                        }
                    }
                    for (LocalDate d : map.keySet()) { //write the vaccination center name, vaccination date and the number of vaccinations
                        writer.writeRow(v.getName(),Formatters.DateWithoutTime.format(d), map.get(d));
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
