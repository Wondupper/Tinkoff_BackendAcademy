package backend.academy.realization.algorithms.wayfound;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.maze.Maze;
import java.util.ArrayList;
import java.util.List;

public abstract class WayFounder {
    protected Maze maze;

    protected List<Cell> visited;

    protected List<Cell> way;

    protected WayFounder(Maze maze) {
        this.maze = maze;
        this.visited = new ArrayList<>();
        this.way = new ArrayList<>();
    }

    protected void clearVisited() {
        visited.clear();
    }

    protected void clearWay() {
        way.clear();
    }

    /**
     * Method for reconstructing the path between two cells
     *
     * @param start start cell
     * @param end   end cell
     */
    protected abstract void restorePath(Cell start, Cell end);
}
