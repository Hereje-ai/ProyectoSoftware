package co.com.ibero.inventoried.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Long price;

    private Long quantityAvailable;

    private Long categoryId;

    private Long suplierId;
}
