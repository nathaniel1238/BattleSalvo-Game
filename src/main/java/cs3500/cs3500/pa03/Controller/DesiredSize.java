package cs3500.cs3500.pa03.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * represents the user's desired dimensions of the game
 */
public class DesiredSize {
  private Scanner scanner;
  private Readable read;

  public DesiredSize(Scanner scanner, Readable read) {
    this.scanner = scanner;
    this.read = read;
  }

  /**
   * reads the user's desired board dimensions
   * @return an array of integers with the height and the width inside
   */
  public List<Integer> readSize() {
    Scanner scanner = new Scanner(read);
    String input = scanner.nextLine();
    String[] parts = input.split(" ");
    List<String> partsList = new ArrayList<>(Arrays.asList(parts));
    List<Integer> dimensions = new ArrayList<>();

    // the height and the width of the board
    dimensions.add(Integer.parseInt(partsList.get(0)));
    dimensions.add(Integer.parseInt(partsList.get(1)));

    if (dimensions.get(0) >= 6 && dimensions.get(0) <= 15 &&
        dimensions.get(1) >= 6 && dimensions.get(1) <= 15) {
      return dimensions;
    } else {
      throw new IllegalArgumentException("Please enter values within the given range.");
    }
  }
}

