package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class RoomTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testAddMeetingMarksRoomBusy() {
        Room room = new Room("LLT6A");
        try {
            Meeting meeting = new Meeting(5, 8, 9, 11);
            room.addMeeting(meeting);
            assertTrue("Room should be busy during meeting", room.isBusy(5, 8, 9, 11));
            assertFalse("Room should be free after meeting", room.isBusy(5, 8, 12, 13));
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingOverlapIncludesRoomId() {
        Room room = new Room("LAB2");
        try {
            Meeting first = new Meeting(5, 9, 9, 11);
            first.setDescription("Lecture");
            room.addMeeting(first);
            Meeting second = new Meeting(5, 9, 10, 12);
            second.setDescription("Lab");
            room.addMeeting(second);
            fail("Expected overlap exception");
        } catch (TimeConflictException e) {
            assertTrue("Expected room ID in message", e.getMessage().contains("LAB2"));
            assertTrue("Expected overlap message", e.getMessage().contains("Overlap with another item"));
        }
    }
}
