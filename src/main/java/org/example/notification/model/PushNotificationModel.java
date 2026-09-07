package org.example.notification.model;

public class PushNotificationModel extends NotificationModel{
    private String receiverDeviceId;

    public void setReceiverDeviceId(String receiverDeviceId) {
        this.receiverDeviceId = receiverDeviceId;
    }

    @Override
    public String getRecipientData() {
        return receiverDeviceId;
    }
}
