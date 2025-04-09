package backend.academy.realization.game;

import backend.academy.realization.console.InputHandler;
import backend.academy.realization.display.Display;
import backend.academy.realization.settings.HangmanSessionParameters;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HangmanGameEndMenu {

    private static final String START_NEW_GAME = "с";

    private static final String START_NEW_GAME_WITH_CHANGED_DIFFICULTY = "с+";

    public static void gameRestartOrEnd(HangmanSessionParameters sessionParameters) {
        Display.logMessage("""
            Конец игры. Для начала новой игры введите:<%s>.
            Для начала новой игры с другой сложностью введите:<%s>.
            При другом вводе игра завершится""".formatted(START_NEW_GAME, START_NEW_GAME_WITH_CHANGED_DIFFICULTY));
        String answer = InputHandler.readWord();
        if (!answer.equalsIgnoreCase(START_NEW_GAME_WITH_CHANGED_DIFFICULTY)
            && !answer.equalsIgnoreCase(START_NEW_GAME)) {
            sessionParameters.inactiveGame();
        } else {
            sessionParameters.refreshParameters();
            if (answer.equalsIgnoreCase(START_NEW_GAME_WITH_CHANGED_DIFFICULTY)) {
                sessionParameters.changeDifficulty();
            }
        }
    }
}
