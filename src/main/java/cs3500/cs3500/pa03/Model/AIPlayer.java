package cs3500.cs3500.pa03.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends AbstractPlayer {
  public AIPlayer() {
    super();
  }

  /**
   * randomizes the shots and makes sure that it doesnt shoot at a previous spot
   * @return the coordinates of the AI's shots
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> availableShots = getAvailableShots();
    List<Coord> shotsTaken = new ArrayList<>();
    int shotsRemaining = SunkenShips.numberOfShots(this.player_board);

    // Generate a random shot
    Random random = new Random();
    while (shotsTaken.size() < shotsRemaining && !availableShots.isEmpty()) {
      int index = random.nextInt(availableShots.size());
      Coord shot = availableShots.get(index);
      // Add the shot to the list of taken shots
      shotsTaken.add(shot);
      // Remove the shot from the available shots
      availableShots.remove(index);
    }
    return shotsTaken;
  }

  /**
   * makes sure which shot coordinates are available to shoot at
   * @return
   */
  private List<Coord> getAvailableShots() {
    List<Coord> availableShots = new ArrayList<>();
    // Iterate through the board and add the coordinates
    for (int i = 0; i < player_board.size(); i++) {
      for (int j = 0; j < player_board.get(i).size(); j++) {
        availableShots.add(new Coord(i, j));
      }
    }
    return availableShots;
  }
}
