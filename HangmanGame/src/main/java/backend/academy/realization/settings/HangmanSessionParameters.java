package backend.academy.realization.settings;

import backend.academy.realization.console.InputHandler;
import backend.academy.realization.display.Display;
import backend.academy.realization.resources.Dictionary;
import backend.academy.realization.resources.DifficultyLevels;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class HangmanSessionParameters {

    private StringBuilder currentUnraveledWord;

    private int currentLives = 0;

    private Set<String> wrongLetters = new HashSet<>();

    private DifficultyLevels difficultyLevel;

    private String hiddenWord;

    private String help;

    private boolean gameSessionActive = false;

    private boolean settingsComplete = false;

    private static final String EASY_GAME = "л";

    private static final String HARD_GAME = "т";

    public static Optional<HangmanSessionParameters> getSessionSettings() {
        HangmanSessionParameters sessionParameters = new HangmanSessionParameters();
        sessionParameters.prepareParameters();
        if (sessionParameters.settingsComplete) {
            return Optional.of(sessionParameters);
        } else {
            return Optional.empty();
        }
    }

    private void prepareParameters() {
        Display.logMessage("""
            Выберите уровень сложности:
            <%s> для легкого уровня(с подсказками, разрешены повторы верных и неверных букв)
            <%s> (без подсказок, без повторов букв)
            Если ввести что либо другое будет игра будет завершена""".formatted(EASY_GAME, HARD_GAME));
        Optional<DifficultyLevels> answer = DifficultyLevels.fromValue(InputHandler.readWord());
        if (answer.isPresent()) {
            this.setHiddenWord(Dictionary.getSecretWord());
            this.activeGame();
            this.setCurrentLives(HangmanGlobalSettings.getMaxLives());
            this.setWrongLetters(new HashSet<>());
            this.setCurrentUnraveledWord(new StringBuilder("*".repeat(hiddenWord.length())));
            if (answer.get().equals(DifficultyLevels.EASY)) {
                this.setDifficultyLevel(DifficultyLevels.EASY);
                this.setHelp(Dictionary.getHelp(hiddenWord));
            } else {
                this.setDifficultyLevel(DifficultyLevels.HARD);
            }
            this.settingsComplete = true;
        }
    }

    public void refreshParameters() {
        hiddenWord = Dictionary.getSecretWord();
        if (difficultyLevel.equals(DifficultyLevels.EASY)) {
            help = Dictionary.getHelp(hiddenWord);
        }
        currentUnraveledWord = new StringBuilder("*".repeat(hiddenWord.length()));
        wrongLetters.clear();
        currentLives = HangmanGlobalSettings.getMaxLives();
    }

    public void activeGame() {
        gameSessionActive = true;
    }

    public void inactiveGame() {
        gameSessionActive = false;
    }

    public void changeDifficulty() {
        difficultyLevel =
            (difficultyLevel.equals(DifficultyLevels.EASY)) ? DifficultyLevels.HARD : DifficultyLevels.EASY;
    }

    public boolean gameIsActive() {
        return gameSessionActive;
    }

    public void reduceCurrentLives() {
        this.currentLives--;
    }

    public StringBuilder getCurrentUnraveledWord() {
        return currentUnraveledWord;
    }

    public void setCurrentUnraveledWord(StringBuilder currentUnraveledWord) {
        this.currentUnraveledWord = currentUnraveledWord;
    }

    public int getCurrentLives() {
        return currentLives;
    }

    public void setCurrentLives(int maxLives) {
        this.currentLives = maxLives;
    }

    public Set<String> getWrongLetters() {
        return wrongLetters;
    }

    public void setWrongLetters(Set<String> wrongLetters) {
        this.wrongLetters = wrongLetters;
    }

    public DifficultyLevels getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevels difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
