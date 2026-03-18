import java.util.*;

/**
 * --- CUSTOM EXCEPTIONS ---
 * These ensure our error handling is specific to the Booking domain.
 */
class UC9InvalidRoomException extends Exception {
    public UC9InvalidRoomException(String message) {
        super(message);
    }
}

class UC9NoAvailabilityException extends Exception {
    public UC9NoAvailabilityException(String message) {
        super(message);
    }
}

/**
 * --- VALIDATOR SERVICE ---
 * Uses 'Fail-Fast' logic to detect issues before they reach the database/inventory.
 */
class UC9BookingValidator {
    public static void validate(String type, Map<String, Integer> inventory)
            throws UC9InvalidRoomException, UC9NoAvailabilityException {

        // 1. Validation: Check if room type exists (Case Sensitive)
        if (!inventory.containsKey(type)) {
            throw new UC9InvalidRoomException("Error: Room type '" + type + "' does not exist in our catalog.");
        }

        // 2. Validation: Check if there is stock
        if (inventory.get(type) <= 0) {
            throw new UC9NoAvailabilityException("Failure: No more '" + type + "' units available.");
        }
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 9: Error Handling & Validation.
 * @author Maitreyii11
 * @version 9.0
 */
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 9.0 (VALIDATION)    ");
        System.out.println("*************************************************");

        // 1. Setup Mock Inventory for testing
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 0); // Sold out

        // 2. Test Requests
        String[] guestRequests = {"Single Room", "Presidential Suite", "Suite Room"};

        for (String request : guestRequests) {
            System.out.println("Guest Request: " + request);

            try {
                // Call validation service
                UC9BookingValidator.validate(request, inventory);

                // If it passes:
                System.out.println(">>> SUCCESS: Request validated. Proceeding to booking...");

            } catch (UC9InvalidRoomException e) {
                // Handles non-existent room types
                System.err.println(">>> VALIDATION FAILED: " + e.getMessage());
            } catch (UC9NoAvailabilityException e) {
                // Handles zero availability
                System.err.println(">>> AVAILABILITY FAILED: " + e.getMessage());
            } finally {
                // Ensure the separator prints after the error message
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                System.out.println("-------------------------------------------------");
            }
        }

        System.out.println("System remains stable after handling all errors.");
        System.out.println("*************************************************");
    }
}