package snippet;

import java.util.Scanner;

public class HotelRoomBookingSyste {

    // Abstract Room class representing a generic room
    abstract static class Room {
        String roomId;
        String roomType;
        double pricePerNight;
        boolean isBooked;

        public Room(String roomId, String roomType, double pricePerNight) {
            this.roomId = roomId;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.isBooked = false; // Initially, the room is not booked
        }

        // Abstract method to calculate the total price for booking
        public abstract double calculateTotalPrice(int nights);

        // Book the room
        public boolean bookRoom() {
            if (isBooked) {
                System.out.println("Sorry, this room is already booked.");
                return false;
            } else {
                isBooked = true;
                System.out.println("Room " + roomId + " has been successfully booked!");
                return true;
            }
        }

        // Check out the room
        public void checkOut() {
            isBooked = false;
            System.out.println("Room " + roomId + " has been checked out successfully.");
        }

        @Override
        public String toString() {
            return roomType + " (ID: " + roomId + ") - $" + pricePerNight + " per night";
        }
    }

    // Standard Room class extending Room
    static class StandardRoom extends Room {
        public StandardRoom(String roomId, double pricePerNight) {
            super(roomId, "Standard", pricePerNight);
        }

        @Override
        public double calculateTotalPrice(int nights) {
            return pricePerNight * nights;
        }
    }

    // Deluxe Room class extending Room
    static class DeluxeRoom extends Room {
        public DeluxeRoom(String roomId, double pricePerNight) {
            super(roomId, "Deluxe", pricePerNight);
        }

        @Override
        public double calculateTotalPrice(int nights) {
            return pricePerNight * nights;
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

    // Main class to demonstrate the Hotel Room Booking System
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some sample rooms
        Room room1 = new StandardRoom("R101", 100.0);
        Room room2 = new DeluxeRoom("R102", 150.0);
        Room room3 = new DeluxeRoom("R103", 180.0);

        // Create a customer
        Customer customer1 = new Customer("C001", "John Doe", "123-456-7890");

        // Show available rooms
        System.out.println("Available Rooms:");
        System.out.println("1. " + room1);
        System.out.println("2. " + room2);
        System.out.println("3. " + room3);

        // Customer chooses a room
        System.out.print("\nChoose a room to book (1 for Standard, 2 for Deluxe, 3 for Deluxe): ");
        int roomChoice = scanner.nextInt();

        // Ask for number of nights to book the room
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        // Book the chosen room
        Room selectedRoom = null;
        switch (roomChoice) {
            case 1:
                selectedRoom = room1;
                break;
            case 2:
                selectedRoom = room2;
                break;
            case 3:
                selectedRoom = room3;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (selectedRoom.bookRoom()) {
            System.out.println("Booking Details:");
            System.out.println(customer1);
            System.out.println("Room: " + selectedRoom);
            System.out.println("Total Price: $" + selectedRoom.calculateTotalPrice(nights));
        }

        // Customer checks out
        System.out.print("\nDo you want to check-out? (y/n): ");
        char checkOutChoice = scanner.next().charAt(0);

        if (checkOutChoice == 'y' || checkOutChoice == 'Y') {
            selectedRoom.checkOut();
        }

        scanner.close();
    }
}

