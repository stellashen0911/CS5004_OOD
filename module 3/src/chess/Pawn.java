package chess;

/**
 * This class represents a Pawn. It defines all the operations mandated by
 * the Chess piece interface.
 */
public class Pawn extends AbstractChessPiece {
  /**
   * Constructs a Pawn chess piece with the given row and column
   * and color.
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @param color the color of the cell
   */
  public Pawn(int row, int col, Color color) throws IllegalArgumentException {
    if ((row < 1 && color == Color.WHITE) || (row > 6 && color == Color.BLACK)) {
      throw new IllegalArgumentException("Row for pawn must in the range of 1 to 6");
    }
    this.cell = new CellPosition(row, col);
    this.color = color;
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
    
    /* when the piece is a black piece, it can only go from up to bottom */
    if (this.getColorIndex(this.color) == 1) {
      if (r >= 6) {
        arr[r - 1][c] = 1;
        arr[r - 2][c] = 1;
      } else {
        arr[r - 1][c] = 1;
      }
    } else {
      if (r <= 1) {
        arr[r + 1][c] = 1;
        arr[r + 2][c] = 1;
      } else {
        arr[r + 1][c] = 1;
      }
    }
    
    return arr[row][col] == 1;
  }
  
  @Override
  public boolean canKill(ChessPiece piece) {
    if (this.getColorIndex(color) == piece.getColorIndex(this.color)) {
      return false;
    }
    
    int r = this.getRow();
    int c = this.getColumn();
    
    int killRow = piece.getRow();
    int killCol = piece.getColumn();
    
    /* when the piece is a black piece, it can only go from up to bottom */
    if (this.getColorIndex(this.color) == 1) {
      if (r >= 6) {
        if (killRow == r - 1 || killRow == r - 2) {
          if (killCol == c + 1 || killCol == c - 1) {
            return true;
          }
        }
      } else {
        if (killRow == r - 1 && (killCol == c + 1 || killCol == c - 1)) {
          return true;
        }
      }
    } else {
      if (r <= 1) {
        if (killRow == r + 1 || killRow == r + 2) {
          if (killCol == c + 1 || killCol == c - 1) {
            return true;
          }
        } else {
          if (killRow == r + 1 && (killCol == c + 1 || killCol == c - 1)) {
            return true;
          }
        }
      } else {
        if (killRow == r + 1 && (killCol == c + 1 || killCol == c - 1)) {
          return true;
        }
      }  
    }
    return false;
  }
}
