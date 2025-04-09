package backend.academy.realization.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("checkstyle:UnusedImports")
public class Utility {

    private static final DateTimeFormatter LOG_DATE_FORMAT = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );

    public static ZonedDateTime parseDate(String dateString) {
        return ZonedDateTime.parse(dateString, LOG_DATE_FORMAT);
    }

    public static String formatDate(ZonedDateTime dateTime) {
        return dateTime.format(LOG_DATE_FORMAT);
    }

    public static boolean matchesPattern(String value, String pattern) {
        String regex = pattern.replace("*", ".*").replace("?", ".");
        return Pattern.matches(regex, value);
    }

    public static String escapeForMarkdownOrAsciiDoc(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("|", "\\|").replace("\n", " ");
    }
}
