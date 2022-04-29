package tiles;

import java.util.NoSuchElementException;

/**
 * This class represents a Rectangle.
 * Each rectangle has the (x, y) coordinates of its lower-left corner, its width, 
 * and its height.
 */
public class Rectangle {
  private int width;
  private int height;
  private Coordinate corner;
  
  /**
   * Constructs a rectangle object and initializes it to the given. 
   * width, height and coordinate.
   * @param width   the width of this rectangle;
   * @param height  the height of this rectangle;
   * @param x       the x coordinate of the left down corner;
   * @param y       the y coordinate of the left down corner;
   */
  public Rectangle(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width < 0) {
      throw new IllegalArgumentException("Width cannot be negative");
    }
    
    if (height < 0) {
      throw new IllegalArgumentException("Height cannot be negative");
    }
    
    this.width = width;
    this.height = height;
    this.corner = new Coordinate(x, y);
  }
  
  
  /**
   * Get the width of the Rectangle.
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }


  /**
   * Set the width of the rectangle.
   * @param width the width to set;
   */
  public void setWidth(int width) {
    this.width = width;
  }


  /**
   * Get the height of the Rectangle.
   * @return the height;
   */
  public int getHeight() {
    return this.height;
  }


  /**
   * Set the height of the rectangle.
   * @param height the height to set;
   */
  public void setHeight(int height) {
    this.height = height;
  }


  /**
   * Get the lower-left corner coordinate of the Rectangle.
   * @return the corner;
   */
  public Coordinate getCorner() {
    return this.corner;
  }


  /**
   * Set the corner coordinate of the rectangle.
   * @param corner the corner to set;
   */
  public void setCorner(Coordinate corner) {
    this.corner = corner;
  }



  /**
   * Set the corner coordinate of the rectangle.
   * @param other the other rectangle to compare with;
   * @return returns whether the other rectangle overlap with self rectangle;
   */
  public boolean overlap(Rectangle other) {
    int leftUpY = this.getCorner().getY() + this.getHeight();
    int rightX = this.getCorner().getX() + this.getWidth();
    int otherUpY = other.getCorner().getY() + other.getHeight();
    int otherRightX = other.getCorner().getX() + other.getWidth();
    
    /* if the other is on the left side of this or other is on the right side of this
     * there will be no overlap between two rectangles
     */
    if ((this.getCorner().getX() >= otherRightX) || (other.getCorner().getX() >= rightX)) {
      return false;
    }
    
    /* if the other is on the up side of this or other is on the down side of this
     * there will be no overlap between two rectangles
     */
    if ((this.getCorner().getY() >= otherUpY) || (other.getCorner().getY() >= leftUpY)) {
      return false;
    }
    
    /* else the other and this rectangle has some overlap, return true
     */
    return true;
  }
  
  
  /**
   * return intersection of the two rectangles.
   * if there is no intersection, throw error
   * @param other the other rectangle to compare with;
   * @return returns the intersected area as a new rectangle;
   */
  public Rectangle intersect(Rectangle other) throws NoSuchElementException {
    if (this.overlap(other) == false) {
      throw new NoSuchElementException("There is not intersection between these two rectangles");
    }
    
    int leftUpY = this.getCorner().getY() + this.getHeight();
    int leftUpX = this.getCorner().getX();
    int rightX = this.getCorner().getX() + this.getWidth();
    int rightY = this.getCorner().getY();
    
    int otherLeftUpY = other.getCorner().getY() + other.getHeight();
    int otherLeftUpX = other.getCorner().getX();
    int otherRightdownX = other.getCorner().getX() + other.getWidth();
    int otherRightdownY = other.getCorner().getY();
    
    /* if the other is inside of this rectangle
     * the intersection is just the other rectangle;
     */
    if ((leftUpX <= otherLeftUpX) && (leftUpY >= otherLeftUpY) 
        && (rightX >= otherRightdownX) && (rightY <= otherRightdownY)) {
      return other;
    }
    
    /* if the this is inside of the other rectangle
     * the intersection is just this self rectangle;
     */
    if ((leftUpX >= otherLeftUpX) && (leftUpY <= otherLeftUpY) 
        && (rightX <= otherRightdownX) && (rightY >= otherRightdownY)) {
      return this;
    }
    
    int newLeft = Math.max(leftUpX, otherLeftUpX);
    int newRight = Math.min(rightX, otherRightdownX);
    int newBottom = Math.max(rightY, otherRightdownY);
    int newTop = Math.min(leftUpY, otherLeftUpY);
    
    Rectangle intersectRectangle = new Rectangle(newLeft, newBottom,
                                                 newRight - newLeft, 
                                                 newTop - newBottom);
    return intersectRectangle;
  }

  /**
   * return union of the two rectangles.
   * @param other the other rectangle to compare with;
   * @return returns the union section of two rectangles as a new rectangle;
   */
  public Rectangle union(Rectangle other) {
    int leftUpY = this.getCorner().getY() + this.getHeight();
    int leftUpX = this.getCorner().getX();
    int rightX = this.getCorner().getX() + this.getWidth();
    int rightY = this.getCorner().getY();
    
    int otherLeftUpY = other.getCorner().getY() + other.getHeight();
    int otherLeftUpX = other.getCorner().getX();
    int otherRightdownX = other.getCorner().getX() + other.getWidth();
    int otherRightdownY = other.getCorner().getY();
    
    /* if the other is inside of this rectangle
     * the union is just this self rectangle;
     */
    if ((leftUpX <= otherLeftUpX) && (leftUpY >= otherLeftUpY) 
        && (rightX >= otherRightdownX) && (rightY <= otherRightdownY)) {
      return this;
    }
    
    /* if the self is inside of the other rectangle
     * the intersection is just the other rectangle;
     */
    if ((leftUpX >= otherLeftUpX) && (leftUpY <= otherLeftUpY) 
        && (rightX <= otherRightdownX) && (rightY >= otherRightdownY)) {
      return other;
    }
    
    int newLeft = Math.min(leftUpX, otherLeftUpX);
    int newRight = Math.max(rightX, otherRightdownX);
    int newBottom = Math.min(rightY, otherRightdownY);
    int newTop = Math.max(leftUpY, otherLeftUpY);
    
    Rectangle unionRectangle = new Rectangle(newLeft, newBottom,
                                             newRight - newLeft,
                                             newTop - newBottom);
    return unionRectangle;
  }
  
  /**
   * Returns a string representation of this rectangle with x, y coordinates and height and width.
   * @return a formatted string represent the x, y coordinate and the width and the height;
   */
  public String toString() {
    return String.format(
        "x:%d, y:%d, w:%d, h:%d", 
        this.getCorner().getX(), 
        this.getCorner().getY(), 
        this.getWidth(), 
        this.getHeight()
    );
  }
  
}
