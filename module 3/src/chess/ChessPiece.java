package chess;

/**
 * This interface contains all operations that all types of chess piece should
 * support.
 */
public interface ChessPiece {
  /**
   * Returns the row position of this chess piece.
   * @return the row of this chess piece
   */
  int getRow();
  
  /**
   * Returns the column position of this chess piece.
   * @return the column of this chess piece
   */
  int getColumn();
  
  /**
   * print out the color of this chess piece, black or white.
   * @return color color of the chess piece
   */
  Color getColor();
  
  /**
   * get the color of the chess piece by color.
   * @param color the color of the cell.
   * @return integer representation of the color, 1 if black, 0 if white.
   */
  int getColorIndex(Color color);
  
  /**
   * Returns whether this cell can move to the row and column position.
   * @param row the row position of the new cell
   * @param col the column position of the new cell
   * @return true or false whether this cell can move to certain position
   */
  boolean canMove(int row, int col);
  
  /**
   * Returns whether this piece can kill another piece starting at its current location.
   * @param piece the piece that need to be killed
   * @return true or false whether this piece can kill another piece
   */
  boolean canKill(ChessPiece piece);
  
}
