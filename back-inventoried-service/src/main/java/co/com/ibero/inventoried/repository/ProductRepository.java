package co.com.ibero.inventoried.repository;

import co.com.ibero.inventoried.dto.ProductDTO;
import co.com.ibero.inventoried.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
