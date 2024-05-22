package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

interface BikeOperations {
    void addNewBikeToInventory(String model, String serialNumber, String initialCondition);
    void updateBikeInformation(String serialNumber, String location, String maintenanceRecord, String status);
    void removeBikeFromService(String serialNumber);
    void retireBike(String serialNumber);
    void monitorBikeAvailability();
    void rentBike(String bikeModel, int rentalDuration);
    void returnBike(String serialNumber);
    void trackMaintenanceHistory(String serialNumber);
    void scheduleMaintenance(String serialNumber, Date date);
}

class BikeRentalSubSystem implements BikeOperations {
    private static BikeRentalSubSystem instance;
    private List<Bike> bikeInventory;

    private BikeRentalSubSystem() {
        bikeInventory = new ArrayList<>();
    }

    public static synchronized BikeRentalSubSystem getInstance() {
        if (instance == null) {
            instance = new BikeRentalSubSystem();
        }
        return instance;
    }

    @Override
    public void addNewBikeToInventory(String model, String serialNumber, String initialCondition) {
        Bike bike = new Bike(model, serialNumber, initialCondition);
        bikeInventory.add(bike);
        System.out.println("Added new bike: " + model + ", Serial Number: " + serialNumber);
    }

    @Override
    public void updateBikeInformation(String serialNumber, String location, String maintenanceRecord, String status) {
        for (Bike bike : bikeInventory) {
            if (bike.getSerialNumber().equals(serialNumber)) {
                bike.setLocation(location);
                bike.setMaintenanceRecord(maintenanceRecord);
                bike.setStatus(status);
                System.out.println("Updated bike information: " + serialNumber);
            }
        }
    }

    @Override
    public void removeBikeFromService(String serialNumber) {
        for (Bike bike : bikeInventory) {
            if (bike.getSerialNumber().equals(serialNumber)) {
                bike.setStatus("Out of Service");
                System.out.println("Removed bike from service: " + serialNumber);
            }
        }
    }

    @Override
    public void retireBike(String serialNumber) {
        bikeInventory.removeIf(bike -> bike.getSerialNumber().equals(serialNumber));
        System.out.println("Retired bike: " + serialNumber);
    }

    @Override
    public void monitorBikeAvailability() {
        for (Bike bike : bikeInventory) {
            System.out.println("Bike Model: " + bike.getModel() + ", Serial Number: " + bike.getSerialNumber() + ", Status: " + bike.getStatus());
        }
    }

    @Override
    public void rentBike(String bikeModel, int rentalDuration) {
        for (Bike bike : bikeInventory) {
            if (bike.getModel().equals(bikeModel) && bike.getStatus().equals("Available")) {
                bike.setStatus("Rented");
                System.out.println("Bike rented: " + bike.getModel() + " for duration: " + rentalDuration + " days.");
                return;
            }
        }
        System.out.println("Bike model: " + bikeModel + " is not available.");
    }

    @Override
    public void returnBike(String serialNumber) {
        for (Bike bike : bikeInventory) {
            if (bike.getSerialNumber().equals(serialNumber) && bike.getStatus().equals("Rented")) {
                bike.setStatus("Available");
                System.out.println("Bike returned: " + bike.getModel() + ".");
                return;
            }
        }
        System.out.println("Bike with serial number: " + serialNumber + " is not found or not rented.");
    }

    @Override
    public void trackMaintenanceHistory(String serialNumber) {
        for (Bike bike : bikeInventory) {
            if (bike.getSerialNumber().equals(serialNumber)) {
                System.out.println("Maintenance Record for Bike Serial Number: " + serialNumber + " - " + bike.getMaintenanceRecord());
                return;
            }
        }
        System.out.println("Bike with serial number: " + serialNumber + " is not found.");
    }

