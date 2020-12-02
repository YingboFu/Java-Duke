

public class Part1 {
    String findSimpleGene (String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            result = dna.substring(startIndex, stopIndex+3);
        }
        return result;
    }
    
    void testSimpleGene () {
        String test1 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA is : " + test1);
        System.out.println("Gene is : " + findSimpleGene(test1));
        String test2 = "ATGTGCGCT";
        System.out.println("DNA is : " + test2);
        System.out.println("Gene is : " + findSimpleGene(test2));
        String test3 = "ATGTGCGCTTAA";
        System.out.println("DNA is : " + test3);
        System.out.println("Gene is : " + findSimpleGene(test3));
        String test4 = "ATGTCGCTTAA";
        System.out.println("DNA is : " + test4);
        System.out.println("Gene is : " + findSimpleGene(test4));
    }
}
