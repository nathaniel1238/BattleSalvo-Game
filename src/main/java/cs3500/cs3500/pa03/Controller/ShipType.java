package cs3500.cs3500.pa03.Controller;

/**
 * represents the 4 different types of ships that have to be on the board
 */
public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  private final int size;

  private ShipType(int size) {
    this.size = size;
  }

  /**
   * Returns the size of the ship.
   *
   * @return the size of the ship
   */
  public int getSize() {
    return size;
  }
}
