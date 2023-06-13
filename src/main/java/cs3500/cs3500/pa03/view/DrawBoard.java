package cs3500.cs3500.pa03.view;

import java.io.IOException;
import java.util.List;

/**
 * Represents the BattleSalvo board during the game.
 */
public class DrawBoard {

  /**
   * Draws the player board and opponent board with appropriate headers.
   *
   * @param output      The Appendable to which the board will be drawn.
   * @param playerBoard The player board.
   * @param oppData     The opponent board data.
   * @throws IOException If an I/O error occurs while writing the board.
   */
  public void draw(Appendable output, List<List<String>> playerBoard, List<List<String>> oppData)
      throws IOException {
    output.append("Opponent Board").append(System.lineSeparator());
    drawBoard(output, oppData);
    output.append("Player Board").append(System.lineSeparator());
    drawBoard(output, playerBoard);
  }

  /**
   * draws any game board that is put in as the parameter
   * @param output
   * @param board
   * @throws IOException
   */
  private void drawBoard(Appendable output, List<List<String>> board)
      throws IOException {
    for (int i = 0; i < board.size(); i++) {
      for (int k = 0; k < board.get(0).size(); k++) {
        output.append(board.get(i).get(k)).append(" ");
      }
      output.append(System.lineSeparator());
    }
  }
}
