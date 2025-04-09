package backend.academy.realization.settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MaxLivesLoader {
    public static int getMaxLives() {
        try {
            int maxLives = 0;
            Pattern pattern = Pattern.compile("\\d+");
            String line = Files.readString(Path.of("src/main/java/backend/academy/realization/settings/settings.txt"));
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                maxLives = Integer.parseInt(line.substring(matcher.start(), matcher.end()));
            }
            if (maxLives <= 0) {
                throw new RuntimeException("maximum lives must be a positive number greater than zero");
            }
            return maxLives;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
