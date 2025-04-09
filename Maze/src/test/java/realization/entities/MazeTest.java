package realization.entities;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MazeTest {
    //given
    private static final Maze maze = new Maze(Map.of(
        new Cell(0, 0, CellType.PATH), Map.of(new Cell(0, 1, CellType.PATH), 1, new Cell(1, 0, CellType.PATH), 1),
        new Cell(0, 1, CellType.PATH), Map.of(new Cell(0, 0, CellType.PATH), 1, new Cell(1, 1, CellType.PATH), 1),
        new Cell(1, 0, CellType.PATH), Map.of(new Cell(0, 0, CellType.PATH), 1, new Cell(1, 1, CellType.PATH), 1),
        new Cell(1, 1, CellType.PATH), Map.of(new Cell(0, 1, CellType.PATH), 1, new Cell(1, 0, CellType.PATH), 1)
    ), 2, 2);

    @Test
    void testGetEdges() {
        //given
        List<Map<Cell, Cell>> edges = List.of(
            Map.of(new Cell(0, 0, CellType.PATH), new Cell(0, 1, CellType.PATH)),
            Map.of(new Cell(0, 1, CellType.PATH), new Cell(0, 0, CellType.PATH)),
            Map.of(new Cell(0, 0, CellType.PATH), new Cell(1, 0, CellType.PATH)),
            Map.of(new Cell(1, 0, CellType.PATH), new Cell(0, 0, CellType.PATH)),
            Map.of(new Cell(1, 0, CellType.PATH), new Cell(1, 1, CellType.PATH)),
            Map.of(new Cell(1, 1, CellType.PATH), new Cell(1, 0, CellType.PATH)),
            Map.of(new Cell(0, 1, CellType.PATH), new Cell(1, 1, CellType.PATH)),
            Map.of(new Cell(1, 1, CellType.PATH), new Cell(0, 1, CellType.PATH))
        );

        //when
        List<Map<Cell, Cell>> edgesFromMethod = maze.getEdges();

        //then
        assertThat(edges).hasSameElementsAs(edgesFromMethod);
    }

    @Test
    void testGetCellByCoordinates() {
        //given
        Cell cell = new Cell(0, 0, CellType.PATH);

        //when
        Cell cellFromMaze = maze.getCellByCoordinates(0, 0);

        //then
        org.junit.jupiter.api.Assertions.assertEquals(cell, cellFromMaze);
    }

    @Test
    void testGetCellNeighborsByCoordinates() {
        //given
        List<Cell> neighbors = List.of(new Cell(0, 1, CellType.PATH), new Cell(1, 0, CellType.PATH));
        Cell cell = new Cell(0, 0, CellType.PATH);

        //when
        List<Cell> neighborsFromMaze = maze.getCellNeighborsByCoordinates(cell);

        //then
        assertThat(neighbors).hasSameElementsAs(neighborsFromMaze);
    }

    @Test
    void testAddNeighborToCell() {
        //given
        Map<Cell, Map<Cell, Integer>> testingGraph = new HashMap<>();
        Cell testingCell = new Cell(0, 0, CellType.PATH);
        testingGraph.put(testingCell, new HashMap<>());
        Maze testingMaze = new Maze(testingGraph, 1, 1);
        Cell testCell = new Cell(0, 1, CellType.PATH);
        Map<Cell, Map<Cell, Integer>> testGraph = new HashMap<>();
        Cell addingTestCell1 = new Cell(0, 0, CellType.PATH);
        Cell addingTestCell2 = new Cell(0, 1, CellType.PATH);
        testGraph.put(addingTestCell1, new HashMap<>());
        testGraph.get(addingTestCell1).put(addingTestCell2, 1);

        //when
        testingMaze.addNeighborToCell(testCell, testingCell);

        //then
        assertThat(testingGraph).containsExactlyInAnyOrderEntriesOf(testGraph);
    }
}
