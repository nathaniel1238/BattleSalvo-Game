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
  private int height;
  private int width;

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
    this.height = height;
    this.width = width;
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
    height = arguments.get("height").asInt();
    width = arguments.get("width").asInt();

    System.out.println(height);
    System.out.println(width);

    Map<ShipType, Integer> shipSpecifications = parseShipSpecifications(arguments.get("fleet-spec"));
    List<Ship> shipPlacements = player.setup(height, width, shipSpecifications);
    player.generateBoard(shipPlacements, new ArrayList<>(), new ArrayList<>(), height, width);

    List<ShipJson> response = createShipPlacementsResponse(shipPlacements);
    Fleet fleet = new Fleet(response);

    JsonNode jsonResponse = JsonUtils.serializeRecord(fleet);
    MessageJson messageJson = new MessageJson("setup", jsonResponse);
    JsonNode output = JsonUtils.serializeRecord(messageJson);
    drawBoard(System.out, player.getPlayerBoard());
    this.out.println(output);
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
    MessageJson message = new MessageJson("end-game", jsonResponse);
    JsonNode output = JsonUtils.serializeRecord(message);
    this.out.println(output);

  }

  private void handleDamage(JsonNode arguments) {
    List<Coord> opponentShots = volleyParser(arguments);
    for (Coord c: opponentShots) {
      System.out.println(c.toString());
    }

    System.out.println(height);
    System.out.println(width);

    drawBoard(System.out, player.getPlayerBoard());
    List<Coord> hits = player.reportDamage(opponentShots);

    player.generateBoard(player.getShips(), opponentShots, hits, height, width);
    drawBoard(System.out, player.getPlayerBoard());

    for (Coord c: hits) {
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

  private void handleShots() {
    List<Coord> shots = player.takeShots();
//    for (Coord c : shots) {
//      System.out.println(c.toString());
//    }

    List<CoordJson> coordJsons = createCoordJsons(shots);
    Volley shot = new Volley(coordJsons);
    JsonNode response = JsonUtils.serializeRecord(shot);
    MessageJson message = new MessageJson("take-shots", response);
    JsonNode output = JsonUtils.serializeRecord(message);

    this.out.println(output);
  }

  private void handleSuccessful(JsonNode arguments) {
    List<Coord> successful_shots = volleyParser(arguments);
    player.successfulHits(successful_shots);

    JsonNode jsonResponse = JsonNodeFactory.instance.objectNode();
    MessageJson message = new MessageJson("successful-hits", jsonResponse);
    JsonNode output = JsonUtils.serializeRecord(message);
    this.out.println(output);
  }



  private Map<ShipType, Integer> parseShipSpecifications(JsonNode node) {
    SpecificationJson shipSpecifications = this.mapper.convertValue(node, SpecificationJson.class);
    Map<ShipType, Integer> specMap = new HashMap<>();
    specMap.put(ShipType.CARRIER, shipSpecifications.carrier());
    specMap.put(ShipType.BATTLESHIP, shipSpecifications.battleship());
    specMap.put(ShipType.DESTROYER, shipSpecifications.destroyer());
    specMap.put(ShipType.SUBMARINE, shipSpecifications.submarine());
    return specMap;
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
    Volley reportDamageJson = this.mapper.convertValue(node, Volley.class);
    List<CoordJson> opCoordList = reportDamageJson.coordinates();
    List<Coord> tempList = new ArrayList<>();
    for (int i = 0; i < opCoordList.size(); i++) {
      CoordJson coordinates = opCoordList.get(i);
      tempList.add(new Coord(coordinates.x(), coordinates.y()));
    }

    return tempList;
  }

  private void drawBoard(Appendable output, List<List<String>> board) {
    try {
      for (int i = 0; i < board.size(); i++) {
        for (int k = 0; k < board.get(0).size(); k++) {
          output.append(board.get(i).get(k)).append(" ");
        }
        output.append(System.lineSeparator());
      }
    }catch(IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
