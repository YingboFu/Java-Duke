import java.util.*;

/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovOne extends AbstractMarkovModel {
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        int index = 0;
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        index = myRandom.nextInt(myText.length()-1);
        String next = myText.substring(index,index + 1);
        sb.append(next);
        //System.out.println(next);
        
        for(int k=0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(next);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
            next = follows.get(index);
            //System.out.println(next);
        }
        
        return sb.toString();
    }
    
    public String toString () {
        return "MarkovModel of order 1.";
    }
}
