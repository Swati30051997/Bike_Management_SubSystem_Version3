package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikeTest {
    
    @Test
    void testBikeCreation() {
        Bike bike = new Bike("Road Bike", "RB123", "New");
        assertEquals("Road Bike", bike.getModel());
        assertEquals("RB123", bike.getSerialNumber());
        assertEquals("New", bike.getInitialCondition());
        assertEquals("Available", bike.getStatus());
    }

    @Test
    void testUpdateLocation() {
        Bike bike = new Bike("Road Bike", "RB123", "New");
        bike.setLocation("Location A");
        assertEquals("Location A", bike.getLocation());
    }

    @Test
    void testUpdateMaintenanceRecord() {
        Bike bike = new Bike("Road Bike", "RB123", "New");
        bike.setMaintenanceRecord("Maintenance done on 2024-05-17");
        assertEquals("Maintenance done on 2024-05-17", bike.getMaintenanceRecord());
    }

    @Test
    void testUpdateStatus() {
        Bike bike = new Bike("Road Bike", "RB123", "New");
        bike.setStatus("Rented");
        assertEquals("Rented", bike.getStatus());
    }
    
    @Test
    void testUpdateMultipleFields() {
        Bike bike = new Bike("Road Bike", "RB123", "New");
        bike.setLocation("Location A");
        bike.setMaintenanceRecord("Maintenance done on 2024-05-17");
        bike.setStatus("Rented");
        
        assertEquals("Location A", bike.getLocation());
        assertEquals("Maintenance done on 2024-05-17", bike.getMaintenanceRecord());
        assertEquals("Rented", bike.getStatus());
    }
}

