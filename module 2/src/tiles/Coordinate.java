package tiles;


/**
 * This class represents a coordinate.
 * Each coordinate has the x value and y value.
 */
public class Coordinate {
  private int xcoordinate;
  private int ycoordinate;
  
  /**
   * Constructs a Coordinate object and initializes it to the given x, y value.
   * @param x   the x value of this coordinate;
   * @param y   the y value of this coordinate;
   */
  public Coordinate(int x, int y) {
    this.xcoordinate = x;
    this.ycoordinate = y;
  }

  /**
   * Get the x value of the coordinate.
   * @return the x;
   */
  public int getX() {
    return this.xcoordinate;
  }

  /**
   * set the x value of the coordinate.
   * @param x the x to set;
   */
  public void setX(int x) {
    this.xcoordinate = x;
  }

  /**
   * Get the y value of the coordinate.
   * @return the y;
   */
  public int getY() {
    return this.ycoordinate;
  }

  /**
   * set the y value of the coordinate.
   * @param y the y to set;
   */
  public void setY(int y) {
    this.ycoordinate = y;
  }
  
}
