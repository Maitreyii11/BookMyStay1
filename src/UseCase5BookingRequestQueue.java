import java.util.LinkedList;
import java.util.Queue;

/**
 * --- DATA MODEL ---
 * Represents a Guest's intent to book a room.
 * This class is a simple POJO (Plain Old Java Object).
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Order Received -> [Guest: " + guestName + " | Requested: " + roomType + "]";
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 5: Booking Request (First-Come-First-Served).
 * Goal: Handle multiple requests fairly using a FIFO Queue.
 * * @author Maitreyii11
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 5.0 (QUEUE)         ");
        System.out.println("*************************************************");

        // 1. Initialize the Booking Request Queue
        // We use LinkedList because it implements the Queue interface.
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // 2. Simulating incoming requests (Arrival Order)
        System.out.println("System Status: Accepting incoming requests...");
        System.out.println("-------------------------------------------------");

        bookingQueue.add(new Reservation("Maitreyi", "Suite Room"));
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));

        // 3. Displaying the Queue State
        // The loop will show them in the order they were added (FIFO).
        System.out.println("Current Waiting List:");
        for (Reservation request : bookingQueue) {
            System.out.println(request);
        }

        // 4. Inspecting the Next Request
        // peek() lets us see the first item without removing it.
        System.out.println("-------------------------------------------------");
        System.out.println("Total Requests in Queue: " + bookingQueue.size());

        if (!bookingQueue.isEmpty()) {
            System.out.println("Next Guest to be processed: " + bookingQueue.peek().getGuestName());
        }

        System.out.println("*************************************************");
        System.out.println("Queue initialized. Ready for Allocation Service.");
    }
}