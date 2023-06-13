package java.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.Coord;
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
}