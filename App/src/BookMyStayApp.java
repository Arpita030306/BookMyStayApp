import java.util.*;

// Reservation class (represents booking request)
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

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   BOOKING REQUEST QUEUE (UC5) ");
        System.out.println("=======================================");

        // Queue for booking requests (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Adding booking requests (no allocation here)
        bookingQueue.add(new Reservation("Arpita", "Single Room"));
        bookingQueue.add(new Reservation("Rahul", "Double Room"));
        bookingQueue.add(new Reservation("Sneha", "Deluxe Room"));
        bookingQueue.add(new Reservation("Amit", "Suite"));

        System.out.println("\nBooking Requests Added to Queue:\n");

        // Display queue (FIFO order)
        for (Reservationgit  r : bookingQueue) {
            System.out.println("Guest: " + r.getGuestName() +
                    " | Requested Room: " + r.getRoomType());
        }

        System.out.println("\n(All requests are stored in arrival order)");
        System.out.println("No rooms allocated yet (Read-only queue)");
    }
}