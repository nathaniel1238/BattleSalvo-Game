package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.cs3500.pa03.Model.AIPlayer;
import cs3500.cs3500.pa03.Model.AbstractPlayer;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AbstractPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();

  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player.
   * @throws IOException if
   */
  public ProxyController(Socket server, AbstractPlayer player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = new AIPlayer();
  }

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

  private void handleJoin(JsonNode arguments) {
    JoinJson joinJson = new JoinJson("kevleee21", "SINGLE");
    JsonNode jsonNode = this.mapper.convertValue(joinJson, JsonNode.class);
    MessageJson messageJson = new MessageJson("join", jsonNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(messageJson);
    this.out.println(jsonResponse);
  }

  private void handleSetUp(JsonNode arguments) {
    int height = arguments.get("height").asInt();
    int width = arguments.get("width").asInt();
    Map<ShipType, Integer> shipSpecifications = parseShipSpecifications(arguments.get("shipSpecifications"));
    List<Ship> shipPlacements = player.setup(height, width, shipSpecifications);

    List<ShipJson> response = createShipPlacementsResponse(shipPlacements);
    Fleet fleet = new Fleet(response);

    JsonNode jsonResponse = JsonUtils.serializeRecord(fleet);
    MessageJson messageJson = new MessageJson("setup", jsonResponse);
    this.out.println(messageJson);
  }

  private void handleFinal(JsonNode arguments) {
    String result = arguments.get("result").asText();
    String reason = arguments.get("reason").asText();
    if (result.equals("WIN")) {
      player.endGame(GameResult.WIN, reason);
    } else if(result.equals("LOSE")) {
      player.endGame(GameResult.LOSE, reason);
    } else {
      player.endGame(GameResult.DRAW, reason);
    }

    JsonNode jsonResponse = JsonNodeFactory.instance.objectNode();
    MessageJson messageJson = new MessageJson("end-game", jsonResponse);
    this.out.println(messageJson);

  }

  private void handleDamage(JsonNode arguments) {
    List<Coord> opponentShots = volleyParser(arguments);

    List<Coord> hits = player.reportDamage(opponentShots);

    List<CoordJson> coordJsons = createCoordJsons(hits);

    Volley damaged = new Volley(coordJsons);
    JsonNode response = JsonUtils.serializeRecord(damaged);
    MessageJson message = new MessageJson("report-damaged", response);

    out.println(message);
  }

  private void handleShots() {
    List<Coord> shots = player.takeShots();
    List<CoordJson> coordJsons = createCoordJsons(shots);
    Volley shot = new Volley(coordJsons);
    JsonNode response = JsonUtils.serializeRecord(shot);
    MessageJson message = new MessageJson("take-shots", response);
    this.out.println(message);
  }

  private void handleSuccessful(JsonNode arguments) {
    List<Coord> successful_shots = volleyParser(arguments);
    player.successfulHits(successful_shots);

    JsonNode jsonResponse = JsonNodeFactory.instance.objectNode();
    MessageJson message = new MessageJson("successful-hits", jsonResponse);
    this.out.println(message);
  }



  private Map<ShipType, Integer> parseShipSpecifications(JsonNode node) {
    Map<ShipType, Integer> shipSpecifications = new HashMap<>();
    for (JsonNode entry : node) {
      ShipType shipType = ShipType.valueOf(entry.get("shipType").asText().toUpperCase());
      int count = entry.get("count").asInt();
      shipSpecifications.put(shipType, count);
    }
    return shipSpecifications;
  }


  private List<ShipJson> createShipPlacementsResponse(List<Ship> shipPlacements) {
    List<ShipJson> ships = new ArrayList<>();
    for (Ship ship : shipPlacements) {
      ships.add(new ShipJson(ship.getCoord().get(0), ship.getCoord().size(), ship.getDirection()));
    }
    return ships;
  }

  private List<CoordJson> createCoordJsons(List<Coord> coords) {
    List<CoordJson> jsons = new ArrayList<>();
    for (Coord coord : coords) {
      jsons.add(new CoordJson(coord.getX(), coord.getY()));
    }
    return jsons;
  }

  private List<Coord> volleyParser(JsonNode node) {
    List<Coord> shots = new ArrayList<>();
    JsonNode coordinatesNode = node.path("arguments").path("coordinates");
    for (JsonNode coordNode : coordinatesNode) {
      int x = coordNode.path("x").asInt();
      int y = coordNode.path("y").asInt();
      shots.add(new Coord(x, y));
    }
    return shots;
  }

}
