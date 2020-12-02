import edu.duke.*;

public class CaesarBreaker {
    int[] countLetters (String message) {
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
    
    int maxIndex (int[] freqs) {
        int maxIndex = 0;
        for (int i = 1; i < freqs.length; i++) {
            if (freqs[i] > freqs[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    String decrypt (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    String halfOfString (String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
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
    
    String decryptTwoKeys (String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalfString = halfOfString(encrypted, 0);
        String secondHalfString = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalfString);
        int key2 = getKey(secondHalfString);
        System.out.println("Key1: " + key1 + " Key2 " + key2);
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
    }
    
    void testDecrypt () {
        System.out.println(decrypt("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!"
                                        + "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"));
    }
    
    void testHalfOfString () {
        System.out.println(halfOfString("Qbkm Zgis", 0));
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    void testDecryptTwoKeys () {
        FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        String s = fr.asString();
        System.out.println(decryptTwoKeys(s));
    }
}
