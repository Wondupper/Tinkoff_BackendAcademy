package backend.academy.realization.game;

import backend.academy.realization.display.Display;
import backend.academy.realization.settings.HangmanSessionParameters;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HangmanGameStarter {

    public static void start() {
        Optional<HangmanSessionParameters> sessionParameters = HangmanSessionParameters.getSessionSettings();
        if (sessionParameters.isPresent()) {
            HangmanGame game = new HangmanGame(sessionParameters.get());
            game.run();
        } else {
            Display.logMessage("Игра не была начата");
        }
    }

}
