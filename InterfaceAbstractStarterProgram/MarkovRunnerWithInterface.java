
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 715;
        /*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        */
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap () {
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        emm.setRandom(42);
        emm.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        emm.getRandomText(50);
    }
    
    public void compareMethods () {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 531;
        /*
        MarkovModel mm = new MarkovModel(2);
        long startTime = System.nanoTime();
        runModel(mm, st, size, seed);
        long stopTime = System.nanoTime();
        System.out.println("MarkovModel Execution Time: " + ((stopTime - startTime) / 1000000000));
        */
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        //long startTime2 = System.nanoTime();
        runModel(emm, st, size, seed);
        //long stopTime2 = System.nanoTime();
        //System.out.println("EfficientMarkovModel Execution Time: " + ((stopTime2 - startTime2) / 1000000000));
        
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
