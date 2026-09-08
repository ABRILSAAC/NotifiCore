package org.example.notification.factory;

import org.example.notification.model.*;
import org.example.notification.rule.NotificationRule;
import org.example.notification.rule.NotificationRuleResolver;
import org.example.notification.strategy.*;

import java.util.*;

public class NotificationStrategyFactory {
    private final NotificationRuleResolver ruleResolver = new NotificationRuleResolver();
    private final Map<String, INotificationStrategy> strategies = loadStrategies();

    private Map<String, INotificationStrategy> loadStrategies() {
        Map<String, INotificationStrategy> map = new HashMap<>();
        for (INotificationStrategy strategy : ServiceLoader.load(INotificationStrategy.class)) {
            map.put(strategy.getChannelName(), strategy);
        }
        return map;
    }

    public List<PreparedNotification> create(EventModel event, ReceiverModel receiver) {
        NotificationRule rule = ruleResolver.resolveRule(event, receiver);
        List<PreparedNotification> prepared = new ArrayList<>();

        for (String channel : rule.strategies()) {
            INotificationStrategy strategy = strategies.get(channel);
            if (strategy == null) {
                throw new IllegalStateException("No hay estrategia registrada para el canal: " + channel);
            }
            NotificationModel model = strategy.buildModel(receiver, rule.message());
            if (model.isValid()) {
                prepared.add(new PreparedNotification(strategy, model));
            }
            else{
                System.out.println("El destinatario no tiene datos válidos para enviar notificación por el canal: " + channel);
            }
        }

        if (prepared.isEmpty()) {
            throw new IllegalStateException(
                    "El destinatario no tiene datos válidos para enviar notificación por ninguno de los canales: " + rule.strategies()
            );
        }
        return prepared;

    }
}
