package org.example.notification.rule;

import org.example.notification.model.EventModel;
import org.example.notification.model.ReceiverModel;
import java.util.Map;

public class NotificationRuleResolver {
    private static final String DEFAULT_ROLE = "DEFAULT";

    private final Map<String, Map<String, NotificationRule>> rulesByTypeAndRole = Map.of(
            "RETRASO_MENOR", Map.of(
                    "CLIENTE", new NotificationRule("SMS", "Tu pedido tiene un retraso menor"),
                    DEFAULT_ROLE, new NotificationRule("EMAIL", "Retraso menor reportado.")
            ),
            "RETRASO_CRITICO", Map.of(
                    "CLIENTE", new NotificationRule("SMS", "Retraso crítico en tu pedido."),
                    "REPARTIDOR", new NotificationRule("PUSH", "Reasignación posible por retraso crítico."),
                    DEFAULT_ROLE, new NotificationRule("EMAIL", "Alerta de retraso crítico.")
            ),
            "ENTREGA_FALLIDA", Map.of(
                    DEFAULT_ROLE, new NotificationRule("SMS", "Tu entrega no pudo completarse.")
            )
    );

    public NotificationRule resolveRule(EventModel event, ReceiverModel receiver) {
        Map<String, NotificationRule> rulesByRole = rulesByTypeAndRole.get(event.getType());
        if (rulesByRole == null) {
            throw new IllegalArgumentException("No hay reglas configuradas para el evento: " + event.getType());
        }
        String role = receiver.getRole() != null ? receiver.getRole() : DEFAULT_ROLE;
        return rulesByRole.getOrDefault(role, rulesByRole.get(DEFAULT_ROLE));
    }
}
