
package generated.eventim;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@context",
    "@type",
    "name",
    "url",
    "startDate",
    "location",
    "offers"
})
public class ParsedEvent {

    @JsonProperty("@context")
    public String context;
    @JsonProperty("@type")
    public String type;
    @JsonProperty("name")
    public String name;
    @JsonProperty("url")
    public String url;
    @JsonProperty("startDate")
    public String startDate;
    @JsonProperty("location")
    public Location location;
    @JsonProperty("offers")
    public Offers offers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

   
    
}
