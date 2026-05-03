package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class RoomTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    //Checking availability of a room

    //A room should not be busy when no meeting exists
    @Test
    public void roomShouldNotBeBusyWhenNoMeetingExists() throws TimeConflictException {
        Room room = new Room("CR1");
        boolean result = room.isBusy(5,4,9,10);
        assertFalse("The room should not be busy when no meeting exists.",result);
    }

    //A room should be busy when a meeting already exists
    @Test
    public void roomShouldBeBusyWhenMeetingAlreadyExists() throws TimeConflictException {
        Room room = new Room("CR1");
        Person person = new Person("Ainamani Jim");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meeting = new Meeting(5, 4, 9, 10, attendees, room, "Board Meeting");

        room.addMeeting(meeting);

        boolean result = room.isBusy(5, 4, 9, 10);
        assertTrue("The room should be busy when a meeting already exists.", result);
    }

    //Checking a room with an invalid day (should throw an exception)
    @Test
    public void checkingRoomWithInvalidDayShouldThrowException() {
        Room room = new Room("CR1");

        try {
            room.isBusy(2, 35, 9, 10);
            fail("Expected TimeConflictException because day 35 does not exist.");
        } catch (TimeConflictException e) {
            assertEquals("Day does not exist.", e.getMessage());
        }
    }

    //Checking a room with an invalid hour (should throw an exception)
    @Test
    public void checkingRoomWithInvalidHourShouldThrowException() {
        Room room = new Room("CR1");

        try {
            room.isBusy(5, 4, -1, 10);
            fail("Expected TimeConflictException because -1 is an illegal hour.");
        } catch (TimeConflictException e) {
            assertEquals("Illegal hour.", e.getMessage());
        }
    }

    //Checking room where start time is after end time (should throw an exception)
    @Test
    public void checkingRoomWhereStartTimeIsAfterEndTimeShouldThrowException() {
        Room room = new Room("CR1");

        try {
            room.isBusy(5, 4, 15, 10);

            fail("Expected TimeConflictException because start time is after end time.");
        } catch (TimeConflictException e) {
            assertEquals("Meeting starts before it ends.", e.getMessage());
        }
    }


}
