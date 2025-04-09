package backend.academy.realization.algorithms.wayfound;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends WayFounder implements WayFound {

    /**
     * map of storing the precursors of each cell
     */
    private HashMap<Cell, Cell> previousCells;

    public BFS(Maze maze) {
        super(maze);
        previousCells = new HashMap<>();
    }

    /**
     * Find way by BFS algorithm
     *
     * @param startRow    row of start cell
     * @param startColumn column of start cell
     * @param endRow      row of end cell
     * @param endColumn   column of end cell
     */
    @Override
    public void foundWay(int startRow, int startColumn, int endRow, int endColumn) {
        Cell start = maze.getCellByCoordinates(startRow, startColumn);
        Cell end = maze.getCellByCoordinates(endRow, endColumn);
        clearVisited();
        clearWay();
        clearPreviousCells();
        getWay(start, end);
    }

    private void getWay(Cell start, Cell end) {
        doBFS(start);
        restorePath(start, end);
    }

    /**
     * Do BFS algorithm from start cell
     *
     * @param start cell for starting BFS
     */
    private void doBFS(Cell start) {
        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        HashMap<Cell, Integer> dists = initDists(start);
        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();
            for (Cell neighbor : maze.graph().get(currentCell).keySet()) {
                if (dists.get(currentCell) + 1 < dists.get(neighbor)) {
                    dists.put(neighbor, dists.get(currentCell) + 1);
                    previousCells.put(neighbor, currentCell);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Method for creating a matrix of distances between cells
     *
     * @param start start cell
     * @return matrix of initial distances between cells
     */
    private HashMap<Cell, Integer> initDists(Cell start) {
        HashMap<Cell, Integer> dists = new HashMap<>();
        for (Cell cell : maze.graph().keySet()) {
            if (!start.equals(cell)) {
                dists.put(cell, Integer.MAX_VALUE);
            } else {
                dists.put(cell, 0);
            }
        }
        return dists;
    }

    @Override
    protected void restorePath(Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Cell currentCell = end;
        path.add(currentCell);
        while (!currentCell.equals(start)) {
            currentCell = previousCells.get(currentCell);
            path.add(currentCell);
        }
        for (Cell cell : path) {
            cell.type(CellType.PATH_TO_TARGET);
        }
    }

    private void clearPreviousCells() {
        previousCells.clear();
    }
}
