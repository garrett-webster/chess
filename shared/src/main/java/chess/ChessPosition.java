package chess;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {
    int row;
    int col;

    Map<Integer, String> colMap = Map.of(
        1, "a",
        2, "b",
        3, "c",
        4, "d",
        5, "e",
        6, "f",
        7, "g",
        8, "h"
    );

    public ChessPosition(int row, int col) {
        this.col = col;
        this.row = row;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return this.row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPosition that = (ChessPosition) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return this.col;
    }
    @Override
    public String toString() {
        return colMap.get(col) + row;
    }
}
