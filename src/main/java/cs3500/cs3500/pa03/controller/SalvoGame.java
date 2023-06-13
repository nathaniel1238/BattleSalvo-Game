package cs3500.cs3500.pa03.controller;

import cs3500.cs3500.pa03.model.AIPlayer;
import cs3500.cs3500.pa03.model.AbstractPlayer;
import cs3500.cs3500.pa03.model.GameOutcomeChecker;
import cs3500.cs3500.pa03.model.SalvoPlayer;
import cs3500.pa04.Coord;
import cs3500.pa04.GameResult;
import cs3500.cs3500.pa03.view.DrawBoard;
import cs3500.cs3500.pa03.view.UserQuestions;
import cs3500.pa04.Ship;
import cs3500.pa04.ShipType;
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
    this.scanner = new Scanner(System.in);
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
      DesiredFleetSize fleetsize = new DesiredFleetSize(scanner, input);
      Map<ShipType, Integer> fleets = fleetsize.readFleet(lesserValue);


      // setup the ship placement
      AbstractPlayer player1 = new SalvoPlayer();
      AbstractPlayer player2 = new AIPlayer();
      List<Ship> player1ships = player1.setup(height, width, fleets);
      List<Ship> player2ships = player2.setup(height, width, fleets);

      // mutate the player board field
      List<Coord> empty = new ArrayList<>();


      player1.generateBoard(player1ships, empty, empty, height, width);
      player1.generateOppBoard(empty, empty, height, width);
      player2.generateBoard(player2ships, empty, empty, height, width);
      player2.generateOppBoard(empty, empty, height, width);

      GameResult gamestate = GameResult.IN_PROGRESS;
      while (gamestate.equals(GameResult.IN_PROGRESS)) {
        // display the initial manuel player board and opponent data
        DrawBoard board = new DrawBoard();
        int shots = AbstractPlayer.count(player1ships, player1.getPlayerBoard());
        board.draw(output, player1.getPlayerBoard(), player1.getOpponentBoard());
        UserQuestions.displayShots(output, shots);
        // attack
        List<Coord> usershots = player1.takeShots();
        List<Coord> aishots = player2.takeShots();
        for (Coord c : aishots) {
          System.out.println(c.toString());
        }
        System.out.println();
        List<Coord> userdamaged = player1.reportDamage(aishots);
        List<Coord> aidamaged = player2.reportDamage(usershots);
        player1.successfulHits(aidamaged);
        player2.successfulHits(userdamaged);
        player1.generateBoard(player1ships, aishots, userdamaged, height, width);
        player1.generateOppBoard(usershots, aidamaged, height, width);
        player2.generateBoard(player2ships, usershots, aidamaged, height, width);
        // check the outcome
        gamestate = GameOutcomeChecker.checkGameOutcome(player1.getPlayerBoard(),
            player2.getPlayerBoard());
      }
      player1.endGame(gamestate, "Your final result is");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
