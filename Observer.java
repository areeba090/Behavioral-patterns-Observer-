import java.util.ArrayList;
import java.util.List;

// Observer abstract class
public abstract class Observer {
    protected Subject subject;
    protected boolean active = true;

    public void setActive(boolean active) {
        this.active = active;
    }

    public abstract void update();
}

// Subject class
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    // Attach an observer
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // Detach an observer
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    // Notify only active observers
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            if (observer.active) { // Only notify active observers
                observer.update();
            }
        }
    }
}

// BinaryObserver class
class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

// OctalObserver class
class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}

// HexaObserver class
class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}

// Demo class to test the observer pattern


