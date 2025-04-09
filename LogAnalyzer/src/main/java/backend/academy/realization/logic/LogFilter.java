package backend.academy.realization.logic;

import backend.academy.realization.pojo.LogEntry;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogFilter {

    public List<LogEntry> filterByTime(List<LogEntry> entries, ZonedDateTime from, ZonedDateTime to) {
        return entries.stream()
            .filter(entry -> {
                boolean afterFrom = (from == null) || !entry.timeLocal().isBefore(from);
                boolean beforeTo = (to == null) || !entry.timeLocal().isAfter(to);
                return afterFrom && beforeTo;
            })
            .collect(Collectors.toList());
    }

    public List<LogEntry> filterByField(List<LogEntry> entries, String field, String value) {
        Predicate<LogEntry> predicate = switch (field.toLowerCase()) {
            case "agent" -> entry -> matchesPattern(entry.userAgent(), value);
            case "method" -> entry -> matchesPattern(entry.method(), value);
            case "resource" -> entry -> matchesPattern(entry.resource(), value);
            case "status" -> entry -> String.valueOf(entry.status()).equals(value);
            case "ip", "remoteaddr" -> entry -> matchesPattern(entry.remoteAddr(), value);
            default -> throw new IllegalArgumentException("Неизвестное поле для фильтрации: " + field);
        };

        return entries.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }

    private boolean matchesPattern(String fieldValue, String pattern) {
        String regex = pattern.replace("*", ".*");
        return fieldValue.matches(regex);
    }
}
