import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvTool {

    private static final String CONFIG_FILE = "settings.txt";

    public List<Movie> readCsv() {
        List<Movie> movies = new ArrayList<>();
        String csvPath = getCsvPathFromConfig();

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
                double duration = Double.parseDouble(row[4]);
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

    public void writeCsv(List<Movie> movies, String outputCsvPath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputCsvPath))) {
            // Write header
            String[] header = {"Year", "Duration", "Director", "Star1", "Star2", "Star3", "Star4"};
            writer.writeNext(header);

            // Write movie data
            for (Movie movie : movies) {
                /*writer.writeNext(new String[]{
                        movie.getYear(),
                        movie.getDuration(),
                        movie.getDirector(),
                        movie.getStar1(),
                        movie.getStar2(),
                        movie.getStar3(),
                        movie.getStar4()
                });*/
            }
        } catch (IOException e) {
            System.err.println("Error writing the CSV file: " + e.getMessage());
        }
    }

    private String getCsvPathFromConfig() {
        try (BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE))) {
            return br.readLine(); // Read the first line as CSV path
        } catch (IOException e) {
            System.err.println("Error reading the config file: " + e.getMessage());
            return null;
        }
    }
}