package cs3500.cs3500.pa03.Controller;

import cs3500.cs3500.pa03.Model.AIPlayer;
import cs3500.cs3500.pa03.Model.Coord;
import cs3500.cs3500.pa03.Model.GameOutcomeChecker;
import cs3500.cs3500.pa03.Model.GameResult;
import cs3500.cs3500.pa03.Model.SalvoPlayer;
import cs3500.cs3500.pa03.Model.Ship;
import cs3500.cs3500.pa03.Model.SunkenShips;
import cs3500.cs3500.pa03.View.DrawBoard;
import cs3500.cs3500.pa03.View.UserQuestions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * represent the SalvoGame where the functionality and the display are interacting with the user
 * input, takes user inputs and updates the model classes and updates displays messages,
 */
public class SalvoGame implements Controller {
  private Readable input;
  private Appendable output;
  private Scanner scanner;

  public SalvoGame(Readable input, Appendable output) {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    this.scanner = scanner;
  }

  /**
   * runs the game logic for the SalvoBattleShip game
   */
  @Override
  public void run() {
    try {
      // ask user for board size
      UserQuestions.displaySize(output);
      DesiredSize size = new DesiredSize(scanner, input);
      List<Integer> dimension = size.readSize();
      int height = dimension.get(0);
      int width = dimension.get(1);
      int lesserValue = Math.min(height, width);

      // ask user for fleet size
      UserQuestions.displayFleet(output, lesserValue);
      DesiredFleetSize fleet_size = new DesiredFleetSize(scanner, input);
      Map<ShipType, Integer> fleets = fleet_size.readFleet(lesserValue);

      // initialize the players
      SalvoPlayer player1 = new SalvoPlayer();
      AIPlayer player2 = new AIPlayer();

      // setup the ship placement
      List<Ship> player1_ships = player1.setup(height, width, fleets);
      List<Ship> player2_ships = player2.setup(height, width, fleets);

      // mutate the player board field
      List<Coord> empty = new ArrayList<>();


      player1.generateBoard(player1_ships, empty, empty, height, width);
      player1.generateOppBoard(empty,empty, height, width);
      player2.generateBoard(player2_ships, empty, empty, height, width);
      player2.generateOppBoard(empty, empty, height, width);

      GameResult game_state = GameResult.IN_PROGRESS;
      while (game_state.equals(GameResult.IN_PROGRESS)) {
        // display the initial manuel player board and opponent data
        DrawBoard board = new DrawBoard();
        int shots = SunkenShips.numberOfShots(player1.getPlayerBoard());
        board.draw(output, player1.getPlayerBoard(), player1.getOpponentBoard());
        UserQuestions.displayShots(output, shots);
        // attack
        List<Coord> user_shots = player1.takeShots();
        List<Coord> ai_shots = player2.takeShots();
        List<Coord> user_damaged = player1.reportDamage(ai_shots);
        List<Coord> ai_damaged = player2.reportDamage(user_shots);
        player1.successfulHits(ai_damaged);
        player2.successfulHits(user_damaged);
        player1.generateBoard(player1_ships, ai_shots, user_damaged, height, width);
        player1.generateOppBoard(user_shots, ai_damaged, height, width);
        player2.generateBoard(player2_ships, user_shots, ai_damaged, height, width);
        // check the outcome
        game_state = GameOutcomeChecker.checkGameOutcome(player1.getPlayerBoard(), player2.getPlayerBoard());
      }

      player1.endGame(game_state, "Your final result is");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}