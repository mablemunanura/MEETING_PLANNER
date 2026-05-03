package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RoomTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    @Test
    public void testRoomInitialization() {
        Room r = new Room("Lab 4");
        assertEquals("Room ID should be set correctly", "Lab 4", r.getID());
    }
}
