package chess;

/**
 * This class represents a Rook. It defines all the operations mandated by
 * the Chess piece interface.
 */
public class Rook extends AbstractChessPiece {
  /**
   * Constructs a Rook chess piece with the given row and column
   * and color.
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @param color the color of the cell
   */
  public Rook(int row, int col, Color color) {
    super(row, col, color);
  }
  
  @Override
  public boolean canMove(int row, int col) {
    if (row > 7 || col > 7 || row < 0 || col < 0) {
      return false;
    }
    int currentRow = this.getRow();
    int currentCol = this.getColumn();
    
    /* set two variables r and c to iterate through the board*/
    int r = currentRow;
    int c = currentCol;
    
    /* chess stay the same cannot consider move */
    if (r == row && c == col) {
      return false;
    }
    
    r = currentRow;
    c = currentCol;
    
    /* initialize an 2D array with 7 rows and 7 columns with all 0 */
    int[][] arr = new int[8][8];
    for (int i = 0; i < arr.length; i++) {
      arr[r][i] = 1;
    }
    
    r = currentRow;
    c = currentCol;
    for (int i = 0; i < arr.length; i++) {
      arr[i][c] = 1;
    }
    
    return arr[row][col] == 1;
  }
  
}
