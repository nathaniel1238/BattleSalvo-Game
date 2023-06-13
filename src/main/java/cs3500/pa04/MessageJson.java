package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a JSON message containing a method name and arguments.
 */
public record MessageJson(
    @JsonProperty("method-name") String methodName,
    @JsonProperty("arguments") JsonNode args) {
}


