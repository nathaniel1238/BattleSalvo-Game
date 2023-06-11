package cs3500.pa04;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Fleet (
    @JsonProperty("fleet") List<ShipJson> ships) {

}
