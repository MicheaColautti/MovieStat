import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utility class for reading and writing CSV files containing movie data.
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-02-21
 */
public class CsvTool {

    private static final String CONFIG_FILE = "assets/settings.properties";

    //initially empty, these two variables will be filled as soon as "readCsv" is called
    private static String inputFile = "";
    private static String outputFile = "";

    /**
     * Reads a CSV file containing movie information and returns a list of {@code Movie} objects.
     * The CSV file path is retrieved from a configuration file.
     *
     * @return a list of {@code Movie} objects parsed from the CSV file.
     */
    public List<Movie> readCsv() {
        List<Movie> movies = new ArrayList<>();
        getCsvPathFromConfig();
        String csvPath = inputFile;

        if (csvPath == null) {
            System.err.println("CSV path not found in configuration file.");
            return movies;
        }

        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++) { // Skipping header
                String[] row = records.get(i);
                if (row.length < 14) continue; // Ensure proper row length

                // Extract required fields
                int year = Integer.parseInt(row[2]);
                double duration = Double.parseDouble(row[4].replaceAll("[^0-9]", "")); // Remove non-numeric characters
                String director = row[9];
                String star1 = row[10];
                String star2 = row[11];
                String star3 = row[12];
                String star4 = row[13];

                movies.add(new Movie(year, duration, director, star1, star2, star3, star4));
            }
        } catch (IOException | CsvException | NumberFormatException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }

        return movies;
    }

    /**
     * Writes a list of {@code Movie} objects to a CSV file.
     *
     * @param movies        the list of {@code Movie} objects to write.
     * @param outputCsvPath the file path where the CSV should be saved.
     */
    public void writeCsv(List<Movie> movies, String outputCsvPath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputCsvPath))) {
            // Write header
            String[] header = {"Year", "Duration", "Director", "Star1", "Star2", "Star3", "Star4"};
            writer.writeNext(header);

            // Write movie data
            for (Movie movie : movies) {
                writer.writeNext(new String[]{
                        /*String.valueOf(movie.getYear()),
                         String.valueOf(movie.getDuration()),
                         movie.getDirector(),
                         movie.getStar1(),
                         movie.getStar2(),
                         movie.getStar3(),
                         movie.getStar4()*/
                });
            }
        } catch (IOException e) {
            System.err.println("Error writing the CSV file: " + e.getMessage());
        }
    }

    /**
     * Reads the CSV file path from the configuration file.
     * Sets inputFile and outputFile
     */
    private void getCsvPathFromConfig() {
        try {
            Properties pathsProp = new Properties();
            pathsProp.load(new FileInputStream(CONFIG_FILE));
            inputFile = pathsProp.getProperty("input");
            outputFile = pathsProp.getProperty("output");
        } catch (IOException e) {
            System.out.println("Error while reading properties file");;
        }
    }
}
