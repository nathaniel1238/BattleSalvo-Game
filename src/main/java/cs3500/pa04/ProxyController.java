//package cs3500.pa04;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import cs3500.cs3500.pa03.Model.AbstractPlayer;
//import cs3500.pa03.model.Coord;
//import cs3500.pa03.model.Ship;
//import cs3500.pa03.model.ShipType;
//import cs3500.pa03.model.Player;
//import cs3500.pa03.model.GameResult;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ProxyController {
//  private final Socket server;
//  private final InputStream in;
//  private final PrintStream out;
//  private final AbstractPlayer player;
//  private final ObjectMapper mapper = new ObjectMapper();
//
//  private static final JsonNode VOID_RESPONSE =
//      new ObjectMapper().getNodeFactory().textNode("void");
//
//  /**
//   * Construct an instance of a ProxyPlayer.
//   *
//   * @param server the socket connection to the server
//   * @param player the instance of the player
//   * @throws IOException if
//   */
//  public ProxyController(Socket server, AbstractPlayer player) throws IOException {
//    this.server = server;
//    this.in = server.getInputStream();
//    this.out = new PrintStream(server.getOutputStream());
//    this.player = player;
//  }
//
//  public void run() {
//    try {
//      JsonParser parser = this.mapper.getFactory().createParser(this.in);
//
//      while (!this.server.isClosed()) {
//        MessageJson message = mapper.readTree(parser).traverse();
//        delegate(message);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  private void delegate(MessageJson message) {
//    String command = message.getCommand();
//    JsonNode arguments = message.getArguments();
//
//    switch (command) {
//      case "join":
//        handleJoin(arguments);
//        break;
//      case "setup":
//        handleSetUp(arguments);
//        break;
//      case "win":
//        handleWin(arguments);
//        break;
//      case "damage":
//        handleDamage(arguments);
//        break;
//      case "take-shots":
//        handleShots();
//        break;
//      case "successful":
//        handleSuccessful(arguments);
//        break;
//      default:
//        System.out.println("Unknown command: " + command);
//    }
//  }
//
//  private void handleJoin(JsonNode arguments) {
//    String playerName = arguments.get("playerName").asText();
//    player.name(playerName);
//    sendResponse("join", VOID_RESPONSE);
//  }
//
//  private void handleSetUp(JsonNode arguments) {
//    int height = arguments.get("height").asInt();
//    int width = arguments.get("width").asInt();
//    Map<ShipType, Integer> shipSpecifications = parseShipSpecifications(arguments.get("shipSpecifications"));
//    List<Ship> shipPlacements = player.setup(height, width, shipSpecifications);
//    JsonNode response = createShipPlacementsResponse(shipPlacements);
//    sendResponse("setup", response);
//  }
//
//  private void handleWin(JsonNode arguments) {
//    String playerName = arguments.get("playerName").asText();
//    String reason = arguments.get("reason").asText();
//    player.endGame(GameResult.WIN, reason);
//    sendResponse("win", VOID_RESPONSE);
//  }
//
//  private void handleDamage(JsonNode arguments) {
//    List<Coord> opponentShots = parseCoordList(arguments.get("opponentShots"));
//    List<Coord> hits = player.reportDamage(opponentShots);
//    JsonNode response = createCoordListResponse(hits);
//    sendResponse("damage", response);
//  }
//
//  private void handleShots() {
//    List<Coord> shots = player.takeShots();
//    Volley shot = new Volley(shots);
//    JsonNode response = createCoordListResponse(shots);
//    sendResponse("coordinates", response);
//  }
//
//  private void handleSuccessful(JsonNode arguments) {
//    List<Coord> shotsThatHitOpponentShips = parseCoordList(arguments.get("shotsThatHitOpponentShips"));
//    player.successfulHits(shotsThatHitOpponentShips);
//    sendResponse("successful", VOID_RESPONSE);
//  }
//
//  private Map<ShipType, Integer> parseShipSpecifications(JsonNode node) {
//    Map<ShipType, Integer> shipSpecifications = new HashMap<>();
//    for (JsonNode entry : node) {
//      ShipType shipType = ShipType.valueOf(entry.get("shipType").asText().toUpperCase());
//      int count = entry.get("count").asInt();
//      shipSpecifications.put(shipType, count);
//    }
//    return shipSpecifications;
//  }
//
//  private List<Coord> parseCoordList(JsonNode node) {
//    List<Coord> coordList = new ArrayList<>();
//    for (JsonNode entry : node) {
//      int x = entry.get("x").asInt();
//      int y = entry.get("y").asInt();
//      Coord coord = new Coord(x, y);
//      coordList.add(coord);
//    }
//    return coordList;
//  }
//
//  private JsonNode createShipPlacementsResponse(List<Ship> shipPlacements) {
//    ArrayNode arrayNode = mapper.createArrayNode();
//    for (Ship ship : shipPlacements) {
//      ObjectNode shipNode = mapper.createObjectNode();
//      shipNode.put("shipType", ship.getType().toString().toLowerCase());
//      shipNode.put("size", ship.getCoord().size());
//      arrayNode.add(shipNode);
//    }
//    return arrayNode;
//  }
//
//  private JsonNode createCoordListResponse(List<Coord> coordList) {
//    ArrayNode arrayNode = mapper.createArrayNode();
//    for (Coord coord : coordList) {
//      ObjectNode coordNode = mapper.createObjectNode();
//      coordNode.put("x", coord.getX());
//      coordNode.put("y", coord.getY());
//      arrayNode.add(coordNode);
//    }
//    return arrayNode;
//  }
//
//  private void sendResponse(String command, JsonNode response) {
//    ObjectNode responseNode = mapper.createObjectNode();
//    responseNode.put("command", command);
//    responseNode.set("response", response);
//    out.println(responseNode.toString());
//  }
//}
