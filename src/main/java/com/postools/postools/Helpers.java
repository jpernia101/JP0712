package com.postools.postools;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Helpers {

    public static Boolean isDateWeekend(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ;
    }

    public static Boolean isDateHoliday(LocalDate date){
        
        if(date.getMonth() == Month.JULY && date.getDayOfMonth() == 4){
            return true;
        }
        
        /*
         * labor day falls on the first monday of september. 
         * So check if is september 
         * then check if the day is a monday 
         * then check that is the first monday . hence the (<7) condition. you wont have 2 mondays withing a 7 day span lol
         */
        if(date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() < 7 ){
            return true;
        }

        return false;
    }
}
