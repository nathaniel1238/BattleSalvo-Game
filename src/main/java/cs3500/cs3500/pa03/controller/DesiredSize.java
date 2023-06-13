package cs3500.cs3500.pa03.controller;

import java.util.ArrayList;
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
   *
   * @return an array of integers with the height and the width inside
   */
  public List<Integer> readSize() {
    List<Integer> dimensions = new ArrayList<>();

    boolean validInput = false;
    while (!validInput) {
      try {
        scanner = new Scanner(read);
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        if (height >= 6 && height <= 15 && width >= 6 && width <= 15) {
          dimensions.add(height);
          dimensions.add(width);
          validInput = true; // Input is valid, exit the loop
        } else {
          System.out.println("Please enter values within the range [6, 15].");
        }
      } catch (Exception e) {
        System.out.println("Invalid input. Please try again.");
      }
    }
    return dimensions;
  }

}

