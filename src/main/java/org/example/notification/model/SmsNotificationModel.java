package org.example.notification.model;

public class SmsNotificationModel extends NotificationModel{
    private String receiverPhone;

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    @Override
    public String getRecipientData() {
        return receiverPhone;
    }
}
