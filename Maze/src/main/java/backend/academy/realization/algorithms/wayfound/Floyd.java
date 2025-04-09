package backend.academy.realization.algorithms.wayfound;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Floyd extends WayFounder implements WayFound {

    /**
     * map of storing the precursors of each cell
     */
    private HashMap<Cell, HashMap<Cell, Cell>> previousCells;

    public Floyd(Maze maze) {
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
        doFloyd();
        restorePath(start, end);
    }

    /**
     * Do Floyd algorithm from start cell
     */
    private void doFloyd() {
        HashMap<Cell, HashMap<Cell, Integer>> matrix = initMatrix();
        initStartPreviousCells();
        for (Cell k : matrix.keySet()) {
            for (Cell i : matrix.keySet()) {
                for (Cell j : matrix.keySet()) {
                    if (matrix.get(i).get(k) < Integer.MAX_VALUE
                        && matrix.get(k).get(j) < Integer.MAX_VALUE
                        && matrix.get(i).get(k) + matrix.get(k).get(j) < matrix.get(i).get(j)) {
                        matrix.get(i).put(j, matrix.get(i).get(k) + matrix.get(k).get(j));
                        previousCells.get(i).put(j, previousCells.get(i).get(k));
                    }
                }
            }
        }
    }

    /**
     * Method for creating a matrix of distances between cells
     *
     * @return matrix of initial distances between cells
     */
    private HashMap<Cell, HashMap<Cell, Integer>> initMatrix() {
        HashMap<Cell, HashMap<Cell, Integer>> matrix = new HashMap<>();
        for (Cell cellFirst : maze.graph().keySet()) {
            HashMap<Cell, Integer> row = new HashMap<>();
            for (Cell cellSecond : maze.graph().keySet()) {
                if (cellFirst.equals(cellSecond)) {
                    row.put(cellSecond, 0);
                } else {
                    if (maze.graph().get(cellFirst).containsKey(cellSecond)) {
                        row.put(cellSecond, 1);
                    } else {
                        row.put(cellSecond, Integer.MAX_VALUE);
                    }
                }
            }
            matrix.put(cellFirst, row);
        }
        return matrix;
    }

    /**
     * Method for init a map of initial previous cells
     */
    private void initStartPreviousCells() {
        for (Cell cell : maze.graph().keySet()) {
            HashMap<Cell, Cell> neighbors = new HashMap<>();
            neighbors.put(cell, cell);
            for (Cell neighbor : maze.graph().get(cell).keySet()) {
                neighbors.put(neighbor, neighbor);
            }
            previousCells.put(cell, neighbors);
        }
    }

    @Override
    protected void restorePath(Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Cell currentCell = start;
        path.add(currentCell);
        while (!currentCell.equals(end)) {
            currentCell = previousCells.get(currentCell).get(end);
            path.add(currentCell);
        }
        path.add(end);
        for (Cell cell : path) {
            cell.type(CellType.PATH_TO_TARGET);
        }
    }

    private void clearPreviousCells() {
        previousCells.clear();
    }
}
