package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Volley(
  @JsonProperty("volley") List<Coord> coordinates) {

  }

