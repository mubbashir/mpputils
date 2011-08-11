/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.ashishpaliwal.mpputils.examples;

import com.ashishpaliwal.mpputils.CalendarUtils;
import com.google.gdata.data.calendar.CalendarEntry;

import java.util.List;

/**
 * Example that shows usage of CalendarUtils class
 */
public class ListAllCalendars {

    static final String USAGE = "java com.ashishpaliwal.mpputils.examples.ListAllCalendars <googleusername> <password>";

    /**
     * Prints all the calendar's for the specified account
     *
     * @param userName  User Name
     * @param password  Password
     */
    public void printAllCalendars(String userName, String password) {
        try {
            List<CalendarEntry> calendars = CalendarUtils.getAllCalendars(userName, password);
            System.out.println("Calendar's : ");
            for(CalendarEntry calendarEntry : calendars) {
                System.out.println(calendarEntry.getTitle().getPlainText());
                System.out.println(calendarEntry.getSelfLink().getHref());
                System.out.println(calendarEntry.getEditLink().getHref());
                System.out.println("-----------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ListAllCalendars calendarLister = new ListAllCalendars();
        if(args.length != 2) {
            System.err.println("Invalid Usage");
            System.out.println(USAGE);
            System.exit(-1);
        }
        calendarLister.printAllCalendars(args[0], args[1]);
    }
}