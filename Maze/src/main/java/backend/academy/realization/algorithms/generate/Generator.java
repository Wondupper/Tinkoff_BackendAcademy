package backend.academy.realization.algorithms.generate;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Generator {
    protected int height;

    protected int width;

    /**
     * List of visited cells
     */
    protected List<Cell> visited = new ArrayList<>();

    /**
     * Constructor
     *
     * @param height of generated maze
     * @param width  of generated maze
     */
    protected Generator(int height, int width) {
        this.width = width;
        this.height = height;
    }

    protected void clearVisited() {
        visited.clear();
    }

    /**
     * The cells are not yet linked in the maze
     *
     * @return generate maze with cells with block type
     */
    protected Maze buildMazeWithBlocks() {
        Maze maze = new Maze();
        maze.height(height);
        maze.weight(width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze.graph().put(new Cell(i, j, CellType.BLOCK), new HashMap<>());
            }
        }
        linkNeighborsCells(maze);
        return maze;
    }

    /**
     * Link neighboring cells in maze by their coordinates
     *
     * @param maze form buildMazeWithBlocks()
     */
    protected void linkNeighborsCells(Maze maze) {
        for (Cell cell : maze.graph().keySet()) {
            List<Cell> neighbors = maze.getCellNeighborsByCoordinates(cell);
            if (!neighbors.isEmpty()) {
                for (Cell neighbor : neighbors) {
                    maze.addNeighborToCell(neighbor, cell);
                }
            }
        }
    }

    /**
     * Blocks cells if they only have one edge
     *
     * @param maze form linkNeighborsCells()
     */
    protected void blockingCells(Maze maze) {
        Set<Cell> checkedCells = new HashSet<>();
        for (Cell cell : maze.graph().keySet()) {
            if (!checkedCells.contains(cell)) {
                List<Integer> values = maze.graph().get(cell).values().stream().toList();
                if (values.contains(Integer.MAX_VALUE) && Collections.frequency(values, 1) == 1) {
                    cell.type(CellType.BLOCK);
                }
                checkedCells.add(cell);
            }
        }
    }
}
