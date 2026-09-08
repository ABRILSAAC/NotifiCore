package org.example.notification.strategy;
import org.example.notification.model.EmailNotificationModel;
import org.example.notification.model.NotificationModel;
import org.example.notification.model.ReceiverModel;

public class EmailNotificationStrategy implements INotificationStrategy{

    @Override
    public String getChannelName() {
        return "EMAIL";
    }

    @Override
    public NotificationModel buildModel(ReceiverModel receiver, String message) {
        EmailNotificationModel model = new EmailNotificationModel();
        model.setMessage(message);
        model.setReceiverEmail(receiver.getEmail());
        return model;
    }

    @Override
    public void send(NotificationModel notificationModel) {
        System.out.println("[" + getChannelName() + "] a " + notificationModel.getRecipientData()
                + ": " + notificationModel.getMessage());
    }
}
