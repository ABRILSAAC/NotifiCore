package org.example.notification.strategy;
import org.example.notification.model.NotificationModel;

public class EmailNotificationStrategy implements INotificationStrategy{

    @Override
    public String getChannelName() {
        return "EMAIL";
    }

    @Override
    public void send(NotificationModel notificationModel) {
        System.out.println("[" + getChannelName() + "] a " + notificationModel.getRecipientData()
                + ": " + notificationModel.getMessage());
    }
}
