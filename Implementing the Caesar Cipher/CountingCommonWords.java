import edu.duke.*;

public class CountingCommonWords {
    String[] getCommon() {
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int i = 0;
        for (String word : fr.words()) {
            common[i] = word;
            i++;
        }
        return common;
    }
    
    int indexOf(String[] common, String word) {
        for (int i = 0; i < common.length; i++) {
            if (common[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
    
    void countWords (FileResource resource, String[] common, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if (index != -1) {
                counts[index]++;
            }
        }
    }
    
    void countShakespeare(){
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int i = 0; i < plays.length; i++) {
            FileResource resource = new FileResource("data/" + plays[i]);
            countWords(resource,common,counts);
            System.out.println("Done with " + plays[i]);
        }
        
        for (int i = 0; i < common.length; i++) {
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }
}
