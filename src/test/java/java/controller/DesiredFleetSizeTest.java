package java.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.cs3500.pa03.controller.DesiredFleetSize;
import cs3500.cs3500.pa03.controller.DesiredSize;
import cs3500.pa04.ShipType;
import java.io.StringReader;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class DesiredFleetSizeTest {

  @Test
  public void testReadFleet() {
    // Prepare
    String fleet_size = "1 1 1 1";
    int max = 6;
    Readable read = new StringReader(fleet_size);
    Scanner sc  = new Scanner(read);

    DesiredFleetSize user_fleet = new DesiredFleetSize(sc, read);
    Map<ShipType, Integer> list = user_fleet.readFleet(max);
    assertEquals(list.size(), 4);
  }

  @Test
  public void testFleetMax() {
    // Prepare
    String fleet_size = "2 2 1 1";
    int max = 6;
    Readable read = new StringReader(fleet_size);
    Scanner sc  = new Scanner(read);

    DesiredFleetSize user_fleet = new DesiredFleetSize(sc, read);
    Map<ShipType, Integer> list = user_fleet.readFleet(max);
    // Accessing the values
    Integer ship1Count = list.get(ShipType.CARRIER);
    Integer ship2Count = list.get(ShipType.BATTLESHIP);
    Integer ship3Count = list.get(ShipType.DESTROYER);
    Integer ship4Count = list.get(ShipType.SUBMARINE);

    // Asserting the values
    assertEquals(2, ship1Count);
    assertEquals(2, ship2Count);
    assertEquals(1, ship3Count);
    assertEquals(1, ship4Count);
  }

  @Test
  void testFleetSizeWithInvalidInput() {
    // Prepare
    String input = "2 2";  // Fewer values than expected
    int max = 8;
    Readable read = new StringReader(input);
    Scanner sc = new Scanner(read);
    DesiredFleetSize user_fleet = new DesiredFleetSize(sc, read);

    // Asserting the exception
    assertThrows(NoSuchElementException.class, () -> user_fleet.readFleet(max));
  }

  @Test
  void testSum() {
    // Prepare
    int one = 0;
    int two = 2;
    int three = 0;
    int four = 4;

    // Execute and Verify
    Exception exc = assertThrows(IllegalArgumentException.class, () -> {
      DesiredFleetSize user_fleet = new DesiredFleetSize(null, null);
      user_fleet.sum(one, two, three, four);
    });

    assertEquals("Please make sure you assign at least one value to every shiptype!", exc.getMessage());
  }

}
