import java.util.Arrays;
import java.util.List;

/**
 * The main entry point for the movie statistics application.
 * This class initializes a List of Movies, populates it with data from a CSV file,
 * executes the statistics, and then writes the result on a CSV.
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-02-21
 */
public class MovieStat {

    /**
     * The main method that runs the movie statistics application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MovieList movieList = new MovieList();
        movieList.pupulate();
        System.out.println(movieList);
    }
}
