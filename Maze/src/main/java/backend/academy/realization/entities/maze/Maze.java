package backend.academy.realization.entities.maze;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class Maze {
    private Map<Cell, Map<Cell, Integer>> graph;

    private int height;

    private int weight;

    public Maze(Map<Cell, Map<Cell, Integer>> graph, int height, int weight) {
        this.graph = graph;
        this.height = height;
        this.weight = weight;
    }

    public Maze() {
        this.graph = new HashMap<>();
    }

    /**
     * Method to return list of all cell pairs
     *
     * @return list of all cell pairs
     */
    public List<Map<Cell, Cell>> getEdges() {
        List<Map<Cell, Cell>> edgesList = new ArrayList<>();
        for (Cell cell : graph.keySet()) {
            for (Cell neighbor : graph.get(cell).keySet()) {
                Map<Cell, Cell> edge = new HashMap<>();
                edge.put(cell, neighbor);
                edgesList.add(edge);
            }
        }
        return edgesList;
    }

    /**
     * Method to return cell by row and column
     *
     * @param row    cell row
     * @param column cell column
     * @return cell by row and column
     */
    public Cell getCellByCoordinates(int row, int column) {
        for (Cell cell : graph.keySet()) {
            if (cell.row() == row && cell.column() == column) {
                return cell;
            }
        }
        return new Cell(row, column, CellType.BLOCK);
    }

    /**
     * Method to return all existing in the maze of neighbors of the cell by coordinates
     *
     * @param cell cell to find neighbors
     * @return all existing in the maze of neighbors of the cell by coordinates
     */
    public List<Cell> getCellNeighborsByCoordinates(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        Optional<Cell> leftNeighbor = getLeftNeighborByCoordinates(cell);
        Optional<Cell> rightNeighbor = getRightNeighborOfCellByCoordinates(cell);
        Optional<Cell> topNeighbor = getTopNeighborOfCellByCoordinates(cell);
        Optional<Cell> bottomNeighbor = getBottomNeighborOfCellByCoordinates(cell);
        leftNeighbor.ifPresent(neighbors::add);
        rightNeighbor.ifPresent(neighbors::add);
        topNeighbor.ifPresent(neighbors::add);
        bottomNeighbor.ifPresent(neighbors::add);
        return neighbors;
    }

    /**
     * Method to add present in maze neighbor to present in maze cell
     *
     * @param neighbor neighbor of the cell present in the maze
     * @param cell     present cell in the maze
     */
    public void addNeighborToCell(Cell neighbor, Cell cell) {
        if (neighbor.type() == CellType.BLOCK) {
            graph.get(cell).put(neighbor, Integer.MAX_VALUE);
        } else {
            graph.get(cell).put(neighbor, 1);
        }
    }

    /**
     * Method to return cell left neighbor
     *
     * @param cell cell to find left neighbor by coordinates
     * @return cell left neighbor
     */
    private Optional<Cell> getLeftNeighborByCoordinates(Cell cell) {
        for (Cell mazeCell : graph.keySet()) {
            if (mazeCell.row() == cell.row() && mazeCell.column() == cell.column() - 1) {
                return Optional.of(mazeCell);
            }
        }
        return Optional.empty();
    }

    /**
     * Method to return cell right neighbor
     *
     * @param cell cell to find right neighbor by coordinates
     * @return cell right neighbor
     */
    private Optional<Cell> getRightNeighborOfCellByCoordinates(Cell cell) {
        for (Cell mazeCell : graph.keySet()) {
            if (mazeCell.row() == cell.row() && mazeCell.column() == cell.column() + 1) {
                return Optional.of(mazeCell);
            }
        }
        return Optional.empty();
    }

    /**
     * Method to return cell top neighbor
     *
     * @param cell cell to find top neighbor by coordinates
     * @return cell top neighbor
     */
    private Optional<Cell> getTopNeighborOfCellByCoordinates(Cell cell) {
        for (Cell mazeCell : graph.keySet()) {
            if (mazeCell.row() == cell.row() - 1 && mazeCell.column() == cell.column()) {
                return Optional.of(mazeCell);
            }
        }
        return Optional.empty();
    }

    /**
     * Method to return cell bottom neighbor
     *
     * @param cell cell to find bottom neighbor by coordinates
     * @return cell bottom neighbor
     */
    private Optional<Cell> getBottomNeighborOfCellByCoordinates(Cell cell) {
        for (Cell mazeCell : graph.keySet()) {
            if (mazeCell.row() == cell.row() + 1 && mazeCell.column() == cell.column()) {
                return Optional.of(mazeCell);
            }
        }
        return Optional.empty();
    }
}
