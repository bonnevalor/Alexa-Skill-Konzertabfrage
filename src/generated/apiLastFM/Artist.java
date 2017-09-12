
package generated.apiLastFM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "mbid",
    "url",
    "image",
    "streamable",
    "ontour",
    "stats",
    "similar",
    "tags",
    "bio"
})
public class Artist {

    @JsonProperty("name")
    public String name;
    @JsonProperty("mbid")
    public String mbid;
    @JsonProperty("url")
    public String url;
    @JsonProperty("image")
    public List<Image> image = null;
    @JsonProperty("streamable")
    public String streamable;
    @JsonProperty("ontour")
    public String ontour;
    @JsonProperty("stats")
    public Stats stats;
    @JsonProperty("similar")
    public Similar similar;
    @JsonProperty("tags")
    public Tags tags;
    @JsonProperty("bio")
    public Bio bio;
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
