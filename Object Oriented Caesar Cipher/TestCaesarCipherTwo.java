import edu.duke.*;

public class TestCaesarCipherTwo {
    private String halfOfString (String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    private int[] countLetters (String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index]++;
            }
        }
        return counts;
    }
    
    private int maxIndex (int[] freqs) {
        int maxIndex = 0;
        for (int i = 1; i < freqs.length; i++) {
            if (freqs[i] > freqs[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    int getKey (String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public String breakCaesarCipher (String input) {
        String firstHalfString = halfOfString(input, 0);
        String secondHalfString = halfOfString(input, 1);
        int key1 = getKey(firstHalfString);
        int key2 = getKey(secondHalfString);
        System.out.println("Key1: " + key1 + " Key2: " + key2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(key1,key2);
        return cc2.decrypt(input);
    }
    
    public void simpleTests () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17,3);
        String encrypted = cc2.encrypt(input);
        System.out.println("Encrypted String:\n" + encrypted);
        String decrypted = cc2.decrypt(encrypted);
        System.out.println("Dncrypted String:\n" + decrypted);
        
        String decryptedWithBreak = breakCaesarCipher(encrypted);
        System.out.println("Dncrypted With Break String:\n" + decryptedWithBreak);
    }
    
    public void exam () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        
        String decryptedWithBreak = breakCaesarCipher(input);
        System.out.println("Dncrypted With Break String:\n" + decryptedWithBreak);
    }
}
