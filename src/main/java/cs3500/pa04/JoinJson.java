package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JoinJson(
    @JsonProperty("name") String username,
    @JsonProperty("game-type") String gameType) {
}
