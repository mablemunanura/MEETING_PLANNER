package edu.sc.bse3211.meetingplanner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MeetingTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    @Test
    public void testMeetingConstructor_Basic() {
        // Testing the constructor used for vacations or holidays.
        Meeting meeting = new Meeting(5, 20, "Public Holiday");
        assertEquals(5, meeting.getMonth());
        assertEquals(20, meeting.getDay());
        assertEquals(0, meeting.getStartTime()); // Default start for this constructor
        assertEquals(23, meeting.getEndTime());   // Default end for this constructor
        assertEquals("Public Holiday", meeting.getDescription());
    }

    @Test
    public void testAttendeeManagement() {
        // Testing the full constructor.
        ArrayList<Person> attendees = new ArrayList<>();
        Person p1 = new Person("Abisha");
        attendees.add(p1);
        
        Room room = new Room("LAB2");
        Meeting meeting = new Meeting(10, 5, 9, 11, attendees, room, "Project Sync");
        
        // Verify initial attendee.
        assertEquals(1, meeting.getAttendees().size());
        
        // Test adding a second attendee[cite: 2].
        Person p2 = new Person("Martha");
        meeting.addAttendee(p2);
        assertEquals(2, meeting.getAttendees().size());
        
        // Test removing an attendee[cite: 2].
        meeting.removeAttendee(p1);
        assertEquals(1, meeting.getAttendees().size());
        assertEquals("Martha", meeting.getAttendees().get(0).getName());
    }

    @Test
    public void testToStringFormat() {
        // Ensuring the output string matches the expected format for the agenda.
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Collins"));
        Room room = new Room("LLT6A");
        
        Meeting meeting = new Meeting(12, 25, 10, 12, attendees, room, "Christmas Lunch");
        String result = meeting.toString();
        
        // The toString format: month/day, start - end, roomID: description[cite: 2].
        assertTrue(result.contains("12/25"));
        assertTrue(result.contains("LLT6A"));
        assertTrue(result.contains("Christmas Lunch"));
    }
}