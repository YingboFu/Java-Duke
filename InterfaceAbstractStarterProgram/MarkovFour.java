import java.util.*;

/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovFour extends AbstractMarkovModel {
    
    public MarkovFour() {
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
        index = myRandom.nextInt(myText.length()-4);
        String next = myText.substring(index,index + 4);
        sb.append(next);
        //System.out.println(next);
        
        for(int k=0; k < numChars - 4; k++){
            ArrayList<String> follows = getFollows(next);
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
        return "MarkovModel of order 4.";
    }
}
