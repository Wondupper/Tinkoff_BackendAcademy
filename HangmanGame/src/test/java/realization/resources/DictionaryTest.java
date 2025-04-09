package realization.resources;

import backend.academy.realization.resources.Dictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DictionaryTest {

    @Test
    void getHelp() {
        Assertions.assertEquals("он могуч", Dictionary.getHelp("ветер"));
    }
}
