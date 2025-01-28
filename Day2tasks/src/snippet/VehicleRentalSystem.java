package snippet;

import java.util.Scanner;

public class VehicleRentalSystem {

    // Abstract base class for Vehicle
    abstract static class Vehicle {
        String vehicleId;
        String model;
        double rentalRate; // Per day rental rate

        public Vehicle(String vehicleId, String model, double rentalRate) {
            this.vehicleId = vehicleId;
            this.model = model;
            this.rentalRate = rentalRate;
        }

        // Abstract method for calculating rental cost
        public abstract double calculateRentalCost(int days);

        @Override
        public String toString() {
            return model + " (ID: " + vehicleId + ") - $" + rentalRate + "/day";
        }
    }

    // Car class extending Vehicle
    static class Car extends Vehicle {
        int seats;

        public Car(String vehicleId, String model, double rentalRate, int seats) {
            super(vehicleId, model, rentalRate);
            this.seats = seats;
        }

        @Override
        public double calculateRentalCost(int days) {
            return rentalRate * days;
        }

        @Override
        public String toString() {
            return super.toString() + " | Seats: " + seats;
        }
    }

    // Bike class extending Vehicle
    static class Bike extends Vehicle {
        boolean hasGear;

        public Bike(String vehicleId, String model, double rentalRate, boolean hasGear) {
            super(vehicleId, model, rentalRate);
            this.hasGear = hasGear;
        }

        @Override
        public double calculateRentalCost(int days) {
            return rentalRate * days;
        }

        @Override
        public String toString() {
            return super.toString() + " | Gear: " + (hasGear ? "Yes" : "No");
        }
    }

    // Customer class to store customer details
    static class Customer {
        String customerId;
        String name;
        String contact;

        public Customer(String customerId, String name, String contact) {
            this.customerId = customerId;
            this.name = name;
            this.contact = contact;
        }

        @Override
        public String toString() {
            return "Customer ID: " + customerId + " | Name: " + name + " | Contact: " + contact;
        }
    }

    // Main method to simulate renting process
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some sample vehicles
        Vehicle car1 = new Car("V001", "Toyota Camry", 50.0, 5);
        Vehicle bike1 = new Bike("V002", "Yamaha R15", 20.0, true);

        // Create customers
        Customer customer1 = new Customer("C001", "John Doe", "123-456-7890");
        Customer customer2 = new Customer("C002", "Jane Smith", "987-654-3210");

        // Simulate renting process for Customer 1
        System.out.println("Customer: " + customer1);
        System.out.println("Available vehicles: ");
        System.out.println("1. " + car1);
        System.out.println("2. " + bike1);

        System.out.print("\nChoose a vehicle to rent (1 for Car, 2 for Bike): ");
        int choice = scanner.nextInt();

        System.out.print("Enter rental days: ");
        int rentalDays = scanner.nextInt();

        Vehicle chosenVehicle = (choice == 1) ? car1 : bike1;

        System.out.println("\nRental Details:");
        System.out.println("Rented Vehicle: " + chosenVehicle);
        System.out.println("Rental Period: " + rentalDays + " days");
        System.out.println("Total Rental Cost: $" + chosenVehicle.calculateRentalCost(rentalDays));

        // Simulate returning the vehicle
        System.out.println("\nReturning Vehicle: " + chosenVehicle);
        System.out.println("Total rental days: " + rentalDays);
        System.out.println("Total Cost: $" + chosenVehicle.calculateRentalCost(rentalDays));

        // Simulate renting process for Customer 2
        System.out.println("\nCustomer: " + customer2);
        System.out.println("Available vehicles: ");
        System.out.println("1. " + car1);
        System.out.println("2. " + bike1);

        System.out.print("\nChoose a vehicle to rent (1 for Car, 2 for Bike): ");
        choice = scanner.nextInt();

        System.out.print("Enter rental days: ");
        rentalDays = scanner.nextInt();

        chosenVehicle = (choice == 1) ? car1 : bike1;

        System.out.println("\nRental Details:");
        System.out.println("Rented Vehicle: " + chosenVehicle);
        System.out.println("Rental Period: " + rentalDays + " days");
        System.out.println("Total Rental Cost: $" + chosenVehicle.calculateRentalCost(rentalDays));

        // Simulate returning the vehicle
        System.out.println("\nReturning Vehicle: " + chosenVehicle);
        System.out.println("Total rental days: " + rentalDays);
        System.out.println("Total Cost: $" + chosenVehicle.calculateRentalCost(rentalDays));

        scanner.close();
    }
}
