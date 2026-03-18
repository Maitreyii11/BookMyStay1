import java.io.*;
import java.util.*;

/**
 * --- PERSISTENCE SERVICE ---
 * Handles saving and loading the system state to a physical file.
 * This demonstrates the transition from in-memory to durable design.
 */
class PersistenceService {
    private static final String FILE_NAME = "inventory_state.txt";

    /**
     * Serialization: Writing the Map to a file.
     */
    public static void saveState(Map<String, Integer> inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println(">>> SYSTEM SHUTDOWN: State successfully persisted to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    /**
     * Deserialization: Reading from the file back into a Map.
     */
    public static Map<String, Integer> loadState() {
        Map<String, Integer> recoveredInventory = new HashMap<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println(">>> STARTUP: No persistence file found. Initializing fresh state.");
            return null;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                if (parts.length == 2) {
                    recoveredInventory.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            System.out.println(">>> STARTUP: System state recovered from " + FILE_NAME);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading state: " + e.getMessage());
        }
        return recoveredInventory;
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 12: Data Persistence & System Recovery.
 * @author Maitreyii11
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 12.0 (DURABLE)      ");
        System.out.println("*************************************************");

        // 1. Attempt to Recovery State
        Map<String, Integer> inventory = PersistenceService.loadState();

        // 2. If no saved state exists, initialize default values
        if (inventory == null) {
            inventory = new HashMap<>();
            inventory.put("Single Room", 10);
            inventory.put("Suite Room", 5);
            System.out.println("Default Inventory Created: " + inventory);
        } else {
            System.out.println("Recovered Inventory: " + inventory);
        }

        // 3. Simulate a Booking Transaction
        System.out.println("-------------------------------------------------");
        System.out.println("Action: Booking 1 Suite Room...");
        if (inventory.get("Suite Room") > 0) {
            inventory.put("Suite Room", inventory.get("Suite Room") - 1);
        }
        System.out.println("Current Live State: " + inventory);

        // 4. Persist State before "Shutdown"
        System.out.println("-------------------------------------------------");
        PersistenceService.saveState(inventory);

        System.out.println("*************************************************");
        System.out.println("Run this program again to see the count persist!");
    }
}