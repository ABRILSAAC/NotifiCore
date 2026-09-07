package org.example.notification.model;

public class EmailNotificationModel extends NotificationModel{
    private String receiverEmail;

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }


    /*private String smmtpAddress;
    private String smtpUser;
    private String smtpPassword;*/
}
