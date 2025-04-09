package backend.academy.realization.resources;

import java.util.Optional;

public enum DifficultyLevels {

    HARD,
    EASY;

    public static Optional<DifficultyLevels> fromValue(String value) {
        return switch (value) {
            case "л" -> Optional.of(EASY);
            case "т" -> Optional.of(HARD);
            default -> Optional.empty();
        };
    }
}
