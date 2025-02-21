import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class MovieList {

    private List<Movie> movieList;
    Logger logger = new Logger();
    CsvTool csvTool = new CsvTool();


    public void pupulate() {
        movieList = csvTool.readCsv();
    }

    public int getMoviesNumber() {
        return movieList.size();
    }

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

    @Override
    public String toString() {
        for (Movie m : movieList) {
            System.out.println(m);

        }
        return "";
    }

    public String getBestDirector() {
        return "";
    }

    public String getMostPresentStar() {
        return "";
    }

    public int getMostProdYear() {
        return 0;
    }

}
