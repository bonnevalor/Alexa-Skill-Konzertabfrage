
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
    "@type",
    "streetAddress",
    "addressLocality",
    "addressRegion",
    "postalCode",
    "addressCountry"
})
public class Address {

    @JsonProperty("@type")
    public String type;
    @JsonProperty("streetAddress")
    public String streetAddress;
    @JsonProperty("addressLocality")
    public String addressLocality;
    @JsonProperty("addressRegion")
    public Object addressRegion;
    @JsonProperty("postalCode")
    public String postalCode;
    @JsonProperty("addressCountry")
    public String addressCountry;
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
