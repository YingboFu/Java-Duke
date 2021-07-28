import edu.duke.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows () {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        String key = "t.";
        System.out.println(markov.getFollows(key));
        System.out.println(markov.getFollows(key).size());
    }
    
    public void testGetFollowsWithFile () {
        FileResource fr = new FileResource();
        MarkovOne markov = new MarkovOne();
        markov.setTraining(fr.asString());
        String key = "he";
        System.out.println(markov.getFollows(key));
        System.out.println(markov.getFollows(key).size());
    }
}
