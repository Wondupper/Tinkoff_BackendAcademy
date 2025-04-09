package backend.academy.realization.validation;

import backend.academy.realization.display.Display;
import backend.academy.realization.resources.DifficultyLevels;
import backend.academy.realization.resources.TypeEnteredLetter;
import backend.academy.realization.settings.HangmanSessionParameters;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InputInGameLetterChecker {

    public static void letterProcessing(HangmanSessionParameters sessionParameters, String letter) {
        switch (checkLetter(sessionParameters, letter)) {
            case RIGHT:
                Display.logMessage("Вы отгадали букву: <%s>".formatted(letter));
                break;
            case WRONG:
                Display.logMessage("Вы не отгадали букву: <%s>".formatted(letter));
                sessionParameters.getWrongLetters().add(letter);
                sessionParameters.reduceCurrentLives();
                break;
            case RIGHT_EXIST, WRONG_EXIST:
                Display.logMessage("Вы уже вводили букву: <%s>".formatted(letter));
                if (sessionParameters.getDifficultyLevel().equals(DifficultyLevels.HARD)) {
                    Display.logMessage("Отнимается жизнь за тяжелый уровень сложности!");
                    sessionParameters.reduceCurrentLives();
                }
                break;
            default:
                break;
        }
    }

    private static TypeEnteredLetter checkLetter(HangmanSessionParameters sessionParameters, String letter) {
        if (sessionParameters.getCurrentUnraveledWord().toString().contains(letter)) {
            return TypeEnteredLetter.RIGHT_EXIST;
        } else if (sessionParameters.getWrongLetters().contains(letter)) {
            return TypeEnteredLetter.WRONG_EXIST;
        } else if (sessionParameters.getHiddenWord().contains(letter)) {
            sessionParameters.setCurrentUnraveledWord(
                Display.revealLetters(sessionParameters.getHiddenWord(), sessionParameters.getCurrentUnraveledWord(),
                    letter));
            return TypeEnteredLetter.RIGHT;
        }
        return TypeEnteredLetter.WRONG;
    }

}
