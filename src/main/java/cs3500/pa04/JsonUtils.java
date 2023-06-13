package cs3500.pa04;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for JSON operations.
 */
public class JsonUtils {

  /**
   * Serializes a record object to a JSON node.
   *
   * @param record the record object to serialize
   * @return the JSON node representing the serialized record object
   * @throws IllegalArgumentException if the given record cannot be serialized
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
}

