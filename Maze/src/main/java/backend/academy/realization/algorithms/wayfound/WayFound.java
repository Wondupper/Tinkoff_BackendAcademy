package backend.academy.realization.algorithms.wayfound;

public interface WayFound {
    /**
     * Method to find way between two cells by them coordinates
     *
     * @param startRow    row of start cell
     * @param startColumn column of start cell
     * @param endRow      row of end cell
     * @param endColumn   column of end cell
     */
    void foundWay(int startRow, int startColumn, int endRow, int endColumn);
}
