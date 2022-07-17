package au.nab.productservice.handlers.etos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class ProductEto {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "colors")
    private List<String> colors;
    @JsonProperty(value = "categories")
    private List<String> categories;
    @JsonProperty(value = "brand")
    private String brand;
    @JsonProperty(value = "quantity")
    private long quantity;
    @JsonProperty(value = "price")
    private BigDecimal price;

    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
