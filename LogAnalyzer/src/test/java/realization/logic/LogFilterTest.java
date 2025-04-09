package realization.logic;

import backend.academy.realization.logic.LogFilter;
import backend.academy.realization.pojo.LogEntry;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogFilterTest {

    private final List<LogEntry> testLogEntries = List.of(
        new LogEntry("93.180.71.3", ZonedDateTime.parse("2024-08-31T12:00:00Z"), "GET", "/index.html", 200, 512,
            "Mozilla/5.0"),
        new LogEntry("217.168.17.5", ZonedDateTime.parse("2024-08-31T12:05:00Z"), "POST", "/api/data", 404, 0,
            "Curl/7.68.0"),
        new LogEntry("80.91.33.133", ZonedDateTime.parse("2024-08-31T12:10:00Z"), "GET", "/index.html", 200, 512,
            "Mozilla/5.0")
    );

    @Test
    void filterByTime() {
        // given
        LogFilter logFilter = new LogFilter();
        ZonedDateTime from = ZonedDateTime.parse("2024-08-31T12:05:00Z");
        ZonedDateTime to = ZonedDateTime.parse("2024-08-31T12:10:00Z");

        // when
        List<LogEntry> filteredEntries = logFilter.filterByTime(testLogEntries, from, to);

        // then
        assertEquals(2, filteredEntries.size());
        assertTrue(filteredEntries.stream().anyMatch(entry -> entry.remoteAddr().equals("217.168.17.5")));
        assertTrue(filteredEntries.stream().anyMatch(entry -> entry.remoteAddr().equals("80.91.33.133")));
    }

    @Test
    void givenLogEntries_whenFilterByField_thenReturnsFilteredEntries() {
        // given
        LogFilter logFilter = new LogFilter();
        String field = "agent";
        String value = "Mozilla*";

        // when
        List<LogEntry> filteredEntries = logFilter.filterByField(testLogEntries, field, value);

        // then
        assertEquals(2, filteredEntries.size());
        assertTrue(filteredEntries.stream().allMatch(entry -> entry.userAgent().startsWith("Mozilla")));
    }
}
