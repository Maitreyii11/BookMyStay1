
import java.util.HashMap;
import java.util.Map;

/**
 * RoomInventory manages the centralized state of room availability.
 * It uses a HashMap to provide O(1) lookup and update times.
 * * @author Maitreyii11
 * @version 3.0
 */
public class RoomInventory {
    // HashMap to map Room Type (String) to Available Count (Integer)
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    /**
     * Registers a room type with an initial count.
     */
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    /**
     * Retrieves the current availability for a specific room type.
     */
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    /**
     * Updates the availability (e.g., after a booking or cancellation).
     */
    public void updateAvailability(String type, int change) {
        if (inventory.containsKey(type)) {
            int currentCount = inventory.get(type);
            inventory.put(type, currentCount + change);
        }
    }

    /**
     * Displays the full state of the inventory.
     */
    public void displayInventory() {
        System.out.println("Current Inventory Status:");
        System.out.println("-------------------------");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
        System.out.println("-------------------------");
    }
}