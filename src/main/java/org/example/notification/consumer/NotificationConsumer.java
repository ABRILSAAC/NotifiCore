package org.example.notification.consumer;

import org.example.notification.factory.NotificationStrategyFactory;
import org.example.notification.factory.PreparedNotification;
import org.example.notification.model.EventModel;
import org.example.notification.model.NotificationModel;
import org.example.notification.model.ReceiverModel;

public class NotificationConsumer {
    private final EventModel event;
    private final ReceiverModel receiver;
    private final NotificationStrategyFactory factory = new NotificationStrategyFactory();

    public NotificationConsumer(EventModel event, ReceiverModel receiver) {
        this.event = event;
        this.receiver = receiver;
    }

    public void dispatchNotification() {
        PreparedNotification prepared = factory.create(event, receiver);
        NotificationModel notification = prepared.model();
        prepared.strategy().send(notification);
    }
}