//package java.model;
//
//import cs3500.cs3500.pa03.model.AIPlayer;
//import cs3500.cs3500.pa03.model.AbstractPlayer;
//import cs3500.cs3500.pa03.model.Direction;
//import cs3500.cs3500.pa03.model.SalvoPlayer;
//import cs3500.pa04.Coord;
//import cs3500.pa04.GameResult;
//import cs3500.pa04.Ship;
//import cs3500.pa04.ShipType;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class AbstractPlayerTest {
//
//  @Test
//  void testSetup() {
//    AbstractPlayer player = new SalvoPlayer();
//    int height = 10;
//    int width = 10;
//    Map<ShipType, Integer> specifications = new HashMap<>();
//    specifications.put(ShipType.CARRIER, 1);
//    specifications.put(ShipType.BATTLESHIP, 2);
//
//    List<Ship> ships = player.setup(height, width, specifications);
//    List<Ship> player_ships = player.getShips();
//
//    // Assertions
//    assertEquals(3, ships.size());
//    assertEquals(player_ships.size(), ships.size());
//  }
//
//
//  @Test
//  void testReportDamage() {
//    AbstractPlayer player = new SalvoPlayer();
//    List<Coord> coords1 = Arrays.asList(new Coord(0, 0), new Coord(1, 0),
//        new Coord(2, 0), new Coord(3, 0), new Coord(4, 0), new Coord(5, 0));
//    List<Coord> coords2 = Arrays.asList(new Coord(0, 1), new Coord(1, 1),
//        new Coord(2, 1), new Coord(3, 1), new Coord(4, 1));
//    List<Coord> coords3 = Arrays.asList(new Coord(0, 2), new Coord(1, 2),
//        new Coord(2, 2), new Coord(3, 2));
//    List<Coord> coords4 = Arrays.asList(new Coord(0, 3), new Coord(1, 3),
//        new Coord(2, 3));
//    List<Ship> ships = new ArrayList<>();
//    ships.add(new Ship(ShipType.CARRIER, coords1, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.BATTLESHIP, coords2, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.DESTROYER, coords3, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.SUBMARINE, coords4, Direction.HORIZONTAL));
//    List<Coord> opponentShots = Arrays.asList(
//        new Coord(0, 0),
//        new Coord(1, 0),
//        new Coord(0, 1),
//        new Coord(2, 2),
//        new Coord(5, 5)
//    );
//    player.generateBoard(ships, new ArrayList<>(), new ArrayList<>(), 6, 6);
//    List<Coord> hitCoordinates = player.reportDamage(opponentShots);
//    player.generateBoard(ships, opponentShots, hitCoordinates, 6, 6);
//
//    assertTrue(hitCoordinates.contains(new Coord(0, 0)));
//    assertTrue(hitCoordinates.contains(new Coord(1, 0)));
//    assertTrue(hitCoordinates.contains(new Coord(0, 1)));
//    assertTrue(hitCoordinates.contains(new Coord(2, 2)));
//    assertTrue(!hitCoordinates.contains(new Coord(5, 25)));
//    assertEquals(4, hitCoordinates.size());
//
//
//  }
//  @Test
//  void testSuccessfulHits() {
//    AbstractPlayer player = new SalvoPlayer();
//    List<Coord> coords1 = Arrays.asList(new Coord(0, 0), new Coord(1, 0),
//        new Coord(2, 0), new Coord(3, 0), new Coord(4, 0), new Coord(5, 0));
//    List<Coord> coords2 = Arrays.asList(new Coord(0, 1), new Coord(1, 1),
//        new Coord(2, 1), new Coord(3, 1), new Coord(4, 1));
//    List<Coord> coords3 = Arrays.asList(new Coord(0, 2), new Coord(1, 2),
//        new Coord(2, 2), new Coord(3, 2));
//    List<Coord> coords4 = Arrays.asList(new Coord(0, 3), new Coord(1, 3),
//        new Coord(2, 3));
//    List<Ship> ships = new ArrayList<>();
//    ships.add(new Ship(ShipType.CARRIER, coords1, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.BATTLESHIP, coords2, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.DESTROYER, coords3, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.SUBMARINE, coords4, Direction.HORIZONTAL));
//
//    AbstractPlayer player2 = new AIPlayer();
//    List<Coord> coords5 = Arrays.asList(new Coord(0, 0), new Coord(1, 0),
//        new Coord(2, 0), new Coord(3, 0), new Coord(4, 0), new Coord(5, 0));
//    List<Coord> coords6 = Arrays.asList(new Coord(0, 1), new Coord(1, 1),
//        new Coord(2, 1), new Coord(3, 1), new Coord(4, 1));
//    List<Coord> coords7 = Arrays.asList(new Coord(0, 2), new Coord(1, 2),
//        new Coord(2, 2), new Coord(3, 2));
//    List<Coord> coords8 = Arrays.asList(new Coord(0, 3), new Coord(1, 3),
//        new Coord(2, 3));
//    List<Ship> ships2 = new ArrayList<>();
//    ships2.add(new Ship(ShipType.CARRIER, coords1, Direction.HORIZONTAL));
//    ships2.add(new Ship(ShipType.BATTLESHIP, coords2, Direction.HORIZONTAL));
//    ships2.add(new Ship(ShipType.DESTROYER, coords3, Direction.HORIZONTAL));
//    ships2.add(new Ship(ShipType.SUBMARINE, coords4, Direction.HORIZONTAL));
//    List<Coord> aiShots = Arrays.asList(
//        new Coord(0, 0),
//        new Coord(1, 0),
//        new Coord(5, 5));
//    List<Coord> playerShots = Arrays.asList(
//        new Coord(0, 0),
//        new Coord(5, 5),
//        new Coord(2, 2));
//
//    player.generateBoard(ships, new ArrayList<>(), new ArrayList<>(), 6, 6);
//    player2.generateBoard(ships2, new ArrayList<>(), new ArrayList<>(), 6, 6);
//    player.generateOppBoard(new ArrayList<>(), new ArrayList<>(), 6, 6);
//    List<Coord> hitCoordinates1 = player2.reportDamage(playerShots);
//    List<Coord> hitCoordinates2 = player.reportDamage(aiShots);
//    player.successfulHits(hitCoordinates1);
//    List<List<String>> oppboard = player.getOpponentBoard();
//    List<List<String>> player_board = player.getPlayerBoard();
//    player.generateBoard(ships, aiShots, hitCoordinates2, 6, 6);
//    player.generateOppBoard(playerShots, hitCoordinates1, 6, 6);
//
//    assertEquals("X", oppboard.get(0).get(0));
//    assertEquals("X", oppboard.get(2).get(2));
//    assertEquals("O", oppboard.get(5).get(5));
//    assertEquals("O", player_board.get(5).get(5));
//  }
//
//  @Test
//  void testCount() {
//    AbstractPlayer player = new SalvoPlayer();
//    List<Coord> coords3 = Arrays.asList(new Coord(0, 2), new Coord(1, 2),
//        new Coord(2, 2), new Coord(3, 2));
//    List<Coord> coords4 = Arrays.asList(new Coord(0, 3), new Coord(1, 3),
//        new Coord(2, 3));
//    List<Ship> ships = new ArrayList<>();
//    ships.add(new Ship(ShipType.DESTROYER, coords3, Direction.HORIZONTAL));
//    ships.add(new Ship(ShipType.SUBMARINE, coords4, Direction.HORIZONTAL));
//
//    List<Coord> opponentShots = Arrays.asList(
//        new Coord(0, 2),
//        new Coord(1, 2),
//        new Coord(3, 2),
//        new Coord(2, 2),
//        new Coord(5, 5)
//    );
//    player.generateBoard(ships, new ArrayList<>(), new ArrayList<>(), 6, 6);
//    List<Coord> hitCoordinates = player.reportDamage(opponentShots);
//    player.generateBoard(ships, opponentShots, hitCoordinates, 6, 6);
//    List<List<String>> board = player.getPlayerBoard();
//    int c = player.count(ships, board);
//    assertEquals(c, 1);
//  }
//
//  @Test
//  void testEndGame() {
//    // Create an instance of your player
//    AbstractPlayer player = new SalvoPlayer();
//// Test case 1: Player wins
//    ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
//    PrintStream printStream1 = new PrintStream(outputStream1);
//    PrintStream originalOut1 = System.out;
//    System.setOut(printStream1);
//    player.endGame(GameResult.WIN, "You sunk all opponent's ships!");
//    String expectedOutput1 = "You sunk all opponent's ships! Congrats!";
//    String capturedOutput1 = outputStream1.toString().trim();
//    assertEquals(expectedOutput1, capturedOutput1);
//    System.setOut(originalOut1);
//
//    // Test case 2: Player loses
//    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
//    PrintStream printStream2 = new PrintStream(outputStream2);
//    PrintStream originalOut2 = System.out;
//    System.setOut(printStream2);
//    player.endGame(GameResult.LOSE, "All your ships are sunk!");
//    String expectedOutput2 = "All your ships are sunk! Try again!";
//    String capturedOutput2 = outputStream2.toString().trim();
//    assertEquals(expectedOutput2, capturedOutput2);
//    System.setOut(originalOut2);
//
//    // Test case 3: Game ends in a draw
//    ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
//    PrintStream printStream3 = new PrintStream(outputStream3);
//    PrintStream originalOut3 = System.out;
//    System.setOut(printStream3);
//    player.endGame(GameResult.DRAW, "The game is a draw!");
//    String expectedOutput3 = "The game is a draw! So close!";
//    String capturedOutput3 = outputStream3.toString().trim();
//    assertEquals(expectedOutput3, capturedOutput3);
//    System.setOut(originalOut3);
//  }
//
//  @Test
//  void testName() {
//    String name = "kevandnat";
//    AbstractPlayer player = new SalvoPlayer();
//    String player_name = player.name();
//    assertEquals(name, player_name);
//
//  }
//
//
//
//
//}