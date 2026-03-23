import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

public class RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("====== ROOM ALLOCATION SERVICE ======");

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Deluxe Room", 1);

        // Booking Queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Arpita", "Single Room"));
        bookingQueue.add(new Reservation("Rahul", "Single Room"));
        bookingQueue.add(new Reservation("Neha", "Single Room")); // should fail
        bookingQueue.add(new Reservation("Aman", "Double Room"));

        // Allocated rooms (NO DUPLICATES)
        Set<String> allocatedRoomIds = new HashSet<>();

        // Mapping room type → allocated IDs
        Map<String, Set<String>> roomAllocations = new HashMap<>();

        int roomCounter = 1;

        while (!bookingQueue.isEmpty()) {

            Reservation r = bookingQueue.poll();
            String type = r.getRoomType();

            System.out.println("\nProcessing: " + r.getGuestName());

            // Check availability
            if (inventory.containsKey(type) && inventory.get(type) > 0) {

                // Generate unique room ID
                String roomId = type.replace(" ", "").toUpperCase() + "-" + roomCounter++;

                // Ensure uniqueness (Set prevents duplicate)
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    // Add to mapping
                    roomAllocations.putIfAbsent(type, new HashSet<>());
                    roomAllocations.get(type).add(roomId);

                    // Decrement inventory
                    inventory.put(type, inventory.get(type) - 1);

                    System.out.println("Booking Confirmed!");
                    System.out.println("Guest: " + r.getGuestName());
                    System.out.println("Room Type: " + type);
                    System.out.println("Assigned Room ID: " + roomId);

                }

            } else {
                System.out.println("Booking Failed (No rooms available for " + type + ")");
            }
        }

        // Final state
        System.out.println("\n====== FINAL ALLOCATIONS ======");
        for (String type : roomAllocations.keySet()) {
            System.out.println(type + " → " + roomAllocations.get(type));
        }

        System.out.println("\nRemaining Inventory: " + inventory);
    }
}