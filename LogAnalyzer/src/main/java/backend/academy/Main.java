package backend.academy;

import backend.academy.realization.io.LogFileReader;
import backend.academy.realization.io.ReportGenerator;
import backend.academy.realization.logic.CommandLineArgsParser;
import backend.academy.realization.logic.LogAnalyzer;
import backend.academy.realization.logic.LogFilter;
import backend.academy.realization.logic.LogParser;
import backend.academy.realization.pojo.AnalysisResult;
import backend.academy.realization.pojo.LogEntry;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        CommandLineArgsParser parsedArgs = CommandLineArgsParser.parse(args);
        LogFileReader logFileReader = new LogFileReader();
        LogParser logParser = new LogParser();
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        LogFilter logFilter = new LogFilter();
        ReportGenerator reportGenerator = new ReportGenerator();
        List<String> rawLogs = logFileReader.readLogs(parsedArgs.path());
        List<LogEntry> logEntries = rawLogs.stream()
            .map(logParser::parse)
            .filter(Objects::nonNull)
            .toList();
        if (parsedArgs.from() != null || parsedArgs.to() != null) {
            logEntries = logFilter.filterByTime(logEntries, parsedArgs.from(), parsedArgs.to());
        }
        if (parsedArgs.filterField() != null && parsedArgs.filterValue() != null) {
            logEntries = logFilter.filterByField(logEntries, parsedArgs.filterField(), parsedArgs.filterValue());
        }
        AnalysisResult analysisResult = logAnalyzer.analyze(logEntries);

        String report = "";
        if ("markdown".equalsIgnoreCase(parsedArgs.format())) {
            report = reportGenerator.generateMarkdownReport(analysisResult);
        } else if ("adoc".equalsIgnoreCase(parsedArgs.format())) {
            report = reportGenerator.generateAsciiDocReport(analysisResult);
        }
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.info(report);
    }
}
