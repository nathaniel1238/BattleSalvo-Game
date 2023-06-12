package cs3500.pa04;

import cs3500.cs3500.pa03.Controller.SalvoGame;
import cs3500.cs3500.pa03.Model.AIPlayer;
import cs3500.cs3500.pa03.Model.AbstractPlayer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    try {
      AbstractPlayer ai = new AIPlayer()
      Socket socket = new Socket("0.0.0.0", 35001);
      Appendable output = System.out;
      SalvoGame game = new SalvoGame(new InputStreamReader(System.in), output);
      ProxyController controller = new ProxyController(socket, player);
      controller.run();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}