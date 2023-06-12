package cs3500.cs3500.pa03.Model;

import cs3500.pa04.Coord;
import cs3500.pa04.Ship;
import cs3500.pa04.ShipType;
import java.util.List;

/**
 * The UpdateCoordinates class provides methods to update ship coordinates based on certain conditions.
 */
public class UpdateCoordinates {

  /**
   * Updates the coordinates of a ship based on the specified height, width, ship type, existing ships, and current coordinates.
   * If there are overlapping coordinates with existing ships, a new set of random ship coordinates will be generated.
   *
   * @param height The height of the game board.
   * @param width  The width of the game board.
   * @param type   The type of ship being updated.
   * @param ships  The list of existing ships on the board.
   * @param coords The current coordinates of the ship being updated.
   * @return The updated coordinates, ensuring there are no overlapping coordinates with existing ships.
   */
  public static List<Coord> updateCoord(int height, int width, ShipType type, List<Ship> ships, List<Coord> coords) {
    if (ships != null) {
      for (Ship s : ships) {
        if (s != null && matchingCoords(s.getCoord(), coords)) {
          Direction direct = Direction.randomDirection();
          return updateCoord(height, width, type, ships, RandomCoordinates.generateShipCoord(height, width, type, direct));
        }
      }
    }
    return coords;
  }

  /**
   * Checks if there are any matching coordinates between two lists of coordinates.
   *
   * @param c1 The first list of coordinates.
   * @param c2 The second list of coordinates.
   * @return True if there are matching coordinates, false otherwise.
   */
  public static boolean matchingCoords(List<Coord> c1, List<Coord> c2) {
    if (c1 != null && c2 != null) {
      for (Coord coord1 : c1) {
        for (Coord coord2 : c2) {
          if (coord1 != null && coord2 != null &&
              coord1.getX() == coord2.getX() && coord1.getY() == coord2.getY()) {
            return true;
          }
        }
      }
    }
    return false;
  }
}

