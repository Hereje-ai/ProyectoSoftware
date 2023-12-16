package co.com.ibero.inventoried.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @JsonProperty("quantity_available")
    private Long quantityAvailable;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("suplier_id")
    private Long suplierId;
}
