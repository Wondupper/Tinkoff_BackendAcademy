package realization.display;

import backend.academy.realization.display.DisplayMaze;
import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayMazeTest {
    @Test
    void testDisplay() {
        //given
        Maze maze = new Maze(Map.of(
            new Cell(0, 0, CellType.PATH), Map.of(new Cell(0, 1, CellType.BLOCK), 1),
            new Cell(0, 1, CellType.BLOCK), Map.of(new Cell(0, 0, CellType.PATH), 1)
        ), 1, 2);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStreamCaptor);
        System.setOut(stream);
        DisplayMaze displayMaze = new DisplayMaze(maze);

        //when
        displayMaze.display(stream);
        //then
        assertEquals(" #%n".formatted(), outputStreamCaptor.toString());
    }
}
