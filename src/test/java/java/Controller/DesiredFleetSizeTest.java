package Controller;

import cs3500.cs3500.pa03.Controller.DesiredFleetSize;
import cs3500.pa04.ShipType;
import java.util.Map;
import java.util.Scanner;

public class DesiredFleetSizeTest {
  public static void main(String[] args) {
    testReadFleet();
  }

  public static void testReadFleet() {
    Scanner scanner = new Scanner(System.in);
    Readable read = (Readable) System.in;
    DesiredFleetSize desiredFleetSize = new DesiredFleetSize(scanner, read);

    // Test case 1: Valid fleet size
    System.out.println("Enter fleet size: (e.g., 1 2 1 1)");
    Map<ShipType, Integer> fleetMap1 = desiredFleetSize.readFleet(5);
    System.out.println("Fleet size: " + fleetMap1);

    // Test case 2: Invalid fleet size (exceeds maximum)
    System.out.println("Enter fleet size: ( 2 2 2 2)");
    try {
      Map<ShipType, Integer> fleetMap2 = desiredFleetSize.readFleet(5);
      System.out.println("Fleet size: " + fleetMap2);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Test case 3: Invalid fleet size (missing ship type value)
    System.out.println("Enter fleet size: (e.g., 0 2 1 1)");
    try {
      Map<ShipType, Integer> fleetMap3 = desiredFleetSize.readFleet(5);
      System.out.println("Fleet size: " + fleetMap3);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