    @Override
    public void scheduleMaintenance(String serialNumber, Date date) {
        for (Bike bike : bikeInventory) {
            if (bike.getSerialNumber().equals(serialNumber)) {
                bike.setMaintenanceRecord("Maintenance scheduled on: " + date.toString());
                bike.setStatus("Under Maintenance");
                System.out.println("Maintenance scheduled for Bike Serial Number: " + serialNumber + " on: " + date);
                return;
            }
        }
        System.out.println("Bike with serial number: " + serialNumber + " is not found.");
    }

    public List<Bike> getBikeInventory() {
        return bikeInventory;
    }
}



// public class BikeRentalSubSystem {

//     private static BikeRentalSubSystem instance;
//     private List<Bike> bikeInventory;

//     private BikeRentalSubSystem() {
//         bikeInventory = new ArrayList<>();
//     }

//     public static synchronized BikeRentalSubSystem getInstance() {
//         if (instance == null) {
//             instance = new BikeRentalSubSystem();
//         }
//         return instance;
//     }

//     public void addNewBikeToInventory(String model, String serialNumber, String initialCondition) {
//         Bike bike = new Bike(model, serialNumber, initialCondition);
//         bikeInventory.add(bike);
//     }

//     public void updateBikeInformation(String serialNumber, String location, String maintenanceRecord, String status) {
//         for (Bike bike : bikeInventory) {
//             if (bike.getSerialNumber().equals(serialNumber)) {
//                 bike.setLocation(location);
//                 bike.setMaintenanceRecord(maintenanceRecord);
//                 bike.setStatus(status);
//             }
//         }
//     }

//     public void removeBikeFromService(String serialNumber) {
//         for (Bike bike : bikeInventory) {
//             if (bike.getSerialNumber().equals(serialNumber)) {
//                 bike.setStatus("Out of Service");
//             }
//         }
//     }

//     public void retireBike(String serialNumber) {
//         bikeInventory.removeIf(bike -> bike.getSerialNumber().equals(serialNumber));
//     }

//     public void monitorBikeAvailability() {
//         for (Bike bike : bikeInventory) {
//             System.out.println("Bike Model: " + bike.getModel() + ", Serial Number: " + bike.getSerialNumber() + ", Status: " + bike.getStatus());
//         }
//     }

//     public void rentBike(String bikeModel, int rentalDuration) {
//         for (Bike bike : bikeInventory) {
//             if (bike.getModel().equals(bikeModel) && bike.getStatus().equals("Available")) {
//                 bike.setStatus("Rented");
//                 System.out.println("Bike rented: " + bike.getModel() + " for duration: " + rentalDuration + " days.");
//                 return;
//             }
//         }
//         System.out.println("Bike model: " + bikeModel + " is not available.");
//     }

//     public void returnBike(String serialNumber) {
//         for (Bike bike : bikeInventory) {
//             if (bike.getSerialNumber().equals(serialNumber) && bike.getStatus().equals("Rented")) {
//                 bike.setStatus("Available");
//                 System.out.println("Bike returned: " + bike.getModel() + ".");
//                 return;
//             }
//         }
//         System.out.println("Bike with serial number: " + serialNumber + " is not found or not rented.");
//     }

//     public void trackMaintenanceHistory(String serialNumber) {
//         for (Bike bike : bikeInventory) {
//             if (bike.getSerialNumber().equals(serialNumber)) {
//                 System.out.println("Maintenance Record for Bike Serial Number: " + serialNumber + " - " + bike.getMaintenanceRecord());
//                 return;
//             }
//         }
//         System.out.println("Bike with serial number: " + serialNumber + " is not found.");
//     }

//     public void scheduleMaintenance(String serialNumber, Date date) {
//         for (Bike bike : bikeInventory) {
//             if (bike.getSerialNumber().equals(serialNumber)) {
//                 bike.setMaintenanceRecord("Maintenance scheduled on: " + date.toString());
//                 bike.setStatus("Under Maintenance");
//                 System.out.println("Maintenance scheduled for Bike Serial Number: " + serialNumber + " on: " + date);
//                 return;
//             }
//         }
//         System.out.println("Bike with serial number: " + serialNumber + " is not found.");
//     }

//     public List<Bike> getBikeInventory() {
//         return bikeInventory;
//     }
// }

