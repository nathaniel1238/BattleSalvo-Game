package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
    String command = message.getCommand();
    JsonNode arguments = message.getArguments();

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
    JsonNode response = createShipPlacementsResponse(shipPlacements);
    sendResponse("setup", response);
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
    out.println();
    }
  }

  private void handleDamage(JsonNode arguments) {
    List<Coord> opponentShots = new ArrayList<>();
    for (JsonNode shotNode : arguments) {
      Coord shot = this.mapper.convertValue(shotNode, Coord.class);
      opponentShots.add(shot);
    }
    List<Coord> hits = player.reportDamage(opponentShots);
    Volley damaged = new Volley(hits);
    JsonNode response = JsonUtils.serializeRecord(damaged);
    MessageJson message = new MessageJson("report-damaged", response);
    out.println(message);
  }

  private void handleShots() {
    List<Coord> shots = player.takeShots();
    Volley shot = new Volley(shots);
    JsonNode response = JsonUtils.serializeRecord(shot);
    MessageJson message = new MessageJson("take-shots", response);
    out.println(message);
  }

  private void handleSuccessful(JsonNode arguments) {
    List<Coord> successful_shots = new ArrayList<>();
    for (JsonNode shotNode : arguments) {
      Coord shot = this.mapper.convertValue(shotNode, Coord.class);
      successful_shots.add(shot);
    }
    player.successfulHits(successful_shots);
    MessageJson message = new MessageJson("successful-hits", mapper.createObjectNode());
    JsonNode jsonResponse = JsonUtils.serializeRecord(message);
    this.out.println(jsonResponse);
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

  private List<Coord> parseCoordList(JsonNode node) {
    List<Coord> coordList = new ArrayList<>();
    for (JsonNode entry : node) {
      int x = entry.get("x").asInt();
      int y = entry.get("y").asInt();
      Coord coord = new Coord(x, y);
      coordList.add(coord);
    }
    return coordList;
  }

  private JsonNode createShipPlacementsResponse(List<Ship> shipPlacements) {
    ArrayNode arrayNode = mapper.createArrayNode();
    for (Ship ship : shipPlacements) {
      ObjectNode shipNode = mapper.createObjectNode();
      shipNode.put("shipType", ship.getType().toString().toLowerCase());
      shipNode.put("size", ship.getCoord().size());
      arrayNode.add(shipNode);
    }
    return arrayNode;
  }

  private JsonNode createCoordListResponse(List<Coord> coordList) {
    ArrayNode arrayNode = mapper.createArrayNode();
    for (Coord coord : coordList) {
      ObjectNode coordNode = mapper.createObjectNode();
      coordNode.put("x", coord.getX());
      coordNode.put("y", coord.getY());
      arrayNode.add(coordNode);
    }
    return arrayNode;
  }
}
