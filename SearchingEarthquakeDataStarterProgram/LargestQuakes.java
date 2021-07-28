import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes () {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        //System.out.printf("Index: %d\t Magnitude: %.2f\n", indexOfLargest(list), list.get(indexOfLargest(list)).getMagnitude());
        
        ArrayList<QuakeEntry> listLarge = getLargest(list, 50);
        for (QuakeEntry qe : listLarge) {
            System.out.println(qe);
        }
        System.out.println("number found: "+listLarge.size());
    }
    
    public Integer indexOfLargest (ArrayList<QuakeEntry> data) {
        Integer largestIdx = 0;
        for (QuakeEntry qe : data) {
            if (qe.getMagnitude() > data.get(largestIdx).getMagnitude()) {
                largestIdx = data.indexOf(qe);
            }
        }
        return largestIdx;
    }
    
    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, Integer howMany) {
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry> (quakeData);
        for (int i = 0; i < howMany; i++) {
            int largestIdx = indexOfLargest(copy);
            ans.add(copy.get(largestIdx));
            copy.remove(largestIdx);
            if(copy.size() == 0) {
                break;
            }
        }
        return ans;
    }
}
