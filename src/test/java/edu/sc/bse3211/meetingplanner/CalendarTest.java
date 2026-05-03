package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CalendarTest {
	// Add test methods here.
	// You are not required to write tests for all classes.

	@Test
	public void testAddMeeting_holiday() {
		// Create Janan Luwum holiday
		Calendar calendar = new Calendar();
		// Add to calendar object.
		try {
			Meeting janan = new Meeting(2, 16, "Janan Luwum");
			calendar.addMeeting(janan);
			// Verify that it was added.
			Boolean added = calendar.isBusy(2, 16, 0, 23);
			assertTrue("Janan Luwum Day should be marked as busy on the calendar", added);
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_validTimeRangeMarksBusy() {
		Calendar calendar = new Calendar();
		try {
			Meeting meeting = new Meeting(3, 10, 9, 11);
			calendar.addMeeting(meeting);
			assertTrue("Meeting window should be busy", calendar.isBusy(3, 10, 9, 11));
			assertFalse("Later time should be free", calendar.isBusy(3, 10, 12, 13));
		} catch (TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_overlapThrows() {
		Calendar calendar = new Calendar();
		try {
			Meeting first = new Meeting(3, 12, 9, 11);
			first.setDescription("Planning");
			calendar.addMeeting(first);
			Meeting second = new Meeting(3, 12, 10, 12);
			second.setDescription("Review");
			calendar.addMeeting(second);
			fail("Expected overlap exception");
		} catch (TimeConflictException e) {
			assertTrue("Expected overlap message", e.getMessage().contains("Overlap with another item"));
		}
	}

	@Test
	public void testInvalidDayThrows() {
		Calendar calendar = new Calendar();
		try {
			calendar.addMeeting(new Meeting(2, 35, 9, 10));
			fail("Expected invalid day exception");
		} catch (TimeConflictException e) {
			assertTrue("Expected day error", e.getMessage().contains("Day does not exist"));
		}
	}

	@Test
	public void testInvalidHourThrows() {
		Calendar calendar = new Calendar();
		try {
			calendar.addMeeting(new Meeting(4, 10, -1, 10));
			fail("Expected invalid hour exception");
		} catch (TimeConflictException e) {
			assertTrue("Expected hour error", e.getMessage().contains("Illegal hour"));
		}
	}

	@Test
	public void testStartAfterEndThrows() {
		Calendar calendar = new Calendar();
		try {
			calendar.addMeeting(new Meeting(4, 10, 12, 11));
			fail("Expected start/end exception");
		} catch (TimeConflictException e) {
			assertTrue("Expected start/end error", e.getMessage().contains("Meeting starts before it ends"));
		}
	}
}
