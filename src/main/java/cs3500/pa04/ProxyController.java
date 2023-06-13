package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import cs3500.cs3500.pa03.model.AIPlayer;
import cs3500.cs3500.pa03.model.AbstractPlayer;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a proxy controller for the battleship game, which communicates with a server using
 * JSON messages. It handles incoming messages from the server and delegates them to the appropriate
 * methods in the player instance.
 */
public class ProxyController {

  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AbstractPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();
  private int height;
  private int width;

  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Constructs an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player.
   * @throws IOException if an I/O error occurs when creating the input/output streams
   */
  public ProxyController(Socket server, AbstractPlayer player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = new AIPlayer();
    this.height = height;
    this.width = width;
  }

  /**
   * Starts the proxy controller, listening for messages from the server and delegating them to the
   * appropriate methods.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegate(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Delegates the incoming message to the appropriate handler method based on the command.
   *
   * @param message the incoming message
   */
  private void delegate(MessageJson message) {
    String command = message.methodName();
    JsonNode arguments = message.args();

    switch (command) {
      case "join":
        handleJoin(arguments);
        break;
      case "setup":
        handleSetUp(arguments);
        break;
      case "end-game":
        handleFinal(arguments);
        break;
      case "report-damage":
        handleDamage(arguments);
        break;
      case "take-shots":
        handleShots();
        break;
      case "successful-hits":
        handleSuccessful(arguments);
        break;
      default:
        System.out.println("Unknown command: " + command);
    }
  }

