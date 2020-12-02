import edu.duke.*;

public class Part1 {
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
    
    StorageResource getAllGenes (String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            sr.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return sr;
    }
    
    float cgRatio (String dna) {
        int countCG = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                countCG++;
            }
        }
        return (float)countCG/dna.length();
    }
    
    int countCTG (String dna) {
        int count = 0;
        int startIndex = 0;
        while (dna.indexOf("CTG", startIndex) != -1) {
            count++;
            startIndex = dna.indexOf("CTG", startIndex) + 1;
        }
        return count;
    }
    
    void processGenes (StorageResource sr) {
        int count60 = 0;
        int countCGR = 0;
        int maxLength = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.out.println("Gene with longer than 60 chars : " + gene);
                count60++;
            }
            if (cgRatio(gene) > 0.35) {
                System.out.println("C-G-ratio is higher than 0.35 : " + gene);
                countCGR++;
            }
            if (gene.length() > maxLength) {
                maxLength = gene.length();
            }
        }
        System.out.println("The number of genes with longer than 60 chars : " + count60);
        System.out.println("The number of genes with C-G-ratio higher than 0.35 : " + countCGR);
        System.out.println("The length of the longest gene : " + maxLength);
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
        
        // FileResource fr = new FileResource("brca1line.fa");
        // String dna = fr.asString().toUpperCase();
        // int indexTAA = findStopCodon(dna,0,"TAA");
        // System.out.println("IndexTAA" + indexTAA);
        // int indexTAG = findStopCodon(dna,0,"TAG");
        // System.out.println("IndexTAG" + indexTAG);
        // int indexTGA = findStopCodon(dna,0,"TGA");
        // System.out.println("IndexTGA" + indexTGA);
        // System.out.println("dna length" + dna.length());
    }
    
    void testFindGene () {
        String test1 = "ATCATCATC";
        System.out.println(test1);
        System.out.println("The Gene is : " + findGene(test1, 0));
        
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
    
    void testGetAllGenes () {
        String test1 = "ATCATCATCATGATCATCTAAATGATCATCTAGATGATCATCTGAATGATCATC";
        System.out.println(test1);
        StorageResource genes = getAllGenes(test1);
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
    }
    
    void testCgRatio () {
        String test1 = "ATGCCATAG";
        System.out.println(test1);
        System.out.println("The cg ratio is : " + cgRatio(test1));
    }
    
    void testCountCTG () {
        String test1 = "ATCATCATCATGATCATCTAAATGATCTGCATCTAGATGATCATCTGAATGATCATC";
        System.out.println(test1);
        System.out.println("The occurrence times of CTG : " + countCTG(test1));
    }
    
    void testProcessGenes () {
        System.out.println("");
        //String test1 = "ATGGATGATGATTAA";
        //processGenes(getAllGenes(test1));
        //String test2 = "ATGGATTAA";
        //processGenes(getAllGenes(test2));
        //String test3 = "ATGCCATAG";
        //processGenes(getAllGenes(test3));
        //String test4 = "ATGAAATAG";
        //processGenes(getAllGenes(test4));
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("How many genes are there in the file GRch38dnapart.fa? " + getAllGenes(dna).size());
        processGenes(getAllGenes(dna));
        System.out.println("The occurrence times of CTG : " + countCTG(dna));
    }
}
