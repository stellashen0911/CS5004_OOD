package chess;

/**
 * This class represents a Knight. It defines all the operations mandated by
 * the Chess piece interface.
 */
public class Knight extends AbstractChessPiece {
  /**
   * Constructs a Knight chess piece with the given row and column
   * and color.
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @param color the color of the cell
   */
  public Knight(int row, int col, Color color) {
    super(row, col, color);
  }
  
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    /* initialize an 2D array with 7 rows and 7 columns with all 0 */
    int[][] arr = new int[8][8];
    int currentRow = this.getRow();
    int currentCol = this.getColumn();
    
    /* set two variables r and c to iterate through the board*/
    int r = currentRow;
    int c = currentCol;
    
    /* chess stay the same cannot consider move */
    if (r == row && c == col) {
      return false;
    }
    
    if (r + 2 >= 0 && r + 2 < arr.length && c + 1 >= 0 && c + 1 < arr.length) {
      arr[r + 2][c + 1] = 1;
    }
    
    if (r + 2 >= 0 && r + 2 < arr.length && c - 1 >= 0 && c - 1 < arr.length) {
      arr[r + 2][c - 1] = 1;
    }
   
    if (r - 2 >= 0 && r - 2 < arr.length && c + 1 >= 0 && c + 1 < arr.length) {
      arr[r - 2][c + 1] = 1;
    }
    
    if (r - 2 >= 0 && r - 2 < arr.length && c - 1 >= 0 && c - 1 < arr.length) {
      arr[r - 2][c - 1] = 1;
    }
  
    if (r - 1 >= 0 && r - 1 < arr.length && c + 2 >= 0 && c + 2 < arr.length) {
      arr[r - 1][c + 2] = 1;
    }
    
    if (r - 1 >= 0 && r - 1 < arr.length && c - 2 >= 0 && c - 2 < arr.length) {
      arr[r - 1][c - 2] = 1;
    }
    
    if (r + 1 >= 0 && r + 1 < arr.length && c - 2 >= 0 && c - 2 < arr.length) {
      arr[r + 1][c - 2] = 1;
    }
    
    if (r + 1 >= 0 && r + 1 < arr.length && c + 2 >= 0 && c + 2 < arr.length) {
      arr[r + 1][c + 2] = 1;
    }
    
    return arr[row][col] == 1;
  }
  
}
