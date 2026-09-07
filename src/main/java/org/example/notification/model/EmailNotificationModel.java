package org.example.notification.model;

public class EmailNotificationModel extends NotificationModel{
    private String receiverEmail;

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    @Override
    public String getRecipientData() {
        return receiverEmail;
    }


    /*private String smmtpAddress;
    private String smtpUser;
    private String smtpPassword;*/
}
