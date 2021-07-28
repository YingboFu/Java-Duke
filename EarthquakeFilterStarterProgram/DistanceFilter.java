
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location myLocation;
    private float maxDistance;
    private String name;
    
    public DistanceFilter (Location loc, float max, String s) {
        myLocation = loc;
        maxDistance = max;
        name = s;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(myLocation) < maxDistance;
    }
    
    public String getName() {
        return name;
    }
}
