package realization.algorithms.wayfound;

import backend.academy.realization.algorithms.wayfound.Floyd;
import backend.academy.realization.algorithms.wayfound.WayFound;
import backend.academy.realization.display.DisplayMaze;
import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FloydTest {
    @Test
    void testFoundWayByFloyd() {
        //given
        Maze maze = new Maze(Map.of(
            new Cell(0, 0, CellType.PATH), Map.of(new Cell(0, 1, CellType.PATH), 1),
            new Cell(0, 1, CellType.PATH), Map.of(new Cell(0, 0, CellType.PATH), 1)
        ), 1, 2);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStreamCaptor);
        System.setOut(stream);
        DisplayMaze displayMaze = new DisplayMaze(maze);
        WayFound wayFound = new Floyd(maze);

        //when
        wayFound.foundWay(0, 0, 0, 1);
        displayMaze.display(stream);

        //then
        assertEquals("..%n".formatted(), outputStreamCaptor.toString());
    }
}
