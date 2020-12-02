

public class Part2 {
    int howMany (String a, String b) {
        int startIndex = 0;
        int count = 0;
        while (b.indexOf(a, startIndex) != -1) {
            count++;
            startIndex = b.indexOf(a, startIndex) + a.length();
        }
        return count;
    }
    
    void testHowMany () {
        System.out.println("Occurrence Times : " + howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println("Occurrence Times : " + howMany("AA", "ATAAAA"));
        System.out.println("Occurrence Times : " + howMany("AA", "ATAATAA"));
    }
}
