import java.util.*;
import edu.duke.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    
    public WordsInFiles () {
        myMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!myMap.containsKey(word)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(f.getName());
                myMap.put(word, list);
            } else {
                if (!myMap.get(word).contains(f.getName())) {
                    myMap.get(word).add(f.getName());
                }
            }
        }
    }
    
    private void buildWordFileMap () {
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber () {
        int max = 0;
        for (String word : myMap.keySet()) {
            if (myMap.get(word).size() > max) {
                max = myMap.get(word).size();
            }
        }
        return max;
    }
    
    private ArrayList wordsInNumFiles (int number) {
        ArrayList<String> wordsInNumFiles = new ArrayList<String>();
        for (String word : myMap.keySet()) {
            if (myMap.get(word).size() == number) {
                wordsInNumFiles.add(word);
            }
        }
        return wordsInNumFiles;
    }
    
    private void printFilesIn (String word) {
        ArrayList<String> list = myMap.get(word);
        for (String file : list) {
            System.out.println(file);
        }
    }
    
    public void tester () {
        buildWordFileMap();
        System.out.println();
        //System.out.println("maxNumber: " + maxNumber());
        //ArrayList<String> wordsInNumFiles = wordsInNumFiles(5);
        System.out.println("How many words are there that occur in 4 files?" + wordsInNumFiles(4).size());
        // for (String word : wordsInNumFiles) {
            // System.out.println("Files with \"" + word + "\" : ");
            // printFilesIn(word);
        // }
        // for (String word : myMap.keySet()) {
            // System.out.print("\"" + word + "\"" + " appears in the files: ");
            // for (String file : myMap.get(word)) {
                // System.out.print(file + ", ");
            // }
            // System.out.println();
        // }
    }
    
    public void exam () {
        buildWordFileMap();
        System.out.println();
        System.out.println("In which file does the word “tree” appear? ");
        //System.out.println("caesar.txt, confucius.txt, errors.txt, hamlet.txt, likeit.txt, macbeth.txt and romeo.txt.");
        for (String file : myMap.get("tree")) {
            System.out.println(file);
        }
    }
}
