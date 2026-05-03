package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PersonTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    @Test
    public void testPersonInitialization() {
        Person p = new Person("Abisha");
        assertEquals("Name should be set correctly", "Abisha", p.getName());
        // Checking if the person's internal calendar was initialized.
        assertNotNull("Person should have an internal calendar", p.printAgenda(1)); 
    }
}
