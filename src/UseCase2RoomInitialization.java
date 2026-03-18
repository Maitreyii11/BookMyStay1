/**
 * Use Case 2: Basic Room Types & Static Availability.
 * This class serves as the entry point to demonstrate Object-Oriented
 * modeling (Inheritance & Abstraction).
 * * @author YourName
 * @version 2.0
 */
public class UseCase2RoomInitialization {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("      BOOK MY STAY - VERSION 2.0 (BETA)        ");
        System.out.println("===============================================");

        // 1. Instantiating Room objects using Polymorphism
        // We use the parent type (Room) to hold child objects (SingleRoom, etc.)
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();

        // 2. Representing Availability with Static Variables
        // Note: This is a "hardcoded" approach to show its limitations
        int availableSingles = 5;
        int availableDoubles = 3;

        // 3. Displaying Room Information
        System.out.println("Checking Room Categories...");
        System.out.println("-----------------------------------------------");

        displayStatus(singleRoom, availableSingles);
        displayStatus(doubleRoom, availableDoubles);

        System.out.println("===============================================");
        System.out.println("Application execution completed successfully.");
    }

    /**
     * Helper method to print room details and availability.
     * Demonstrates how we can treat any subclass of Room uniformly.
     */
    public static void displayStatus(Room room, int count) {
        System.out.println(room.toString()); // Uses the toString from Room class
        room.displayFeatures();             // Uses the specific implementation from the subclass
        System.out.println("Current Availability: " + count + " units");
        System.out.println("-----------------------------------------------");
    }
}