package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;

public interface INotificationStrategy {
    public void send(NotificationModel notificationModel);
}
