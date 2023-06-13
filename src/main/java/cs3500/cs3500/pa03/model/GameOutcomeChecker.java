package cs3500.cs3500.pa03.model;

import cs3500.pa04.GameResult;
import java.util.List;

/**
 * checks the outcome of the game
 */
public class GameOutcomeChecker {
  /**
   * the game result of the two boards, determining if there is a winner, loser, draw, or the
   * game is in progress
   * @param playerBoard the players board state
   * @param opponentBoard the ai player's board state
   * @return a Gameresult of the SalvoGame
   */
  public static GameResult checkGameOutcome(List<List<String>>
                playerBoard, List<List<String>> opponentBoard) {
    boolean allPlayerHits = checkAllHits(playerBoard);
    boolean allOpponentHits = checkAllHits(opponentBoard);

    if (allPlayerHits && allOpponentHits) {
      return GameResult.DRAW;
    } else if (allOpponentHits) {
      return GameResult.WIN;
    } else if (allPlayerHits) {
      return GameResult.LOSE;
    } else {
      return GameResult.IN_PROGRESS;
    }
  }

  /**
   * checks if all the ships are hit on the board
   *
   * @param board takes in either the player or ai's board
   *
   * @return true if all the ships are hit and false if they arent
   */
  private static boolean checkAllHits(List<List<String>> board) {
    for (List<String> row : board) {
      for (String content : row) {
        if (content.equals("S")) {
          return false;
        }
      }
    }
    return true;
  }
}
