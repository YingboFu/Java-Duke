import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.HashMap;


public class BabyNames {
    public void printNames () {
        System.out.println();
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                                   " Gender " + rec.get(1) + 
                                   " Num Born " + rec.get(2));
            }
        }
    }
    
    private void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int boysNames = 0;
        int girlsNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalBirths += Integer.parseInt(rec.get(2));
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += Integer.parseInt(rec.get(2));
                boysNames += 1;
            } else {
                totalGirls += Integer.parseInt(rec.get(2));
                girlsNames += 1;
            }
        }
        
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
        System.out.println("the number of girls names = " + girlsNames);
        System.out.println("the number of boys names = " + boysNames);
        System.out.println("the total names = " + totalNames);
    }
    
    private int getRank (int year, String name, String gender) {
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        
        return -1;
    }
    
    private String getName (int year, int rank, String gender) {
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int currRank = 0;
        
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                currRank += 1;
                if (currRank == rank) {
                    return rec.get(0);
                }
            }
        }
        
        return "NO NAME";
    }
    
    private void whatIsNameInYear (String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + 
                            newName + " if she was born in " + newYear + ".");
    }
    
    private int yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        HashMap<Integer, Integer> yearToRank = new HashMap<Integer, Integer>();
        Integer highestRankYear = null;
        
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                yearToRank.put(year, rank);
            }
        }
        
        if (yearToRank.size() == 0) {
            return -1;
        }
        
        for (Integer year : yearToRank.keySet()) {
            if (highestRankYear == null) {
                highestRankYear = year;
            } else {
                if (yearToRank.get(year) < yearToRank.get(highestRankYear)) {
                    highestRankYear = year;
                }
            }
        }
        
        return highestRankYear;
    }
    
    private double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double sum = 0.0;
        double count = 0.0;
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rank = getRank(year, name, gender);
            if (rank == -1) {
                return -1.0;
            }
            sum += rank;
            count += 1;
        }
        
        return sum/count;
    }
    
    private int getTotalBirthsRankedHigher (int year, String name, String gender) {
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int total = 0;
        
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    break;
                }
                total += Integer.parseInt(rec.get(2));
            }
        }
        
        return total;
    }
    
    public void testTotalBirths () {
        //FileResource fr = new FileResource("testing/yob2014short.csv");
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public void testGetRank () {
        System.out.println("The rank of given name is: " + getRank(1971, "Frank", "M"));
    }
    
    public void testGetName () {
        System.out.println("The name at given rank is: " + getName(1982, 450, "M"));
    }
    
    public void testWhatIsNameInYear () {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testYearOfHighestRank () {
        System.out.println("Year of Highest Rank of Given Name and Gender: " + yearOfHighestRank("Mich", "M"));
    }
    
    public void testGetAverageRank () {
        System.out.println("The Average Rank is: " + getAverageRank("Robert", "M"));
        //System.out.println("The Average Rank is: " + getAverageRank("Jacob", "M"));
    }
    
    public void testGetTotalBirthsRankedHigher () {
        System.out.println("The Total Births Ranked Higher Than Given Name: " + 
                                getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
