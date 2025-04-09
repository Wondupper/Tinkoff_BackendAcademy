package realization.io;

import backend.academy.realization.io.LogFileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogFileReaderTest {

    @Test
    void readLogs() throws Exception {
        // given
        Path tempLogFile = Files.createTempFile("test-log", ".log");
        Files.writeString(tempLogFile, """
            93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 200 512 "-" "Mozilla/5.0"
            217.168.17.5 - - [17/May/2015:08:06:10 +0000] "POST /api/data HTTP/1.1" 404 0 "-" "Curl/7.68.0"
            """);
        LogFileReader logFileReader = new LogFileReader();

        // when
        List<String> logLines = logFileReader.readLogs(tempLogFile.toString());

        // then
        assertEquals(2, logLines.size());
        assertTrue(logLines.get(0).contains("93.180.71.3"));
        assertTrue(logLines.get(1).contains("POST"));

        Files.delete(tempLogFile);
    }
}
