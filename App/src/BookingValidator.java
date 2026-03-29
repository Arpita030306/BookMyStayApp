public class BookingValidator {

    public static void validate(String roomType, int availableRooms)
            throws InvalidBookingException {

        // Check empty input
        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }

        // Check valid room types
        if (!(roomType.equals("Standard") ||
                roomType.equals("Deluxe") ||
                roomType.equals("Suite"))) {

            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Check availability
        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available");
        }
    }
}