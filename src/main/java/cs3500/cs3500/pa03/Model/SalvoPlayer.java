package cs3500.cs3500.pa03.Model;

import cs3500.pa04.Coord;
import cs3500.pa04.ShipType;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * represents a manuel player in the BattleSalvo Game
 */
public class SalvoPlayer extends AbstractPlayer {
  protected Scanner scanner;
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

    try {
      for (int i = 0; i < AbstractPlayer.count(ships, player_board); i++) {
        int xvalue = scanner.nextInt();
        int yvalue = scanner.nextInt();
        boolean validInput = true;

        while (validInput) {
          if ((xvalue >= 0 && xvalue < player_board.get(0).size()) && (yvalue >= 0 && yvalue < player_board.size())) {
            Coord coordinate = new Coord(xvalue, yvalue);
            coordinates.add(coordinate);
            validInput = false; // Exit the loop
          } else {
            System.out.println("Your coordinates are not on the board!");
            xvalue = scanner.nextInt();
            yvalue = scanner.nextInt();
          }
        }
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter integer values.");
      scanner.nextLine();
    }
    return coordinates;
  }

}

