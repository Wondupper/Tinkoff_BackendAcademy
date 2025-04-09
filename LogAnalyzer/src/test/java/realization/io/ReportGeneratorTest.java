package realization.io;

import backend.academy.realization.io.ReportGenerator;
import backend.academy.realization.pojo.AnalysisResult;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportGeneratorTest {

    @Test
    void generateMarkdownReport() {
        // given
        AnalysisResult analysisResult = new AnalysisResult(
            1000,
            Map.of("/index.html", 500L, "/about.html", 300L),
            Map.of(200, 800L, 404, 200L),
            512.5,
            1024,
            150,
            Map.of("GET", 900L, "POST", 100L)
        );
        ReportGenerator reportGenerator = new ReportGenerator();

        // when
        String markdownReport = reportGenerator.generateMarkdownReport(analysisResult);

        // then
        assertTrue(markdownReport.contains("## Общая информация"));
        assertTrue(markdownReport.contains("| Общее количество запросов | 1000 |"));
        assertTrue(markdownReport.contains("## Топ запрашиваемых ресурсов"));
        assertTrue(markdownReport.contains("| /index.html | 500 |"));
    }

    @Test
    void givenAnalysisResult_whenGenerateAsciiDocReport_thenReportContainsExpectedSections() {
        // given
        AnalysisResult analysisResult = new AnalysisResult(
            1000,
            Map.of("/index.html", 500L, "/about.html", 300L),
            Map.of(200, 800L, 404, 200L),
            512.5,
            1024,
            150,
            Map.of("GET", 900L, "POST", 100L)
        );
        ReportGenerator reportGenerator = new ReportGenerator();

        // when
        String asciidocReport = reportGenerator.generateAsciiDocReport(analysisResult);

        // then
        assertTrue(asciidocReport.contains("== Общая информация"));
        assertTrue(asciidocReport.contains("| Общее количество запросов | 1000"));
        assertTrue(asciidocReport.contains("== Топ запрашиваемых ресурсов"));
        assertTrue(asciidocReport.contains("| /index.html | 500"));
        assertTrue(asciidocReport.contains("== Частота кодов ответа"));
        assertTrue(asciidocReport.contains("| 200 | 800"));
    }
}
