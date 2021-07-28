
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] directors;
    
    public DirectorsFilter (String myDirectors) {
        directors = myDirectors.split(",");
    }
    
    public boolean satisfies(String id) {
        for (int i = 0; i < directors.length; i++) {
            if (MovieDatabase.getDirector(id).indexOf(directors[i]) != -1) {
                return true;
            }
        }
        return false;
    }
}
