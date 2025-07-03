package com.tripgain.common;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class GenerateDates {

    public static String[] GenerateDatesToSelectFlights() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(5);
        LocalDate returnFutureDate = today.plusDays(10); // Add 5 days
        LocalDate inPolicyFlights = today.plusDays(20); // Add 5 days
        LocalDate outOfPolicyFlights = today.plusDays(2); // Add 5 days
        LocalDate returnInPolicyFlights = today.plusDays(25); // Add 5 days

        // Get the day of the week and format it
        String dayOfWeekShort = returnFutureDate.getDayOfWeek()
                .getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH);

        int day = futureDate.getDayOfMonth();// e.g., 13
        String fromDate = String.valueOf(day);
        int returnDay = returnFutureDate.getDayOfMonth();// e.g., 13
        String returnDate = String.valueOf(returnDay);
        int inPolicyDay = inPolicyFlights.getDayOfMonth();// e.g., 13
        String inPolicyDate = String.valueOf(inPolicyDay);
        int outOfPolicyDay = outOfPolicyFlights.getDayOfMonth();// e.g., 13
        String outOfPolicyDate = String.valueOf(outOfPolicyDay);
        int returnInPolicyDay = returnInPolicyFlights.getDayOfMonth();
        String returnInPolicyDate = String.valueOf(returnInPolicyDay);


        String month = futureDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // e.g., "May"
        int year = futureDate.getYear();
        String fromYear = String.valueOf(year);
        String returnMonth = returnFutureDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // e.g., "May"
        int returnYear = returnFutureDate.getYear();
        String returnYears = String.valueOf(returnYear);
        String inPolicyMonth = inPolicyFlights.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // e.g., "May"
        int inPolicyYear = inPolicyFlights.getYear();
        String inPolicyYears = String.valueOf(inPolicyYear);
        String outOfPolicyMonth = outOfPolicyFlights.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // e.g., "May"
        int outOfPolicyYear = outOfPolicyFlights.getYear();
        String outOfPolicyYears = String.valueOf(outOfPolicyYear);
        String returnInPolicyMonths = returnInPolicyFlights.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH); // e.g., "May"
        int returnInPolicyYear = returnInPolicyFlights.getYear();
        String returnInPolicyYears = String.valueOf(returnInPolicyYear);




        String fromMonthYear = month + " " + year;
        String returnMonthYear = returnMonth + " " + returnYear;
        String inPolicyMonthYear = inPolicyMonth + " " + inPolicyYears;
        String outOfPolicyMonthYear = outOfPolicyMonth + " " + outOfPolicyYears;
        String returnInPolicyMonthYear = returnInPolicyMonths + " " + returnInPolicyYears;


        return new String[] {fromDate,returnDate,fromMonthYear, returnMonthYear,month,fromYear,returnMonth,returnYears,dayOfWeekShort,inPolicyDate,inPolicyMonthYear,outOfPolicyDate,outOfPolicyMonthYear,returnInPolicyDate,returnInPolicyMonthYear};

    }
}


