package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;

public interface INotificationStrategy {
    String getChannelName();
    void send(NotificationModel notificationModel);
}
