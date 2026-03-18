import java.util.*;

/**
 * --- DATA MODELS ---
 */
class UC11Reservation {
    private String guestName;
    private String roomType;

    public UC11Reservation(String name, String type) {
        this.guestName = name;
        this.roomType = type;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * --- CONCURRENT PROCESSOR ---
 * Uses 'synchronized' to ensure Thread Safety during inventory updates.
 */
class BookingProcessor {
    private Map<String, Integer> inventory;

    public BookingProcessor(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * This method is 'synchronized' to prevent Race Conditions.
     * Only one thread can execute this block for a specific room type at a time.
     */
    public synchronized void processBooking(UC11Reservation request) {
        String type = request.getRoomType();
        int available = inventory.getOrDefault(type, 0);

        System.out.println(Thread.currentThread().getName() + " checking " + type + " for " + request.getGuestName());

        if (available > 0) {
            // Simulate processing delay to expose potential race conditions
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            inventory.put(type, available - 1);
            System.out.println(">>> SUCCESS: " + request.getGuestName() + " booked " + type +
                    ". Remaining: " + (available - 1));
        } else {
            System.out.println(">>> FAILURE: No " + type + " left for " + request.getGuestName());
        }
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 11: Concurrent Booking Simulation (Thread Safety).
 * @author Maitreyii11
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 11.0 (THREADS)      ");
        System.out.println("*************************************************");

        // 1. Initialize Shared Inventory (Only 1 Suite available)
        Map<String, Integer> sharedInventory = new HashMap<>();
        sharedInventory.put("Suite Room", 1);

        BookingProcessor processor = new BookingProcessor(sharedInventory);

        // 2. Create multiple threads (Guests) trying to book the SAME room at the SAME time
        UC11Reservation req1 = new UC11Reservation("Maitreyi", "Suite Room");
        UC11Reservation req2 = new UC11Reservation("Alice", "Suite Room");
        UC11Reservation req3 = new UC11Reservation("Bob", "Suite Room");

        // 3. Define the Thread Tasks
        Thread t1 = new Thread(() -> processor.processBooking(req1), "Thread-Maitreyi");
        Thread t2 = new Thread(() -> processor.processBooking(req2), "Thread-Alice");
        Thread t3 = new Thread(() -> processor.processBooking(req3), "Thread-Bob");

        // 4. Start all threads concurrently
        System.out.println("Simulating simultaneous requests for the last Suite...");
        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("*************************************************");
        System.out.println("Final Inventory State: " + sharedInventory);
        System.out.println("Multi-user synchronization verified.");
    }
}
