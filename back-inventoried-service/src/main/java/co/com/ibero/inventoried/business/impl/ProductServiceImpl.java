package co.com.ibero.inventoried.business.impl;

import co.com.ibero.inventoried.business.ProductService;
import co.com.ibero.inventoried.dto.CategoryDTO;
import co.com.ibero.inventoried.dto.ProductDTO;
import co.com.ibero.inventoried.dto.ResponseSuccessDTO;
import co.com.ibero.inventoried.exception.GenericException;
import co.com.ibero.inventoried.model.Category;
import co.com.ibero.inventoried.model.Product;
import co.com.ibero.inventoried.repository.CategoryRepository;
import co.com.ibero.inventoried.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseSuccessDTO saveProduct(ProductDTO productDTO) {
        log.info("Inicio de metodo saveProduct");
        var productSave = Product.builder().description(productDTO.getDescription()).categoryId(productDTO.getCategoryId())
                .name(productDTO.getName()).quantityAvailable(productDTO.getQuantityAvailable()).suplierId(productDTO.getSuplierId())
                .build();
        var validate = productRepository.save(productSave);
        if (validate.getName() == null) {
            throw new GenericException("BAD_ERROR", "Error al crear el producto", "Lo sentimos.", HttpStatus.BAD_REQUEST);
        }
        return ResponseSuccessDTO.builder().code(1L).message("Producto creado correctamente").build();
    }

    @Override
    public ResponseSuccessDTO updateProduct(ProductDTO productDTO) {
        log.info("Inicio de metodo updateProduct");
        var productSave = Product.builder().id(productDTO.getId()).description(productDTO.getDescription()).categoryId(productDTO.getCategoryId())
                .name(productDTO.getName()).price(productDTO.getPrice()).quantityAvailable(productDTO.getQuantityAvailable()).suplierId(productDTO.getSuplierId())
                .build();
        var validate = productRepository.save(productSave);
        if (validate.getName() == null) {
            throw new GenericException("BAD_ERROR", "Error al actualizar el producto", "Lo sentimos.", HttpStatus.BAD_REQUEST);
        }
        return ResponseSuccessDTO.builder().code(1L).message("Producto actualizado correctamente").build();
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        log.info("Inicio de metodo getAllProduct");
        var product = productRepository.findAll();
        log.info("Product -> {} " + product);
        return convertProducListToDTOList((List<Product>) product);
    }

    public List<ProductDTO> convertProducListToDTOList(List<Product> productList) {
        List<ProductDTO> list = new ArrayList<>();

        for (Product product : productList) {
            var productDTO = ProductDTO.builder().name(product.getName()).description(product.getDescription()).quantityAvailable(product.getQuantityAvailable())
                    .suplierId(product.getSuplierId()).price(product.getPrice()).categoryId(product.getCategoryId())
                    .id(product.getId()).build();
            list.add(productDTO);
        }
        return list;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        log.info("Inicio de metodo getAllCategory");
        var category = categoryRepository.findAll();
        log.info("Category -> {} " + category);
        return convertCategoryListToDTOList((List<Category>) category);
    }

    public List<CategoryDTO> convertCategoryListToDTOList(List<Category> categoriesList) {
        List<CategoryDTO> list = new ArrayList<>();

        for (Category category : categoriesList) {
            var categoryDTO = CategoryDTO.builder().id(category.getId()).description(category.getDescription()).name(category.getName()).build();
            list.add(categoryDTO);
        }
        return list;
    }
}
