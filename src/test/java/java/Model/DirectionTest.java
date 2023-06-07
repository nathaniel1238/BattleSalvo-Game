package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Direction enum.
 */
public class DirectionTest {

  /**
   * Test the randomDirection method of the Direction enum.
   * It should return a random direction, either VERTICAL or HORIZONTAL.
   */
  @Test
  public void testRandomDirection() {
    // Generate a large number of random directions
    int numIterations = 1000;
    Direction[] directions = new Direction[numIterations];
    for (int i = 0; i < numIterations; i++) {
      directions[i] = Direction.randomDirection();
    }

    // Check that both VERTICAL and HORIZONTAL directions are present
    boolean hasVertical = false;
    boolean hasHorizontal = false;
    for (Direction direction : directions) {
      if (direction == Direction.VERTICAL) {
        hasVertical = true;
      } else if (direction == Direction.HORIZONTAL) {
        hasHorizontal = true;
      }
    }

    // Assert that both directions are present
    assertTrue(hasVertical);
    assertTrue(hasHorizontal);
  }

}
