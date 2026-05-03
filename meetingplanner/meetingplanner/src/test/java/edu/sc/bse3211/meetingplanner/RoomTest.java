package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class RoomTest {
    /*
    Checking availability of a room
    */

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

    /*
    Tests for printing a rooms agenda
    */

    //Room with no meetings (should print an empty agenda)
    @Test
    public void printAgendaForRoomWithNoMeetings() {
        Room room = new Room("CR1");

        String agenda = room.printAgenda(5, 4);

        assertEquals("Agenda for 5/4:\n", agenda);
    }

    //Room with one meeting (should print the details of that meeting)
    @Test
    public void printAgendaForRoomWithOneMeeting() throws TimeConflictException {
        Room room = new Room("CR1");
        Person person = new Person("Ainamani Jim");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meeting = new Meeting(5, 4, 9, 10, attendees, room, "Software Testing Lecture");
        room.addMeeting(meeting);

        String agenda = room.printAgenda(5, 4);

        assertTrue(agenda.contains("Software Testing Lecture")
                && agenda.contains("CR1")
                && agenda.contains("9 - 10")
                && agenda.contains("Ainamani Jim"));
    }

    //Room with more than one meeting (should print the details of all meetings)
    @Test
    public void printAgendaForRoomWithMoreThanOneMeeting() throws TimeConflictException {
        Room room = new Room("CR1");
        Person person = new Person("Ainamani Jim");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meetingOne = new Meeting(5, 4, 9, 10, attendees, room, "Morning Meeting");

        Meeting meetingTwo = new Meeting(5, 4, 14, 15, attendees, room, "Afternoon Meeting");

        room.addMeeting(meetingOne);
        room.addMeeting(meetingTwo);

        String agenda = room.printAgenda(5, 4);
        assertTrue(agenda.contains("Morning Meeting") && agenda.contains("Afternoon Meeting"));
    }

    //Room agenda for a full month (should print the details of all meetings for that month)
    @Test
    public void printAgendaForFullMonthForRoom() throws TimeConflictException {
        Room room = new Room("CR1");
        Person person = new Person("Ainamani Jim");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meetingOne = new Meeting(5, 4, 9, 10, attendees, room, "First Room Meeting");

        Meeting meetingTwo = new Meeting(5, 10, 11, 12, attendees, room, "Second Room Meeting");

        room.addMeeting(meetingOne);
        room.addMeeting(meetingTwo);

        String agenda = room.printAgenda(5);

        assertTrue(agenda.contains("First Room Meeting") && agenda.contains("Second Room Meeting"));
    }

    //Order of printed meetings
    @Test
    public void checkOrderOfPrintedMeetingsForRoom() throws TimeConflictException {
        Room room = new Room("CR1");
        Person person = new Person("Ainamani Jim");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting morningMeeting = new Meeting(5, 4, 8, 9, attendees, room, "Morning Meeting");

        Meeting afternoonMeeting = new Meeting(5, 4, 14, 15, attendees, room, "Afternoon Meeting");

        room.addMeeting(morningMeeting);
        room.addMeeting(afternoonMeeting);

        String agenda = room.printAgenda(5, 4);

        assertTrue(agenda.indexOf("Morning Meeting") < agenda.indexOf("Afternoon Meeting"));
    }

}
