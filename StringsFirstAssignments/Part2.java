

public class Part2 {
    String findSimpleGene (String dna, String startCodon, String stopCodon){
        String result = "";
        if (dna == dna.toUpperCase()) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if (stopIndex == -1) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            result = dna.substring(startIndex, stopIndex+3);
        }
        return result;
    }
    
    void testSimpleGene () {
        String test1 = "TGCGCTTAA";
        System.out.println("DNA is : " + test1);
        System.out.println("Gene is : " + findSimpleGene(test1, "ATG", "TAA"));
        String test2 = "ATGTGCGCT";
        System.out.println("DNA is : " + test2);
        System.out.println("Gene is : " + findSimpleGene(test2, "ATG", "TAA"));
        String test3 = "ATGTGCGCTTAA";
        System.out.println("DNA is : " + test3);
        System.out.println("Gene is : " + findSimpleGene(test3, "ATG", "TAA"));
        String test4 = "ATGTCGCTTAA";
        System.out.println("DNA is : " + test4);
        System.out.println("Gene is : " + findSimpleGene(test4, "ATG", "TAA"));
        String test5 = "atgtgcgcttaa";
        System.out.println("DNA is : " + test5);
        System.out.println("Gene is : " + findSimpleGene(test5, "ATG", "TAA"));
    }
}
