
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;
    
    public PhraseFilter (String w, String p, String s) {
        where = w;
        phrase = p;
        name = s;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if(where.equals("start") && qe.getInfo().startsWith(phrase)) {
            return true;
        } else if (where.equals("end") && qe.getInfo().endsWith(phrase)) {
            return true;
        } else if (where.equals("any") && (qe.getInfo().indexOf(phrase) != -1)) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
}
