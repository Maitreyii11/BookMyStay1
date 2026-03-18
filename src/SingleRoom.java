
public class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 100.0);
    }
    @Override
    public void displayFeatures() {
        System.out.println("Features: 1 Twin Bed, Wi-Fi, Desk.");
    }
}