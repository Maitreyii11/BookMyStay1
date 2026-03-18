import java.util.*;

/**
 * --- DATA MODELS ---
 */
class UC10Reservation {
    private String guestName;
    private String roomType;
    private String roomId;

    public UC10Reservation(String name, String type, String id) {
        this.guestName = name;
        this.roomType = type;
        this.roomId = id;
    }

    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }
    public String getGuestName() { return guestName; }

    @Override
    public String toString() {
        return "Booking[Guest: " + guestName + " | ID: " + roomId + "]";
    }
}

/**
 * --- CANCELLATION SERVICE ---
 * Uses a Stack to manage LIFO rollback of room IDs.
 */
class CancellationService {
    private Stack<String> releasedRooms = new Stack<>();

    public void cancelBooking(UC10Reservation res, Map<String, Integer> inventory) {
        System.out.println("Processing Cancellation for: " + res.getGuestName());

        // 1. Record the room ID in the Rollback Stack
        releasedRooms.push(res.getRoomId());
        System.out.println(">>> Room ID " + res.getRoomId() + " pushed to Rollback Stack.");

        // 2. Increment Inventory (Rollback the state)
        String type = res.getRoomType();
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);

        System.out.println(">>> Inventory restored for " + type + ". New Count: " + inventory.get(type));
    }

    public void showRecentRollbacks() {
        System.out.println("\n--- Recent Room ID Rollbacks (LIFO Order) ---");
        if (releasedRooms.isEmpty()) {
            System.out.println("No rollbacks recorded.");
        } else {
            while (!releasedRooms.isEmpty()) {
                System.out.println("Released Room ID: " + releasedRooms.pop());
            }
        }
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 10: Booking Cancellation & Inventory Rollback.
 * @author Maitreyii11
 * @version 10.0
 */
public class UseCase10BookingCancellation {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 10.0 (ROLLBACK)     ");
        System.out.println("*************************************************");

        // 1. Initial System State
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Suite Room", 0); // Currently sold out
        inventory.put("Single Room", 1);

        // 2. Existing Confirmed Bookings
        UC10Reservation res1 = new UC10Reservation("Maitreyi", "Suite Room", "S-101");
        UC10Reservation res2 = new UC10Reservation("Alice", "Suite Room", "S-102");

        System.out.println("Initial Inventory: " + inventory);
        System.out.println("-------------------------------------------------");

        // 3. Initialize Cancellation Service
        CancellationService cancelService = new CancellationService();

        // 4. Perform Cancellations (LIFO Logic)
        cancelService.cancelBooking(res1, inventory);
        cancelService.cancelBooking(res2, inventory);

        // 5. Show Rollback Order (Alice was last, so she is first in the stack)
        cancelService.showRecentRollbacks();

        System.out.println("-------------------------------------------------");
        System.out.println("Final Inventory State: " + inventory);
        System.out.println("*************************************************");
        System.out.println("Cancellation and State Rollback verified.");
    }
}
