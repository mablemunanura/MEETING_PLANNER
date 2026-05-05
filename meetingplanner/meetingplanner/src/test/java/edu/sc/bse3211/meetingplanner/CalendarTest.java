package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.util.ArrayList;

public class CalendarTest {
	// Add test methods here.
	// You are not required to write tests for all classes.

	@Test
	public void testAddMeeting_holiday() {
		Calendar calendar = new Calendar();
		try {
			Meeting janan = new Meeting(2, 16, "Janan Luwum");
			calendar.addMeeting(janan);
			Boolean added = calendar.isBusy(2, 16, 0, 23);
			assertTrue("Janan Luwum Day should be marked as busy on the calendar", added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_invalidDateThrowsException() {
		Calendar calendar = new Calendar();
		try {
			calendar.addMeeting(new Meeting(2, 35, 10, 11));
			fail("Expected TimeConflictException for invalid date");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Day does not exist"));
		}
	}

	@Test
	public void testAddMeeting_endBeforeStartThrowsException() {
		Calendar calendar = new Calendar();
		try {
			calendar.addMeeting(new Meeting(3, 10, 14, 13));
			fail("Expected TimeConflictException for end time before start time");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Meeting starts before it ends"));
		}
	}

	@Test
	public void testRoomDoubleBookingThrowsConflict() {
		Room room = new Room("LLT1");
		ArrayList<Person> attendees = new ArrayList<Person>();
		attendees.add(new Person("Alice"));
		try {
			Meeting first = new Meeting(4, 12, 9, 11, attendees, room, "Planning session");
			Meeting second = new Meeting(4, 12, 10, 12, attendees, room, "Follow-up session");
			room.addMeeting(first);
			room.addMeeting(second);
			fail("Expected TimeConflictException for room double booking");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Conflict for room"));
		}
	}

	@Test
	public void testPersonDoubleBookingThrowsConflict() {
		Person person = new Person("Bob");
		Room room = new Room("LLT2");
		ArrayList<Person> attendees = new ArrayList<Person>();
		attendees.add(person);
		try {
			Meeting first = new Meeting(5, 20, 13, 15, attendees, room, "Team review");
			Meeting second = new Meeting(5, 20, 14, 16, attendees, room, "Project update");
			person.addMeeting(first);
			person.addMeeting(second);
			fail("Expected TimeConflictException for person double booking");
		} catch (TimeConflictException e) {
			assertTrue(e.getMessage().contains("Conflict for attendee"));
		}
	}
}
