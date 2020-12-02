import edu.duke.*;

public class WordLengths {
    void countWordLengths (FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            if (word.length() == 1 && !Character.isLetter(word.charAt(0))) {
                counts[0]++;
            } else {
                if (!Character.isLetter(word.charAt(0)) && !Character.isLetter(word.charAt(word.length() - 1))) {
                    if (word.length() - 2 > counts.length - 1) {
                        counts[counts.length - 1]++;
                    } else {
                        counts[word.length() - 2]++;
                    }
                } else if (!Character.isLetter(word.charAt(0)) || !Character.isLetter(word.charAt(word.length() - 1))) {
                    if (word.length() - 1 > counts.length - 1) {
                        counts[counts.length - 1]++;
                    } else {
                        counts[word.length() - 1]++;
                    }
                } else {
                    if (word.length() > counts.length - 1) {
                        counts[counts.length - 1]++;
                    } else {
                        counts[word.length()]++;
                    }
                }
            }
        }
    }
    
    int indexOfMax (int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 1; i < counts.length; i++) {
            System.out.println(counts[i] + " words of length " + i);
        }
        System.out.println("The most common length is: " + indexOfMax(counts));
    }
}
