import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * The main entry point for the movie statistics application.
 * This class initializes a List of Movies, populates it with data from a CSV file,
 * executes the statistics, and then writes the result on a CSV.
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-03-09
 */
public class MovieStat {

    private List<Movie> movieList;
    CsvTool csvTool = new CsvTool();

    /**
     * The main method that runs the movie statistics application.
     */
    public void load() {
        movieList = new ArrayList<Movie>();
        movieList = pupulate();
    }

    public void compute() {
        int moviesNumber = getMoviesNumber();
        double avgRuntime = getAvgRuntime();
        String bestDirector = getBestDirector();
        String mostPresentS = getMostPresentStar();
        int mostProdYear = getMostProdYear();

        csvTool.writeCsv(moviesNumber, avgRuntime, bestDirector, mostPresentS, mostProdYear);
    }

    /**
     * Populates the movie list by reading from a CSV file using {@code CsvTool}.
     */
    public List<Movie> pupulate() {
        return csvTool.readCsv();
    }

    /**
     * Returns the number of movies in the list.
     *
     * @return the total number of movies.
     */
    public int getMoviesNumber() {

        return movieList.size();
    }

    /**
     * Calculates and returns the average runtime of all movies in the list.
     *
     * @return the average runtime in minutes, or 0 if no movies are present.
     */
    public double getAvgRuntime() {
        return movieList.stream()
                .mapToDouble(Movie::getDuration)
                .average()
                .orElse(0.0);
    }

    /**
     * Returns the name of the director with the best average IMDB rating.
     *
     * @return the director's name with the highest average rating, or an empty string if no data is available.
     */
    public String getBestDirector() {
        return movieList.stream()
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.averagingDouble(Movie::getRating)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    /**
     * Returns the name of the most frequently appearing actor in the movie list.
     *
     * @return the name of the most common star, or an empty string if no data is available.
     */
    public String getMostPresentStar() {
        List<String> allStars = movieList.stream()
                .flatMap(movie -> Arrays.stream(movie.getStars()))
                .collect(Collectors.toList());
        return getMostFrequent(allStars);
    }

    /**
     * Method to return most productive year,aka the year with most movies.
     *
     * @return the most productive year.
     */
    public int getMostProdYear() {
        return movieList.stream()
                .collect(Collectors.groupingBy(Movie::getYear, Collectors.counting())) // Group by year, count occurrences
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()) // Find the year with the highest count
                .map(Map.Entry::getKey) // Extract the year
                .orElse(0); // Default to 0 if no data
    }
    
    /**
     * Generic method to find the most frequent occurrence of a string in a list.
     * Used for both director counting and star counting.
     *
     * @param items The list of items (directors or stars) to be analyzed.
     * @return The most frequently occurring string, or an empty string if the list is empty.
     */
    private String getMostFrequent(List<String> items) {
        return items.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())) // Count occurrences
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()) // Find the max by count
                .map(Map.Entry::getKey) // Get the name with the highest count
                .orElse(""); // Default to empty if no data
    }
}
