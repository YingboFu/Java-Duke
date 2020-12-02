

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1,26) + alphabet.substring(0, key1)
                                    + alphabet.substring(key1 + 26) + alphabet.substring(26, key1 + 26);
        shiftedAlphabet2 = alphabet.substring(key2,26) + alphabet.substring(0, key2)
                                    + alphabet.substring(key2 + 26) + alphabet.substring(26, key2 + 26);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt (String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (alphabet.indexOf(currChar) != -1) {
                if (i % 2 == 0) {
                    char newChar = shiftedAlphabet1.charAt(alphabet.indexOf(currChar));
                    sb.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAlphabet2.charAt(alphabet.indexOf(currChar));
                    sb.setCharAt(i, newChar);
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt (String input) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc2.encrypt(input);
    }
}
