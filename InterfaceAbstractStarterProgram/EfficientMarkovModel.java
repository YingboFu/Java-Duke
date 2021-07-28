import java.util.*;

/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int keyLength;
    private HashMap<String, ArrayList<String>> followsMap;
    
    public EfficientMarkovModel(int keyLen) {
        myRandom = new Random();
        keyLength = keyLen;
    }
    
    public void setTraining(String s){
        myText = s.trim();
        followsMap = buildMap();
        printHashMapInfo();
    }
    
    public HashMap<String, ArrayList<String>> buildMap () {
        followsMap = new HashMap<String, ArrayList<String>>();
        for(int k=0; k <= myText.length() - keyLength; k++){
            String key = myText.substring(k, k + keyLength);
            if (!followsMap.containsKey(key)) {
                followsMap.put(key, getFollows(key));
            }
            //System.out.println(key);
        }
        return followsMap;
    }
    
    public void printHashMapInfo () {
        //System.out.println(followsMap);
        System.out.println("The number of keys: " + followsMap.size());
        int larVal = 0;
        for (String key : followsMap.keySet()) {
            if (followsMap.get(key).size() > larVal) {
                larVal = followsMap.get(key).size();
            }
        }
        System.out.println("The size of the largest value: " + larVal);
        for (String key : followsMap.keySet()) {
            if (followsMap.get(key).size() == larVal) {
                System.out.println("The keys that have the maximum size value: " + key);
            }
        }
    }
    
    public ArrayList<String> getFollowsE (HashMap<String, ArrayList<String>> followsMap, String key) {
        return followsMap.get(key);
    }
    
    public String getRandomText(int numChars){
        int index = 0;
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        index = myRandom.nextInt(myText.length()-keyLength);
        String next = myText.substring(index,index + keyLength);
        sb.append(next);
        //System.out.println(next);
        
        for(int k=0; k < numChars - keyLength; k++){
            ArrayList<String> follows = getFollowsE(followsMap, next);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            next = next.substring(1) + follows.get(index);
            //System.out.println(next);
        }
        
        return sb.toString();
    }
    
    public String toString () {
        return "This is the EfficientMarkovModel class of " + keyLength + ".";
    }
}
