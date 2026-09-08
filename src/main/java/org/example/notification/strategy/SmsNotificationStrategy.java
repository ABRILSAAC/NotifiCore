package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;
import org.example.notification.model.ReceiverModel;
import org.example.notification.model.SmsNotificationModel;

public class SmsNotificationStrategy implements  INotificationStrategy{

    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public NotificationModel buildModel(ReceiverModel receiver, String message) {
        SmsNotificationModel model = new SmsNotificationModel();
        model.setMessage(message);
        model.setReceiverPhone(receiver.getPhone());
        return model;
    }

    @Override
    public void send(NotificationModel notificationModel) {
        System.out.println("[" + getChannelName() + "] a " + notificationModel.getRecipientData()
                + ": " + notificationModel.getMessage());
    }
}
