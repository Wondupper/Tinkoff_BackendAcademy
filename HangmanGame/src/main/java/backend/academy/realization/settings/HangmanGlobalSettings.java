package backend.academy.realization.settings;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HangmanGlobalSettings {
    private static final int MAX_LIVES = MaxLivesLoader.getMaxLives();

    public static int getMaxLives() {
        return MAX_LIVES;
    }
}
