package backend.academy;

import backend.academy.realization.algorithms.generate.Generate;
import backend.academy.realization.algorithms.generate.Kruskal;
import backend.academy.realization.algorithms.generate.Prim;
import backend.academy.realization.algorithms.wayfound.BFS;
import backend.academy.realization.algorithms.wayfound.Floyd;
import backend.academy.realization.algorithms.wayfound.WayFound;
import backend.academy.realization.display.DisplayMaze;
import backend.academy.realization.entities.maze.Maze;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("checkstyle:MagicNumber")
public class Main {
    public static void main(String[] args) throws IOException {
        startProgram();
    }

    private static void startProgram() throws IOException {
        try (PrintStream printStream = new PrintStream(System.out);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            printStream.println("""
                Выберите алгоритм генерации: Прим(p) или Краскал(k)
                и размеры лабиринта.
                Введите 'p' или 'k' и далее размеры лабиринта через пробел""");
            String[] answear = bufferedReader.readLine().split(" ");
            Generate generator =
                answear[0].equals("p") ? new Prim(Integer.parseInt(answear[1]), Integer.parseInt(answear[1]))
                    : new Kruskal(Integer.parseInt(answear[2]), Integer.parseInt(answear[2]));
            Maze maze = generator.generateMaze();
            DisplayMaze displayMaze = new DisplayMaze(maze);
            displayMaze.display(printStream);
            printStream.println("""
                Введите координаты старта и конца
                и выберите алгоритм нахождения пути: BFS(b) или Флойд(f).
                Введите 'b' или 'f' и далее координаты вершин через пробел""");
            answear = bufferedReader.readLine().split(" ");
            WayFound wayFound = answear[0].equals("b") ? new BFS(maze) : new Floyd(maze);
            wayFound.foundWay(Integer.parseInt(answear[1]), Integer.parseInt(answear[2]), Integer.parseInt(answear[3]),
                Integer.parseInt(answear[4]));
            displayMaze.display(printStream);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
