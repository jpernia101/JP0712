package com.postools.postools;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Helpers {

    public static Boolean isDateWeekend(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ;
    }

    //this function is used to return the date that a holidays are observed.
    //if holiday falls on sunday then observed on monday
    //if it falls on saturday the observed the friday (the day before)

    public  static ArrayList<LocalDate> getObservedDatesOfHoliday(int year){
        ArrayList<LocalDate> actualHolidayDates = holidayDates(year);
        ArrayList<LocalDate> observedDate = new ArrayList<>();

        for (LocalDate holiday : actualHolidayDates) {
            if(holiday.getDayOfWeek() == DayOfWeek.SATURDAY){
                observedDate.add(holiday.minusDays(1));
            }
            else if(holiday.getDayOfWeek() == DayOfWeek.SUNDAY){
                observedDate.add(holiday.plusDays(1));
            }
            else{
                observedDate.add(holiday);
            }
            
        }
        return observedDate;
    }


    /*
        PRIVATE METHODS
    */

    
    //we can add more holidays here if we want
    private static ArrayList<LocalDate> holidayDates(int year){
        ArrayList<LocalDate> holidays = new ArrayList<>();
        
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        LocalDate laborDay = whenIsLaborDay(year);

        holidays.add(independenceDay);
        holidays.add(laborDay);
        return holidays;
    }

    //Date of Labor day changes depending on the year
    private static LocalDate whenIsLaborDay(int year){
        LocalDate laborDay = LocalDate.of(year, 9, 1);
        int numOfDaysToAdd = 0;
        
        switch (laborDay.getDayOfWeek()) {
            case MONDAY:
                numOfDaysToAdd = 0;
                break;
            case TUESDAY:
                numOfDaysToAdd = 6;
                break;
            case WEDNESDAY:
                numOfDaysToAdd = 5;
                break;
            case THURSDAY:
                numOfDaysToAdd = 4;
                break;
            case FRIDAY:
                numOfDaysToAdd = 3;
                break;
            case SATURDAY:
                numOfDaysToAdd = 2;
                break;
            case SUNDAY:
                numOfDaysToAdd = 1;
                break;
            default:
                break;
        }

        return laborDay.plusDays(numOfDaysToAdd);
    }
}
