import java.util.*;

/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String lastW1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String lastW2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
        if(lastW1.compareTo(lastW2) == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return lastW1.compareTo(lastW2);
    }
}
