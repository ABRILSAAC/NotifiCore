package org.example.notification.factory;

import org.example.notification.model.*;
import org.example.notification.rule.NotificationRule;
import org.example.notification.rule.NotificationRuleResolver;
import org.example.notification.strategy.EmailNotificationStrategy;
import org.example.notification.strategy.INotificationStrategy;
import org.example.notification.strategy.PushNotificationStrategy;
import org.example.notification.strategy.SmsNotificationStrategy;
import java.util.Map;

public class NotificationStrategyFactory {
    private final NotificationRuleResolver ruleResolver = new NotificationRuleResolver();

    private final Map<String, INotificationStrategy> strategies = Map.of(
        "EMAIL", new EmailNotificationStrategy(),
        "SMS", new SmsNotificationStrategy(),
        "PUSH", new PushNotificationStrategy()
    );

    public PreparedNotification create(EventModel event, ReceiverModel receiver) {
        NotificationRule rule = ruleResolver.resolveRule(event, receiver);
        INotificationStrategy strategy = strategies.get(rule.strategy());
        NotificationModel model = buildModel(rule, receiver);

        if (!model.isValid()){
            throw new IllegalStateException("El destinatario no tiene el dato requerido para el canal " + strategy.getChannelName());
        }
        return new PreparedNotification(strategy, model);
    }

    private NotificationModel buildModel(NotificationRule rule, ReceiverModel receiver) {
        return switch (rule.strategy()) {
            case "SMS" -> {
                SmsNotificationModel m = new SmsNotificationModel();
                m.setMessage(rule.message());
                m.setReceiverPhone(receiver.getPhone());
                yield m;
            }
            case "PUSH" -> {
                PushNotificationModel m = new PushNotificationModel();
                m.setMessage(rule.message());
                m.setReceiverDeviceId(receiver.getDeviceId());
                yield m;
            }
            case "EMAIL" -> {
                EmailNotificationModel m = new EmailNotificationModel();
                m.setMessage(rule.message());
                m.setReceiverEmail(receiver.getEmail());
                yield m;
            }
            default -> throw new IllegalStateException("Canal desconocido: " + rule.strategy());
        };
    }
}
