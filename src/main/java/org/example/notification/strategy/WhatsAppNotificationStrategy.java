package org.example.notification.strategy;

import org.example.notification.model.NotificationModel;
import org.example.notification.model.ReceiverModel;
import org.example.notification.model.WhatsappNotificationModel;

public class WhatsAppNotificationStrategy implements  INotificationStrategy{

    @Override
    public String getChannelName() {
        return "WHATSAPP";
    }

    @Override
    public NotificationModel buildModel(ReceiverModel receiver, String message) {
        WhatsappNotificationModel model = new WhatsappNotificationModel();
        model.setMessage(message);
        model.setWhatsAppNumber(receiver.getPhone());
        return model;
    }

    @Override
    public void send(NotificationModel notificationModel) {
        System.out.println("[" + getChannelName() + "] a " + notificationModel.getRecipientData()
                + ": " + notificationModel.getMessage());
    }
}
