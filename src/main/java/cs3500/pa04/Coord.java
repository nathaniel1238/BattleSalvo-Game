package cs3500.pa04;

import java.util.Objects;

/**
 * represents the coordinate of the ship on the gameboard
 */
public class Coord {
  private int x;
  private int y;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * getter for the x coordinate
   * @return the x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * getter for the y coordinate
   * @return the y coordinate
   */
  public int getY() {
    return this.y;
  }
  @Override
  public String toString() {
    return "Coord(x=" + x + ", y=" + y + ")";
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Coord other = (Coord) obj;

    // Compare the x and y values of the objects
    return x == other.x && y == other.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
