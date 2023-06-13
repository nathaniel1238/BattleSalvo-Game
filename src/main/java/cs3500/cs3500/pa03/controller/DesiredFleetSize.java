package cs3500.cs3500.pa03.controller;

import cs3500.pa04.ShipType;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * represents the user's desired fleet size for their board
 */
public class DesiredFleetSize {
  private Scanner scanner;
  private Readable read;

  public DesiredFleetSize(Scanner scanner, Readable read) {
    this.scanner = scanner;
    this.read = read;
  }

  /**
   * read the user inputs regarding their desired fleet size
   *
   * @param max the lesser value of the board dimension
   * @return number of ships for each shiptype for the board
   */
  public Map<ShipType, Integer> readFleet(int max) {
    boolean validInput = false;
    while (!validInput) {
      try {
        scanner = new Scanner(read);
        List<Integer> userfleet = new ArrayList<>();
        int carriersize = scanner.nextInt();
        int batteshipsize = scanner.nextInt();
        int destroyersize = scanner.nextInt();
        int submarinesize = scanner.nextInt();
        userfleet.add(carriersize);
        userfleet.add(batteshipsize);
        userfleet.add(destroyersize);
        userfleet.add(submarinesize);
        int valid_total = sum(carriersize, batteshipsize, destroyersize, submarinesize);
        if (valid_total <= max) {
          Map<ShipType, Integer> fleetMap = new LinkedHashMap<>();
          ShipType[] shipTypes = ShipType.values();
          for (int i = 0; i < userfleet.size(); i++) {
            fleetMap.put(shipTypes[i], userfleet.get(i));
          }
          validInput = true; // Input is valid, exit the loop
          return fleetMap;
        } else {
          System.out.println("Please make sure the total is less than or "
              + "equal to the maximum number of ships.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter integer values.");
        scanner.nextLine();
      }
    }
    return null;
  }


  /**
   *
   * @param v1 Carrier user value
   * @param v2 BattleShip user value
   * @param v3 Destroyer user value
   * @param v4 Submarine user value
   *
   * @return the sum of all the values
   */
  private int sum(int v1, int v2, int v3, int v4) {
    if ((v1 != 0 && v2 != 0 && v3 != 0 && v4 != 0)) {
      return v1 + v2 + v3 + v4;
    } else {
      throw new IllegalArgumentException("Please make sure you assign at "
          + "least one value to every shiptype!");
    }
  }
}
