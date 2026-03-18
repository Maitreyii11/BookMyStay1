
import java.util.Map;

/**
 * SearchService provides read-only access to room availability and details.
 * It demonstrates Defensive Programming by filtering out unavailable rooms.
 * * @author Maitreyii11
 * @version 4.0
 */
public class SearchService {

    /**
     * Searches and displays available rooms.
     * This method reads data but does NOT modify the inventory state.
     */
    public void searchAvailableRooms(RoomInventory inventory, Map<String, Room> roomDetails) {
        System.out.println("\n--- Searching for Available Rooms ---");
        boolean found = false;

        for (String roomType : roomDetails.keySet()) {
            int availableCount = inventory.getAvailability(roomType);

            // Validation Logic: Only show rooms with availability > 0
            if (availableCount > 0) {
                Room room = roomDetails.get(roomType);
                System.out.println(room.toString());
                System.out.println("Status: AVAILABLE (" + availableCount + " left)");
                room.displayFeatures();
                System.out.println("-------------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no rooms are currently available.");
        }
    }
}