import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class EventManager {
    private HashMap<String, List<EventListener>> listeners = new HashMap<>();

    public void subscribe(String eventType, EventListener listener) {
        listeners.putIfAbsent(eventType, new ArrayList<>());
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        if (listeners.containsKey(eventType)) {
            listeners.get(eventType).remove(listener);
        }
    }

    public void notify(String eventType, String data) {
        if (listeners.containsKey(eventType)) {
            for (EventListener listener : listeners.get(eventType)) {
                listener.update(data);
            }
        }
    }
}

class Editor {
    public EventManager events = new EventManager();
    private File file;

    public void openFile(String path) {
        this.file = new File(path);
        events.notify("open", file.getName());
    }

    public void saveFile() {
        file.write();
        events.notify("save", file.getName());
    }
}

interface EventListener {
    void update(String filename);
}

class LoggingListener implements EventListener {
    private File log;
    private String message;

    public LoggingListener(String logFilename, String message) {
        this.log = new File(logFilename);
        this.message = message;
    }

    public void update(String filename) {
        log.write(String.format(message, filename));
    }
}

class EmailAlertsListener implements EventListener {
    private String email;
    private String message;

    public EmailAlertsListener(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public void update(String filename) {
        system.email(email, String.format(message, filename));
    }
}

class SmsAlertsListener implements EventListener {
    private String phoneNumber;
    private String message;

    public SmsAlertsListener(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public void update(String filename) {
        if (message.length() > 160) {
            System.out.println("Define valid default SMS; length exceeds 160 characters.");
        } else {
            System.sendSms(phoneNumber, String.format(message, filename));
        }
    }
}

class Application {
    public void config() {
        Editor editor = new Editor();

        LoggingListener logger = new LoggingListener("/path/to/log.txt", "Someone has opened the file: %s");
        editor.events.subscribe("open", logger);

        EmailAlertsListener emailAlerts = new EmailAlertsListener("admin@example.com", "Someone has changed the file: %s");
        editor.events.subscribe("save", emailAlerts);

        SmsAlertsListener smsAlerts = new SmsAlertsListener("1234567890", "File has been updated: %s");
        editor.events.subscribe("save", smsAlerts);
    }
}
