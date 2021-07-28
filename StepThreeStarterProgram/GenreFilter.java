
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
         if (MovieDatabase.getGenres(id).indexOf(genre) != -1) {
             return true;
         }
         return false;
    }
}
