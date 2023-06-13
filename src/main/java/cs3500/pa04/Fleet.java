package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a fleet of ships in the game.
 */
public record Fleet(
    @JsonProperty("fleet") List<ShipJson> ships) {
}
