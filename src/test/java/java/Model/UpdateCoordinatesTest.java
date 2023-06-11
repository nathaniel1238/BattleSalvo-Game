package Model;

import cs3500.pa04.ShipType;
import cs3500.pa04.Coord;
import cs3500.pa04.Ship;
import cs3500.cs3500.pa03.Model.UpdateCoordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UpdateCoordinatesTest {

  @Test
  public void testUpdateCoord_NoOverlap() {
    // Arrange
    int height = 10;
    int width = 10;
    ShipType type = ShipType.BATTLESHIP;
    List<Ship> ships = new ArrayList<>();
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 1));

    // Act
    List<Coord> updatedCoords = UpdateCoordinates.updateCoord(height, width, type, ships, coords);

    // Assert
    Assertions.assertEquals(coords, updatedCoords);
  }

  @Test
  public void testUpdateCoord_Overlap() {
    // Arrange
    int height = 10;
    int width = 10;
    ShipType type = ShipType.BATTLESHIP;
    List<Ship> ships = new ArrayList<>();
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(1, 1));
    coords.add(new Coord(2, 1));
    ships.add(new Ship(type, coords));

    // Act
    List<Coord> updatedCoords = UpdateCoordinates.updateCoord(height, width, type, ships, coords);

    // Assert
    Assertions.assertNotEquals(coords, updatedCoords);
  }

  @Test
  public void testMatchingCoords_Matching() {
    // Arrange
    List<Coord> c1 = new ArrayList<>();
    c1.add(new Coord(1, 1));
    c1.add(new Coord(2, 2));
    List<Coord> c2 = new ArrayList<>();
    c2.add(new Coord(1, 1));
    c2.add(new Coord(2, 2));

    // Act
    boolean result = UpdateCoordinates.matchingCoords(c1, c2);

    // Assert
    Assertions.assertTrue(result);
  }

  @Test
  public void testMatchingCoords_NoMatching() {
    // Arrange
    List<Coord> c1 = new ArrayList<>();
    c1.add(new Coord(1, 1));
    c1.add(new Coord(2, 2));
    List<Coord> c2 = new ArrayList<>();
    c2.add(new Coord(3, 3));
    c2.add(new Coord(4, 4));

    // Act
    boolean result = UpdateCoordinates.matchingCoords(c1, c2);

    // Assert
    Assertions.assertFalse(result);
  }
}
