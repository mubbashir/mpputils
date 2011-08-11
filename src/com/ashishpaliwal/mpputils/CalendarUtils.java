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

package com.ashishpaliwal.mpputils;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;

import java.net.URL;
import java.util.List;

/**
 * Calendar utilities class
 */
public class CalendarUtils {

    /**
     * Retrieves all the calendar for the specified users
     *
     * @param userName  User name of the account
     * @param password  password for the account
     * @return  All {@link List<CalendarEntry>} for the specified account
     * @throws Exception    If an error occurs while retrieving the account
     */
    public static List<CalendarEntry> getAllCalendars(String userName, String password) throws Exception {
        CalendarService myService = new CalendarService("CalendarService-"+userName);
        myService.setUserCredentials(userName, password);

        // Send the request and print the response
        URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/allcalendars/full");
//        URL feedUrl = new URL("https://www.google.com/calendar/feeds/private/full");
        CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);

        return resultFeed.getEntries();
    }
}