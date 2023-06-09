package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.cs3500.pa03.Model.Player;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final Player player;
  private final ObjectMapper mapper = new ObjectMapper();

  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @throws IOException if
   */
  public ProxyController(Socket server, Player player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        delegate(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void delegate(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin(arguments);
    } else if ("win".equals(name)) {
      handleWin(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  private void handleJoin(JsonNode arguments) {
    HintJson guessArgs = this.mapper.convertValue(arguments, HintJson.class);

    int guess = getPlayerGuess(guessArgs);

    GuessJson response = new GuessJson(guess);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response);
    this.out.println(jsonResponse);
  }

  private void handleSetUp(JsonNode arguments) {
    Fleet fleet = fleet.arguments;

  }


  private void handleWin(JsonNode arguments) {
    WinJson winJson = this.mapper.convertValue(arguments, WinJson.class);

    this.player.win(winJson.isWinner());

    this.out.println(VOID_RESPONSE);
  }

  private void handleDamage(JsonNode arguments) {

  }

  private void handleShots(JsonNode arguments) {

  }

  private void handleSuccessful(JsonNode arguments) {

  }

}
