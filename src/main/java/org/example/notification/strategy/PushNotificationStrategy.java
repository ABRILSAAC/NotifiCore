package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;

public class PushNotificationStrategy implements INotificationStrategy{
    @Override
    public void send(NotificationModel notificationModel) {
        System.out.println("Notificación enviada por Push");
    }
}
