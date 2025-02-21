import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * A utility class that manages a list of {@code Movie} objects.
 * This class provides methods to populate the movie list from a CSV file,
 * calculate statistics, and retrieve specific details about the movies.
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-02-21
 */
public class MovieList {

    private List<Movie> movieList;
    Logger logger = new Logger();
    CsvTool csvTool = new CsvTool();

    /**
     * Populates the movie list by reading from a CSV file using {@code CsvTool}.
     */
    public void pupulate() {
        movieList = csvTool.readCsv();
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
        OptionalDouble avgRuntime = movieList.stream().mapToDouble(Movie::getDuration).average();
        if (avgRuntime.isPresent()) {
            logger.log("Runtime generated successfully");
            return avgRuntime.getAsDouble();
        } else {
            logger.log("No movies in the list.");
        }
        return 0;
    }

    /**
     * Returns a string representation of all movies in the list.
     * Each movie is printed to the console.
     *
     * @return an empty string.
     */
    @Override
    public String toString() {
        for (Movie m : movieList) {
            System.out.println(m);
        }
        return "";
    }

    /**
     * Returns the name of the director with the most movies in the list.
     *
     * @return the director's name, or an empty string if no data is available.
     */
    public String getBestDirector() {
        return "";
    }

    /**
     * Returns the name of the most frequently appearing actor in the movie list.
     *
     * @return the name of the most common star, or an empty string if no data is available.
     */
    public String getMostPresentStar() {
        return "";
    }

    /**
     * Returns the year with the highest number of movie productions.
     *
     * @return the most productive year, or 0 if no data is available.
     */
    public int getMostProdYear() {
        return 0;
    }
}
