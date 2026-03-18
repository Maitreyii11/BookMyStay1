
public class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 180.0);
    }
    @Override
    public void displayFeatures() {
        System.out.println("Features: 2 Queen Beds, Wi-Fi, Mini-fridge.");
    }
}