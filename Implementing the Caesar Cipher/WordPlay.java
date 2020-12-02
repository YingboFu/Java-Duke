import edu.duke.*;


public class WordPlay {
    boolean isVowel (char ch) {
        String vowel = "aeiouAEIOU";
        if (vowel.indexOf(ch) != -1) {
            return true;
        }
        return false;
    }
    
    String replaceVowels (String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (isVowel(currChar)) {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    String emphasize (String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (currChar == Character.toLowerCase(ch) || currChar == Character.toUpperCase(ch)) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, '*');
                } else {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
    
    void testIsVowel () {
        System.out.println("Is 'F' a vowel? " + isVowel('F'));
        System.out.println("Is 'a' a vowel? " + isVowel('a'));
    }
    
    void testReplaceVowels () {
        FileResource fr = new FileResource();
        String test = fr.asString();
        System.out.println("“Hello World”, ‘*’: " + replaceVowels(test, '*'));
    }
    
    void testEmphasize () {
        FileResource fr = new FileResource();
        String test = fr.asString();
        System.out.println("“dna ctgaaactga”, ‘a’: " + emphasize("dna ctgaaactga", 'a'));
        System.out.println("“Mary Bella Abracadabra”, ‘a’: " + emphasize("Mary Bella Abracadabra", 'a'));
    }
}
