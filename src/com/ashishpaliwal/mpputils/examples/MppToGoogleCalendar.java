package com.ashishpaliwal.mpputils.examples;

import com.ashishpaliwal.mpputils.CalendarUtils;
import com.ashishpaliwal.mpputils.MppUtil;
import com.google.gdata.data.calendar.CalendarEntry;

import java.io.BufferedReader;
import java.io.Console;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class MppToGoogleCalendar {

    public static void main(String[] args) throws Exception {

        CalendarUtils calendarUtils = new CalendarUtils();
        List<CalendarEntry> calendars = CalendarUtils.getAllCalendars(args[1], args[2]);

        int count = 1;
        System.out.println("Please select one of the Calendar");
        // display all Calendars
        for (Iterator<CalendarEntry> iterator = calendars.iterator(); iterator.hasNext(); ) {
            CalendarEntry next =  iterator.next();
            System.out.println(count++ + ". " + next.getTitle().getPlainText());
        }

        System.out.print("Enter your Choice: ");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        System.out.println("You selected : "+choice);

        if(choice > calendars.size() || choice < 1) {
            System.err.println("Invalid Choice entered : "+ choice);
            return;
        }

        MppUtil utils = new MppUtil();

        // Create URL
        String calendarUrlString = calendars.get(choice - 1).getLinks().get(0).getHref();
        System.out.println(calendarUrlString);

        utils.updateCalenderWithMppTask(args[0], args[1], args[2], calendarUrlString);
    }

}
