import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    private double getAverageByID(String ID, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        for (Rater rater : RaterDatabase.getRaters()) {
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
    
    private int dotProduct (Rater me, Rater r) {
        int ans = 0;
        for (String item : me.getItemsRated()) {
            if (r.hasRating(item)) {
                ans += (me.getRating(item) - 5) * (r.getRating(item) - 5);
            }
        }
        return ans;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            if (!r.getID().equals(id)) {
                int dp = dotProduct(RaterDatabase.getRater(id), r);
                if (dp > 0) {
                    ans.add(new Rating(r.getID(), dp));
                }
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
    
    private double getWeightedAverageByID(String ID, ArrayList<Rating> similarities, int numSimilarRaters, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        for (int i = 0; i < numSimilarRaters; i++) {
            Rater rater = RaterDatabase.getRater(similarities.get(i).getItem());
            if (rater.hasRating(ID)) {
                sum += rater.getRating(ID) * similarities.get(i).getValue();
                count++;
            }
        }
        if (count >= minimalRaters) {
            return sum/count;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String ID : movies) {
            double avg = getWeightedAverageByID(ID, similarities, numSimilarRaters, minimalRaters);
            if (avg != 0.0) {
                Rating curr = new Rating(ID, avg);
                ratings.add(curr);
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter (String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String ID : movies) {
            double avg = getWeightedAverageByID(ID, similarities, numSimilarRaters, minimalRaters);
            if (avg != 0.0) {
                Rating curr = new Rating(ID, avg);
                ratings.add(curr);
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
}
