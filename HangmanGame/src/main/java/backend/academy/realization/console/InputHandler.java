package backend.academy.realization.console;

import java.util.Scanner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readWord() {
        return SCANNER.next();
    }

    public static void closeScanner() {
        SCANNER.close();
    }
}
