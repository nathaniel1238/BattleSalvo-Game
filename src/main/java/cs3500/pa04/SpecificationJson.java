package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the JSON representation of the ship specifications, including the number of each ship type.
 */
public record SpecificationJson(
    @JsonProperty("CARRIER") int carrier,
    @JsonProperty("BATTLESHIP") int battleship,
    @JsonProperty("DESTROYER") int destroyer,
    @JsonProperty("SUBMARINE") int submarine) {
}
