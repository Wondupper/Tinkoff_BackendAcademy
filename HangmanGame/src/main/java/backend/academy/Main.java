package backend.academy;

import backend.academy.realization.console.InputHandler;
import backend.academy.realization.game.HangmanStartMenu;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        HangmanStartMenu.run();
        InputHandler.closeScanner();
    }
}
