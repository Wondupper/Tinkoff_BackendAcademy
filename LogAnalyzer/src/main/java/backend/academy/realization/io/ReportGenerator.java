package backend.academy.realization.io;

import backend.academy.realization.pojo.AnalysisResult;
import java.util.Map;

public class ReportGenerator {

    public String generateMarkdownReport(AnalysisResult result) {
        StringBuilder report = new StringBuilder();
        String markdownLine = "|------------------------|---------------------|\n";
        String insertableMarkdownLine = "| %s | %d |%n";
        report.append("## Общая информация\n\n")
            .append("| Метрика                | Значение            |\n")
            .append(markdownLine)
            .append(String.format("| Общее количество запросов | %d |%n", result.totalRequests()))
            .append(String.format("| Средний размер ответа      | %.2f байт |%n", result.averageResponseSize()))
            .append(String.format("| 95-й перцентиль ответа     | %d байт |%n", result.percentile95ResponseSize()))
            .append(String.format("| Уникальные пользователи    | %d |%n", result.uniqueUsers()));

        report.append("\n## Топ запрашиваемых ресурсов\n\n")
            .append("| Ресурс                 | Запросы            |\n")
            .append(markdownLine);
        for (Map.Entry<String, Long> entry : result.topResources().entrySet()) {
            report.append(String.format(insertableMarkdownLine, entry.getKey(), entry.getValue()));
        }

        report.append("\n## Частота кодов ответа\n\n")
            .append("| Код ответа             | Количество         |\n")
            .append(markdownLine);
        for (Map.Entry<Integer, Long> entry : result.statusCodeFrequency().entrySet()) {
            report.append(String.format(insertableMarkdownLine, entry.getKey(), entry.getValue()));
        }

        report.append("\n## Популярные методы HTTP\n\n")
            .append("| Метод                 | Количество         |\n")
            .append(markdownLine);
        for (Map.Entry<String, Long> entry : result.topMethods().entrySet()) {
            report.append(String.format(insertableMarkdownLine, entry.getKey(), entry.getValue()));
        }

        return report.toString();
    }

    public String generateAsciiDocReport(AnalysisResult result) {
        StringBuilder report = new StringBuilder();
        String topLineForAsciiDoc = "|===\n";
        String bottomLineForAsciiDoc = "|===\n\n";
        String insertableAsciiLine = "| %s | %d%n";
        report.append("== Общая информация\n\n")
            .append(topLineForAsciiDoc)
            .append("| Метрика                | Значение\n")
            .append(String.format("| Общее количество запросов | %d%n", result.totalRequests()))
            .append(String.format("| Средний размер ответа      | %.2f байт%n", result.averageResponseSize()))
            .append(String.format("| 95-й перцентиль ответа     | %d байт%n", result.percentile95ResponseSize()))
            .append(String.format("| Уникальные пользователи    | %d%n", result.uniqueUsers()))
            .append(bottomLineForAsciiDoc);

        report.append("== Топ запрашиваемых ресурсов\n\n")
            .append(topLineForAsciiDoc)
            .append("| Ресурс                 | Запросы\n");
        for (Map.Entry<String, Long> entry : result.topResources().entrySet()) {
            report.append(String.format(insertableAsciiLine, entry.getKey(), entry.getValue()));
        }
        report.append(bottomLineForAsciiDoc);

        report.append("== Частота кодов ответа\n\n")
            .append(topLineForAsciiDoc)
            .append("| Код ответа             | Количество\n");
        for (Map.Entry<Integer, Long> entry : result.statusCodeFrequency().entrySet()) {
            report.append(String.format(insertableAsciiLine, entry.getKey(), entry.getValue()));
        }
        report.append(bottomLineForAsciiDoc);

        report.append("== Популярные методы HTTP\n\n")
            .append(topLineForAsciiDoc)
            .append("| Метод                 | Количество\n");
        for (Map.Entry<String, Long> entry : result.topMethods().entrySet()) {
            report.append(String.format(insertableAsciiLine, entry.getKey(), entry.getValue()));
        }
        report.append(topLineForAsciiDoc);

        return report.toString();
    }
}
