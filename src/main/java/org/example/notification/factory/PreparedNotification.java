package org.example.notification.factory;
import org.example.notification.model.NotificationModel;
import org.example.notification.strategy.INotificationStrategy;

public record PreparedNotification(INotificationStrategy strategy, NotificationModel model) {}