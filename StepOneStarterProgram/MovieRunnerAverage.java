import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings() {
        System.out.println();
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        //System.out.println("The number of movies: " + sr.getMovieSize());
        //System.out.println("The number of raters: " + sr.getRaterSize());
        
        ArrayList<Rating> ratings = sr.getAverageRatings(12);
        //System.out.println(ratings.size());
        Collections.sort(ratings);
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        System.out.println();
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String movie = "Vacation";
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        for (Rating r : ratings) {
            if (r.getItem().equals(sr.getID(movie))) {
                System.out.println("The average rating for " + movie + " is: " + r.getValue());
            }
        }
    }
}
