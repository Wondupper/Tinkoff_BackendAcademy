package backend.academy.realization.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Dictionary {
    private static final Random RANDOM_INDEX_GENERATOR = new Random();
    private static final Map<String, String> SECRET_WORDS_AND_HELPS = Map.of(
        "актер", "человек изображающий других",
        "золото", "самая бесполезная руда в майнкрафте",
        "рисунок", "изображает что-либо",
        "трава", "тише ... ниже...",
        "рука", "у вас её 2",
        "ракета", "быстро летает, больное бьет",
        "часы", "сориентируют во времени",
        "алмаз", "топ 1 предмет майнкрафта",
        "ветер", "он могуч"
    );


    public static String getSecretWord() {
        List<String> keys = new ArrayList<>(SECRET_WORDS_AND_HELPS.keySet());
        return keys.get(RANDOM_INDEX_GENERATOR.nextInt(keys.size()));
    }


    public static String getHelp(String hiddenWord) {
        return SECRET_WORDS_AND_HELPS.get(hiddenWord);
    }

}
