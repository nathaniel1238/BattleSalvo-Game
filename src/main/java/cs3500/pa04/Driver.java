package cs3500.pa04;

import cs3500.cs3500.pa03.Controller.SalvoGame;
import java.io.InputStreamReader;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    Appendable output = System.out;
    SalvoGame game = new SalvoGame(new InputStreamReader(System.in), output);
    game.run();
  }
}