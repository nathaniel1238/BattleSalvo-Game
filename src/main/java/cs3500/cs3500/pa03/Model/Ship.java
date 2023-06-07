package cs3500.cs3500.pa03.Model;

import cs3500.cs3500.pa03.Controller.ShipType;
import java.util.List;

/**
 * represents a ship on the baord its repective components like its
 * type, coordinates, and whether its vertical on the board
 */
public class Ship {
  private ShipType type;
  private List<Coord> coordinates;

  public Ship(ShipType type, List<Coord> coordinates) {
    this.type = type;
    this.coordinates = coordinates;
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
}
