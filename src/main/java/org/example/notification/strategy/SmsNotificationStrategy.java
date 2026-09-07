package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;

public class SmsNotificationStrategy implements  INotificationStrategy{

    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public void send(NotificationModel notificationModel) {
        System.out.println("Mensaje: " + notificationModel.getMessage());
    }
}
