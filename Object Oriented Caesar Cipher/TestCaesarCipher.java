import edu.duke.*;

public class TestCaesarCipher {
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
    
    public String breakCaesarCipher (String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    
    public void simpleTests () {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted String:\n" + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Dncrypted String:\n" + decrypted);
        
        String decryptedWithBreak = breakCaesarCipher(encrypted);
        System.out.println("Dncrypted With Break String:\n" + decryptedWithBreak);
    }
    
    public void exam () {
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println("Encrypted String:\n" + encrypted);
    }
}
