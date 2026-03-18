
/**
 * Use Case 3: Centralized Room Inventory Management.
 * Demonstrates how HashMap solves the problem of scattered state.
 * * @author Maitreyii11
 * @version 3.0
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 3.0 (INVENTORY)     ");
        System.out.println("*************************************************");

        // 1. Initialize the Centralized Inventory
        RoomInventory hotelInventory = new RoomInventory();

        // 2. Register Room Types (Populating the Map)
        hotelInventory.addRoomType("Single Room", 10);
        hotelInventory.addRoomType("Double Room", 5);
        hotelInventory.addRoomType("Suite Room", 2);

        // 3. Show Initial State
        System.out.println("Initial State:");
        hotelInventory.displayInventory();

        // 4. Demonstrate a Controlled Update (e.g., a booking occurs)
        System.out.println("Action: Booking 1 Single Room...");
        hotelInventory.updateAvailability("Single Room", -1);

        // 5. Show Updated State
        hotelInventory.displayInventory();

        System.out.println("*************************************************");
        System.out.println("Inventory logic verified successfully.");
    }
}