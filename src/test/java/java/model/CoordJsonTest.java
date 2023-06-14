package java.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import cs3500.pa04.Coord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordJsonTest {

  @Test
  void getX() {
    Coord coord = new Coord(3, 5);
    int x = coord.getX();
    assertEquals(3, x);
  }

  @Test
  public void testGetY() {
    Coord coord = new Coord(3, 5);
    int y = coord.getY();
    assertEquals(5, y);
  }

  @Test
  public void testCoordinates() {
    Coord coord = new Coord(3, 5);
    assertEquals(3, coord.getX());
    assertEquals(5, coord.getY());
  }

  @Test
  public void testEquals_SameInstance_ReturnsTrue() {
    Coord coord = new Coord(1, 2);
    assertEquals(coord, coord);
  }

  @Test
  public void testEquals_EqualCoords_ReturnsTrue() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(1, 2);
    assertEquals(coord1, coord2);
  }

  @Test
  public void testEquals_DifferentCoords_ReturnsFalse() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(3, 4);
    assertNotEquals(coord1, coord2);
  }

  @Test
  public void testEquals_DifferentTypes_ReturnsFalse() {
    Coord coord = new Coord(1, 2);
    Object obj = new Object();
    assertNotEquals(coord, obj);
  }

  @Test
  public void testToString() {
    Coord coord = new Coord(1, 2);
    String expected = "Coord(x=1, y=2)";
    assertEquals(expected, coord.toString());
  }

  @Test
  public void testHashCode_EqualCoords_ReturnsSameValue() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(1, 2);
    assertEquals(coord1.hashCode(), coord2.hashCode());
  }

  @Test
  public void testHashCode_DifferentCoords_ReturnsDifferentValue() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(3, 4);
    assertNotEquals(coord1.hashCode(), coord2.hashCode());
  }
}