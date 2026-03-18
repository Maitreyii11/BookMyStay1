import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 * Demonstrates how to extend a system using Composition and Map-based associations.
 */

// 1. Individual Service Model
class Service {
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

// 2. Add-On Service Manager
class AddOnServiceManager {
    // Key: Reservation ID, Value: List of selected services
    private Map<String, List<Service>> reservationServices;

    public AddOnServiceManager() {
        this.reservationServices = new HashMap<>();
    }

    // Association logic: Maps services to a specific reservation
    public void addServiceToReservation(String reservationId, Service service) {
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Added " + service.getName() + " to Reservation: " + reservationId);
    }

    // Cost Aggregation logic
    public double calculateTotalAddOnCost(String reservationId) {
        List<Service> services = reservationServices.get(reservationId);
        if (services == null) return 0.0;

        return services.stream()
                .mapToDouble(Service::getPrice)
                .sum();
    }

    public List<Service> getServicesForReservation(String reservationId) {
        return reservationServices.getOrDefault(reservationId, Collections.emptyList());
    }
}

// 3. Main Application Class
public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("--- Book My Stay: Add-On Service Selection ---");

        // Initialize Manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Sample Reservation IDs (Assume these were created in previous use cases)
        String resId1 = "RES-101";
        String resId2 = "RES-102";

        // Available Services
        Service breakfast = new Service("Buffet Breakfast", 25.0);
        Service spa = new Service("Spa Treatment", 80.0);
        Service wifi = new Service("Premium WiFi", 15.0);
        Service airportShuttle = new Service("Airport Shuttle", 40.0);

        // --- Action: Guest for RES-101 selects multiple services ---
        System.out.println("\nProcessing services for " + resId1 + "...");
        serviceManager.addServiceToReservation(resId1, breakfast);
        serviceManager.addServiceToReservation(resId1, spa);
        serviceManager.addServiceToReservation(resId1, wifi);

        // --- Action: Guest for RES-102 selects one service ---
        System.out.println("\nProcessing services for " + resId2 + "...");
        serviceManager.addServiceToReservation(resId2, airportShuttle);

        // --- Summary Output ---
        displaySummary(serviceManager, resId1);
        displaySummary(serviceManager, resId2);

        System.out.println("\nLogic Check: Core booking logic remains decoupled from service logic.");
    }

    private static void displaySummary(AddOnServiceManager manager, String resId) {
        System.out.println("\n-------------------------------------------");
        System.out.println("Summary for Reservation: " + resId);
        List<Service> services = manager.getServicesForReservation(resId);

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
        } else {
            services.forEach(s -> System.out.println("- " + s));
            double total = manager.calculateTotalAddOnCost(resId);
            System.out.println("Total Additional Cost: $" + total);
        }
    }
}