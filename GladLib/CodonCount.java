import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> myMap;
    
    public CodonCount () {
        myMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap (int start, String dna) {
        myMap.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (!myMap.containsKey(codon)) {
                myMap.put(codon, 1);
            } else {
                int value = myMap.get(codon);
                myMap.put(codon, value + 1);
            }
        }
    }
    
    private String getMostCommonCodon () {
        String mostCommonCodon = "";
        for (String codon : myMap.keySet()) {
            if (mostCommonCodon == "") {
                mostCommonCodon = codon;
            } else {
                if (myMap.get(codon) > myMap.get(mostCommonCodon)) {
                    mostCommonCodon = codon;
                }
            }
        }
        return mostCommonCodon;
    }
    
    private void printCodonCounts (int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String codon : myMap.keySet()) {
            if (myMap.get(codon) >= start && myMap.get(codon) <= end) {
                System.out.println(codon + "\t" + myMap.get(codon));
            }
        }
    }
    
    public void tester () {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        dna = dna.substring(0, dna.indexOf("\n"));
        
        
        System.out.println();
        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in " + myMap.size() + " unique codons");
        String mostCommonCodon = getMostCommonCodon();
        System.out.println(" and most common codon is " + mostCommonCodon +" with count "  + myMap.get(mostCommonCodon));
        printCodonCounts(7, 7);
        
        System.out.println();
        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in " + myMap.size() + " unique codons");
        mostCommonCodon = getMostCommonCodon();
        System.out.println(" and most common codon is " + mostCommonCodon +" with count "  + myMap.get(mostCommonCodon));
        printCodonCounts(6, 6);
        
        System.out.println();
        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in " + myMap.size() + " unique codons");
        mostCommonCodon = getMostCommonCodon();
        System.out.println(" and most common codon is " + mostCommonCodon +" with count "  + myMap.get(mostCommonCodon));
        printCodonCounts(4, 4);
    }
}
