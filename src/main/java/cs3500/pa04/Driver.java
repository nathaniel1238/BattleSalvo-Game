package cs3500.pa04;

import cs3500.cs3500.pa03.Controller.SalvoGame;
import cs3500.cs3500.pa03.Model.AIPlayer;
import cs3500.cs3500.pa03.Model.AbstractPlayer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {

  public static void runClient(String host, int port)
      throws IOException, IllegalStateException {

    AbstractPlayer player = new AIPlayer();
    Socket server = new Socket(host, port);
    ProxyController controller = new ProxyController(server, player);
    controller.run();
  }
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    try {
      String host = "0.0.0.0";
      int port = 35001;
      runClient(host, port);
      Appendable output = System.out;
      SalvoGame game = new SalvoGame(new InputStreamReader(System.in), output);
    } catch (IOException e) {
      e.printStackTrace();
    }
//    Appendable output = System.out;
//    Scanner scanner = new Scanner(System.in);
//
//    SalvoGame game = new SalvoGame(new InputStreamReader(System.in), output);
//    game.run();
  }
}