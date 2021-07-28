
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String ID, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        for (Rater rater : myRaters) {
            if (rater.hasRating(ID)) {
                sum += rater.getRating(ID);
                count++;
            }
        }
        if (count >= minimalRaters) {
            return sum/count;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            String ID = movie.getID();
            double avg = getAverageByID(ID, minimalRaters);
            if (avg != 0.0) {
                Rating curr = new Rating(ID, avg);
                ratings.add(curr);
            }
        }
        return ratings;
    }
    
    public String getTitle (String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "NO SUCH ID.";
    }
    
    public String getID (String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
