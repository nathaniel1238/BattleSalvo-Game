//package Model;
//
//import Model.Coord;
//import org.junit.jupiter.api.Test;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class SalvoPlayerTest {
//
//  /**
//   * Tests the takeShots() method to ensure that it returns the correct
//   * number of shot coordinates based on the number of unsunken ships on the player's board.
//   */
//  @Test
//  public void testTakeShots() {
//    List<List<String>> playerBoard = createEmptyBoard(8, 8);
//    playerBoard.get(0).set(0, "S"); // Place a ship on the board
//    playerBoard.get(1).set(0, "S");
//    playerBoard.get(2).set(0, "S");
//
//    SalvoPlayer player = new SalvoPlayer(playerBoard, new ArrayList<>());
//
//    InputStream originalInput = System.in;
//    ByteArrayInputStream testInput = new ByteArrayInputStream("1 1 2 2 3 3".getBytes());
//    System.setIn(testInput);
//
//    List<Coord> shots = player.takeShots();
//
//    assertEquals(3, shots.size()); // Should return 3 shots
//    assertEquals(1, shots.get(0).getX()); // First shot should be at coordinates (1, 1)
//    assertEquals(1, shots.get(0).getY());
//    assertEquals(2, shots.get(1).getX()); // Second shot should be at coordinates (2, 2)
//    assertEquals(2, shots.get(1).getY());
//    assertEquals(3, shots.get(2).getX()); // Third shot should be at coordinates (3, 3)
//    assertEquals(3, shots.get(2).getY());
//
//    System.setIn(originalInput);
//  }
//
//  /**
//   * Tests the takeShots()} method to ensure that it throws an IllegalArgumentException
//   * if the shot coordinates provided by the player are not on the game board.
//   */
//  @Test
//  public void testTakeShotsInvalidCoordinates() {
//    List<List<String>> playerBoard = createEmptyBoard(8, 8);
//
//    SalvoPlayer player = new SalvoPlayer(playerBoard, new ArrayList<>());
//
//    InputStream originalInput = System.in;
//    ByteArrayInputStream testInput = new ByteArrayInputStream("9 9".getBytes());
//    System.setIn(testInput);
//
////    assertThrows(IllegalArgumentException.class, () -> player.takeShots());
//
//    System.setIn(originalInput);
//  }
//
//  /**
//   * Tests the {@link SalvoPlayer#takeShots()} method
//   to ensure that it throws an {@link IllegalArgumentException}
//   * if the number of shots provided by the player
//   exceeds the number of unsunken ships on their board.
//   */
//  @Test
//  public void testTakeShotsExceedingShips() {
//    List<List<String>> playerBoard = createEmptyBoard(8, 8);
//    playerBoard.get(0).set(0, "S"); // Place a ship on the board
//
//    SalvoPlayer player = new SalvoPlayer(playerBoard, new ArrayList<>());
//
//    InputStream originalInput = System.in;
//    ByteArrayInputStream testInput = new ByteArrayInputStream("1 1 2 2".getBytes());
//    System.setIn(testInput);
//
//    assertThrows(IllegalArgumentException.class, () -> player.takeShots());
//
//    System.setIn(originalInput);
//  }
//
//  /**
//   * Tests the {@link SalvoPlayer#takeShots()} method to
//   ensure that it handles multiple lines of input correctly.
//   */
//  @Test
//  public void testTakeShotsMultipleLines() {
//    List<List<String>> playerBoard = createEmptyBoard(8, 8);
//    playerBoard.get(0).set(0, "S"); // Place a ship on the board
//
//    SalvoPlayer player = new SalvoPlayer(playerBoard, new ArrayList<>());
//
//    InputStream originalInput = System.in;
//    ByteArrayInputStream testInput = new ByteArrayInputStream("1 1\n2 2\n3 3".getBytes());
//    System.setIn(testInput);
//
//    List<Coord> shots = player.takeShots();
//
//    assertEquals(3, shots.size()); // Should return 3 shots
//    assertEquals(1, shots.get(0).getX()); // First shot should be at coordinates (1, 1)
//    assertEquals(1, shots.get(0).getY());
//    assertEquals(2, shots.get(1).getX()); // Second shot should be at coordinates (2, 2)
//    assertEquals(2, shots.get(1).getY());
//    assertEquals(3, shots.get(2).getX()); // Third shot should be at coordinates (3, 3)
//    assertEquals(3, shots.get(2).getY());
//
//    System.setIn(originalInput);
//  }
//
//  /**
//   * Creates an empty game board with the specified dimensions.
//   *
//   * @param height the height of the board
//   * @param width  the width of the board
//   * @return the empty game board
//   */
//  private List<List<String>> createEmptyBoard(int height, int width) {
//    List<List<String>> board = new ArrayList<>();
//    for (int i = 0; i < height; i++) {
//      List<String> row = new ArrayList<>();
//      for (int j = 0; j < width; j++) {
//        row.add("");
//      }
//      board.add(row);
//    }
//    return board;
//  }
//}
