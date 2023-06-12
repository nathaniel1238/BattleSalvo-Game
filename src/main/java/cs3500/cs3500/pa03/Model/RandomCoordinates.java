package cs3500.cs3500.pa03.Model;

import cs3500.pa04.Coord;
import cs3500.pa04.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents the randomized coordinates of a ship
 */
public class RandomCoordinates {
  /**
   * creates a set of randomized coordinates for the ship placement on the board, checking if the
   * direction is vert or not and randomizing from there
   * @param height the height of the board
   * @param width the width of the board
   * @param type the type of ship
   * @param direct the direction of the ship
   * @return a collection of randomized coordinates for the given ship
   */
  public static List<Coord> generateShipCoord(int height, int width, ShipType type, Direction direct) {
    List<Coord> coordinates = new ArrayList<>();
    Random random = new Random();
    int randWidth = random.nextInt(width);
    int randHeight = random.nextInt(height);
    int totalCoord = Math.min(coord_count(type), 6);

    if (direct.equals(Direction.VERTICAL)) {
      for (int i = 0; i < totalCoord; i++) {
        Coord coord = new Coord(randWidth, i);
        coordinates.add(coord);
      }
    } else if (direct.equals(Direction.HORIZONTAL)) {
      for (int i = 0; i < totalCoord; i++) {
        Coord coord = new Coord(i, randHeight);
        coordinates.add(coord);
      }
    }
    return coordinates;
  }

  /**
   * checks which type of ship the parameter is a returns the number of coordinates for that
   * @param type the shiptype
   * @return total number of coordinates for a ship
   */
  private static int coord_count(ShipType type) {
    if (type.equals(ShipType.CARRIER)) {
      return ShipType.CARRIER.getSize();
    } else if (type.equals(ShipType.BATTLESHIP)) {
      return ShipType.BATTLESHIP.getSize();
    }else if (type.equals(ShipType.DESTROYER)) {
      return ShipType.DESTROYER.getSize();
    } else {
      return ShipType.SUBMARINE.getSize();
    }
  }
}
