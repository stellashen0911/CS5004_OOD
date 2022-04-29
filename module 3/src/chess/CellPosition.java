package chess;

/**
 * This class represents a cell position.
 */
public class CellPosition {
  private int row;
  private int column;
  
  /**
   * Constructs a cell position and initializes it to the given. 
   * row and column.
   * @param row   the row of the cell;
   * @param column  the column of the cell;
   */
  public CellPosition(int row, int column) throws IllegalArgumentException {
    if (row < 0 || row > 7) {
      throw new IllegalArgumentException("Row must in the range of 0 to 7");
    }
    
    if (column < 0 || column > 7) {
      throw new IllegalArgumentException("Column must in the range of 0 to 7");
    }
    
    this.row = row;
    this.column = column;
  }

  /**
   * Get the row of the cell.
   * @return the row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * set the row of the cell.
   * @param row the row to set
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Get the column of the cell.
   * @return the column
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * set the column of the cell.
   * @param column the column to set
   */
  public void setColumn(int column) {
    this.column = column;
  }
  
}
