package backend.academy.realization.display;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Display {

    private static final Logger LOGGER = Logger.getLogger(Display.class.getName());

    public static void logMessage(final String message) {
        LOGGER.info(message);
    }

    public static StringBuilder revealLetters(String hiddenWord, StringBuilder currentUnraveledWord, String letter) {
        Matcher matcher = Pattern.compile(letter).matcher(hiddenWord);
        List<Integer> indexesOfLetter = new ArrayList<>();
        while (matcher.find()) {
            indexesOfLetter.add(matcher.start());
        }
        for (Integer el : indexesOfLetter) {
            currentUnraveledWord.setCharAt(el, letter.charAt(0));
        }
        return currentUnraveledWord;
    }

    @SuppressWarnings("MagicNumber")
    public static String displayHangman(int currentLives) {
        if (currentLives >= 6) {
            return "----------";
        } else {
            return switch (currentLives) {
                case 5 -> """
                    ----------
                         |
                         0""";
                case 4 -> """
                    ----------
                         |
                         0
                        \\""";
                case 3 -> """
                    ----------
                         |
                         0
                        \\ /""";
                case 2 -> """
                    ----------
                         |
                         0
                        \\ /
                         |""";
                case 1 -> """
                    ----------
                         |
                         0
                        \\ /
                         |
                        /""";
                case 0 -> """
                    ----------
                         |
                         0
                        \\ /
                         |
                        / \\""";
                default -> "";
            };
        }
    }
}
