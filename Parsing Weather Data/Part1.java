import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestHourSoFar = null;
        for (CSVRecord currRecord : parser) {
            if (!currRecord.get("TemperatureF").equals("-9999")) {
                if (coldestHourSoFar == null) {
                    coldestHourSoFar = currRecord;
                } else {
                    //System.out.println("DEBUGGING INFO!!");
                    double coldestTemperature = Double.parseDouble(coldestHourSoFar.get("TemperatureF"));
                    double currTemperature = Double.parseDouble(currRecord.get("TemperatureF"));
                    //System.out.println("coldestTemperature: " + coldestTemperature + "currTemperature: " + currTemperature);
                    if (currTemperature < coldestTemperature) {
                        coldestHourSoFar = currRecord;
                        //System.out.println("DEBUGGING INFO!!");
                    }
                }
            }
        }
        return coldestHourSoFar;
    }
    
    String fileWithColdestTemperature () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestHourSoFar = null;
        String answer = "";
        for (File f : dr.selectedFiles()) {
            //System.out.println(f.getName());
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = coldestHourInFile(parser);
            if (coldestHourSoFar == null) {
                coldestHourSoFar = currRecord;
            } else {
                double coldestTemperature = Double.parseDouble(coldestHourSoFar.get("TemperatureF"));
                double currTemperature = Double.parseDouble(currRecord.get("TemperatureF"));
                if (currTemperature < coldestTemperature) {
                    coldestHourSoFar = currRecord;
                }
            }
        }
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = coldestHourInFile(parser);
            double coldestTemperature = Double.parseDouble(coldestHourSoFar.get("TemperatureF"));
            double currTemperature = Double.parseDouble(currRecord.get("TemperatureF"));
            if (currTemperature == coldestTemperature) {
                String coldestDayFile = f.getName();
                answer += "Coldest day was in file " + coldestDayFile + "\n";
                answer += "Coldest temperature on that day was " + coldestHourSoFar.get("TemperatureF") + "\n";
                answer += "All the Temperatures on the coldest day were:\n";
                FileResource fr2 = new FileResource(f);
                CSVParser parser2 = fr2.getCSVParser();
                for (CSVRecord record : parser2) {
                    answer += record.get("DateUTC") + ": " + record.get("TemperatureF") + "\n";
                }
                break;
            }
        }
        return answer;
    }
    
    CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestHumidityHourSoFar = null;
        for (CSVRecord currRecord : parser) {
            if (!currRecord.get("Humidity").equals("N/A")) {
                if (lowestHumidityHourSoFar == null) {
                    lowestHumidityHourSoFar = currRecord;
                } else {
                    int lowestHumidity = Integer.parseInt(lowestHumidityHourSoFar.get("Humidity"));
                    int currHumidity = Integer.parseInt(currRecord.get("Humidity"));
                    if (currHumidity < lowestHumidity) {
                        lowestHumidityHourSoFar = currRecord;
                    }
                }
            }
        }
        return lowestHumidityHourSoFar;
    }
    
    CSVRecord lowestHumidityInManyFiles () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityHourSoFar = null;
        for (File f : dr.selectedFiles()) {
            //System.out.println(f.getName());
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = lowestHumidityInFile(parser);
            if (lowestHumidityHourSoFar == null) {
                lowestHumidityHourSoFar = currRecord;
            } else {
                int lowestHumidity = Integer.parseInt(lowestHumidityHourSoFar.get("Humidity"));
                int currHumidity = Integer.parseInt(currRecord.get("Humidity"));
                if (currHumidity < lowestHumidity) {
                    lowestHumidityHourSoFar = currRecord;
                }
            }
        }
        return lowestHumidityHourSoFar;
    }
    
    double averageTemperatureInFile (CSVParser parser) {
        double sumTemperature = 0.0;
        int count = 0;
        for (CSVRecord currRecord : parser) {
            if (!currRecord.get("TemperatureF").equals("-9999")) {
                double currTemperature = Double.parseDouble(currRecord.get("TemperatureF"));
                sumTemperature += currTemperature;
                count++;
            }
        }
        return sumTemperature/count;
    }
    
    double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double sumTemperature = 0.0;
        int count = 0;
        for (CSVRecord currRecord : parser) {
            if (!currRecord.get("TemperatureF").equals("-9999") && !currRecord.get("Humidity").equals("N/A")) {
                double currTemperature = Double.parseDouble(currRecord.get("TemperatureF"));
                int currHumidity = Integer.parseInt(currRecord.get("Humidity"));
                if (currHumidity >= value) {
                    sumTemperature += currTemperature;
                    count++;
                }
            }
        }
        if (count == 0) {
            return -9999.0;
        }
        return sumTemperature/count;
    }
    
    void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("The coldest temperature in file is " + coldestRecord.get("TemperatureF") + " at "
                            + coldestRecord.get("DateUTC"));
    }
    
    void testFileWithColdestTemperature () {
        System.out.println(fileWithColdestTemperature ());
    }
    
    void testLowestHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    void testLowestHumidityInManyFiles () {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemperature);
    }
    
    void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
        CSVParser parser = fr.getCSVParser();
        double averageTemperatureWithHighHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (averageTemperatureWithHighHumidity != -9999.0) {
            System.out.println("Average Temp when high Humidity is " + averageTemperatureWithHighHumidity);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
