package cs3500.pa04;

import cs3500.cs3500.pa03.Model.Direction;
import cs3500.pa04.Coord;
import cs3500.pa04.ShipType;
import java.util.List;

/**
 * represents a ship on the baord its repective components like its
 * type, coordinates, and whether its vertical on the board
 */
public class Ship {
  private ShipType type;
  private List<Coord> coordinates;
  private Direction direction;

  public Ship(ShipType type, List<Coord> coordinates, Direction direction) {
    this.type = type;
    this.coordinates = coordinates;
    this.direction = direction;
  }

  /**
   * getter for the coordinates of a ship
   * @return the coordinate field of a ship
   */
  public List<Coord> getCoord() {
    return this.coordinates;
  }

  /**
   * getter for the coordinates of a ship
   * @return the coordinate field of a ship
   */
  public ShipType getType() {
    return this.type;
  }

  public Direction getDirection() {
    return this.direction;
  }
}
