package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;
import org.example.notification.model.ReceiverModel;

public interface INotificationStrategy {
    String getChannelName();
    NotificationModel buildModel(ReceiverModel receiver, String message);
    void send(NotificationModel notificationModel);
}
