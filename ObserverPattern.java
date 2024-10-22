public class ObserverPattern {
    public static void main(String[] args) {
        // Creating the subject
        Subject subject = new Subject();

        // Creating and attaching observers
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);
        HexaObserver hexaObserver = new HexaObserver(subject);

        // Changing state
        System.out.println("First state change: 15");
        subject.setState(15);

        // Deactivating the BinaryObserver
        System.out.println("Deactivating BinaryObserver");
        binaryObserver.setActive(false); // Set binary observer as inactive

        // Changing state again, only active observers will be notified
        System.out.println("Second state change: 10");
        subject.setState(10);

        // Reactivating the BinaryObserver
        System.out.println("Reactivating BinaryObserver");
        binaryObserver.setActive(true); // Reactivate binary observer

        // Changing state again after reactivating BinaryObserver
        System.out.println("Third state change: 5");
        subject.setState(5);
    }}