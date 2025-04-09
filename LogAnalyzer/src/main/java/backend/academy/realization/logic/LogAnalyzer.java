package backend.academy.realization.logic;

import backend.academy.realization.pojo.AnalysisResult;
import backend.academy.realization.pojo.LogEntry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private static final double FULL_PERCENT = 100.0;
    private static final int COUNT_TOP_RESOURCES = 5;
    private static final int PERCENTILE = 95;

    public int countRequests(List<LogEntry> entries) {
        return entries.size();
    }

    public Map<String, Long> topResources(List<LogEntry> entries, int limit) {
        return entries.stream()
            .collect(Collectors.groupingBy(LogEntry::resource, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(limit)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<Integer, Long> statusCodeFrequency(List<LogEntry> entries) {
        return entries.stream()
            .collect(Collectors.groupingBy(LogEntry::status, Collectors.counting()));
    }

    public double calculateAverageResponseSize(List<LogEntry> entries) {
        return entries.stream()
            .mapToLong(LogEntry::bodyBytesSent)
            .average()
            .orElse(0.0);
    }

    public long calculatePercentileResponseSize(List<LogEntry> entries, int percentile) {
        List<Long> sortedSizes = entries.stream()
            .map(LogEntry::bodyBytesSent)
            .sorted()
            .toList();
        if (sortedSizes.isEmpty()) {
            return 0;
        }
        int index = (int) Math.ceil(percentile / FULL_PERCENT * sortedSizes.size()) - 1;
        return sortedSizes.get(index);
    }

    public AnalysisResult analyze(List<LogEntry> entries) {
        int totalRequests = countRequests(entries);
        Map<String, Long> topResources = topResources(entries, COUNT_TOP_RESOURCES);
        Map<Integer, Long> statusCodeFrequency = statusCodeFrequency(entries);
        double averageResponseSize = calculateAverageResponseSize(entries);
        long percentile95ResponseSize = calculatePercentileResponseSize(entries, PERCENTILE);
        long uniqueUsers = entries.stream()
            .map(LogEntry::remoteAddr)
            .distinct()
            .count();

        Map<String, Long> topMethods = entries.stream()
            .collect(Collectors.groupingBy(LogEntry::method, Collectors.counting()));

        return new AnalysisResult(
            totalRequests,
            topResources,
            statusCodeFrequency,
            averageResponseSize,
            percentile95ResponseSize,
            uniqueUsers,
            topMethods
        );
    }
}
