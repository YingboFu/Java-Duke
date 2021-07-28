import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        /*
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        */
    }
    
    public void printAverageRatingsByYear() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        YearAfterFilter yf = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20, yf);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) 
                                + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        GenreFilter filter = new GenreFilter("Comedy");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(20, filter);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new MinutesFilter(105, 135);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(5, f);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        /*
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getMinutes(r.getItem()) 
                                + " " + MovieDatabase.getTitle(r.getItem()));
        }
        */
    }
    
    public void printAverageRatingsByDirectors() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(4, f);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters mf = new AllFilters();
        mf.addFilter(new YearAfterFilter(1990));
        mf.addFilter(new GenreFilter("Drama"));
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(8, mf);
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
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        System.out.println();
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("The number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        AllFilters mf = new AllFilters();
        mf.addFilter(new MinutesFilter(90, 180));
        mf.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(3, mf);
        System.out.println(ratings.size());
        Collections.sort(ratings);
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getMinutes(r.getItem()) 
                                + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
}
