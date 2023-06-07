package cs3500.cs3500.pa03.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * represents a manuel player in the BattleSalvo Game
 */
public class SalvoPlayer extends AbstractPlayer {
  //protected List<List<String>> player_board;
  protected Scanner scanner;
  //protected List<List<String>> opp_board;
  public SalvoPlayer() {
    super();
    this.scanner = new Scanner(System.in);
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> coordinates = new ArrayList<>();

    for (int i = 0; i < SunkenShips.numberOfShots(player_board); i++) {
      String input = scanner.nextLine();
      String[] parts = input.split(" ");

      /**
       * look through the user input and parse into a int
       * and check if the coordinates are in the board, handle the exception if not
       */
      for (int k = 0; k < parts.length; k += 2) {
        int xvalue = Integer.parseInt(parts[k]);
        int yvalue = Integer.parseInt(parts[k + 1]);
        if ((xvalue >= 0 && xvalue <= player_board.get(0).size()) && (yvalue >= 0 && yvalue <= player_board.size())) {
          Coord coordinate = new Coord(xvalue, yvalue);
          coordinates.add(coordinate);
        } else {
          throw new IllegalArgumentException("Your coordinates are not on the board!");
        }
      }
    }
    return coordinates;
  }
}
