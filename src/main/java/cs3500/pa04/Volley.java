package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Volley(
  @JsonProperty("coordinates") List<CoordJson> coordinates) {

  }

