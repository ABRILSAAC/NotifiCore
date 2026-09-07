package org.example.notification.consumer;

import org.example.notification.factory.NotificationStrategyFactory;
import org.example.notification.model.EventModel;
import org.example.notification.model.ReceiverModel;

public class NotificationConsumer {
    private final EventModel event;
    private final ReceiverModel receiver;

    public NotificationConsumer(EventModel event, ReceiverModel receiver) {
        this.event = event;
        this.receiver = receiver;
    }

    public void startNotificationFromConsumer(){
        new  NotificationStrategyFactory(event, receiver).startNotification();
    }
}