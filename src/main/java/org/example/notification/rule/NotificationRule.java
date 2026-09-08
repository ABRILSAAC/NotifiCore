package org.example.notification.rule;
import java.util.List;

public record NotificationRule(List<String> strategies, String message) {}