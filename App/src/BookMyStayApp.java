import java.util.HashMap;
import java.util.Map;

public class BookMyStayApp {

    public static void main(String[] args) {

        // UC1 - Welcome Message
        System.out.println("=======================================");
        System.out.println("        WELCOME TO BOOK MY STAY        ");
        System.out.println("=======================================");

        // UC2 + UC3 - Centralized Room Inventory
        Map<String, Integer> roomInventory = new HashMap<>();

        roomInventory.put("Single Room", 10);
        roomInventory.put("Double Room", 8);
        roomInventory.put("Deluxe Room", 5);
        roomInventory.put("Suite", 2);

        System.out.println("\nCurrent Room Availability:");
        System.out.println("----------------------------");

        for (Map.Entry<String, Integer> room : roomInventory.entrySet()) {
            System.out.println(room.getKey() + " : " + room.getValue() + " rooms available");
        }

        System.out.println("\nBooking a Double Room...");

        String roomType = "Double Room";

        // UC3 - Booking logic
        if (roomInventory.containsKey(roomType) && roomInventory.get(roomType) > 0) {

            int remainingRooms = roomInventory.get(roomType) - 1;
            roomInventory.put(roomType, remainingRooms);

            System.out.println("Booking successful!");
        } else {
            System.out.println("Sorry! Room not available.");
        }

        System.out.println("\nUpdated Room Availability:");
        System.out.println("----------------------------");

        for (Map.Entry<String, Integer> room : roomInventory.entrySet()) {
            System.out.println(room.getKey() + " : " + room.getValue() + " rooms available");
        }
    }
}