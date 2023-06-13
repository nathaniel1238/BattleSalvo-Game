//package Model;
//
//import Model.Coord;
//import Model.Ship;
//import Controller.ShipType;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import org.junit.jupiter.api.Test;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class AbstractPlayerTest {
//
//  /**
//   * Tests the setup(int, int, Map) method to ensure that it returns a list of ships
//   * with valid locations on the board.
//   */
//  @Test
//  public void testSetup() {
//    AbstractPlayer player = new AbstractPlayerTestImpl();
//
//    int height = 10;
//    int width = 10;
//    Map<ShipType, Integer> specifications = new HashMap<>();
//    specifications.put(ShipType.DESTROYER, 2);
//    specifications.put(ShipType.CARRIER, 1);
//
//    List<Ship> ships = player.setup(height, width, specifications);
//
//    assertEquals(3, ships.size()); // Should return 3 ships in total
//    assertTrue(areShipsValid(ships, height, width)); // Check if the ship coordinates are valid
//  }
//
//  /**
//   * Tests the reportDamage(List) method to ensure that it returns a filtered
//   * list of shot coordinates that hit a ship on the player's board.
//   */
//  @Test
//  public void testReportDamage() {
//    AbstractPlayer player = new AbstractPlayerTestImpl();
//
//    List<List<String>> playerBoard = createEmptyBoard(8, 8);
//    List<Coord> opponentShots = new ArrayList<>();
//    opponentShots.add(new Coord(2, 3));
//    opponentShots.add(new Coord(4, 5));
//
//    player.generateBoard(new ArrayList<>(), new ArrayList<>(), opponentShots, 8, 8);
//
//    List<Coord> hitCoordinates = player.reportDamage(opponentShots);
//
//    assertEquals(2, hitCoordinates.size()); // Both shots should hit a ship
//    assertTrue(areCoordinatesValid(hitCoordinates, 8, 8)); // Check if the hit coordinates are valid
//  }
//
//  /**
//   * Tests the successfulHits(List) method to ensure that it marks the
//   * successfully hit shots on the opponent's board.
//   */
//  @Test
//  public void testSuccessfulHits() {
//    AbstractPlayer player = new AbstractPlayerTestImpl();
//
//    List<List<String>> opponentBoard = createEmptyBoard(8, 8);
//    List<Coord> shotsThatHitOpponentShips = new ArrayList<>();
//    shotsThatHitOpponentShips.add(new Coord(2, 3));
//    shotsThatHitOpponentShips.add(new Coord(4, 5));
//
//    player.successfulHits(shotsThatHitOpponentShips);
//
//    assertEquals("X", opponentBoard.get(3).get(2)); // The shot (2, 3) should be marked as hit
//    assertEquals("X", opponentBoard.get(5).get(4)); // The shot (4, 5) should be marked as hit
//  }
//
//  /**
//   * Tests the endGame(GameResult, String)} method to ensure that it prints
//   * the correct end game message based on the result and reason.
//   */
//  @Test
//  public void testEndGame() {
//    AbstractPlayer player = new AbstractPlayerTestImpl();
//
//    // Capture console output for testing
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    PrintStream printStream = new PrintStream(outputStream);
//    PrintStream originalPrintStream = System.out;
//    System.setOut(printStream);
//
//    player.endGame(GameResult.WIN, "Congratulations, it's ");
//
//    String output = outputStream.toString().trim();
//    assertEquals("Congratulations, it's a win! You hit all their ships before they hit all yours!", output);
//
//    // Restore original print stream
//    System.setOut(originalPrintStream);
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
//
//  /**
//   * Checks if the ship coordinates are valid within the given board dimensions.
//   *
//   * @param ships  the list of ships
//   * @param height the height of the board
//   * @param width  the width of the board
//   * @return true if the ship coordinates are valid, false otherwise
//   */
//  private boolean areShipsValid(List<Ship> ships, int height, int width) {
//    for (Ship ship : ships) {
//      for (Coord coord : ship.getCoord()) {
//        int x = coord.getX();
//        int y = coord.getY();
//        if (x < 0 || x >= width || y < 0 || y >= height) {
//          return false;
//        }
//      }
//    }
//    return true;
//  }
//
//  /**
//   * Checks if the shot coordinates are valid within the given board dimensions.
//   *
//   * @param coordinates the shot coordinates
//   * @param height      the height of the board
//   * @param width       the width of the board
//   * @return true if the coordinates are valid, false otherwise
//   */
//  private boolean areCoordinatesValid(List<Coord> coordinates, int height, int width) {
//    for (Coord coord : coordinates) {
//      int x = coord.getX();
//      int y = coord.getY();
//      if (x < 0 || x >= width || y < 0 || y >= height) {
//        return false;
//      }
//    }
//    return true;
//  }
//
//  /**
//   * An implementation of AbstractPlayer for testing purposes.
//   */
//  private static class AbstractPlayerTestImpl extends AbstractPlayer {
//    public AbstractPlayerTestImpl() {
//      super(new ArrayList<>(), new ArrayList<>());
//    }
//
//    @Override
//    public String name() {
//      return null;
//    }
//
//    @Override
//    public List<Coord> takeShots() {
//      return null;
//    }
//  }
//}
