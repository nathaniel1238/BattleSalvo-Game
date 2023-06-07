package cs3500.cs3500.pa03.Model;

import java.util.List;

/**
 * the sunken ships on the board
 */
public class SunkenShips {
  /**
   * looks through a game board and checks for sunken ships
   * and
   * @param board the game board
   * @return total number of sunken ships
   */
  public static int numberOfShots(List<List<String>> board) {
    int height = board.size();
    int width = board.get(0).size();
    int shipCount = 0;
    boolean[][] visited = new boolean[height][width];  // Track visited ship cells

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String currentShot = board.get(i).get(j);
        if (currentShot.equals("S") && !visited[i][j]) {
          // Increment shipCount if it is the start of a ship or an unvisited ship cell
          shipCount++;
          visited[i][j] = true;

          // Check adjacent shots horizontally
          boolean isShipSunk = true;  // Assume ship is sunk until proven otherwise
          for (int k = j + 1; k < width; k++) {
            if (board.get(i).get(k).equals("S")) {
              visited[i][k] = true;
              isShipSunk = false;  // Ship is not fully sunk
            } else {
              break;
            }
          }

          // Check adjacent shots vertically
          for (int k = i + 1; k < height; k++) {
            if (board.get(k).get(j).equals("S")) {
              visited[k][j] = true;
              isShipSunk = false;  // Ship is not fully sunk
            } else {
              break;
            }
          }

          // Decrement shipCount if ship is fully sunk
          if (isShipSunk) {
            shipCount--;
          }
        }
      }
    }
    return shipCount;
  }
}


