//package Model;
//import cs3500.pa04.ShipType;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Tests for the Ship class.
// */
//public class ShipTest {
//
//  /**
//   * Test the getCoord method of the Ship class.
//   * It should return the coordinates of the ship.
//   */
//  @Test
//  public void testGetCoord() {
//    // Create a ship with some coordinates
//    List<Coord> coordinates = new ArrayList<>();
//    coordinates.add(new Coord(1, 1));
//    coordinates.add(new Coord(1, 2));
//    Ship ship = new Ship(ShipType.BATTLESHIP, coordinates);
//
//    // Get the coordinates of the ship and assert that it matches the input coordinates
//    List<Coord> shipCoordinates = ship.getCoord();
//    assertEquals(coordinates, shipCoordinates);
//  }
//
//  /**
//   * Test the getType method of the Ship class.
//   * It should return the type of the ship.
//   */
//  @Test
//  public void testGetType() {
//    // Create a ship with a specific type
//    List<Coord> coordinates = new ArrayList<>();
//    coordinates.add(new Coord(1, 1));
//    coordinates.add(new Coord(1, 2));
//    Ship ship = new Ship(ShipType.BATTLESHIP, coordinates);
//
//    // Get the type of the ship and assert that it matches the input type
//    ShipType shipType = ship.getType();
//    assertEquals(ShipType.BATTLESHIP, shipType);
//  }
//
//}
