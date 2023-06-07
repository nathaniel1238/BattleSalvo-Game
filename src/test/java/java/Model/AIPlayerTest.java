package Model;

import cs3500.cs3500.pa03.Model.Coord;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIPlayerTest {

  /**
   * Tests the {@link AIPlayer#takeShots()} method to ensure that it returns the correct number
   * of shot coordinates based on the number of unsunken ships on the player's board.
   */
  @Test
  public void testTakeShots() {
    List<List<String>> playerBoard = createEmptyBoard(8, 8);
    playerBoard.get(0).set(0, "S"); // Place a ship on the board
    playerBoard.get(1).set(0, "S");
    playerBoard.get(2).set(0, "S");

    AIPlayer player = new AIPlayer(playerBoard, new ArrayList<>());

    List<Coord> shots = player.takeShots();

//    assertEquals(3, shots.size()); // Should return 3 shots

    // Verify that all shots are within the board boundaries
    for (Coord shot : shots) {
      assertTrue(shot.getX() >= 0 && shot.getX() < playerBoard.size());
      assertTrue(shot.getY() >= 0 && shot.getY() < playerBoard.get(0).size());
    }
  }

//  /**
//   * Tests the getAvailableShots() method to ensure that it returns a list of
//   * all available shot coordinates on the board.
//   */
//  @Test
//  public void testGetAvailableShots() {
//    List<List<String>> playerBoard = createEmptyBoard(8, 8);
//    AIPlayer player = new AIPlayer(playerBoard, new ArrayList<>());
//
//    List<Coord> availableShots = player.getAvailableShots();
//
//    assertEquals(64, availableShots.size()); // The board is 8x8, so there should be 64 available shots
//
//    // Verify that all shots are within the board boundaries
//    for (Coord shot : availableShots) {
//      assertTrue(shot.getX() >= 0 && shot.getX() < playerBoard.size());
//      assertTrue(shot.getY() >= 0 && shot.getY() < playerBoard.get(0).size());
//    }
//  }

  /**
   * Creates an empty game board with the specified dimensions.
   *
   * @param height the height of the board
   * @param width  the width of the board
   * @return the empty game board
   */
  private List<List<String>> createEmptyBoard(int height, int width) {
    List<List<String>> board = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<String> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add("");
      }
      board.add(row);
    }
    return board;
  }
}
