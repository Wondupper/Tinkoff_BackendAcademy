package realization.logic;

import backend.academy.realization.logic.LogParser;
import backend.academy.realization.pojo.LogEntry;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogParserTest {

    @Test
    void parse() {
        // given
        String logLine = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] " +
            "\"GET /downloads/product_1 HTTP/1.1\" 200 512 \"-\" \"Mozilla/5.0\"";
        LogParser logParser = new LogParser();

        // when
        LogEntry logEntry = logParser.parse(logLine);

        // then
        assertNotNull(logEntry);
        assertEquals("93.180.71.3", logEntry.remoteAddr());
        assertEquals("/downloads/product_1", logEntry.resource());
        assertEquals(200, logEntry.status());
        assertEquals(512, logEntry.bodyBytesSent());
        assertEquals("Mozilla/5.0", logEntry.userAgent());

        ZonedDateTime expectedTime = ZonedDateTime.parse("2015-05-17T08:05:32+00:00");
        assertEquals(expectedTime.toInstant(), logEntry.timeLocal().toInstant());
    }
}
