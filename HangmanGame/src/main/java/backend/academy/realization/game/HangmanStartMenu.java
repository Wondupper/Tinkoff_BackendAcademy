package backend.academy.realization.game;

import backend.academy.realization.console.InputHandler;
import backend.academy.realization.display.Display;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HangmanStartMenu {

    private static final String START_GAME = "с";

    public static void run() {
        Display.logMessage("Для начала игры введите:<%s>".formatted(START_GAME));
        if (InputHandler.readWord().equalsIgnoreCase(START_GAME)) {
            HangmanGameStarter.start();
        } else {
            Display.logMessage("Игра не была начата");
        }
    }
}
