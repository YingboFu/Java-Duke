import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+= totalSlices) {
            sb.append(message.charAt(i));
        }
        String ans = sb.toString();
        return ans;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i = 0; i < klength; i++) {
            String tmp = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int tmpKey = cc.getKey(tmp);
            key[i] = tmpKey;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String tmp = fr.asString();
        // Q1
        //int[] key = tryKeyLength(tmp, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(key);
        //String ans = vc.decrypt(tmp);
        //Q2
        //FileResource frD = new FileResource("dictionaries/English");
        //HashSet<String> dictionary = readDictionary(frD);
        //String ans = breakForLanguage(tmp, dictionary);
        //System.out.println(ans.substring(0,ans.indexOf("\n")));
        String[] languageNames = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (int i = 0; i < languageNames.length; i++) {
            FileResource frD = new FileResource("dictionaries/" + languageNames[i]);
            languages.put(languageNames[i], readDictionary(frD));
            System.out.println(languageNames[i] + " Finished!");
        }
        breakForAllLangs(tmp, languages);
    }
    
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for(String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    public Integer countWords (String message, HashSet<String> dictionary) {
        Integer count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        HashMap<String, int[]> countKeyMap = new HashMap<String, int[]>();
        int max = 0;
        char c = mostCommonCharIn (dictionary);
        for (int i = 1; i < 100; i++) {
            int[] key = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            Integer count = countWords(decrypted, dictionary);
            countKeyMap.put(Integer.toString(count), key);
        }
        for (String countS : countKeyMap.keySet()) {
            if (Integer.parseInt(countS) > max) {
                max = Integer.parseInt(countS);
            }
        }
        System.out.println(Integer.toString(max)); //debugging information
        int[] key = countKeyMap.get(Integer.toString(max));
        System.out.println(Arrays.toString(key));  //debugging information
        System.out.println(key.length);  //debugging information
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        
        return decrypted;
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        int maxIdx = 0;
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                int index = alph.indexOf(Character.toLowerCase(word.charAt(i)));
                if (index != -1){
                    counts[index] += 1;
                }
            }
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > counts[maxIdx]) {
                maxIdx = i;
            }
        }
        return alph.charAt(maxIdx);
    }
    
    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        for (String language : languages.keySet()) {
            String decrypted = breakForLanguage(encrypted, languages.get(language));
            count.put(language, countWords(decrypted, languages.get(language)));
        }
        for (String language : count.keySet()) {
            if (count.get(language).intValue() > max) {
                max = count.get(language).intValue();
            }
        }
        for (String language : count.keySet()) {
            if (count.get(language).intValue() == max) {
                System.out.println(breakForLanguage(encrypted, languages.get(language)));
                System.out.println(language);
            }
        }
    }
}
