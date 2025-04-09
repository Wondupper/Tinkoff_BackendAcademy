package backend.academy.realization.display;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class DisplayMaze implements Display {

    private Maze maze;

    public DisplayMaze(Maze maze) {
        this.maze = maze;
    }

    public DisplayMaze() {
    }

    @Override
    public void display(PrintStream stream) {
        List<String> lines = generateSymbolicMaze();
        for (String line : lines) {
            stream.println(line);
        }
    }

    /**
     * Method for creating a symbolic maze matrix
     *
     * @return symbolic maze matrix
     */
    private List<String> generateSymbolicMaze() {
        List<String> mazeLines = new ArrayList<>();
        for (int i = 0; i < maze.height(); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < maze.weight(); j++) {
                Cell cell = maze.getCellByCoordinates(i, j);
                switch (cell.type()) {
                    case CellType.PATH -> line.append(" ");
                    case CellType.BLOCK -> line.append("#");
                    case CellType.PATH_TO_TARGET -> line.append(".");
                    default -> line.append("!");
                }
            }
            mazeLines.add(line.toString());
        }
        return mazeLines;
    }
}
