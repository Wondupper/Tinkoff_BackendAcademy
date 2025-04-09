package realization.settings;

import backend.academy.realization.settings.MaxLivesLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxLivesLoaderTest {

    @Test
    void getMaxLives() {
        Assertions.assertEquals(8, MaxLivesLoader.getMaxLives());
    }
}
