package cs3500.cs3500.pa03.model;

import cs3500.pa04.Coord;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends AbstractPlayer {
  private List<Coord> shotAt;

  public AIPlayer() {
    super();
    this.shotAt = new ArrayList<>();
  }

  /**
   * randomizes the shots and makes sure that it doesnt shoot at a previous spot
   * @return the coordinates of the AI's shots
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> available = generateAvailableShots();
    List<Coord> shotsTaken = new ArrayList<>();
    int shotsRemaining = AbstractPlayer.count(ships, playerboard);

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
   *
   * @return
   */
  private List<Coord> generateAvailableShots() {
    List<Coord> availableshots = new ArrayList<>();
    // Iterate through the board and add the coordinates
    for (int x = 0; x < playerboard.get(0).size(); x++) {
      for (int y = 0; y < playerboard.size(); y++) {
        availableshots.add(new Coord(x, y));
      }
    }
    return availableshots;
  }

}
