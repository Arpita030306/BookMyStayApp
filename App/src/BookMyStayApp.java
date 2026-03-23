import java.util.HashMap;
import java.util.Map;

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("        WELCOME TO BOOK MY STAY        ");
        System.out.println("=======================================");

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 0);
        inventory.put("Deluxe Room", 5);
        inventory.put("Suite", 2);

        // Room details
        Map<String, Room> roomDetails = new HashMap<>();
        roomDetails.put("Single Room", new Room("Single Room", 1000, "1 Bed, AC"));
        roomDetails.put("Double Room", new Room("Double Room", 2000, "2 Beds, AC"));
        roomDetails.put("Deluxe Room", new Room("Deluxe Room", 3000, "King Bed, AC, TV"));
        roomDetails.put("Suite", new Room("Suite", 5000, "Luxury, WiFi, TV"));

        // UC4 Search
        searchAvailableRooms(inventory, roomDetails);
    }

    public static void searchAvailableRooms(Map<String, Integer> inventory,
                                            Map<String, Room> roomDetails) {

        System.out.println("\nAvailable Rooms:");
        System.out.println("-----------------------------------");

        for (String type : inventory.keySet()) {

            int available = inventory.get(type);

            if (available > 0 && roomDetails.containsKey(type)) {

                Room room = roomDetails.get(type);

                System.out.println("Room Type : " + room.getType());
                System.out.println("Price     : ₹" + room.getPrice());
                System.out.println("Amenities : " + room.getAmenities());
                System.out.println("Available : " + available);
                System.out.println("-----------------------------------");
            }
        }
    }
}