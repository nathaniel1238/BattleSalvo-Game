package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoordJson(
    @JsonProperty("coord") int x, int y) {
}
