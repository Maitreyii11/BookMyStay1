import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 4: Room Search & Availability Check.
 * Demonstrates separation of concerns between searching and booking.
 * * @author Maitreyii11
 * @version 4.0
 */
public class UseCase4RoomSearch {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 4.0 (SEARCH)        ");
        System.out.println("*************************************************");

        // 1. Setup Room Data (Domain Models)
        Map<String, Room> roomCatalog = new HashMap<>();
        roomCatalog.put("Single Room", new SingleRoom());
        roomCatalog.put("Double Room", new DoubleRoom());
        roomCatalog.put("Suite Room", new SuiteRoom());

        // 2. Setup Inventory State
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 0); // This should be filtered out
        inventory.addRoomType("Suite Room", 2);

        // 3. Initialize Search Service
        SearchService searchService = new SearchService();

        // 4. Perform Search
        // Notice: Double Room will not appear because availability is 0
        searchService.searchAvailableRooms(inventory, roomCatalog);

        System.out.println("*************************************************");
        System.out.println("Search completed. System state remains unchanged.");
    }
}