package realization.settings;

import backend.academy.realization.resources.DifficultyLevels;
import backend.academy.realization.settings.HangmanGlobalSettings;
import backend.academy.realization.settings.HangmanSessionParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HangmanSessionParametersTest {

    @Test
    void refreshParameters() {
        HangmanSessionParameters sessionParameters = new HangmanSessionParameters();
        sessionParameters.setDifficultyLevel(DifficultyLevels.HARD);

        sessionParameters.refreshParameters();

        Assertions.assertEquals(0, sessionParameters.getWrongLetters().size());
        Assertions.assertEquals(HangmanGlobalSettings.getMaxLives(), sessionParameters.getCurrentLives());
    }

    @Test
    void changeDifficulty() {
        HangmanSessionParameters sessionParameters = new HangmanSessionParameters();
        sessionParameters.setDifficultyLevel(DifficultyLevels.EASY);

        sessionParameters.changeDifficulty();

        Assertions.assertEquals(DifficultyLevels.HARD, sessionParameters.getDifficultyLevel());
    }
}
