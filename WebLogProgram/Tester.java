
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //la.printAll();
        //System.out.println("Count Unique IPs: " + la.countUniqueIPs());
        //la.printAllHigherThanNum(400);
        // System.out.println("Mar 24: " + la.uniqueIPVisitsOnDay("Mar 24").size());
        // ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Mar 24");
        // for (String ip : uniqueIPs) {
            // System.out.println(ip);
        // }
        // System.out.println("Count Unique IPs In Range: " + la.countUniqueIPsInRange(200,299));
        // System.out.println();
        // System.out.println(la.countVisitsPerIP());
        // System.out.println("Most Number Visits By IP: " + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        // System.out.println("IP addresses that all have the maximum number of visits: ");
        // System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
        // System.out.println(la.iPsForDays());
        // System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }
    
    public void testPracticeQuiz () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //System.out.println("Quiz 1: " + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        //System.out.println("Quiz 2: " + la.iPsMostVisits(la.countVisitsPerIP()));
        //System.out.println("Quiz 3: " + la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println("Quiz 4: " + la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Mar 17"));
    }
    
    public void grader () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //System.out.println("Count Unique IPs: " + la.countUniqueIPs());
        //System.out.println("Sep 24: " + la.uniqueIPVisitsOnDay("Sep 24").size());
        //System.out.println("Count Unique IPs In Range: " + la.countUniqueIPsInRange(400,499));
        //System.out.println("Most Number Visits By IP: " + la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        //System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
        //System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }
}
