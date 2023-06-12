package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.cs3500.pa03.Model.Direction;

public record ShipJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") Direction direction) {

  }

