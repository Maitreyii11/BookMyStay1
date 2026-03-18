import java.util.*;

/**
 * --- DATA MODELS ---
 * These are package-private to avoid "duplicate class" errors with your other files.
 */
abstract class RoomUC6 {
    private String type;
    public RoomUC6(String type) { this.type = type; }
    public String getType() { return type; }
    @Override
    public String toString() { return type; }
}

class SuiteRoomUC6 extends RoomUC6 { public SuiteRoomUC6() { super("Suite Room"); } }
class SingleRoomUC6 extends RoomUC6 { public SingleRoomUC6() { super("Single Room"); } }

class ReservationUC6 {
    private String guestName;
    private String roomType;
    public ReservationUC6(String name, String type) {
        this.guestName = name;
        this.roomType = type;
    }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * --- INVENTORY MANAGEMENT ---
 */
class InventoryManager {
    private Map<String, Integer> counts = new HashMap<>();
    public void addType(String type, int count) { counts.put(type, count); }
    public int getAvailability(String type) { return counts.getOrDefault(type, 0); }
    public void updateAvailability(String type, int delta) {
        counts.put(type, getAvailability(type) + delta);
    }
}

/**
 * --- ALLOCATION SERVICE ---
 */
class AllocationService {
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public void processBookings(Queue<ReservationUC6> queue, InventoryManager inventory) {
        System.out.println("\n--- Processing Allocation Queue (FIFO) ---");

        while (!queue.isEmpty()) {
            ReservationUC6 request = queue.poll();
            String type = request.getRoomType();

            if (inventory.getAvailability(type) > 0) {
                // Generate Unique Room ID
                String roomId = type.split(" ")[0] + "-" + (101 + (int)(Math.random() * 899));

                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                inventory.updateAvailability(type, -1);

                System.out.println("CONFIRMED: " + request.getGuestName() + " assigned to [" + roomId + "]");
            } else {
                System.out.println("REJECTED: No availability for " + request.getGuestName() + " (" + type + ")");
            }
        }
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 6: Reservation Confirmation & Room Allocation.
 * @author Maitreyii11
 * @version 6.0
 */
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 6.0 (FINAL)         ");
        System.out.println("*************************************************");

        // 1. Setup Inventory
        InventoryManager inventory = new InventoryManager();
        inventory.addType("Suite Room", 2);
        inventory.addType("Single Room", 1);

        // 2. Setup Request Queue
        Queue<ReservationUC6> queue = new LinkedList<>();
        queue.add(new ReservationUC6("Maitreyi", "Suite Room"));
        queue.add(new ReservationUC6("Alice", "Suite Room"));
        queue.add(new ReservationUC6("Bob", "Suite Room")); // Should fail
        queue.add(new ReservationUC6("Charlie", "Single Room"));

        // 3. Run Allocation
        AllocationService service = new AllocationService();
        service.processBookings(queue, inventory);

        System.out.println("*************************************************");
        System.out.println("Booking System finalized successfully.");
    }
}