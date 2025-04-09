package backend.academy.realization.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LogFileReader {

    public List<String> readLogs(String path) throws IOException {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return readFromUrl(path);
        } else {
            return readFromLocalPath(path);
        }
    }

    private List<String> readFromLocalPath(String pathOrGlob) throws IOException {
        List<String> logs = new ArrayList<>();
        Path inputPath = Paths.get(pathOrGlob);
        if (Files.isRegularFile(inputPath)) {
            logs.addAll(Files.readAllLines(inputPath));
        } else {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + pathOrGlob);
            try (Stream<Path> paths = Files.walk(Paths.get("."))) {
                paths.filter(Files::isRegularFile)
                    .filter(path -> matcher.matches(path.toAbsolutePath()))
                    .forEach(path -> {
                        try {
                            logs.addAll(Files.readAllLines(path));
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                    });
            }
        }
        return logs;
    }

    private List<String> readFromUrl(String urlPath) throws IOException {
        List<String> logs = new ArrayList<>();
        URL url = new URL(urlPath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        }
        return logs;
    }
}
