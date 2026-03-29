public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("===== UC9: Error Handling =====");

        String roomType = "Luxury"; // try invalid
        int availableRooms = 2;

        try {
            // Validation call
            BookingValidator.validate(roomType, availableRooms);

            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {

            // Graceful failure
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System continues safely...");
    }
}