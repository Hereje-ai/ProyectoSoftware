package co.com.ibero.inventoried.business;

import co.com.ibero.inventoried.dto.ProductDTO;
import co.com.ibero.inventoried.dto.SuplierDTO;

public interface ProductService {

    <T> T saveProduct(ProductDTO productDTO);

    <T> T updateProduct(ProductDTO productDTO);

    <T> T getAllProduct();

    <T> T getAllCategory();
}
