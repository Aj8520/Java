package c05_polymorphism;

/** Real-life example: the same send() call, three different delivery channels. */
public interface Notifier {
    String send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public String send(String message) {
        return "Email: " + message;
    }
}

class SmsNotifier implements Notifier {
    @Override
    public String send(String message) {
        String text = message.length() > 20 ? message.substring(0, 20) + "..." : message;
        return "SMS: " + text; // SMS has a length limit
    }
}

class PushNotifier implements Notifier {
    @Override
    public String send(String message) {
        return "Push: " + message.toUpperCase();
    }
}
