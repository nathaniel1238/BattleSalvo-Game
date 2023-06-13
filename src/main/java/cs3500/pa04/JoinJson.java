package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the JSON representation of the join request to the game server.
 */
public record JoinJson(
    @JsonProperty("name") String username,
    @JsonProperty("game-type") String gameType) {
}
