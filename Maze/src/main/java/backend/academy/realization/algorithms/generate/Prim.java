package backend.academy.realization.algorithms.generate;

import backend.academy.realization.entities.cell.Cell;
import backend.academy.realization.entities.cell.CellType;
import backend.academy.realization.entities.maze.Maze;
import java.util.Set;

public class Prim extends Generator implements Generate {

    public Prim(int height, int width) {
        super(height, width);
    }

    /**
     * Method to generate maze by Prim algorithm
     *
     * @return generated maze by Prim algorithm
     */
    @Override
    public Maze generateMaze() {
        Maze maze = buildMazeWithBlocks();
        doPrimAlgorithm(maze);
        return maze;
    }

    /**
     * Creating a minimal residual tree from a maze by Prim algorithm
     *
     * @param maze with only block cells
     */
    private void doPrimAlgorithm(Maze maze) {
        clearVisited();
        Cell firstCell = maze.graph().keySet().stream().toList().getFirst();
        visited.add(firstCell);
        while (visited.size() < maze.graph().keySet().size()) {
            for (int i = 0; i < visited.size(); i++) {
                Cell visitedCell = visited.get(i);
                Set<Cell> cellNeighbors = maze.graph().get(visitedCell).keySet();
                for (Cell neighbor : cellNeighbors) {
                    if (!visited.contains(neighbor)) {
                        visitedCell.type(CellType.PATH);
                        neighbor.type(CellType.PATH);
                        maze.graph().get(visitedCell).put(neighbor, 1);
                        maze.graph().get(neighbor).put(visitedCell, 1);
                        visited.add(neighbor);
                    }
                }
            }
        }
        blockingCells(maze);
    }
}
