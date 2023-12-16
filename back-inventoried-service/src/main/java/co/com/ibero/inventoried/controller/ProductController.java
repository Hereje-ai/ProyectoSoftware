package co.com.ibero.inventoried.controller;

import co.com.ibero.inventoried.business.ProductService;
import co.com.ibero.inventoried.business.SuplierService;
import co.com.ibero.inventoried.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.request.mappings}/v1/product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;
    @PostMapping(value = "/save")
    public ResponseSuccessDTO saveProduct(@RequestBody ProductDTO productDTO){

        return productService.saveProduct(productDTO);
    }

    @PutMapping(value = "/update")
    public ResponseSuccessDTO updateproduct(@RequestBody ProductDTO productDTO){

        return productService.updateProduct(productDTO);
    }

    @GetMapping(value = "/getProductAll")
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping(value = "/category-all")
    public List<CategoryDTO> getAllCategory(){
        return productService.getAllCategory();
    }
}