  /**
   * Handles the "join" command from the server, sending the join response with player information.
   *
   * @param arguments the arguments received from the server
   */
  private void handleJoin(JsonNode arguments) {
    JoinJson joinJson = new JoinJson("kevleee21", "SINGLE");
    JsonNode jsonNode = this.mapper.convertValue(joinJson, JsonNode.class);
    MessageJson messageJson = new MessageJson("join", jsonNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(messageJson);
    this.out.println(jsonResponse);
  }

  /**
   * Handles the "setup" command from the server, setting up the player's board and sending the
   * response with ship placements.
   *
   * @param arguments the arguments received from the server
   */
  private void handleSetUp(JsonNode arguments) {
    height = arguments.get("height").asInt();
    width = arguments.get("width").asInt();

    System.out.println(height);
    System.out.println(width);

    Map<ShipType, Integer> shipSpecifications = parseShipSpecifications
        (arguments.get("fleet-spec"));
    List<Ship> shipPlacements = player.setup(height, width, shipSpecifications);
    player.generateBoard(shipPlacements, new ArrayList<>(), new ArrayList<>(), height, width);
    player.generateOppBoard(new ArrayList<>(), new ArrayList<>(), height, width);

    List<ShipJson> response = createShipPlacementsResponse(shipPlacements);
    Fleet fleet = new Fleet(response);

    JsonNode jsonResponse = JsonUtils.serializeRecord(fleet);
    MessageJson messageJson = new MessageJson("setup", jsonResponse);
    JsonNode output = JsonUtils.serializeRecord(messageJson);
    drawBoard(System.out, player.getPlayerBoard());
    this.out.println(output);
  }

  /**
   * Handles the "end-game" command from the server, notifying the player about the game result.
   *
   * @param arguments the arguments received from the server
   */
  private void handleFinal(JsonNode arguments) {
    String result = arguments.get("result").asText();
    String reason = arguments.get("reason").asText();
    if (result.equals("WIN")) {
      player.endGame(GameResult.WIN, reason);
    } else if (result.equals("LOSE")) {
      player.endGame(GameResult.LOSE, reason);
    } else {
      player.endGame(GameResult.DRAW, reason);
    }

    JsonNode jsonResponse = JsonNodeFactory.instance.objectNode();
    MessageJson message = new MessageJson("end-game", jsonResponse);
    JsonNode output = JsonUtils.serializeRecord(message);
    this.out.println(output);
  }

  /**
   * Handles the "report-damage" command from the server, reporting the damage to the player's board
   * and sending the response with the damaged coordinates.
   *
   * @param arguments the arguments received from the server
   */
  private void handleDamage(JsonNode arguments) {
    List<Coord> opponentShots = volleyParser(arguments);
    for (Coord c : opponentShots) {
      System.out.println(c.toString());
    }

    System.out.println(height);
    System.out.println(width);

    drawBoard(System.out, player.getPlayerBoard());
    List<Coord> hits = player.reportDamage(opponentShots);

    player.generateBoard(player.getShips(), opponentShots, hits, height, width);
    drawBoard(System.out, player.getPlayerBoard());

    for (Coord c : hits) {
      System.out.println(c);
    }

    List<CoordJson> coordJsons = createCoordJsons(hits);

    Volley damaged = new Volley(coordJsons);
    JsonNode response = JsonUtils.serializeRecord(damaged);
    MessageJson message = new MessageJson("report-damage", response);
    JsonNode output = JsonUtils.serializeRecord(message);
    drawBoard(System.out, player.getPlayerBoard());
    out.println(output);
  }

  /**
   * Handles the "take-shots" command from the server, allowing the player to take shots and sending
   * the response with the shot coordinates.
   */
  private void handleShots() {
    List<Coord> shots = player.takeShots();

    List<CoordJson> coordJsons = createCoordJsons(shots);
    Volley shot = new Volley(coordJsons);
    JsonNode response = JsonUtils.serializeRecord(shot);
    MessageJson message = new MessageJson("take-shots", response);
    JsonNode output = JsonUtils.serializeRecord(message);

    this.out.println(output);
  }

  /**
   * Handles the "successful-hits" command from the server,
   * notifying the player about the successful
   * hits.
   *
   * @param arguments the arguments received from the server
   */
  private void handleSuccessful(JsonNode arguments) {
    List<Coord> successfulshots = volleyParser(arguments);

    player.successfulHits(successfulshots);

    JsonNode jsonResponse = JsonNodeFactory.instance.objectNode();
    MessageJson message = new MessageJson("successful-hits", jsonResponse);
    JsonNode output = JsonUtils.serializeRecord(message);
    this.out.println(output);
  }

  /**
   * Parses the ship specifications from the JSON arguments received from the server.
   *
   * @param node the JSON node containing the ship specifications
   * @return a map representing the ship specifications
   */
  private Map<ShipType, Integer> parseShipSpecifications(JsonNode node) {
    SpecificationJson shipSpecifications = this.mapper.convertValue(node, SpecificationJson.class);
    Map<ShipType, Integer> specMap = new HashMap<>();
    specMap.put(ShipType.CARRIER, shipSpecifications.carrier());
    specMap.put(ShipType.BATTLESHIP, shipSpecifications.battleship());
    specMap.put(ShipType.DESTROYER, shipSpecifications.destroyer());
    specMap.put(ShipType.SUBMARINE, shipSpecifications.submarine());
    return specMap;
  }

  /**
   * Creates a list of ship placements in JSON format from the list of ship objects.
   *
   * @param shipPlacements the list of ship placements
   * @return a list of ship placements in JSON format
   */
  private List<ShipJson> createShipPlacementsResponse(List<Ship> shipPlacements) {
    List<ShipJson> ships = new ArrayList<>();
    for (Ship ship : shipPlacements) {
      ships.add(new ShipJson(ship.getCoord().get(0), ship.getCoord().size(), ship.getDirection()));
    }
    return ships;
  }

  /**
   * Creates a list of coordinate JSON objects from the list of Coord objects.
   *
   * @param coords the list of Coord objects
   * @return a list of coordinate JSON objects
   */
  private List<CoordJson> createCoordJsons(List<Coord> coords) {
    List<CoordJson> jsons = new ArrayList<>();
    for (Coord coord : coords) {
      jsons.add(new CoordJson(coord.getX(), coord.getY()));
    }
    return jsons;
  }

  /**
   * Parses the volley coordinates from the JSON arguments received from the server.
   *
   * @param node the JSON node containing the volley coordinates
   * @return a list of Coord objects representing the volley coordinates
   */
  private List<Coord> volleyParser(JsonNode node) {
    Volley reportDamageJson = this.mapper.convertValue(node, Volley.class);
    List<CoordJson> opCoordList = reportDamageJson.coordinates();
    List<Coord> tempList = new ArrayList<>();
    for (int i = 0; i < opCoordList.size(); i++) {
      CoordJson coordinates = opCoordList.get(i);
      tempList.add(new Coord(coordinates.x(), coordinates.y()));
    }

    return tempList;
  }

  /**
   * Draws the player's board to the specified output.
   *
   * @param output the output to which the board will be drawn
   * @param board the player's board representation
   */
  private void drawBoard(Appendable output, List<List<String>> board) {
    try {
      for (int i = 0; i < board.size(); i++) {
        for (int k = 0; k < board.get(0).size(); k++) {
          output.append(board.get(i).get(k)).append(" ");
        }
        output.append(System.lineSeparator());
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
