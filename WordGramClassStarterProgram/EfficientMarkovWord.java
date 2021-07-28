import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap () {
        myMap = new HashMap<WordGram, ArrayList<String>>();
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram kGram = new WordGram(myText, i, myOrder);
            if (myMap.containsKey(kGram) && i != (myText.length - myOrder)) {
                myMap.get(kGram).add(myText[i+myOrder]);
            } else if ((!myMap.containsKey(kGram)) && i == (myText.length - myOrder)) {
                myMap.put(kGram, new ArrayList<String>());
            } else if ((!myMap.containsKey(kGram)) && i < (myText.length - myOrder)) {
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(myText[i+myOrder]);
                myMap.put(kGram, tmp);
            }
        }
    }
    
    public void printHashMapInfo(){
        /*
        for (WordGram kGram : myMap.keySet()) {
            System.out.println(kGram + ": " + myMap.get(kGram));
        }
        */
        System.out.println("The number of keys: " + myMap.size());
        int sizeLarVal = 0;
        for (WordGram kGram : myMap.keySet()) {
            if (myMap.get(kGram).size() > sizeLarVal) {
                sizeLarVal = myMap.get(kGram).size();
            }
        }
        System.out.println("The size of the largest value: " + sizeLarVal);
        for (WordGram kGram : myMap.keySet()) {
            if (myMap.get(kGram).size() == sizeLarVal) {
                System.out.println("The keys that have the maximum size value: " + kGram);
            }
        }
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollowsE(key);
            //System.out.println(key + ": " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i <= words.length-target.length(); i++) {
            WordGram curr = new WordGram(words, i, myOrder);
            if(curr.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf () {
        String[] test = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        WordGram target = new WordGram(test, 6, 4);
        System.out.println(indexOf(test, target, 0));
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - kGram.length()) {
            int index = indexOf(myText, kGram, pos);
            if (index == -1 || index == (myText.length - kGram.length())) {
                break;
            }
            follows.add(myText[index + kGram.length()]);
            pos = index + 1;
        }
        return follows;
    }
    
    private ArrayList<String> getFollowsE(WordGram kGram) {
        return myMap.get(kGram);
    }
    
}
