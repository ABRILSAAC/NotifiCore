package org.example.notification.model;

public class SmsNotificationModel extends NotificationModel{
    private String receiverPhone;

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
