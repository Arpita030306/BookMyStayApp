public class Room {

    private String type;
    private int price;
    private String amenities;

    public Room(String type, int price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}