package cs3500.pa04;

/**
 * Represents the four different types of ships that can be placed on the game board.
 */
public enum ShipType {

  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  private final int size;

  /**
   * Constructs a ShipType with the specified size.
   *
   * @param size the size of the ship
   */
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
