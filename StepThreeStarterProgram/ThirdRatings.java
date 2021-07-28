
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String ID : movies) {
            double avg = getAverageByID(ID, minimalRaters);
            if (avg != 0.0) {
                Rating curr = new Rating(ID, avg);
                ratings.add(curr);
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> IDs = MovieDatabase.filterBy(filterCriteria);
        for (String ID : IDs) {
            double avg = getAverageByID(ID, minimalRaters);
            if (avg != 0.0) {
                Rating curr = new Rating(ID, avg);
                ratings.add(curr);
            }
        }
        return ratings;
    }
}
