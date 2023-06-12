package cs3500.pa04;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class ShipAdapter {
  public static ObjectNode convertToJson(Ship ship) {
    ObjectNode shipJson = JsonNodeFactory.instance.objectNode();
    shipJson.put("shipType", ship.getType().toString());
    shipJson.put("size", ship.getCoord().size());
    return shipJson;
  }

}

