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
 * @version 2025-03-09
 */
public class CsvTool {

    private static final String CONFIG_FILE = "assets/settings.properties";

    // Initially empty, these variables will be filled when "readCsv" is called
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
        loadCsvPathsFromConfig();

        if (inputFile == null || inputFile.isEmpty()) {
            System.err.println("CSV input file path is missing in the configuration file.");
            return movies;
        }

        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {
            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++) { // Skipping header
                String[] row = records.get(i);
                if (row.length < 14) continue; // Ensure proper row length

                // Extract required fields
                int year = Integer.parseInt(row[2]);
                double duration = Double.parseDouble(row[4].replaceAll("[^0-9]", "")); // Remove non-numeric characters
                double rating = row[6].isEmpty() ? 0.0 : Double.parseDouble(row[6]); // IMDB Rating
                String director = row[9];
                String star1 = row[10];
                String star2 = row[11];
                String star3 = row[12];
                String star4 = row[13];
                movies.add(new Movie(year, duration, director, star1, star2, star3, star4, rating));
            }
        } catch (IOException | CsvException | NumberFormatException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }

        return movies;
    }

    /**
     * Writes computed statistics to a CSV file.
     *
     * @param moviesNumber    The total number of movies.
     * @param avgRuntime      The average runtime of the movies.
     * @param bestDirector    The director with the highest average IMDB rating.
     * @param mostPresentStar The most frequently appearing actor.
     * @param mostProdYear    The year with the highest number of movie productions.
     */
    public void writeCsv(int moviesNumber, double avgRuntime, String bestDirector, String mostPresentStar, int mostProdYear) {
        if (outputFile == null || outputFile.isEmpty()) {
            System.err.println("No output file path specified.");
            return;
        }

        File file = new File(outputFile);
        if (file.exists()) {
            System.out.println("Output file already existing, will be overwritten");
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            // Write header
            String[] header = {"Movies Count", "Average Runtime", "Best Director", "Most Present Star", "Most Productive Year"};
            writer.writeNext(header);

            // Write statistics row
            writer.writeNext(new String[]{
                    String.valueOf(moviesNumber),
                    String.valueOf(avgRuntime),
                    bestDirector,
                    mostPresentStar,
                    String.valueOf(mostProdYear)
            });

            System.out.println("Statistics written to CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error writing the CSV file: " + e.getMessage());
        }
    }

    /**
     * Reads the CSV file paths from the configuration file and sets inputFile and outputFile.
     */
    private void loadCsvPathsFromConfig() {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            Properties pathsProp = new Properties();
            pathsProp.load(fileInputStream);
            inputFile = pathsProp.getProperty("input", "");
            outputFile = pathsProp.getProperty("output", "");
            System.out.println("CSV Path acquired");

            if (inputFile.isEmpty() || outputFile.isEmpty()) {
                System.err.println("Warning: Some CSV paths are missing in the configuration file.");
            }
        } catch (IOException e) {
            System.err.println("Error while reading properties file: " + e.getMessage());
        }
    }
}
