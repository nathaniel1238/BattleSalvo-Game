package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents the coordinates of a ship on the game board.
 */
public class Coord {

  private int x;
  private int y;

  /**
   * Constructs a `Coord` object with the specified x and y coordinates.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  @JsonCreator
  public Coord(@JsonProperty("x") int x, @JsonProperty("y") int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate of the `Coord`.
   *
   * @return the x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of the `Coord`.
   *
   * @return the y coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns a string representation of the `Coord` in the format "Coord(x=x, y=y)".
   *
   * @return a string representation of the `Coord`
   */
  @Override
  public String toString() {
    return "Coord(x=" + x + ", y=" + y + ")";
  }

  /**
   * Checks if this `Coord` is equal to another object.
   *
   * @param obj the object to compare to
   * @return `true` if the objects are equal, `false` otherwise
   */
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

  /**
   * Returns the hash code value for the `Coord`.
   *
   * @return the hash code value
   */
  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
