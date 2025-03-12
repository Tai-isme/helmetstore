
package utils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static String getTimeNow(){
         LocalDateTime now = LocalDateTime.now();
        
        // Định dạng theo mẫu "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        
        return formattedNow;
    }
    
    public static String timeNowPlus_X_minutes(int minutes){
        LocalDateTime timeNow = LocalDateTime.now();
        
        LocalDateTime later = timeNow.plusMinutes(minutes);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = later.format(formatter);
        
        return formattedNow;
    }
    
    public static int compareTime(String timeNow, String compareWithTime){
        return timeNow.compareTo(compareWithTime);
    }
    
    public static void main(String[] args) {
        String timeNow = "2025-02-23 16:11:01";
        String timeCompare = "2025-02-23 16:11:02";
        
        System.out.println(timeNow);
        System.out.println(timeCompare);
        System.out.println(Time.compareTime(timeNow, timeCompare) > 0);
        

        System.out.println(Time.compareTime(timeNow, timeCompare));
        



    }
}
