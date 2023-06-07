package cs3500.cs3500.pa03.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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
   * @param max the lesser value of the board dimension
   * @return number of ships for each shiptype for the board
   */
  public Map<ShipType, Integer> readFleet(int max) {
    scanner = new Scanner(read);
    String input = scanner.nextLine();
    String[] parts = input.split(" ");
    List<String> partsList = new ArrayList<>(Arrays.asList(parts));
    List<Integer> user_fleet = new ArrayList<>(4);
    for (String s : partsList) {
      user_fleet.add(Integer.parseInt(s));
    }
    int valid_total = sum(user_fleet.get(0), user_fleet.get(1), user_fleet.get(2), user_fleet.get(3));
    if (valid_total <= max) {
      Map<ShipType, Integer> fleetMap = new LinkedHashMap<>();
      ShipType[] shipTypes = ShipType.values();

      for (int i = 0; i < user_fleet.size(); i++) {
        fleetMap.put(shipTypes[i], user_fleet.get(i));
      }
      return fleetMap;
    } else {
      throw new IllegalArgumentException("Please make sure the total less than or equal to the maximum number of ships");
    }
  }

  /**
   *
   * @param v1 Carrier user value
   * @param v2 BattleShip user value
   * @param v3 Destroyer user value
   * @param v4 Submarine user value
   * @return the sum of all the values
   */
  private int sum(int v1, int v2, int v3, int v4) {
    if ((v1 != 0 && v2 != 0 && v3 != 0 && v4 != 0)) {
      return v1 + v2 + v3 + v4;
    } else {
      throw new IllegalArgumentException("Please make sure you assign atleast one value to every shiptype!");
    }
  }
}
