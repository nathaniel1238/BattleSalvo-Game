package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a volley of coordinates in the game.
 */
public record Volley(
    @JsonProperty("coordinates") List<CoordJson> coordinates) {
}

