import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> ratings = fr.getAverageRatings(35);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        /*
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        */
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters mf = new AllFilters();
        mf.addFilter(new YearAfterFilter(1990));
        mf.addFilter(new GenreFilter("Drama"));
        
        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(8, mf);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        /*
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) 
                                + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
        */
    }
    
    public void printSimilarRatings () {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatings("71", 20, 5);

        System.out.println(MovieDatabase.getTitle(similarRatings.get(0).getItem()) + "\t" + similarRatings.get(0).getValue());

    }
    
    public void printSimilarRatingsByGenre() {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        GenreFilter filter = new GenreFilter("Mystery");
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("964", 20, 5, filter);
        
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector() {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("120", 10, 2, f);
        System.out.println(similarRatings.size());
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters mf = new AllFilters();
        mf.addFilter(new GenreFilter("Drama"));
        mf.addFilter(new MinutesFilter(80, 160));
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("168", 10, 3, mf);
        System.out.println(similarRatings.size());
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        System.out.println();
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters mf = new AllFilters();
        mf.addFilter(new YearAfterFilter(1975));
        mf.addFilter(new MinutesFilter(70, 200));
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter("314", 10, 5, mf);
        System.out.println(similarRatings.size());
        for (Rating r : similarRatings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getYear(r.getItem()) + " "
                                + MovieDatabase.getMinutes(r.getItem()) + " " + r.getValue());
        }
    }
}
