package realization.display;

import backend.academy.realization.display.Display;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DisplayTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void testDisplayHangman(int currentLives) {
        if (currentLives >= 6) {
            Assertions.assertEquals("----------", Display.displayHangman(currentLives));
        } else {
            switch (currentLives) {
                case 5:
                    Assertions.assertEquals("""
                        ----------
                             |
                             0""", Display.displayHangman(currentLives));
                    break;
                case 4:
                    Assertions.assertEquals("""
                        ----------
                             |
                             0
                            \\""", Display.displayHangman(currentLives));
                    break;
                case 3:
                    Assertions.assertEquals("""
                        ----------
                             |
                             0
                            \\ /""", Display.displayHangman(currentLives));
                    break;
                case 2:
                    Assertions.assertEquals("""
                        ----------
                             |
                             0
                            \\ /
                             |""", Display.displayHangman(currentLives));
                    break;
                case 1:
                    Assertions.assertEquals("""
                        ----------
                             |
                             0
                            \\ /
                             |
                            /""", Display.displayHangman(currentLives));
                    break;
                case 0:
                    Assertions.assertEquals("""
                        ----------
                             |
                             0
                            \\ /
                             |
                            / \\""", Display.displayHangman(currentLives));
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    void revealLetters() {
        String letter = "а";
        String hiddenWord = "ракета";
        StringBuilder currentUnraveledWord = new StringBuilder("******");

        StringBuilder actualCurrentUnraveledWord = Display.revealLetters(hiddenWord, currentUnraveledWord, letter);

        Assertions.assertEquals("*а***а", actualCurrentUnraveledWord.toString());
    }
}
