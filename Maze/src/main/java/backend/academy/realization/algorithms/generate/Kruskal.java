package backend.academy.realization.algorithms.generate;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Kruskal extends Generator implements Generate {
    public Kruskal(int height, int width) {
        super(height, width);
    }

    /**
     * Method to generate maze by Kruskal algorithm
     *
     * @return generated maze by Kruskal algorithm
     */
    @Override
    public Maze generateMaze() {
        Maze maze = buildMazeWithBlocks();
        doKruskalAlgorithm(maze);
        return maze;
    }

    /**
     * Creating a minimal residual tree from a maze by Kruskal algorithm
     *
     * @param maze with only block cells
     */
    private void doKruskalAlgorithm(Maze maze) {
        clearVisited();
        List<Map<Cell, Cell>> edges = maze.getEdges();
        List<Set<Cell>> disjointSets = buildSingleDisjointSets(maze.graph().keySet());
        for (int i = 0; i < edges.size(); i++) {
            Map<Cell, Cell> edge = edges.get(i);
            Cell firstCell = edge.keySet().stream().toList().getFirst();
            Cell secondCell = edge.get(firstCell);
            if (!getContainingCellSetFromSetsList(disjointSets, firstCell).contains(secondCell)) {
                Set<Cell> firstSet = getContainingCellSetFromSetsList(disjointSets, firstCell);
                Set<Cell> secondSet = getContainingCellSetFromSetsList(disjointSets, secondCell);
                unionSetsFromSetsList(disjointSets, firstSet, secondSet);
                firstCell.type(CellType.PATH);
                secondCell.type(CellType.PATH);
                maze.graph().get(firstCell).put(secondCell, 1);
                maze.graph().get(secondCell).put(firstCell, 1);
            }
        }
        blockingCells(maze);
    }

    /**
     * Method for return list of sets of single cells from maze
     *
     * @param cells set of maze cells
     * @return list of sets of single cells from maze
     */
    private List<Set<Cell>> buildSingleDisjointSets(Set<Cell> cells) {
        List<Set<Cell>> singleDisjointSets = new ArrayList<>();
        for (Cell cell : cells) {
            Set<Cell> set = new HashSet<>();
            set.add(cell);
            singleDisjointSets.add(set);
        }
        return singleDisjointSets;
    }

    /**
     * Method for return set required by cell
     *
     * @param listSets list of sets in Kruskal algorithm
     * @param cell     cell by which the set is searched
     * @return set required by cell
     */
    private Set<Cell> getContainingCellSetFromSetsList(List<Set<Cell>> listSets, Cell cell) {
        for (Set<Cell> set : listSets) {
            if (set.contains(cell)) {
                return set;
            }
        }
        return Set.of();
    }

    /**
     * Method for union two sets from list of sets in Kruskal algorithm
     *
     * @param listSets  list of sets in Kruskal algorithm
     * @param firstSet  first set for unification
     * @param secondSet second set for unification
     */
    private void unionSetsFromSetsList(List<Set<Cell>> listSets, Set<Cell> firstSet, Set<Cell> secondSet) {
        firstSet.addAll(secondSet);
        listSets.remove(secondSet);
    }
}
