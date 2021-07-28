import java.util.*;
import java.util.Random;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate () {
        ArrayList<String> items = new ArrayList<String>();

        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ArrayList<String> genres = new ArrayList<String>();
        String[] tmp = {"Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", 
                        "Film-Noir", "Game-Show", "History", "Horror", "Music", "Musical", "Mystery", "Reality-TV", "Romance", "Sci-Fi", 
                        "Short", "Sport", "Thriller", "War", "Western"};
        for (int i = 0; i < tmp.length; i++) {
            genres.add(tmp[i]);
        }
        
        for (String genere : genres) {
            AllFilters af = new AllFilters();
            af.addFilter(new GenreFilter(genere));
            af.addFilter(new YearAfterFilter(2010));
            
            ArrayList<String> movies = MovieDatabase.filterBy(af);
            if (movies.size() != 0) {
                Random rand = new Random();
                int randomIdx = rand.nextInt(movies.size());
                if (!items.contains(movies.get(randomIdx))) {
                    items.add(movies.get(randomIdx));
                }
            }
        }

        return items;
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, 10, 2);
        
        if (similarRatings.size() == 0) {
            System.out.println("Sorry! No Appropriate Recommendations For You!");
        }
        
        System.out.println("<table>");
        System.out.println("<tr>    <th>ID</th>  <th>Title</th>   <th>Year</th>    <th>Genres</th>     <th>Director</th>");
        System.out.println("        <th>Country</th>  <th>Minutes</th>   <th>Poster</th>    </tr>");
        for (int i = 0; i < 10; i++) {
            if (i >= similarRatings.size()) {
                break;
            }
            System.out.println("<tr>");
                String id = similarRatings.get(i).getItem();
                System.out.println("<td>" + id + "</td>");
                System.out.println("<td>" + MovieDatabase.getTitle(id) + "</td>");
                System.out.println("<td>" + MovieDatabase.getYear(id) + "</td>");
                System.out.println("<td>" + MovieDatabase.getGenres(id) + "</td>");
                System.out.println("<td>" + MovieDatabase.getDirector(id) + "</td>");
                System.out.println("<td>" + MovieDatabase.getCountry(id) + "</td>");
                System.out.println("<td>" + MovieDatabase.getMinutes(id) + "</td>");
                System.out.println("<td> <img src=\"" + MovieDatabase.getPoster(id) + "\" width=\"100\"/> </td>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");
    }
}
