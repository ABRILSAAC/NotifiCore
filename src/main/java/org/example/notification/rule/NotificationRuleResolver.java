package org.example.notification.rule;
import org.example.notification.model.EventModel;
import org.example.notification.model.ReceiverModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class NotificationRuleResolver {
    private static final String DEFAULT_ROLE = "DEFAULT";
    private static final String RULES_FILE = "notification-rules.properties";

    private final Map<String, Map<String, NotificationRule>> rulesByTypeAndRole = loadRules();

    private Map<String, Map<String, NotificationRule>> loadRules() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(RULES_FILE)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró " + RULES_FILE + " en el classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Error leyendo " + RULES_FILE, e);
        }

        Map<String, Map<String, String>> channelsByTypeAndRole = new HashMap<>();
        Map<String, Map<String, String>> messagesByTypeAndRole = new HashMap<>();

        for (String key : props.stringPropertyNames()) {
            String[] parts = key.split("\\.");
            if (parts.length != 3) {
                throw new IllegalStateException("Clave de regla inválida: " + key);
            }
            String eventType = parts[0];
            String role = parts[1];
            String field = parts[2];
            String value = props.getProperty(key);

            if (field.equals("channel")) {
                channelsByTypeAndRole.computeIfAbsent(eventType, k -> new HashMap<>()).put(role, value);
            } else if (field.equals("message")) {
                messagesByTypeAndRole.computeIfAbsent(eventType, k -> new HashMap<>()).put(role, value);
            }
        }

        Map<String, Map<String, NotificationRule>> rules = new HashMap<>();
        for (String eventType : channelsByTypeAndRole.keySet()) {
            Map<String, NotificationRule> rulesForType = new HashMap<>();
            for (String role : channelsByTypeAndRole.get(eventType).keySet()) {
                String channel = channelsByTypeAndRole.get(eventType).get(role);
                String message = messagesByTypeAndRole.get(eventType).get(role);
                rulesForType.put(role, new NotificationRule(channel, message));
            }
            rules.put(eventType, rulesForType);
        }
        return rules;
    }

    public NotificationRule resolveRule(EventModel event, ReceiverModel receiver) {
        Map<String, NotificationRule> rulesByRole = rulesByTypeAndRole.get(event.getType());
        if (rulesByRole == null) {
            throw new IllegalArgumentException("No hay reglas configuradas para el evento: " + event.getType());
        }
        String role = receiver.getRole() != null ? receiver.getRole() : DEFAULT_ROLE;
        return rulesByRole.getOrDefault(role, rulesByRole.get(DEFAULT_ROLE));
    }
}
