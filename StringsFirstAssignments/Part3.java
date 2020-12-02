

public class Part3 {
    boolean twoOccurrences (String stringa, String stringb) {
        int firstOccurIndex = stringb.indexOf(stringa);
        if (firstOccurIndex != -1) {
            int secondOccurIndex = stringb.indexOf(stringa, firstOccurIndex + stringa.length());
            if (secondOccurIndex != -1) {
                return true;
            }
        }
        return false;
    }
    
    String lastPart (String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if (index == -1) {
            return stringb;
        }
        return stringb.substring(index + stringa.length(), stringb.length());
    }

    void testing () {
        String testStringA1 = "by";
        String testStringB1 = "A story by Abby Long";
        System.out.println("stringa : " + testStringA1 + "\nstringb : " + testStringB1);
        System.out.println("Result : " + twoOccurrences(testStringA1, testStringB1) + "\n");
        
        //“a”, “banana”
        String testStringA2 = "a";
        String testStringB2 = "banana";
        System.out.println("stringa : " + testStringA2 + "\nstringb : " + testStringB2);
        System.out.println("Result : " + twoOccurrences(testStringA2, testStringB2) + "\n");
        
        //“atg”, “ctgtatgta”
        String testStringA3 = "atg";
        String testStringB3 = "ctgtatgta";
        System.out.println("stringa : " + testStringA3 + "\nstringb : " + testStringB3);
        System.out.println("Result : " + twoOccurrences(testStringA3, testStringB3) + "\n");
        
        String testStringA4 = "an";
        String testStringB4 = "banana";
        System.out.println("The part of the string after " + testStringA4 + " in " + testStringB4 
                            + " is " + lastPart(testStringA4,testStringB4) + ".");
        
        String testStringA5 = "zoo";
        String testStringB5 = "forest";
        System.out.println("The part of the string after " + testStringA5 + " in " + testStringB5 
                            + " is " + lastPart(testStringA5,testStringB5) + ".");
    }
}
