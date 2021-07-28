
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String genre;
    
    public GenreFilter (String myGenre) {
        genre = myGenre;
    }
    
    public boolean satisfies(String id) {
         String[] genres = MovieDatabase.getGenres(id).split(", ");
         for (int i = 0; i < genres.length; i++) {
             if(genres[i].equals(genre)) {
                 return true;
             }
         }
         return false;
    }
}
