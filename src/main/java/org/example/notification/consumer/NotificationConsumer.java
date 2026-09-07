package org.example.notification.consumer;

import org.example.notification.factory.NotificationStrategyFactory;
import org.example.notification.model.EventModel;
import org.example.notification.model.ReceiverModel;

public class NotificationConsumer {
    static void main() {
        EventModel event = new EventModel();
        ReceiverModel receiver = new ReceiverModel();

        event.setType("RETRASO_MENOR");
        receiver.setRole("CLIENTE");
        receiver.setPhone("123456789");
        new NotificationStrategyFactory(event, receiver).startNotification();
    }
}