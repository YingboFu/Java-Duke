import java.util.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void printGenres () {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<String> genres = new ArrayList<String>();
        for (Movie m : MovieDatabase.getMovies()) {
            String[] tmp = m.getGenres().split(", ");
            for (int i = 0; i < tmp.length; i++) {
                if (!genres.contains(tmp[i]) && (!tmp[i].equals("N/A"))) {
                    genres.add(tmp[i]);
                }
            }
        }
        Collections.sort(genres);
        System.out.println(genres.size());
        System.out.println(genres);
    }
}
