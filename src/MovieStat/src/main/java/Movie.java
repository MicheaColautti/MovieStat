import java.util.Arrays;

/**
 * Represents a movie with details such as release year, duration, director, starring actors, and IMDB rating.
 * This class provides getters to access movie details and a formatted {@code toString} method.
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-03-09
 */
public class Movie {

    private int year;
    private double duration;
    private String director;
    private String[] stars;
    private double rating; // IMDB rating

    /**
     * Constructs a {@code Movie} object with the specified details.
     *
     * @param year     The release year of the movie.
     * @param duration The duration of the movie in minutes.
     * @param director The director of the movie.
     * @param s1       The first starring actor.
     * @param s2       The second starring actor.
     * @param s3       The third starring actor.
     * @param s4       The fourth starring actor.
     * @param rating   The IMDB rating of the movie.
     */
    public Movie(int year, double duration, String director, String s1, String s2, String s3, String s4, double rating) {
        this.year = year;
        this.duration = duration;
        this.director = director;
        this.stars = new String[]{s1, s2, s3, s4};
        this.rating = rating;
    }

    /**
     * Returns the release year of the movie.
     *
     * @return the release year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the duration of the movie in minutes.
     *
     * @return the movie duration.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Returns the director of the movie.
     *
     * @return the director's name.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns an array containing the names of the starring actors.
     *
     * @return an array of starring actors.
     */
    public String[] getStars() {
        return stars;
    }

    /**
     * Returns the IMDB rating of the movie.
     *
     * @return the IMDB rating.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Returns a string representation of the movie.
     *
     * @return a formatted string with movie details.
     */
    @Override
    public String toString() {
        return "Movie{" +
                "year=" + year +
                ", duration=" + duration +
                ", director='" + director + '\'' +
                ", stars=" + Arrays.toString(stars) +
                ", rating=" + rating +
                '}';
    }
}
