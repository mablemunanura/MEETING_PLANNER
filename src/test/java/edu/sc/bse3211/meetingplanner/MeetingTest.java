package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MeetingTest {
    // Add test methods here.
    // You are not required to write tests for all classes.

    @Test
    public void testAllDayConstructorUsesFullDay() {
        Meeting meeting = new Meeting(4, 15);
        assertEquals("Expected start time 0", 0, meeting.getStartTime());
        assertEquals("Expected end time 23", 23, meeting.getEndTime());
    }
}
