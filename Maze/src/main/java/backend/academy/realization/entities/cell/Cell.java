package backend.academy.realization.entities.cell;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class Cell {
    private int row;

    private int column;

    private CellType type;

    public Cell(int row, int column, CellType type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public Cell() {
    }
}
