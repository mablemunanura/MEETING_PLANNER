package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class PersonTest {
    /*
	    Checking availabilty of a person
	*/

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

        /*
	    Printing a person's agenda
	*/

    //A person with no meetings (should print an empty agenda)
    @Test
    public void printAgendaForPersonWithNoMeetings() {
        Person person = new Person("Ainamani Jim");

        String agenda = person.printAgenda(5, 4);

        assertEquals("Agenda for 5/4:\n", agenda);
    }

    //A person with one meeting (should print the details of that meeting)
    @Test
    public void printAgendaForPersonWithOneMeeting() throws TimeConflictException {
        Person person = new Person("Ainamani Jim");
        Room room = new Room("CR1");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meeting = new Meeting(5,4,9,10, attendees, room,"Software Testing Lecture");

        person.addMeeting(meeting);

        String agenda = person.printAgenda(5, 4);

        assertTrue(agenda.contains("Software Testing Lecture") && agenda.contains("CR1") && agenda.contains("9 - 10") && agenda.contains("Ainamani Jim"));
    }

    // Aperson with more than one meeting (should print the details of all meetings)
    @Test
    public void printAgendaForPersonWithMoreThanOneMeeting() throws TimeConflictException {
        Person person = new Person("Ainamani Jim");
        Room room = new Room("CR1");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meetingOne = new Meeting(5,4,9,10, attendees, room,"Morning Meeting");

        Meeting meetingTwo = new Meeting(5,4,14,15, attendees, room,"Afternoon Meeting" );

        person.addMeeting(meetingOne);
        person.addMeeting(meetingTwo);

        String agenda = person.printAgenda(5, 4);

        assertTrue(agenda.contains("Morning Meeting") && agenda.contains("Afternoon Meeting"));
    }

    // A person with meetings on multiple days (should print the details of all meetings for the month)
    @Test
    public void printAgendaForFullMonthForPerson() throws TimeConflictException {
        Person person = new Person("Ainamani Jim");
        Room room = new Room("CR1");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting meetingOne = new Meeting(5,4,9,10, attendees, room,"First Person Meeting" );

        Meeting meetingTwo = new Meeting(5,10,11,12, attendees, room,"Second Person Meeting");

        person.addMeeting(meetingOne);
        person.addMeeting(meetingTwo);

        String agenda = person.printAgenda(5);

        assertTrue(agenda.contains("First Person Meeting")
                && agenda.contains("Second Person Meeting"));
    }

    //Order of a person's meetings
    @Test
    public void checkOrderOfPrintedMeetingsForPerson() throws TimeConflictException {
        Person person = new Person("Ainamani Jim");
        Room room = new Room("CR1");

        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(person);

        Meeting morningMeeting = new Meeting(5,4,8,9,attendees,room,"Morning Meeting" );

        Meeting afternoonMeeting = new Meeting(5,4,14,15, attendees, room,"Afternoon Meeting");

        person.addMeeting(morningMeeting);
        person.addMeeting(afternoonMeeting);

        String agenda = person.printAgenda(5, 4);

        assertTrue(agenda.indexOf("Morning Meeting") < agenda.indexOf("Afternoon Meeting"));
    }

}
