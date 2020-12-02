import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies () {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void findUnique () {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            int index = myWords.indexOf(word.toLowerCase());
            if (index == -1) {
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    private int findIndexOfMax () {
        int maxIndex = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester () {
        findUnique ();
        //System.out.println("The number of unique words: " + myWords.size());
        // for (int i = 0; i < myWords.size(); i++) {
            // System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        // }
        
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs the most often is: " + myWords.get(maxIndex) + 
                            " with " + myFreqs.get(maxIndex) + " times.");
    }
}
