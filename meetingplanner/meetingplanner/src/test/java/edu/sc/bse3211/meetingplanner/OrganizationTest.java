package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    @Test
    public void testDefaultRoomsExist() {
        Organization org = new Organization();
        // Verifying the organization initializes the correct number of rooms.
        assertEquals("Should have 5 default rooms", 5, org.getRooms().size());
        
        try {
            assertNotNull("Room LLT6A should be available", org.getRoom("LLT6A"));
        } catch (Exception e) {
            fail("Default room LLT6A not found: " + e.getMessage());
        }
    }

    @Test
    public void testDefaultEmployeesExist() {
        Organization org = new Organization();
        // Verifying the organization initializes the correct number of employees.
        assertEquals("Should have 5 default employees", 5, org.getEmployees().size());
    }
}