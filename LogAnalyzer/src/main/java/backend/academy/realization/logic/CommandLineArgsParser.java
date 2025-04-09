package backend.academy.realization.logic;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("checkstyle:ModifiedControlVariable")
public class CommandLineArgsParser {
    private String path;
    private ZonedDateTime from;
    private ZonedDateTime to;
    private String format;
    private String filterField;
    private String filterValue;

    public static CommandLineArgsParser parse(String[] args) {
        CommandLineArgsParser parsedArgs = new CommandLineArgsParser();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "--path":
                    parsedArgs.path(args[++i]);
                    break;
                case "--from":
                    parsedArgs.from(ZonedDateTime.parse(args[++i] + "Z"));
                    break;
                case "--to":
                    parsedArgs.to(ZonedDateTime.parse(args[++i] + "Z"));
                    break;
                case "--format":
                    parsedArgs.format(args[++i]);
                    break;
                case "--filter-field":
                    parsedArgs.filterField(args[++i]);
                    break;
                case "--filter-value":
                    parsedArgs.filterValue(args[++i]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown argument: " + arg);
            }
        }
        return parsedArgs;
    }
}
