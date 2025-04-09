package realization.logic;

import backend.academy.realization.logic.CommandLineArgsParser;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandLineArgsParserTest {

    @Test
    void parse() {
        // given
        String[] args = {
            "--path", "logs/2024*",
            "--from", "2024-08-31T00:00:00",
            "--to", "2024-08-31T23:59:59",
            "--format", "markdown"
        };

        // when
        CommandLineArgsParser parsedArgs = CommandLineArgsParser.parse(args);

        // then
        assertEquals("logs/2024*", parsedArgs.path());
        assertEquals(ZonedDateTime.parse("2024-08-31T00:00:00Z"), parsedArgs.from());
        assertEquals(ZonedDateTime.parse("2024-08-31T23:59:59Z"), parsedArgs.to());
        assertEquals("markdown", parsedArgs.format());
    }

}
