import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            //(String anID, String aTitle, String aYear, String theGenres, String aDirector,
            //    String aCountry, String aPoster, int theMinutes)
            //id,title,year,country,genre,director,minutes,poster
            Movie curr = new Movie(record.get("id"), record.get("title"), record.get("year"),
                                     record.get("genre"), record.get("director"), record.get("country"),
                                     record.get("poster"), Integer.parseInt(record.get("minutes")));
            movies.add(curr);
        }
        return movies;
    }
    
    private void addDirector (HashMap<String, Integer> directorCounter, String director) {
        if (!directorCounter.containsKey(director)) {
            directorCounter.put(director, 1);
        } else {
            Integer counter = directorCounter.get(director) + 1;
            directorCounter.put(director, counter);
        }
    }
    
    public void testLoadMovies () {
        System.out.println();
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("The number of movies: " + movies.size());
        
        int genreCounter = 0;
        for (Movie movie : movies) {
            if (movie.getGenres().indexOf("Comedy") != -1) {
                genreCounter++;
            }
        }
        System.out.println("The number of movies including the Comedy genre: " + genreCounter);
        
        int lengthCounter = 0;
        for (Movie movie : movies) {
            if (movie.getMinutes() > 150) {
                lengthCounter++;
            }
        }
        System.out.println("The number of movies that are longer than 150 minutes: " + lengthCounter);
        
        HashMap<String, Integer> directorCounter = new HashMap<String, Integer>();
        for (Movie movie : movies) {
            String director = movie.getDirector();
            if (director.indexOf(",") == -1) {
                addDirector(directorCounter, director);
            } else {
                int from = 0;
                while (director.indexOf(",", from) != -1) {
                    int idx = director.indexOf(",", from);
                    String currDirector = director.substring(from, idx);
                    addDirector(directorCounter, currDirector);
                    from += currDirector.length() + 2;
                }
                String currDirector = director.substring(from);
                addDirector(directorCounter, currDirector);
            }
        }
        int max = 0;
        for (Integer v : directorCounter.values()) {
            if (v > max) {
                max = v;
            }
        }
        System.out.println("The maximum number of movies by any director: " + max);
        for (String name : directorCounter.keySet()) {
            if (directorCounter.get(name) == max) {
                System.out.println("The director's name: " + name);
            }
        }

    }
    
    private int indexOf (ArrayList<Rater> raters, String raterID) {
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).getID().equals(raterID)) {
                return i;
            }
        }
        return -1;
    }
    
    public ArrayList<Rater> loadRaters (String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            //rater_id,movie_id,rating,time
            //public Rater(String id);
            //public void addRating(String item, double rating);
            //public Rating (String anItem, double aValue);
            if (indexOf(raters, record.get("rater_id")) == -1) {
                Rater rater = new Rater(record.get("rater_id"));
                raters.add(rater);
            }
            int idx = indexOf(raters, record.get("rater_id"));
            raters.get(idx).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
        }
        return raters;
    }
    
    public void testLoadRaters () {
        System.out.println();
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("The total number of raters: " + raters.size());
        /*
        for (Rater rater : raters) {
            System.out.println("ID: " + rater.getID() + "\t Number of ratings: " + rater.numRatings());
            ArrayList<String> items = rater.getItemsRated();
            for (String item : items) {
                System.out.println(item + ": " + rater.getRating(item));
            }
        }
        */
        String raterID = "193";
        int idx = indexOf(raters, raterID);
        System.out.println("The rater " + raterID + " has rated " + raters.get(idx).numRatings() + " items");
        
        int max = 0;
        for (Rater rater : raters) {
            if (rater.numRatings() > max) {
                max = rater.numRatings();
            }
        }
        int count = 0;
        for (Rater rater : raters) {
            if (rater.numRatings() == max) {
                System.out.println("Rater ID: " + rater.getID());
                count++;
            }
        }
        System.out.println("The number of raters having " + max + " ratings: " + count);
        
        String item = "1798709";
        int rantingCount = 0;
        for (Rater rater : raters) {
            if (rater.hasRating(item)) {
                rantingCount++;
            }
        }
        System.out.println(item + " was rated by " + rantingCount + " raters");
        
        ArrayList<String> items = new ArrayList<String>();
        for (Rater rater : raters) {
            ArrayList<String> list = rater.getItemsRated();
            for (String currItem : list) {
                if (!items.contains(currItem)) {
                    items.add(currItem);
                }
            }
        }
        System.out.println("There are " + items.size() + " different movies have been rated");
    }
}
