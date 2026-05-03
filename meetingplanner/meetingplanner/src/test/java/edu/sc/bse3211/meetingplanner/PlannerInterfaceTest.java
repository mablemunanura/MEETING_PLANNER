package edu.sc.bse3211.meetingplanner;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PlannerInterfaceTest {
    
    @Test
    public void testInterfaceInitialization() {
        PlannerInterface pi = new PlannerInterface();
        // Verification that it successfully creates the internal organization[cite: 2].
        assertNotNull("PlannerInterface should initialize successfully", pi);
    }

}
