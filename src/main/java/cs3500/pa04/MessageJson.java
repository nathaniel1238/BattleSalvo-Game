package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public record MessageJson(
    @JsonProperty("method-name") String methodName,
    @JsonProperty("arguments") JsonNode args) {
  public String getCommand() {
    return this.methodName;
  }

  public JsonNode getArguments() {
    return this.args;
  }
}


