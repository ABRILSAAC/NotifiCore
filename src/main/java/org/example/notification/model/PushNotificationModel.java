package org.example.notification.model;

public class PushNotificationModel extends NotificationModel{
    private String receiverDeviceId;

    public String getReceiverDeviceId() {
        return receiverDeviceId;
    }

    public void setReceiverDeviceId(String receiverDeviceId) {
        this.receiverDeviceId = receiverDeviceId;
    }
}
