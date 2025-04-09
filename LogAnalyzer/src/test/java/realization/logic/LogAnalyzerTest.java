package realization.logic;

import backend.academy.realization.logic.LogAnalyzer;
import backend.academy.realization.pojo.AnalysisResult;
import backend.academy.realization.pojo.LogEntry;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogAnalyzerTest {

    private final List<LogEntry> testLogEntries = List.of(
        new LogEntry("93.180.71.3", ZonedDateTime.parse("2024-08-31T12:00:00Z"), "GET", "/index.html", 200, 512,
            "Mozilla/5.0"),
        new LogEntry("217.168.17.5", ZonedDateTime.parse("2024-08-31T12:05:00Z"), "POST", "/api/data", 404, 0,
            "Curl/7.68.0"),
        new LogEntry("80.91.33.133", ZonedDateTime.parse("2024-08-31T12:10:00Z"), "GET", "/index.html", 200, 512,
            "Mozilla/5.0")
    );

    @Test
    void countRequests() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        assertEquals(3, logAnalyzer.countRequests(testLogEntries));
    }

    @Test
    void topResources() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        Map<String, Long> topResources = logAnalyzer.topResources(testLogEntries, 1);
        assertEquals(1, topResources.size());
        assertEquals(2, topResources.get("/index.html"));
    }

    @Test
    void statusCodeFrequency() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        Map<Integer, Long> statusCodeFrequency = logAnalyzer.statusCodeFrequency(testLogEntries);
        assertEquals(2, statusCodeFrequency.getOrDefault(200, 0L));
        assertEquals(1, statusCodeFrequency.getOrDefault(404, 0L));
    }

    @Test
    void calculateAverageResponseSize() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        double averageSize = logAnalyzer.calculateAverageResponseSize(testLogEntries);
        assertEquals(341.33, averageSize, 0.01);
    }

    @Test
    void calculatePercentileResponseSize() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        long percentile95 = logAnalyzer.calculatePercentileResponseSize(testLogEntries, 95);
        assertEquals(512, percentile95);
    }

    @Test
    void analyze() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        AnalysisResult result = logAnalyzer.analyze(testLogEntries);

        assertEquals(3, result.totalRequests());
        assertEquals(2, result.topResources().get("/index.html"));
        assertEquals(2, result.statusCodeFrequency().get(200));
        assertEquals(341.33, result.averageResponseSize(), 0.01);
        assertEquals(512, result.percentile95ResponseSize());
    }
}
