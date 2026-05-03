package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import org.junit.Test;

public class PersonTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testAddMeetingMarksPersonBusy() {
        Person person = new Person("Test Person");
        try {
            Meeting meeting = new Meeting(5, 8, 9, 11);
            person.addMeeting(meeting);
            assertTrue("Person should be busy during meeting", person.isBusy(5, 8, 9, 11));
            assertFalse("Person should be free after meeting", person.isBusy(5, 8, 12, 13));
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingOverlapIncludesPersonName() {
        Person person = new Person("Alex");
        try {
            Meeting first = new Meeting(5, 9, 9, 11);
            first.setDescription("Standup");
            person.addMeeting(first);
            Meeting second = new Meeting(5, 9, 10, 12);
            second.setDescription("Planning");
            person.addMeeting(second);
            fail("Expected overlap exception");
        } catch (TimeConflictException e) {
            assertTrue("Expected person name in message", e.getMessage().contains("Alex"));
            assertTrue("Expected overlap message", e.getMessage().contains("Overlap with another item"));
        }
    }

    @Test
    public void testMultiDayVacationSameMonthBlocksAllDays() {
        Person person = new Person("Vacationer");
        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);
        for (int day = 10; day <= 12; day++) {
            Meeting vacation = new Meeting(6, day, 0, 23, attendees, new Room("VAC"), "vacation");
            try {
                person.addMeeting(vacation);
            } catch (TimeConflictException e) {
                fail("Should not throw exception: " + e.getMessage());
            }
        }
        try {
            assertTrue("June 10 should be busy", person.isBusy(6, 10, 0, 23));
            assertTrue("June 11 should be busy", person.isBusy(6, 11, 0, 23));
            assertTrue("June 12 should be busy", person.isBusy(6, 12, 0, 23));
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testMultiDayVacationAcrossMonthsBlocksAllDays() {
        Person person = new Person("CrossMonth");
        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);
        int startMonth = 7;
        int endMonth = 8;
        int startDay = 30;
        int endDay = 2;
        for (int day = startDay; day <= 31; day++) {
            Meeting vacation = new Meeting(startMonth, day, 0, 23, attendees, new Room("VAC"), "vacation");
            try {
                person.addMeeting(vacation);
            } catch (TimeConflictException e) {
                fail("Should not throw exception: " + e.getMessage());
            }
        }
        for (int day = 1; day <= endDay; day++) {
            Meeting vacation = new Meeting(endMonth, day, 0, 23, attendees, new Room("VAC"), "vacation");
            try {
                person.addMeeting(vacation);
            } catch (TimeConflictException e) {
                fail("Should not throw exception: " + e.getMessage());
            }
        }
        try {
            assertTrue("July 30 should be busy", person.isBusy(7, 30, 0, 23));
            assertTrue("July 31 should be busy", person.isBusy(7, 31, 0, 23));
            assertTrue("Aug 1 should be busy", person.isBusy(8, 1, 0, 23));
            assertTrue("Aug 2 should be busy", person.isBusy(8, 2, 0, 23));
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}
