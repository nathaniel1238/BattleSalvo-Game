package Model;

import cs3500.cs3500.pa03.Model.Coord;
import cs3500.cs3500.pa03.Controller.ShipType;
import cs3500.cs3500.pa03.Model.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomCoordinatesTest {

  /**
   * Tests the {@link RandomCoordinates#generateShipCoord(int, int, ShipType, Direction)} method
   * for generating vertical ship coordinates.
   */
  @Test
  public void testGenerateShipCoordVertical() {
    int height = 10;
    int width = 10;
    ShipType type = ShipType.CARRIER;
    Direction direction = Direction.VERTICAL;

    List<Coord> coordinates = RandomCoordinates.generateShipCoord(height, width, type, direction);

    assertEquals(type.getSize(), coordinates.size());
    assertTrue(areCoordinatesValid(height, width, coordinates, direction));
  }

  /**
   * Tests the {@link RandomCoordinates#generateShipCoord(int, int, ShipType, Direction)} method
   * for generating horizontal ship coordinates.
   */
  @Test
  public void testGenerateShipCoordHorizontal() {
    int height = 10;
    int width = 10;
    ShipType type = ShipType.BATTLESHIP;
    Direction direction = Direction.HORIZONTAL;

    List<Coord> coordinates = RandomCoordinates.generateShipCoord(height, width, type, direction);

    assertEquals(type.getSize(), coordinates.size());
    assertTrue(areCoordinatesValid(height, width, coordinates, direction));
  }

  /**
   * Checks if the generated ship coordinates are valid within the given board dimensions
   * and direction.
   *
   * @param height     the height of the board
   * @param width      the width of the board
   * @param coordinates the generated ship coordinates
   * @param direction  the direction of the ship
   * @return true if the coordinates are valid, false otherwise
   */
  private boolean areCoordinatesValid(int height, int width, List<Coord> coordinates, Direction direction) {
    if (direction.equals(Direction.VERTICAL)) {
      int maxX = coordinates.stream().mapToInt(Coord::getX).max().orElse(0);
      int maxY = coordinates.stream().mapToInt(Coord::getY).max().orElse(0);
      return maxX < width && maxY < height;
    } else if (direction.equals(Direction.HORIZONTAL)) {
      int maxX = coordinates.stream().mapToInt(Coord::getX).max().orElse(0);
      int maxY = coordinates.stream().mapToInt(Coord::getY).max().orElse(0);
      return maxX < width && maxY < height;
    }
    return false;
  }

//  /**
//   * Tests the {@link RandomCoordinates#coord_count(ShipType)} method
//   * for calculating the number of coordinates for each ship type.
//   */
//  @Test
//  public void testCoordCount() {
//    assertEquals(ShipType.CARRIER.getSize(), RandomCoordinates.coord_count(ShipType.CARRIER));
//    assertEquals(ShipType.BATTLESHIP.getSize(), RandomCoordinates.coord_count(ShipType.BATTLESHIP));
//    assertEquals(ShipType.DESTROYER.getSize(), RandomCoordinates.coord_count(ShipType.DESTROYER));
//    assertEquals(ShipType.SUBMARINE.getSize(), RandomCoordinates.coord_count(ShipType.SUBMARINE));
//  }
}
