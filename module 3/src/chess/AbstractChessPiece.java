package chess;

/**
 * An abstract class for chess piece that contains all of the code that is common to
 * all classes that implement the ChessPiece interface.
 */
public abstract class AbstractChessPiece implements ChessPiece {
  protected CellPosition cell;
  protected Color color;
  
  public AbstractChessPiece() {}
  
  /**
   * Constructor for chess piece.
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @param color the color of the cell
   */
  public AbstractChessPiece(int row, int col, Color color) {
    this.cell = new CellPosition(row, col);
    this.color = color;
  }
  
  @Override
  public int getColorIndex(Color color) {
    switch (this.color) {
      case BLACK:
        return 1;
      case WHITE:
        return 0;
      default:
        return -1;
    }
  }
  
  @Override
  public int getRow() {
    return cell.getRow();
  }
  
  @Override
  public int getColumn() {
    return cell.getColumn();
  }
  
  @Override
  public Color getColor() {
    switch (this.color) {
      case BLACK:
        return Color.BLACK;
      case WHITE:
        return Color.WHITE;
      default:
        return Color.BLACK;
    }
  }
  
  /**
   * Abstract method canMove that need to be implemented by different chess piece type.
   * Returns whether this cell can move to the row and column position.
   * @param row the row position of the new cell
   * @param col the column position of the new cell
   * @return true or false whether this cell can move to certain position
   */
  public abstract boolean canMove(int row, int col);
  
  @Override
  public boolean canKill(ChessPiece piece) {
    if (this.getColorIndex(color) == piece.getColorIndex(this.color)) {
      return false;
    }
    
    if (!canMove(piece.getRow(), piece.getColumn())) {
      return false;
    }
 
    return true;
  }
  
}
