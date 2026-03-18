/**
 * SuiteRoom represents a high-end luxury room.
 * Extends the abstract Room class.
 */
public class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 350.0);
    }

    @Override
    public void displayFeatures() {
        System.out.println("Features: King Bed, Living Area, Private Balcony, Bathtub.");
    }
}