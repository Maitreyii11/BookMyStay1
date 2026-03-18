
import java.util.*;

/**
 * --- DATA MODELS ---
 */
class ConfirmedBooking {
    private String guestName;
    private String roomType;
    private String roomId;
    private double price;

    public ConfirmedBooking(String guestName, String roomType, String roomId, double price) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Guest: %-10s | Room: %-12s | ID: %-8s | Paid: $%.2f",
                guestName, roomType, roomId, price);
    }
}

/**
 * --- HISTORY SERVICE ---
 * Acts as the "Persistence" layer using a List to store the audit trail.
 */
class BookingHistory {
    private List<ConfirmedBooking> history = new ArrayList<>();

    public void recordBooking(ConfirmedBooking booking) {
        history.add(booking);
    }

    public List<ConfirmedBooking> getHistory() {
        return new ArrayList<>(history); // Returns a copy for Read-Only access
    }
}

/**
 * --- REPORTING SERVICE ---
 * Generates summaries from the history without modifying the data.
 */
class ReportingService {
    public void generateSummary(BookingHistory history) {
        List<ConfirmedBooking> records = history.getHistory();
        double totalRevenue = 0;

        System.out.println("\n--- OFFICIAL BOOKING HISTORY REPORT ---");
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (ConfirmedBooking b : records) {
                System.out.println(b);
                // In a real app, we'd extract price; here we simulate total calculation
            }
        }
        System.out.println("---------------------------------------");
        System.out.println("Total Records Processed: " + records.size());
        System.out.println("Report Status: COMPLETED");
    }
}

/**
 * --- APPLICATION ENTRY POINT ---
 * Use Case 8: Booking History & Reporting.
 * @author Maitreyii11
 * @version 8.0
 */
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {
        System.out.println("*************************************************");
        System.out.println("      BOOK MY STAY - VERSION 8.0 (REPORTS)       ");
        System.out.println("*************************************************");

        // 1. Initialize History and Reporting Services
        BookingHistory historyStore = new BookingHistory();
        ReportingService reportService = new ReportingService();

        // 2. Simulate the outcome of successful allocations
        System.out.println("System: Archiving confirmed bookings...");

        historyStore.recordBooking(new ConfirmedBooking("Maitreyi", "Suite Room", "S-105", 350.0));
        historyStore.recordBooking(new ConfirmedBooking("Alice", "Single Room", "R-202", 100.0));
        historyStore.recordBooking(new ConfirmedBooking("Bob", "Double Room", "D-304", 180.0));

        // 3. Admin requests a report
        System.out.println("Admin: Requesting historical audit trail...");
        reportService.generateSummary(historyStore);

        System.out.println("*************************************************");
        System.out.println("Persistence-oriented reporting verified.");
    }
}