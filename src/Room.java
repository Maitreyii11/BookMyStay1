/**
 * Abstract class representing a generic Room.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class Room {
    private String roomType;
    private double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() { return roomType; }
    public double getPrice() { return price; }

    public abstract void displayFeatures();

    @Override
    public String toString() {
        return "Category: " + roomType + " | Price per Night: $" + price;
    }
}