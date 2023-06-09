package cs3500.cs3500.pa03.Model;

import cs3500.cs3500.pa03.Controller.ShipType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AIPlayer extends AbstractPlayer {
  private List<Coord> shotAt; // Declare the availableShots list as an instance variable

  public AIPlayer(int height, int width, Map<ShipType, Integer> specifications) {
    super(height, width, specifications);
    this.shotAt = new ArrayList<>(); // Initialize the availableShots list
  }

  /**
   * randomizes the shots and makes sure that it doesnt shoot at a previous spot
   * @return the coordinates of the AI's shots
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> available = generateAvailableShots();
    List<Coord> shotsTaken = new ArrayList<>();
    int shotsRemaining = AbstractPlayer.count(ships, player_board);

    // Generate a random shot
    Random random = new Random();
    while (shotsTaken.size() < shotsRemaining && !available.isEmpty()) {
      int index = random.nextInt(available.size());
      Coord shot = available.get(index);
      boolean alreadyShot = false;
      for (Coord c : shotAt) {
        if (c.equals(shot)) {
          alreadyShot = true;
          break;
        }
      }
      if (!alreadyShot) {
        shotsTaken.add(shot);
      }
      available.remove(index); // Remove the coordinate from the available list
    }
    shotAt.addAll(shotsTaken);
    return shotsTaken;
  }

  /**
   * makes sure which shot coordinates are available to shoot at
   * @return
   */
  private List<Coord> generateAvailableShots() {
    List<Coord> availableshots = new ArrayList<>();
    // Iterate through the board and add the coordinates
    for (int i = 0; i < player_board.size(); i++) {
      for (int j = 0; j < player_board.get(i).size(); j++) {
        availableshots.add(new Coord(i, j));
      }
    }
    return availableshots;
  }
}
