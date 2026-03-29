import java.util.*;

class CancellationService {

    private Map<String, Integer> inventory;
    private Map<String, ReservationUC10> confirmedBookings;
    private Stack<String> rollbackStack;

    public CancellationService(Map<String, Integer> inventory,
                               Map<String, ReservationUC10> confirmedBookings,
                               Stack<String> rollbackStack) {

        this.inventory = inventory;
        this.confirmedBookings = confirmedBookings;
        this.rollbackStack = rollbackStack;
    }

    public void cancelBooking(String reservationId) {

        System.out.println("\nProcessing cancellation for: " + reservationId);

        // VALIDATION
        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation not found!");
            return;
        }

        ReservationUC10 r = confirmedBookings.get(reservationId);

        // STEP 1: Push to stack
        rollbackStack.push(r.getRoomId());

        // STEP 2: Restore inventory
        inventory.put(r.getRoomType(), inventory.get(r.getRoomType()) + 1);

        // STEP 3: Remove booking
        confirmedBookings.remove(reservationId);

        System.out.println("Cancellation Successful!");
        System.out.println("Released Room ID: " + r.getRoomId());
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("===== UC10 Booking Cancellation =====");

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Deluxe", 0);

        // Confirmed bookings
        Map<String, ReservationUC10> confirmedBookings = new HashMap<>();

        confirmedBookings.put("RES-101",
                new ReservationUC10("RES-101", "Single", "S-1"));

        confirmedBookings.put("RES-102",
                new ReservationUC10("RES-102", "Deluxe", "D-1"));

        // Stack
        Stack<String> rollbackStack = new Stack<>();

        CancellationService service =
                new CancellationService(inventory, confirmedBookings, rollbackStack);

        // Cancel booking
        service.cancelBooking("RES-102");

        // Final state
        System.out.println("\nRemaining Bookings: " + confirmedBookings.keySet());
        System.out.println("Updated Inventory: " + inventory);
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}