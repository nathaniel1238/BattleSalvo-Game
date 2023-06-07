package cs3500.cs3500.pa03.Model;

import java.util.List;
import java.util.Random;

/**
 * represents the direction of the ship on the board
 */
public enum Direction {
  VERTICAL,
  HORIZONTAL;

  private static final List<Direction> VALUES =
      List.of(values());
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  /**
   * randomizes the direction of the ship
   * @return a random Direction which could be either vertical or horizontal
   */
  public static Direction randomDirection() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}