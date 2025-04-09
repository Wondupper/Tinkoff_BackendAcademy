package backend.academy.realization.pojo;

import java.time.ZonedDateTime;

public record LogEntry(String remoteAddr, ZonedDateTime timeLocal, String method,
                       String resource, int status, long bodyBytesSent, String userAgent) {
}
