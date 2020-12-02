
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs () {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             if (!uniqueIPs.contains(le.getIpAddress())) {
                 uniqueIPs.add(le.getIpAddress());
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum (int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
         ArrayList<String> uniqueIPVisits = new ArrayList<String>();
         for (LogEntry le : records) {
             //System.out.println(le.getAccessTime().toString().substring(4,10));
             if (le.getAccessTime().toString().substring(4,10).equals(someday) && !uniqueIPVisits.contains(le.getIpAddress())) {
                 uniqueIPVisits.add(le.getIpAddress());
             }
         }
         return uniqueIPVisits;
     }
     
     public int countUniqueIPsInRange (int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             if (le.getStatusCode() >= low && le.getStatusCode() <= high && !uniqueIPs.contains(le.getIpAddress())) {
                 uniqueIPs.add(le.getIpAddress());
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP () {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (!counts.containsKey(ipAddress)) {
                 counts.put(ipAddress, 1);
             } else {
                 counts.put(ipAddress, counts.get(ipAddress) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP (HashMap<String, Integer> counts) {
         int max = 0;
         for (String ip : counts.keySet()) {
             if (counts.get(ip) > max) {
                 max = counts.get(ip);
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
         ArrayList<String> iPsMostVisitsList = new ArrayList<String>();
         int mostVisits = mostNumberVisitsByIP(counts);
         for (String ip : counts.keySet()) {
             if (counts.get(ip) == mostVisits) {
                 iPsMostVisitsList.add(ip);
             }
         }
         return iPsMostVisitsList;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays () {
         HashMap<String, ArrayList<String>> iPsForDaysMap = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             String date = le.getAccessTime().toString().substring(4,10);
             String ip = le.getIpAddress();
             if (!iPsForDaysMap.containsKey(date)) {
                 ArrayList<String> list = new ArrayList<String>();
                 list.add(ip);
                 iPsForDaysMap.put(date, list);
             } else {
                 ArrayList<String> list = iPsForDaysMap.get(date);
                 list.add(ip);
                 iPsForDaysMap.put(date, list);
             }
         }
         return iPsForDaysMap;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPsForDaysMap) {
         String mostVisitedDay = "";
         for (String Date : iPsForDaysMap.keySet()) {
             if (mostVisitedDay == "") {
                 mostVisitedDay = Date;
             } else {
                 if (iPsForDaysMap.get(Date).size() > iPsForDaysMap.get(mostVisitedDay).size()) {
                     mostVisitedDay = Date;
                 }
             }
         }
         return mostVisitedDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> iPsForDaysMap, String day) {
         ArrayList<String> iPsOnDay = iPsForDaysMap.get(day);
         
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (String ip : iPsOnDay) {
             if (!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         
         return iPsMostVisits(counts);
     }
}
