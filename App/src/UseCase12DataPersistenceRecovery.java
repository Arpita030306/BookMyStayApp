import java.io.*;
import java.util.*;

// Booking class (Serializable)
class Booking implements Serializable {
    String guestName;
    String roomType;

    public Booking(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return guestName + " -> " + roomType;
    }
}

// System State (Serializable)
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Booking> bookings;

    public SystemState(Map<String, Integer> inventory, List<Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // SAVE STATE
    public static void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("✅ State saved successfully");

        } catch (Exception e) {
            System.out.println("❌ Error saving state: " + e.getMessage());
        }
    }

    // LOAD STATE
    public static SystemState load() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("⚠ No saved state found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("✅ State restored successfully");
            return (SystemState) ois.readObject();

        } catch (Exception e) {
            System.out.println("❌ Corrupted file. Starting fresh.");
            return null;
        }
    }
}

// MAIN CLASS
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("===== UC12 Persistence & Recovery =====");

        // Try to load previous state
        SystemState state = PersistenceService.load();

        Map<String, Integer> inventory;
        List<Booking> bookings;

        if (state == null) {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Deluxe", 2);
            inventory.put("Standard", 3);

            bookings = new ArrayList<>();
        } else {
            inventory = state.inventory;
            bookings = state.bookings;
        }

        // Simulate booking
        Booking b1 = new Booking("Arpita", "Deluxe");

        if (inventory.get("Deluxe") > 0) {
            inventory.put("Deluxe", inventory.get("Deluxe") - 1);
            bookings.add(b1);
            System.out.println("Booking successful: " + b1);
        } else {
            System.out.println("No rooms available");
        }

        // Show current state
        System.out.println("\nInventory: " + inventory);
        System.out.println("Bookings: " + bookings);

        // Save before shutdown
        PersistenceService.save(new SystemState(inventory, bookings));

        System.out.println("\n🔁 Restart program to see recovery!");
    }
}