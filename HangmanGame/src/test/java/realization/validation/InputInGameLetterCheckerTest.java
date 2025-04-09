package realization.validation;

import backend.academy.realization.resources.DifficultyLevels;
import backend.academy.realization.settings.HangmanSessionParameters;
import backend.academy.realization.validation.InputInGameLetterChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class InputInGameLetterCheckerTest {

    @Test
    void letterProcessing() {
        HangmanSessionParameters sessionParameters = new HangmanSessionParameters();
        String actualLetter = "ы";
        sessionParameters.setDifficultyLevel(DifficultyLevels.HARD);
        sessionParameters.setCurrentLives(8);
        sessionParameters.setWrongLetters(new HashSet<>());
        sessionParameters.setHiddenWord("ракета");
        sessionParameters.setCurrentUnraveledWord(new StringBuilder("******"));

        InputInGameLetterChecker.letterProcessing(sessionParameters, actualLetter);

        Assertions.assertEquals("******", sessionParameters.getCurrentUnraveledWord().toString());
        Assertions.assertEquals(Set.of("ы"), sessionParameters.getWrongLetters());
        Assertions.assertEquals(7, sessionParameters.getCurrentLives());
    }
}
