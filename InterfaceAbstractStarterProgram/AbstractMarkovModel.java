
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows (String key) {
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()) {
            int idx = myText.indexOf(key, pos);
            if (idx == -1 || (idx + key.length() >= myText.length())) {
                break;
            }
            ans.add(myText.substring(idx+key.length(), idx+key.length()+1));
            pos = idx + 1;
        }
        return ans;
    }
}
