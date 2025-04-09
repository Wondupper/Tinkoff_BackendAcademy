package backend.academy.realization.pojo;

import java.util.Map;

public record AnalysisResult(int totalRequests, Map<String, Long> topResources,
                             Map<Integer, Long> statusCodeFrequency, double averageResponseSize,
                             long percentile95ResponseSize, long uniqueUsers, Map<String, Long> topMethods) {
}
