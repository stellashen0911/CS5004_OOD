package chess;

/**
 * This class represents a Queen. It defines all the operations mandated by
 * the Chess piece interface.
 */
public class Queen extends AbstractChessPiece {
  /**
   * Constructs a Queen chess piece with the given row and column
   * and color.
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @param color the color of the cell
   */
  public Queen(int row, int col, Color color) {
    super(row, col, color);
  }
  
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    /* initialize an 2D array with 8 rows and 8 columns with all 0 */
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

    /* set the north east line of current cell as available to move */
    while (r < 8 && c < 8) {
      arr[r][c] = 1;
      r++;
      c++;
    }
    
    r = currentRow;
    c = currentCol;
    /* set the south west line of current cell as available to move */
    while (r >= 0 && c >= 0) {
      arr[r][c] = 1;
      r--;
      c--;
    }
    
    r = currentRow;
    c = currentCol;
    /* set the north west line of current cell as available to move */
    while (r < 8 && c >= 0) {
      arr[r][c] = 1;
      r++;
      c--;
    }
    
    r = currentRow;
    c = currentCol;
    /* set the south east line of current cell as available to move */
    while (r >= 0 && c < 8) {
      arr[r][c] = 1;
      r--;
      c++;
    }
    
    r = currentRow;
    c = currentCol;
    for (int i = 0; i <= 7; i++) {
      arr[r][i] = 1;
    }
    
    r = currentRow;
    c = currentCol;
    for (int i = 0; i <= 7; i++) {
      arr[i][c] = 1;
    }
    
    return arr[row][col] == 1;
  }
  
}

