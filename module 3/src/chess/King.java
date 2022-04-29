package chess;

/**
 * This class represents a King. It defines all the operations mandated by
 * the Chess piece interface.
 */
public class King extends AbstractChessPiece {
  /**
   * Constructs a King chess piece with the given row and column
   * and color.
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @param color the color of the cell
   */
  public King(int row, int col, Color color) {
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
    
    if (r - 1 >= 0 && r - 1 <= 7) {
      arr[r - 1][c] = 1;
    }
    
    if (r + 1 >= 0 && r + 1 <= 7) {
      arr[r + 1][c] = 1;
    }
    
    if (c - 1 >= 0 && c - 1 <= 7) {
      arr[r][c - 1] = 1;
    }
    
    if (c + 1 >= 0 && c + 1 <= 7) {
      arr[r][c + 1] = 1;
    }
    
    if (c - 1 >= 0 && c - 1 <= 7 && r - 1 >= 0 && r - 1 <= 7) {
      arr[r - 1][c - 1] = 1;
    }
    
    if (c - 1 >= 0 && c - 1 <= 7 && r + 1 >= 0 && r + 1 <= 7) {
      arr[r + 1][c - 1] = 1;
    }
    
    if (c + 1 >= 0 && c + 1 <= 7 && r + 1 >= 0 && r + 1 <= 7) {
      arr[r + 1][c + 1] = 1;
    }
    
    if (c + 1 >= 0 && c + 1 <= 7 && r - 1 >= 0 && r - 1 <= 7) {
      arr[r - 1][c + 1] = 1;
    }
    
    return arr[row][col] == 1;
  }
 
}