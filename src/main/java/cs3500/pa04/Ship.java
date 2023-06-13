package cs3500.pa04;

import cs3500.cs3500.pa03.model.Direction;
import java.util.List;

/**
 * Represents a ship on the game board, including its type, coordinates, and direction.
 */
public class Ship {

  private ShipType type;
  private List<Coord> coordinates;
  private Direction direction;

  /**
   * Constructs a ship with the specified type, coordinates, and direction.
   *
   * @param type        the type of the ship
   * @param coordinates the coordinates of the ship on the game board
   * @param direction   the direction of the ship (vertical or horizontal)
   */
  public Ship(ShipType type, List<Coord> coordinates, Direction direction) {
    this.type = type;
    this.coordinates = coordinates;
    this.direction = direction;
  }

  /**
   * Returns the coordinates of the ship on the game board.
   *
   * @return the coordinates of the ship
   */
  public List<Coord> getCoord() {
    return this.coordinates;
  }

  /**
   * Returns the type of the ship.
   *
   * @return the type of the ship
   */
  public ShipType getType() {
    return this.type;
  }

  /**
   * Returns the direction of the ship.
   *
   * @return the direction of the ship
   */
  public Direction getDirection() {
    return this.direction;
  }
}
