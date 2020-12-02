

public class Decrypt {
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
    
    void testDecrypt () {
        System.out.println(decrypt("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!"
                                        + "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"));
    }
}
