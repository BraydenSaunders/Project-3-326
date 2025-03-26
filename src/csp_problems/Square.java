package csp_problems;
/**
 * This record represents a square in a Sudoku grid.
 * Note that for a record, the equals() and hashCode()
 * methods are automatically generated by Java, making it
 * safe to use a record as a map key.
 * @param row
 * @param column
 */
public record Square(int row, int column) { }
