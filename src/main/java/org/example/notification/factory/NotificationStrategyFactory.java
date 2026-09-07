package org.example.notification.factory;

import org.example.notification.model.*;
import org.example.notification.strategy.EmailNotificationStrategy;
import org.example.notification.strategy.INotificationStrategy;
import org.example.notification.strategy.PushNotificationStrategy;
import org.example.notification.strategy.SmsNotificationStrategy;

import java.util.Map;

public class NotificationStrategyFactory {
    private static final String DEFAULT_ROLE = "DEFAULT";

    private final EventModel event;
    private final ReceiverModel receiver;

    public NotificationStrategyFactory(EventModel event, ReceiverModel receiver) {
        this.event = event;
        this.receiver = receiver;
    }

    private final Map<String, INotificationStrategy> strategies = Map.of(
        "EMAIL", new EmailNotificationStrategy(),
        "SMS", new SmsNotificationStrategy(),
        "PUSH", new PushNotificationStrategy()
    );

    private final Map<String, Map<String, NotificationRuleFactory>> rulesByTypeAndRole = Map.of(
            "RETRASO_MENOR", Map.of(
                "CLIENTE", new NotificationRuleFactory("SMS", "Tu pedido tiene un retraso menor"),
                    DEFAULT_ROLE, new NotificationRuleFactory("EMAIL", "Retraso menor reportado.")
            ),
            "RETRASO_CRITICO", Map.of(
                "CLIENTE", new NotificationRuleFactory("SMS", "Retraso crítico en tu pedido."),
                "REPARTIDOR", new NotificationRuleFactory("PUSH", "Reasignación posible por retraso crítico."),
                    DEFAULT_ROLE, new NotificationRuleFactory("EMAIL", "Alerta de retraso crítico.")
            ),
            "ENTREGA_FALLIDA", Map.of(
                    DEFAULT_ROLE, new NotificationRuleFactory("SMS", "Tu entrega no pudo completarse.")
            )
    );

    public void startNotification() {
        NotificationRuleFactory rule = resolveRule();
        INotificationStrategy strategy = strategies.get(rule.getStrategy());
        System.out.println("Strategy de envío: " + strategy.getChannelName());
        NotificationModel model = buildModel(rule.getStrategy(), rule.getMessage());
        strategy.send(model);
    }

    private NotificationRuleFactory resolveRule() {
        Map<String, NotificationRuleFactory> rulesByRole = rulesByTypeAndRole.get(event.getType());
        if (rulesByRole == null) {
            throw new IllegalArgumentException("No hay reglas configuradas para el evento: " + event.getType());
        }
        String role = receiver.getRole() != null ? receiver.getRole() : DEFAULT_ROLE;
        return rulesByRole.getOrDefault(role, rulesByRole.get(DEFAULT_ROLE));
    }

    private NotificationModel buildModel(String strategyKey, String message) {
        return switch (strategyKey) {
            case "SMS" -> {
                SmsNotificationModel m = new SmsNotificationModel();
                m.setMessage(message);
                m.setReceiverPhone(receiver.getPhone());
                yield m;
            }
            case "PUSH" -> {
                PushNotificationModel m = new PushNotificationModel();
                m.setMessage(message);
                m.setReceiverDeviceId(receiver.getDeviceId());
                yield m;
            }
            case "EMAIL" -> {
                EmailNotificationModel m = new EmailNotificationModel();
                m.setMessage(message);
                m.setReceiverEmail(receiver.getEmail());
                yield m;
            }
            default -> throw new IllegalStateException("Canal desconocido: " + strategyKey);
        };
    }
}
