package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Date;

class BikeRentalSubSystemTest {
    
    private BikeRentalSubSystem bikeRentalSystem;
    
    @BeforeEach
    void setUp() {
        bikeRentalSystem = BikeRentalSubSystem.getInstance();
        bikeRentalSystem.getBikeInventory().clear();
    }
    
    @Test
    void testSingletonInstance() {
        BikeRentalSubSystem anotherInstance = BikeRentalSubSystem.getInstance();
        assertSame(bikeRentalSystem, anotherInstance);
    }

    @Test
    void testAddNewBikeToInventory() {
        bikeRentalSystem.addNewBikeToInventory("Road Bike", "RB123", "New");
        List<Bike> inventory = bikeRentalSystem.getBikeInventory();
        assertEquals(1, inventory.size());
        assertEquals("RB123", inventory.get(0).getSerialNumber());
    }

    @Test
    void testUpdateBikeInformation() {
        bikeRentalSystem.addNewBikeToInventory("Road Bike", "RB123", "New");
        bikeRentalSystem.updateBikeInformation("RB123", "Location A", "Checked", "Available");
        Bike bike = bikeRentalSystem.getBikeInventory().get(0);
        assertEquals("Location A", bike.getLocation());
        assertEquals("Checked", bike.getMaintenanceRecord());
        assertEquals("Available", bike.getStatus());
    }

    @Test
    void testRemoveBikeFromService() {
        bikeRentalSystem.addNewBikeToInventory("Road Bike", "RB123", "New");
        bikeRentalSystem.removeBikeFromService("RB123");
        Bike bike = bikeRentalSystem.getBikeInventory().get(0);
        assertEquals("Out of Service", bike.getStatus());
    }

    @Test
    void testRentAndReturnBike() {
        bikeRentalSystem.addNewBikeToInventory("Road Bike", "RB123", "New");
        bikeRentalSystem.rentBike("Road Bike", 5);
        Bike bike = bikeRentalSystem.getBikeInventory().get(0);
        assertEquals("Rented", bike.getStatus());

        bikeRentalSystem.returnBike("RB123");
        assertEquals("Available", bike.getStatus());
    }

    @Test
    void testMonitorBikeAvailability() {
        bikeRentalSystem.addNewBikeToInventory("Road Bike", "RB123", "New");
        bikeRentalSystem.addNewBikeToInventory("Mountain Bike", "MB456", "New");
        bikeRentalSystem.rentBike("Road Bike", 5);

        bikeRentalSystem.monitorBikeAvailability(); // Here, we assume it prints the available bikes
        List<Bike> inventory = bikeRentalSystem.getBikeInventory();
        assertEquals("Rented", inventory.get(0).getStatus());
        assertEquals("Available", inventory.get(1).getStatus());
    }
}
