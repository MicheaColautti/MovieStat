/**
 * Test class
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-03-09
 */
public class Test {

    public static void main(String[] args) {
        MovieStat movieStat = new MovieStat();
        movieStat.load();
        movieStat.compute();
    }
}