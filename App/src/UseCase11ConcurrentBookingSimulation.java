import java.util.*;

// Booking Request
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Booking System
class BookingSystem {

    private Map<String, Integer> inventory;

    public BookingSystem(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    // CRITICAL SECTION (Thread-safe)
    public synchronized void bookRoom(BookingRequest request) {

        String roomType = request.roomType;

        System.out.println(Thread.currentThread().getName()
                + " trying to book " + roomType);

        if (inventory.getOrDefault(roomType, 0) > 0) {

            // simulate delay (race condition scenario)
            try { Thread.sleep(100); } catch (Exception e) {}

            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println(Thread.currentThread().getName()
                    + " SUCCESS for " + request.guestName);

        } else {
            System.out.println(Thread.currentThread().getName()
                    + " FAILED (No rooms)");
        }
    }
}

// Thread class
class BookingThread extends Thread {

    private BookingSystem system;
    private BookingRequest request;

    public BookingThread(BookingSystem system, BookingRequest request) {
        this.system = system;
        this.request = request;
    }

    public void run() {
        system.bookRoom(request);
    }
}

// MAIN CLASS
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("===== UC11 Concurrent Booking =====");

        // Shared inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 1); // only 1 room

        BookingSystem system = new BookingSystem(inventory);

        // Multiple requests (same room)
        BookingRequest r1 = new BookingRequest("Arpita", "Deluxe");
        BookingRequest r2 = new BookingRequest("Rahul", "Deluxe");

        // Two threads (simulate concurrency)
        Thread t1 = new BookingThread(system, r1);
        Thread t2 = new BookingThread(system, r2);

        // Start both threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {}

        System.out.println("\nFinal Inventory: " + inventory);
    }
}