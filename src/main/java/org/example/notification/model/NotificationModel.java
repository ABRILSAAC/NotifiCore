package org.example.notification.model;

public abstract class NotificationModel {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract String getRecipientData();

    public boolean isValid() {
        return getRecipientData() != null && !getRecipientData().isBlank();
    }
}
