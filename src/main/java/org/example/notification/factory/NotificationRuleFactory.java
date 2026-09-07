package org.example.notification.factory;

public class NotificationRuleFactory {
    private final String strategy;
    private final String message;

    public NotificationRuleFactory(String strategy, String message) {
        this.strategy = strategy;
        this.message = message;
    }

    public String getStrategy() {
        return strategy;
    }

    public String getMessage() {
        return message;
    }
}
