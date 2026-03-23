import java.util.*;

// Service class (Add-On)
class Service {
    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Manager class
class AddOnServiceManager {

    // ReservationID → List of Services
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Get services
    public List<Service> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        for (Service s : getServices(reservationId)) {
            total += s.getCost();
        }
        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        System.out.println("\nServices for Reservation: " + reservationId);

        List<Service> services = getServices(reservationId);

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (Service s : services) {
            System.out.println("- " + s);
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

// Main class
public class AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("====== ADD-ON SERVICE SELECTION ======");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Example reservation IDs (already created in UC6)
        String reservation1 = "RES-101";
        String reservation2 = "RES-102";

        // Create services
        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1500);
        Service airportPickup = new Service("Airport Pickup", 800);

        // Add services
        manager.addService(reservation1, breakfast);
        manager.addService(reservation1, spa);

        manager.addService(reservation2, airportPickup);

        // Display
        manager.displayServices(reservation1);
        manager.displayServices(reservation2);
    }
}