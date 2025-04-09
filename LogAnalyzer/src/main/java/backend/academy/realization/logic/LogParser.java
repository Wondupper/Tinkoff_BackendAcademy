package backend.academy.realization.logic;

import backend.academy.realization.pojo.LogEntry;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(?<remoteAddr>\\S+) - \\S+ \\[(?<timeLocal>[^\\]]+)] "
            + "\"(?<method>[A-Z]+) (?<resource>\\S+) \\S+\" "
            + "(?<status>\\d{3}) (?<bodyBytesSent>\\d+) "
            + "\"(?<httpReferer>[^\"]*)\" \"(?<userAgent>[^\"]*)\"$"
    );

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );

    public LogEntry parse(String logLine) {
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if (!matcher.matches()) {
            return null;
        }
        String remoteAddr = matcher.group("remoteAddr");
        ZonedDateTime timeLocal = ZonedDateTime.parse(matcher.group("timeLocal"), TIME_FORMATTER);
        String method = matcher.group("method");
        String resource = matcher.group("resource");
        int status = Integer.parseInt(matcher.group("status"));
        long bodyBytesSent = Long.parseLong(matcher.group("bodyBytesSent"));
        String userAgent = matcher.group("userAgent");
        return new LogEntry(remoteAddr, timeLocal, method, resource, status, bodyBytesSent, userAgent);
    }
}
