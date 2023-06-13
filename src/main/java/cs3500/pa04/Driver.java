package cs3500.pa04;

import cs3500.cs3500.pa03.controller.SalvoGame;
import cs3500.cs3500.pa03.model.AIPlayer;
import cs3500.cs3500.pa03.model.AbstractPlayer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;

/**
 * This is the main driver class for the battleship game project.
 */
public class Driver {

  /**
   * Runs the client-side of the battleship game, connecting to the specified server and starting
   * the proxy controller to communicate with the server.
   *
   * @param host the host address of the server
   * @param port the port number of the server
   * @throws IOException          if an I/O error occurs when creating the socket connection
   * @throws IllegalStateException if the ProxyController encounters an I/O error
   */
  public static void runClient(String host, int port)
      throws IOException, IllegalStateException {

    AbstractPlayer player = new AIPlayer();
    Socket server = new Socket(host, port);
    ProxyController controller = new ProxyController(server, player);
    controller.run();
  }

  /**
   * The entry point of the battleship game project.
   *
   * @param args the command line arguments (not required)
   * @throws IOException if an I/O error occurs during the game initialization
   */
  public static void main(String[] args) throws IOException {
    if (args == null) {
      Appendable output = System.out;
      SalvoGame game = new SalvoGame(new InputStreamReader(System.in), output);
      game.run();
    } else {
      try {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        runClient(host, port);
      } catch (IOException e) {
        System.out.println("Invalid Host and Port");
      }
    }
  }
}
