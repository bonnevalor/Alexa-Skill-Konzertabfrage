
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
    "category",
    "price",
    "priceCurrency",
    "availability",
    "url"
})
public class Offers {

    @JsonProperty("@type")
    public String type;
    @JsonProperty("category")
    public String category;
    @JsonProperty("price")
    public Double price;
    @JsonProperty("priceCurrency")
    public String priceCurrency;
    @JsonProperty("availability")
    public String availability;
    @JsonProperty("url")
    public String url;
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
