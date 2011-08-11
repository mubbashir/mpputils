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
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.reader.ProjectReader;
import net.sf.mpxj.reader.ProjectReaderUtility;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MppUtil {

    /**
     * Converts an MPP Task into Google Calendar Entry
     *
     * @param task  Task to be converted to event entry
     * @return  {@link CalendarEventEntry} representation of the {@link Task}
     */
    protected CalendarEventEntry convertTaskToCalenderEntry(Task task) {
        System.out.println("Task "+task);
        CalendarEventEntry eventEntry = new CalendarEventEntry();
        eventEntry.setTitle(new PlainTextConstruct(task.getName()));

        When date = new When();
        date.setStartTime(new DateTime(task.getStart()));
        eventEntry.addTime(date);
        return eventEntry;
    }

    /**
     * Add the entry to the Calendar. This method does not use batch operation
     *
     * @param tasks Tasks to be added
     * @param calendarService Calendar service to use
     * @param url   Calendar URL
     */
    public void updateCalenderWithEntry(List<Task> tasks, CalendarService calendarService, URL url) {
        for (Task task : tasks) {
            CalendarEventEntry entry = convertTaskToCalenderEntry(task);
            try {
                calendarService.insert(url, entry);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads the MPP file and update the Google calender with the specified
     * tasks
     *
     * @param mppFile   The MPP file to be uploaded
     */
    public void updateCalenderWithMppTask(String mppFile, String userName, String password, String calendarUrl) {
        if(mppFile == null) {
            System.err.println("mpp file is null.");
            return;
        }

        try {
            CalendarService cService = new CalendarService("Test Calendar");
            cService.setUserCredentials(userName, password);
            URL url = new URL(calendarUrl);
            System.out.println("URL is : "+url);
            List<Task> tasks = getAllTasks(mppFile);
            updateCalenderWithEntry(tasks, cService, url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves all the Tasks from an MPP file
     *
     * @param mppFile   File to be read
     * @return  All {@link Task} presents in the MPP file
     */
    private List<Task> getAllTasks(String mppFile) throws IllegalAccessException, InstantiationException, MPXJException {
        ProjectReader mppReader = ProjectReaderUtility.getProjectReader(mppFile);
        ProjectFile mppProjectFile = mppReader.read(mppFile);
        return mppProjectFile.getAllTasks();
    }
}