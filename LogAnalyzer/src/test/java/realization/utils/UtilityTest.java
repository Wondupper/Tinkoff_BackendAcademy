package realization.utils;

import backend.academy.realization.utils.Utility;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilityTest {

    @Test
    void parseDate() {
        // given
        String dateString = "17/May/2015:08:05:32 +0000";

        // when
        ZonedDateTime dateTime = Utility.parseDate(dateString);

        // then
        assertNotNull(dateTime);
        assertEquals(2015, dateTime.getYear());
        assertEquals(5, dateTime.getMonthValue());
        assertEquals(17, dateTime.getDayOfMonth());
        assertEquals(0, dateTime.getOffset().getTotalSeconds());
    }

    @Test
    void formatDate() {
        // given
        ZonedDateTime dateTime = ZonedDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.UTC);

        // when
        String formattedDate = Utility.formatDate(dateTime);

        // then
        assertEquals("17/May/2015:08:05:32 +0000", formattedDate);
    }

    @Test
    void matchesPattern() {
        // given
        String value = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)";
        String pattern = "Mozilla*";

        // when
        boolean matches = Utility.matchesPattern(value, pattern);

        // then
        assertTrue(matches);
    }

    @Test
    void escapeForMarkdownOrAsciiDoc() {
        // given
        String unsafeString = "Column|Data\nNewLine";

        // when
        String escapedString = Utility.escapeForMarkdownOrAsciiDoc(unsafeString);

        // then
        assertEquals("Column\\|Data NewLine", escapedString);
    }
}
