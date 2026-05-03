package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class PersonTest {
	// Checking availabilty of a person
    // should not be busy when no meeting exists for them
    @Test
    public void personShouldNotBeBusyWhenNoMeetingExists() throws TimeConflictException {
        Person person = new Person("Ainamani Jim");
        boolean result = person.isBusy(5, 4, 9, 10);

        assertFalse("The person should not be busy when no meeting exists.", result);
    }

    //should be busy when a meeting exists for them
    @Test
    public void personShouldBeBusyWhenMeetingAlreadyExists() throws TimeConflictException {
        Person person = new Person("Ainamani Jim");
        Room room = new Room("CR1");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meeting = new Meeting(5, 4, 9, 10, attendees, room, "Staff Meeting");

        person.addMeeting(meeting);
        boolean result = person.isBusy(5, 4, 9, 10);

        assertTrue("The person should be busy when a meeting already exists.", result);
    }

    // Checking a person with an invalid day (should throw and exception)
    @Test
    public void checkingPersonWithInvalidDayShouldThrowException() {
        Person person = new Person("Ainamani Jim");

        try {
            person.isBusy(2, 35, 9, 10);
            fail("Expected TimeConflictException because day 35 does not exist.");
        } catch (TimeConflictException e) {
            assertEquals("Day does not exist.", e.getMessage());
        }
    }

    //Checking a person with an invalid hour (should throw an exception)
    @Test
    public void checkingPersonWithInvalidHourShouldThrowException() {
        Person person = new Person("Ainamani Jim");

        try {
            person.isBusy(5, 4, -1, 10);

            fail("Expected TimeConflictException because -1 is an illegal hour.");
        } catch (TimeConflictException e) {
            assertEquals("Illegal hour.", e.getMessage());
        }
    }

    //Checking a person where start time is after end time (should throw an exception)
    @Test
    public void checkingPersonWhereStartTimeIsAfterEndTimeShouldThrowException() {
        Person person = new Person("Ainamani Jim");

        try {
            person.isBusy(5, 4, 15, 10);

            fail("Expected TimeConflictException because start time is after end time.");
        } catch (TimeConflictException e) {
            assertEquals("Meeting starts before it ends.", e.getMessage());
        }
    }
}
