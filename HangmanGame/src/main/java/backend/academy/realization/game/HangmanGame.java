package backend.academy.realization.game;

import backend.academy.realization.console.InputHandler;
import backend.academy.realization.display.Display;
import backend.academy.realization.resources.DifficultyLevels;
import backend.academy.realization.settings.HangmanSessionParameters;
import backend.academy.realization.validation.InputInGameLetterChecker;

public class HangmanGame {

    private HangmanSessionParameters sessionParameters;

    public HangmanGame(HangmanSessionParameters sessionParameters) {
        this.sessionParameters = sessionParameters;
    }

    public void run() {
        while (sessionParameters.gameIsActive()) {
            Display.logMessage("Начало новой игры");
            while (sessionParameters.getCurrentLives() != 0) {
                Display.logMessage(Display.displayHangman(sessionParameters.getCurrentLives()));
                Display.logMessage("""
                    Число жизней: %s
                    Неверно угаданные буквы: %s
                    Отгадано: %s""".formatted(sessionParameters.getCurrentLives(), sessionParameters.getWrongLetters(),
                    sessionParameters.getCurrentUnraveledWord()));
                if (sessionParameters.getDifficultyLevel().equals(DifficultyLevels.EASY)) {
                    Display.logMessage("Подсказка: %s".formatted(sessionParameters.getHelp()));
                }
                Display.logMessage("Введите букву");
                String letter = String.valueOf(InputHandler.readWord().charAt(0)).toLowerCase();
                InputInGameLetterChecker.letterProcessing(sessionParameters, letter);
                if (sessionParameters.getCurrentLives() == 0) {
                    Display.logMessage(Display.displayHangman(sessionParameters.getCurrentLives()));
                }
                if (sessionParameters.getHiddenWord().contentEquals(sessionParameters.getCurrentUnraveledWord())) {
                    Display.logMessage(
                        "Вы отгадали слово <%s>. Поздравляю!".formatted(sessionParameters.getHiddenWord()));
                    sessionParameters.setCurrentLives(0);
                }
            }
            HangmanGameEndMenu.gameRestartOrEnd(sessionParameters);
        }
    }

}
