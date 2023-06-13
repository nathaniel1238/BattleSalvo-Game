package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the JSON representation of the
 * coordinates of a ship on the game board.
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}
