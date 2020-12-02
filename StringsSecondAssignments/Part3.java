

public class Part3 {
    int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    String findGene (String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon (dna, startIndex, "TAA");
        int tagIndex = findStopCodon (dna, startIndex, "TAG");
        int tgaIndex = findStopCodon (dna, startIndex, "TGA");
        int minStopIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (minStopIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minStopIndex + 3);
    }
    
    void printAllGenes (String dna) {
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    int countGenes (String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            count++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }
    
    void testFindStopCodon () {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int index = findStopCodon(dna,0,"TAA");
        if (index != 9) System.out.println("error on 9");
        index = findStopCodon(dna,9,"TAA");
        if (index != 21) System.out.println("error on 21");
        index = findStopCodon(dna,1,"TAA");
        if (index != 26) System.out.println("error on 26");
        index = findStopCodon(dna,0,"TAG");
        if (index != 26) System.out.println("error on 26 TAG");
        System.out.println("Tests finished!");
    }
    
    void testFindGene () {
        String test1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println(test1);
        System.out.println("The Answer is : " + findGene(test1, 0));
        
        String test2 = "ATGATCATCTAA";
        System.out.println(test2);
        System.out.println("The Gene is : " + findGene(test2, 0));
        
        String test3 = "ATGATCATCTAG";
        System.out.println(test3);
        System.out.println("The Gene is : " + findGene(test3, 0));
        
        String test4 = "ATGATCATCTGA";
        System.out.println(test4);
        System.out.println("The Gene is : " + findGene(test4, 0));
        
        String test5 = "ATGATCATCTGATAA";
        System.out.println(test5);
        System.out.println("The Gene is : " + findGene(test5, 0));
        
        String test6 = "ATGATCATC";
        System.out.println(test6);
        System.out.println("The Gene is : " + findGene(test6, 0));
    }
    
    void testPrintAllGenes () {
        String test1 = "ATCATCATCATGATCATCTAAATGATCATCTAGATGATCATCTGAATGATCATC";
        System.out.println(test1);
        printAllGenes(test1);
    }
    
    void testCountGenes () {
        String test1 = "ATCATCATCATGATCATCTAAATGATCATCTAGATGATCATCTGAATGATCATC";
        System.out.println(test1);
        System.out.println("Counts : " + countGenes(test1));
        
        String test2 = "ATGTAAGATGCCCTAGT";
        System.out.println(test2);
        System.out.println("Counts : " + countGenes(test2));
    }
}
