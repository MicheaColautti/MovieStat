import java.util.Arrays;

public class Movie {

    private int year;
    private double duration;
    private String director;
    private String[] stars;


    public Movie(int year, double duration, String director, String s1, String s2, String s3, String s4) {
        this.year = year;
        this.duration = duration;
        this.director = director;
        this.stars = new String[]{s1, s2, s3, s4};

    }

    public int getYear() {
        return year;
    }

    public double getDuration() {
        return duration;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "year=" + year +
                ", duration=" + duration +
                ", director='" + director + '\'' +
                ", stars=" + Arrays.toString(stars) +
                '}';
    }

    public String[] getStars() {
        return stars;
    }
}
