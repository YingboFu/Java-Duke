

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher (int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key,26) + alphabet.substring(0, key)
                                    + alphabet.substring(key + 26) + alphabet.substring(26, key + 26);
        mainKey = key;
    }
    
    public String encrypt (String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (alphabet.indexOf(currChar) != -1) {
                char newChar = shiftedAlphabet.charAt(alphabet.indexOf(currChar));
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }
    
    public String decrypt (String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}

