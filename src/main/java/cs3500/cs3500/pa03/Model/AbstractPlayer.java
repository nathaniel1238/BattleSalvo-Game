package cs3500.cs3500.pa03.Model;

import cs3500.pa04.Coord;
import cs3500.pa04.GameResult;
import cs3500.pa04.Ship;
import cs3500.pa04.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * abstract class that represents players in the Salvo game
 */
public abstract class AbstractPlayer implements Player {
  protected List<List<String>> player_board;
  protected List<List<String>> opp_board;
  protected List<Ship> ships;
  protected String name;

  public AbstractPlayer(int height, int width, Map<ShipType, Integer> specifications) {
    this.player_board = new ArrayList<>();
    this.opp_board = new ArrayList<>();
    this.ships = setup(height, width, specifications);
    this.name = "kevandnat";
  }

  @Override
  public String name() {
    return this.name;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> init = new ArrayList<>();
    for (Map.Entry<ShipType, Integer> entry : specifications.entrySet()) {
      ShipType shipType = entry.getKey();
      Integer count = entry.getValue();
      for (int i = 0; i < count; i++) {
        Direction direction = Direction.randomDirection();
        List<Coord> ship_coordinates = RandomCoordinates.generateShipCoord(height, width, shipType, direction);
        List<Coord> filtered = UpdateCoordinates.updateCoord(height, width, shipType, init, ship_coordinates);
        Ship ship;
        if (isVertical(filtered)) {
          ship = new Ship(shipType, filtered, Direction.VERTICAL);
        } else {
          ship = new Ship(shipType, filtered, Direction.HORIZONTAL);
        }
        init.add(ship);
      }
    }
    return init;
  }


  /**
   * Checks if the given list of coordinates represents a vertical arrangement.
   *
   * @param coords The list of coordinates to check.
   * @return True if the coordinates are vertically arranged, false otherwise.
   */
  private boolean isVertical(List<Coord> coords) {
    if (coords != null && coords.size() > 1) {
      int firstX = coords.get(0).getX();
      for (Coord coord : coords) {
        if (coord.getX() != firstX) {
          return false;
        }
      }
      return true;
    }
    return false;
  }



  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   * ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hitCoordinates = new ArrayList<>();

    for (Coord shot : opponentShotsOnBoard) {
      int x = shot.getX();
      int y = shot.getY();

      if (player_board.get(y).get(x).equals("S")) {
        hitCoordinates.add(shot);
      }
    }
    return hitCoordinates;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord shot : shotsThatHitOpponentShips) {
      int x = shot.getX();
      int y = shot.getY();
      opp_board.get(y).set(x, "X");
    }
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    if (result.equals(GameResult.WIN)) {
      System.out.println(reason + " Congrats!");
    } else if (result.equals(GameResult.LOSE)) {
      System.out.println(reason + " Try again!");
    } else {
      System.out.println(reason + " So close!");
    }
  }

  /**
   * updates the state of the players board
   * @param ships the ships on the board
   * @param shots the opponents shots at the players board
   * @param hit the shots that hit the board
   * @param height the height of the board
   * @param width the width of the board
   */
  public void generateBoard(List<Ship> ships, List<Coord> shots, List<Coord> hit, int height, int width) {
    if (shots.isEmpty() && hit.isEmpty()) {
      initializeEmptyBoard(player_board, height, width);
      placeShipsOnBoard(ships);
    } else {
      markHitsOnBoard(hit, shots);
    }
  }

  /**
   * marks the missed and hit shots on the users board
   * @param hits the hit shots
   * @param shots the taken shots
   */
  private void markHitsOnBoard(List<Coord> hits, List<Coord> shots) {
    for (Coord shot : shots) {
      boolean isHit = false;
      for (Coord hit : hits) {
        if (hit.getX() == shot.getX() && hit.getY() == shot.getY()) {
          isHit = true;
          break;
        }
      }
      if (!isHit && !player_board.get(shot.getY()).get(shot.getX()).equals("X")) {
        player_board.get(shot.getY()).set(shot.getX(), "O");
      } else {
        player_board.get(shot.getY()).set(shot.getX(), "X");
      }
    }
  }



  /**
   * places the ships on the board
   * @param ships the ships that are supposed to be on the board
   */
  private void placeShipsOnBoard(List<Ship> ships) {
    for (Ship ship : ships) {
      for (Coord c : ship.getCoord()) {
        int x = c.getX();
        int y = c.getY();
        player_board.get(y).set(x, "S");
      }
    }
  }

  /**
   * updated the opponent board data so the player can know
   *    * where they hit or missed
   * @param shots the users shots
   * @param hits the reported damage
   * @param height the height
   * @param width the width
   */
  public void generateOppBoard(List<Coord> shots, List<Coord> hits, int height, int width) {
    if (shots.isEmpty()) {
      initializeEmptyBoard(opp_board, height, width);
    } else {
      markMissOnOppBoard(hits, shots);
    }
  }

  /**
   * marks the users missed shot
   * @param shots users shots
   * @param hits reported damage
   */
  private void markMissOnOppBoard(List<Coord> hits, List<Coord> shots) {
    for (Coord shot : shots) {
      boolean isHit = false;
      for (Coord hit : hits) {
        if (hit.getX() == shot.getX() && hit.getY() == shot.getY()) {
          isHit = true;
          break;
        }
      }
      if (!isHit && !opp_board.get(shot.getY()).get(shot.getX()).equals("X")) {
        opp_board.get(shot.getY()).set(shot.getX(), "O");
      }
    }
  }


  /**
   * initializes the empty board
   * @param height the height
   * @param width the width
   */
  private void initializeEmptyBoard(List<List<String>> board, int height, int width) {
    for (int i = 0; i < height; i++) {
      List<String> row = new ArrayList<>();
      for (int k = 0; k < width; k++) {
        String wave = "~";
        row.add(wave);
      }
      board.add(row);
    }
  }

  // Modify the AbstractPlayer class

  public List<List<String>> getPlayerBoard() {
    return player_board;
  }

  public List<List<String>> getOpponentBoard() {
    return opp_board;
  }
  public static int count(List<Ship> ships, List<List<String>> board) {
    int fleet_size = ships.size();
    for (Ship ship: ships) {
      int count = sunked_count(ship.getCoord(), board);
      if (count == ship.getCoord().size()) {
        fleet_size--;
      }
    }
    return fleet_size;
  }

  private static int sunked_count(List<Coord> ship_coord, List<List<String>> board) {
    int hit_count = 0;
    for (Coord c: ship_coord) {
      int x = c.getX();
      int y = c.getY();
      if (board.get(y).get(x).equals("X")) {
        hit_count++;
      }
    }
    return hit_count;
  }


  public List<Ship> getShips() {
    return this.ships;
  }

}


