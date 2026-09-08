package org.example.notification.model;

public class WhatsappNotificationModel extends NotificationModel{
    private String whatsAppNumber;

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }

    @Override
    public String getRecipientData() {
        return whatsAppNumber;
    }
}
