import edu.duke.*;

public class CaesarCipher {
    String encrypt (String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String aplhabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String shiftedAplhabet = aplhabet.substring(key,26) + aplhabet.substring(0, key)
                                    + aplhabet.substring(key + 26) + aplhabet.substring(26, key + 26);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (aplhabet.indexOf(currChar) != -1) {
                char newChar = shiftedAplhabet.charAt(aplhabet.indexOf(currChar));
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
    
    String encryptTwoKeys (String input, int key1, int key2) {
        StringBuilder sb = new StringBuilder(input);
        String aplhabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String shiftedAplhabetKey1 = aplhabet.substring(key1,26) + aplhabet.substring(0, key1)
                                    + aplhabet.substring(key1 + 26) + aplhabet.substring(26, key1 + 26);
        String shiftedAplhabetKey2 = aplhabet.substring(key2,26) + aplhabet.substring(0, key2)
                                    + aplhabet.substring(key2 + 26) + aplhabet.substring(26, key2 + 26);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (aplhabet.indexOf(currChar) != -1) {
                //char newChar = shiftedAplhabet.charAt(aplhabet.indexOf(currChar));
                //sb.setCharAt(i, newChar);
                if (i % 2 == 0) {
                    char newChar = shiftedAplhabetKey1.charAt(aplhabet.indexOf(currChar));
                    sb.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAplhabetKey2.charAt(aplhabet.indexOf(currChar));
                    sb.setCharAt(i, newChar);
                }
            }
        }
        return sb.toString();
    }
    
    void testEncrypt () {
        //System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String encrypted = encrypt(message, 23);
        //System.out.println("key is " + 23 + "\n" + encrypted);
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"
                                    + "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE", 15));
        //System.out.println(encrypt("First Legion", 17));
    }
    
    void testEncryptTwoKeys () {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));
    }
}
